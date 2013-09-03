define(['jquery', 'backbone', 'marionette', 'bootstrap', 'js/models/dataelement/data.element.model', 'text!templates/dataelement/specification.modal.template.html', 'js/models/classification/mapping.model', 'js/models/classification/extraction.specification.model'], function($, Backbone, Marionette, BootStrap, DataElementModel, SpecificationTemplate, MappingModel, ExtractionSpecificationModel) {
	var SpecificationModalView = Marionette.ItemView.extend({
		template : SpecificationTemplate,
		events : {
			'click #sendSpecificationForm' : 'sendSpecificationForm'
		},
		ui : {
			// Model inputs
			mdrName : '#mdrName',
			relationship : '#relationship',
			dataElement : '#dataElement',
			contentModelType : '#contentModelType',
			extractionSpecificationType : '#extractionSpecificationType',
			extractionSpecification : '#extractionSpecification',
			// Other UI elements
			sendButton : '#sendSpecificationForm',
			title : '.modal-header h3',
		},
		initialize : function() {
			var self = this;

			if (this.options.modalType == "extractionSpecification") {
				self.specificationModel = new ExtractionSpecificationModel();
			} else if (this.options.modalType == "mapping") {
				self.specificationModel = new MappingModel();
			}
			self.specificationModel.set('dataElementID', self.options.dataElement.get('id'));

		},
		onRender : function() {
			var self = this;
			if (this.options.modalType == "extractionSpecification") {
				self.$('#mappingParameters').hide();
				self.$('#specificationParameters').show();
				this.ui.title.html('Add Extraction Specification');
			} else if (this.options.modalType == "mapping") {
				var relSelect = self.$('#relationship');

				$.ajax({
					url : URL + 'repository/mapping/relations',
					headers : {
						"Accept" : "application/json",
						"Content-Type" : "application/json"
					},
					success : function(response) {
						for (var i = 0; i < response.length; i++) {
							relSelect.append('<option>' + response[i] + '</option>');
						}
					},
					error : function(alert) {
						alert('Error at obtaining relations');
					}
				});

				self.$('#mappingParameters').show();
				self.$('#specificationParameters').hide();
				this.ui.title.html('Add Data Element Mapping');

				var contexts = self.options.app.getMDRModelLocator().getContextCollection();
				var contextNames = contexts.pluck('name');
				this.ui.mdrName.typeahead({
					minLength : 2,
					source : contextNames,
					updater : function(contextName) {
						var selectedContext = contexts.where({
						'name' : contextName
						})[0];
						$.ajax({
							url : URLRest + 'context/' + selectedContext.get('id') + '/de',
							headers : {
								Accept : 'application/rdf+xml'
							},
							success : function(response) {
								dataElements = [];
								dataElementNames = [];
								$(response).find('rdf\\:Description,Description').each(function() {
									dataElements.push(this);
									var deName = $(this).find('skos\\:prefLabel,prefLabel').text();
									// parentheses pose problems for typeahead.
									//deName = deName.replace(")", "");
									//deName = deName.replace("(", "");
									dataElementNames.push(deName);
								});
								self.ui.dataElement.typeahead({
									minLength : 2,
									source : dataElementNames,
									updater : function(item) {
										var index;
										for (var i = 0; i < dataElementNames.length; i++) {
											if (dataElementNames[i] == item) {
												index = i;
											}
										}
										var dataElementId = $(dataElements[index]).find("identifier").first().text()
										self.ui.dataElement.attr('dataElementID', dataElementId);
										return item;
									}
								});

							},
							error : function(response) {
								console.log('Error at obtaining Data Elements of  ' + contextName);
							}
						});
						return contextName;
					}
				});
			}

		},
		sendSpecificationForm : function(valueDomainID, valueDomainName) {
			var self = this;
			if (this.options.modalType == "extractionSpecification") {
				var oidMap = {
					'ASTM/HL7 CCD' : '2.16.840.1.113883.10.20.1',
					'BRIDG Model' : '2.16.840.1.113883.1.8'
				};
				self.specificationModel.set('type', self.ui.extractionSpecificationType.val());
				self.specificationModel.set('value', self.ui.extractionSpecification.val());
				self.specificationModel.set('modelName', self.ui.contentModelType.val());
				self.specificationModel.set('modelOID', oidMap[self.ui.contentModelType.val()]);
			} else if (this.options.modalType == "mapping") {
				var oidMap = {
					'HITSP C154' : '1.11.111.1.111111.1.1',
					'BRIDG' : '2.16.840.1.113883.1.80',
					'CDASH' : '1.11.111.1.111111.1.0',
					'SDTM' : '1.11.111.1.111111.1.2',
					'OMOP' : '1.11.111.1.111111.1.3'
				}
				self.specificationModel.set('termSystem', self.ui.mdrName.val());
				self.specificationModel.set('matchType', self.ui.relationship.val());
				self.specificationModel.set('termUUID', self.ui.dataElement.attr('dataElementID'));
				self.specificationModel.set('termName', self.ui.dataElement.val());
				self.specificationModel.set('termSystemOID', oidMap[self.ui.mdrName.val()]);
			}

			$('#loadingOverlay').fadeIn();
			self.specificationModel.save(self.specificationModel.toJSON(), {
				success : function() {
					self.options.app.vent.trigger('dataElement:detailed:rerender', self.specificationModel.get('dataElementID'));
				},
				complete : function() {
					$('#loadingOverlay').fadeOut();
					self.options.app.vent.trigger('dataElement:detailed:rerender', self.specificationModel.get('dataElementID'));
				}
			});
		},
	});
	return SpecificationModalView;
});
