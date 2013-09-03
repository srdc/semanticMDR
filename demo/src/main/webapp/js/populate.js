require.config({
	baseUrl : '',
	paths : {
		jquery : 'js/libs/jquery/jquery-1.9.0',
		underscore : 'js/libs/underscore/underscore',
		bootstrap : 'js/libs/bootstrap/bootstrap',
		bootstrap_modalmanager : 'js/libs/bootstrap/bootstrap-modalmanager',
		bootstrap_modal : 'js/libs/bootstrap/bootstrap-modal',
		bootbox : 'js/libs/bootstrap/bootbox'
	},

	shim : {
		'jquery' : {
			exports : '$'
		},
		'underscore' : {
			deps : ['jquery'],
			exports : '_'
		},
		'bootstrap' : {
			deps : ['jquery'],
			exports : '$'
		},
		'bootstrap-modalmanager' : {
			deps : ['bootstrap'],
			exports : '$'
		},
		'bootstrap-modal' : {
			deps : ['bootstrap'],
			exports : '$'
		},
		'bootbox' : {
			deps : ['jquery', 'bootstrap'],
			exports : 'bootbox'
		}
	}
});

// Global Variables
// instead of http://localhost:8080/rest so services could be call from other resources
var URL = 'http://' + window.location.hostname + ':8080/web/';
var URLRest = 'http://' + window.location.hostname + ':8080/rest/';
;
/////////////////////////////////////////////

