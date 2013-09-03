define(['jquery', 'backbone', 'marionette', 'bootstrap', 'js/models/dataelement/data.element.model', 'text!templates/dataelement/data.element.serialization.modal.template.html', 'js/models/valuemeaning/value.meaning.collection.js', 'js/models/permissiblevalue/permissible.value.collection', 'js/views/permissiblevalue/permissible.value.composite.view', 'js/models/valuedomain/value.domain.model', 'js/models/datatype/data.type.model'], function($, Backbone, Marionette, BootStrap, DataElementModel, DataElementSerializationTemplate, ValueMeaningCollection, PermissibleValueCollection, PermissibleValueCompositeView, ValueDomainModel, DataTypeModel) {
	var DataElementSerializationModalView = Marionette.ItemView.extend({
		initialize : function() {
			var self = this;
			this.apply(null);
		},
		template : DataElementSerializationTemplate,
		events : {
			'click #selectAll' : 'selectAll',
			'click #apply' : 'apply',
			'click #applyDEX' : 'applyDEX',
		},
		ui : {
			serialization : '#serialization',
			modalTitle : '#modalTitle',
			type : '#type',
		},
		selectAll : function() {
			this.ui.serialization.select();
		},
		apply : function(e) {
			if (e) {
				e.preventDefault();
			}
			var self = this;
			var selection = self.$('#type').val();

			if (selection == "XML") {
				alert('XML serialization is not supported for 11179.')
				return;
			}

			$('#loadingOverlay').fadeIn();
			$('#modalTitleH3').html('Data Element ISO 11179 Serialization');
			$.ajax({
				type : 'GET',
				url : URLRest + 'serialize/graph?id=' + self.options.dataElementID,
				headers : {
					"Accept" : mimeTypes[selection],
				},
				success : function(response) {
					if ( typeof response == "object") {
						response = xml2string(response);
					}
					self.ui.serialization.html(response);
					$('#loadingOverlay').fadeOut();
				},
				error : function(error) {
					self.ui.serialization.html(error.statusText);
					$('#loadingOverlay').fadeOut();
				}
			});
		},

		applyDEX : function(e) {
			if (e) {
				e.preventDefault();
			}
			var self = this;
			var selection = self.$('#type').val();
			$('#loadingOverlay').fadeIn();

			if (selection == "XML") {
				$('#modalTitleH3').html('Data Element IHE DEX XML Serialization');

				var soapReq = "";
				soapReq += '<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:wsa="http://www.w3.org/2005/08/addressing" xmlns:xsd="http://www.w3.org/2001/XMLSchema">';
				soapReq += '<soap:Header>';
				soapReq += '<wsa:MessageID>urn:uuid:f43f7bda-a5f9-42b1-b8dc-e78be1a2a183</wsa:MessageID>';
				soapReq += '<wsa:Action>urn:ihe:qrph:dex:2013:RetrieveMetadata</wsa:Action>';
				soapReq += '</soap:Header>';
				soapReq += '<soap:Body>';
				soapReq += '<dex:RetrieveMetadataRequest xmlns:dex="urn:ihe:qrph:dex:2013">';
				soapReq += '<dex:id>' + self.options.dataElementID + '</dex:id>';
				//TODO: SRDC and version=0.1
				soapReq += '<dex:registrationAuthority>eu.salusproject.mdr</dex:registrationAuthority>';
				soapReq += '<dex:version>0.1</dex:version>';
				soapReq += '</dex:RetrieveMetadataRequest>';
				soapReq += '</soap:Body>';
				soapReq += '</soap:Envelope>';

				$.ajax({
					type : 'POST',
					url : URLDex,
					contentType : "text/plain",
					dataType : "xml",
					data : soapReq,
					success : function(response) {
						var de = response.getElementsByTagName("DataElement")[0];
						self.ui.serialization.html(xml2string(de));
						$('#loadingOverlay').fadeOut();
					},
					error : function(error) {
						self.ui.serialization.html(error.statusText);
						$('#loadingOverlay').fadeOut();
					}
				});
			} else {
				$('#modalTitleH3').html('Data Element IHE DEX Serialization');
				$.ajax({
					type : 'GET',
					url : URLRest + 'serialize/dex?id=' + self.options.dataElementID,
					headers : {
						"Accept" : mimeTypes[selection],
					},
					success : function(response) {
						if ( typeof response == "object") {
							response = xml2string(response);
						}
						self.ui.serialization.html(response);
						$('#loadingOverlay').fadeOut();
					},
					error : function(error) {
						self.ui.serialization.html(error.statusText);
						$('#loadingOverlay').fadeOut();
					}
				});
			}

		},
	});
	return DataElementSerializationModalView;
});