require(['jquery', 'underscore', 'bootstrap'], function($, _, Bootstrap) {
	var contexts = [];
	var contextNames = [];
	var dataElements = [];
	var dataElementNames = [];
	var dataElementIDs = [];
	var selectedDataElement = null;
	var selectedContext = null;
	var patientSummaryDocumentContent = "";
	// request to get contextList and parsing resulting RDF into contextList
	$('#loadingOverlay').show();
	$.ajax({
		url : URLRest + 'context/all',
		dataType : "xml",
		success : function(response) {
			$(response).find("rdf\\:Description, Description").each(function() {
				contexts.push(this);
				contextNames.push($(this).find("skos\\:prefLabel, prefLabel").text());
			});
		},
		error : function(e) {
			alert("Error at retrieving Contexts");
		},
		complete : function(e) {
			$('#loadingOverlay').hide();
		}
	});

	// callback retrieving all annotations to auto population
	$('#populate').click(function(e) {
		var doc;
		try {
			doc = $.parseXML(patientSummaryDocumentContent);
			if (!doc) {
				throw "Empty XML Document";
			}
		} catch(e) {
			alert(e);
			return false;
		}
		var missing = false;
		$('form .row-fluid').each(function() {
			var row = this;
			if ($(this).has('input').length && !$(this).find('input').val()) {
				missing = true;
				return;
			}
		});
		if (missing) {
			alert('Labels are required');
			return false;
		}
		$('form .row-fluid').each(function() {
			var row = this;
			if ($(this).has('.annotate').length == 0) {
				$(row).find('#value').empty();
				var dataElementID = $(row).find('#divselected').find('p').attr('deid');
				// get the xpath, evaluate and put the label
				$.ajax({
					headers : {
						"Accept" : 'application/json',
					},
					url : URLRest + 'de/'+dataElementID+'/es?content-model=HL7%20CCD&specification-format=XPATH',
					success : function(response) {
						result = "";
						if (response && response != "") {
							for (var i = 0; i < response.length; i++) {
								var xpath = response[i];
								xpath = xpath.replace("/ancestor-or-self::./", "/");
								xpath = xpath.replace("ancestor-or-self::./", "//");
								xpath = xpath.replace(new RegExp("\\[cda:templateId[^\\]]*\\]"), "");
								
								// success then try to evaluate XPATH, retrieve the value
								var xPathRes = doc.evaluate(xpath, doc, function(prefix) {
									if (prefix === 'cda')
										return 'urn:hl7-org:v3';
									if (prefix === 'sdtc')
										return "urn:hl7-org:sdtc";
									else
										return null;
								}, XPathResult.ANY_TYPE, null); 
								
								var res = xPathRes.iterateNext();
								while (res) {
									result += xml2Str(res) + "\n";
									res = xPathRes.iterateNext();
								}

								if (result != "") {
									break;
								}
							}

						}
						if (result) {
							$(row).find('#value').empty();
							$(row).find('#value').text(result);
						} else {
							$(row).find('#value').empty();
							$(row).find('#value').text('NOT FOUND');
						}
					},
					error : function(e) {
					}
				});
			}
		});
		//VOLKAN$('#addNewField').hide();
		//VOLKAN$('#populate').hide();
		$('#refresh').show();
	});

	//callback to upload a file
	$('#submitFile').click(function(e) {
		// Check for the various File API support.
		if (window.File && window.FileReader && window.FileList && window.Blob) {
			// handler when file selected
			$('#files').change(function(e) {
				var f = e.target.files[0];
				if (f) {
					var r = new FileReader();
					$('#loadingOverlay').show();
					r.onload = function(e) {
						var contents = e.target.result;
						patientSummaryDocumentContent = contents;
						$('#loadingOverlay').hide();
					}
					r.readAsText(f);

					var fullPath = $('#files').val();
					var filename = fullPath.split(/(\\|\/)/g).pop()
					$('#uploadDiv').empty();
					$('#uploadDiv').append(filename);
					$('#populate').prop('disabled', false);
				} else {
					alert("Failed to load file");
				}
			});
			// trigger file browser window on button click
			$('#files').click();
		} else {
			alert('The File APIs are not fully supported in this browser.');
		}
	});

	// callback executed click on add new field button
	$('#addNewField').click(function(e) {
		$('#formgeneration').append('<div class="row-fluid">' + '<button type="button" class="close" data-dismiss="alert">&times;</button>' + '<div class="input-prepend"><span class="add-on">Field Label</span><input id="prependedInput" class="span6" type="text" placeholder="Label for the population..."/></div>' + '<label>Drag&Drop to annotate: </label><div class="span6" id="divselected" style="width:500px;height:25px;padding:5px;border:1px solid #cccccc;" ondrop="event.preventDefault();var data=event.dataTransfer.getData(\'Text\');var original = document.getElementById(data);event.target.innerHTML=\'\';event.target.appendChild(original.cloneNode(true));"	ondragover="event.preventDefault();"></div>' + '</br></br><label>Population Results: </label><textarea ondrop="event.preventDefault();" id="value" rows="4" class="row-fluid" /></textarea></div></br></br>');
	});

	$('#refresh').click(function(e) {
		$('form .row-fluid').remove();
		$('#addNewField').show();
		$('#populate').show();
		$('#refresh').hide();
	});

	// this is the callback when a data element clicked from type ahead data element search text box
	var selectDataElement = function(item) {
		item = item.replace(")","");
		item = item.replace("(","");
		var id = $(dataElements).find('skos\\:prefLabel:contains(' + item + '),prefLabel:contains(' + item + ')').parent().find('dc\\:identifier,identifier').first().text();
		selectedDataElement = $(dataElements).find('skos\\:prefLabel:contains(' + item + '),prefLabel:contains(' + item + ')').parent();
		return item;
	}
	// function called when a context is selected
	var selectContext = function(item) {
		$('#contextSearch').val(item);
		var id = $(contexts).find('skos\\:prefLabel:contains(' + item + '),prefLabel:contains(' + item + ')').parent().find('dc\\:identifier,identifier').first().text();
		selectedContext = $(contexts).find('skos\\:prefLabel:contains(' + item + '),prefLabel:contains(' + item + ')').parent();
		$('#loadingOverlay').show();
		$.ajax({
			dataType : 'xml',
			url : URLRest + 'context/'+id+'/de',
			success : function(response) {
				dataElements.length = 0;
				dataElementNames.length = 0;
				dataElementIDs.length = 0;
				$(response).find('rdf\\:Description,Description').each(function() {
					dataElements.push(this);
					dataElementNames.push($(this).find('skos\\:prefLabel,prefLabel').text());
					dataElementIDs.push($(this).find("dc\\:identifier, identifier").first().text());
					$('#dataElementSearch').typeahead({
						source : dataElementNames,
						updater : selectDataElement
					});
				});

				$('#deList').empty();
				for (var i = 0; i < dataElementNames.length; i++) {
					$('#filterElements').css("visibility", "visible");
					$('#filterElementsLabel').css("visibility", "visible");
					$('#deList').append('<div style="float:left; margin: 2px;"><p deid=' +  dataElementIDs[i] + ' id="' +  dataElementNames[i] + '" class="label label-important" style="display:inline;" draggable="true" ondragstart="event.dataTransfer.setData(\'Text\',event.target.id);">' + dataElementNames[i] + '</p></div> ');
				}
			},
			error : function(alert) {
				alert('Error at obtaining Data Element with id ' + id);
			},
			complete : function(e) {
				$('#loadingOverlay').hide();
			}
		});
		return item;
	};

	var xml2Str = function(xmlNode) {
		try {
			// Gecko- and Webkit-based browsers (Firefox, Chrome), Opera.
			return (new XMLSerializer()).serializeToString(xmlNode);
		} catch (e) {
			try {
				// Internet Explorer.
				return xmlNode.xml;
			} catch (e) {
				//Other browsers without XML Serializer
				alert('Xmlserializer not supported');
			}
		}
		return false;
	}
	//now setting context search with autoComplete
	$('#contextSearch').typeahead({
		source : contextNames,
		updater : selectContext
	});
});
