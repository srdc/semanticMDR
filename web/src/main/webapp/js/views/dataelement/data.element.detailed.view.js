define(['jquery', 'backbone', 'marionette', 'bootstrap', 'bootbox', 'js/models/dataelement/data.element.model', 'text!templates/dataelement/data.element.detailed.template.html', 'js/models/valuemeaning/value.meaning.collection.js', 'js/models/permissiblevalue/permissible.value.collection', 'js/views/permissiblevalue/permissible.value.composite.view', 'js/models/valuedomain/value.domain.model', 'js/models/dec/data.element.concept.model', 'js/models/conceptualdomain/conceptual.domain.model', 'js/models/objectclass/object.class.model', 'js/models/datatype/data.type.model', 'js/views/dataelement/data.element.modal.view', 'js/views/dataelement/data.element.serialization.modal.view', 'js/views/dataelement/specification.modal.view'], function($, Backbone, Marionette, BootStrap, bootbox, DataElementModel, DataElementDetailedTemplate, ValueMeaningCollection, PermissibleValueCollection, PermissibleValueCompositeView, ValueDomainModel, DataElementConceptModel, ConceptualDomainModel, ObjectClassModel, DataTypeModel, DataElementModalView, DataElementSerializationModalView, SpecificationModalView) {
	var DataElementDetailedView = Marionette.ItemView.extend({
		template : DataElementDetailedTemplate,
		events : {
			'click .icon-edit' : 'editDataElement',
			'click .icon-remove' : 'removeDataElement',
			'click #serialize' : 'getSerialization',
			'click #addExtractionSpecification' : 'addExtractionSpecification',
			'click #addMapping' : 'addMapping'
		},
		modelEvents : {
			'change' : 'render'
		},
		ui : {
			conceptualDomainName : "#conceptualDomainName",
			valueDomainName : '#valueDomainName',
			valueDomainDefinition : '#valueDomainDefinition',
			valueDomainType : '#valueDomainType',
			valueItem : '#valueItem',
			permissibleValues : "#permissibleValues",
			viewPermissibleValues : ".viewPermissibleValues",
			objectClassName : '#objectClassName',
			objectClassDefinition : '#objectClassDefinition',
			parentConceptName : "#parentConceptName",
			viewParentConcept : ".viewParentConcept",
			viewSubConcepts : ".viewSubConcepts",
			subConcepts : "#subConcepts",
			propertyName : '#propertyName',
			propertyDefinition : '#propertyDefinition',
			datatypeName : '#datatypeName',
			datatypeSchemeReference : '#datatypeSchemeReference',
			extractionSpecifications : '#extractionSpecifications',
			viewExtractionSpecifications : '.viewExtractionSpecifications',
			mappings : '#mappings',
			viewMappings : '.viewMappings'
		},
		initialize : function() {
			var self = this;
			var id = this.options.id;
			var locator = this.options.app.getMDRModelLocator();
			this.model = new DataElementModel({
				id : id
			});
			this.model.fetch({
				async : false,
				error : function(error) {
					alert('Nope');
				}
			});

			/*
			 * dataElement:detailed:rerender event takes a dataElementID whose detailed view will be rerendered,
			 * if id is matched with models id, model is updated from server, then this detailed view is rerendered
			 * first usage of this event is from specification modal view
			 * when new specification is added, this event is triggered so that new specifications will be shown
			 */
			this.listenTo(self.options.app.vent, 'dataElement:detailed:rerender', function(dataElementID) {
				if (dataElementID == id) {
					self.model.fetch({
						success : self.render
					});
				}
			});

			locator.setCurrentContext(self.model.get('contextID'));

			self.valueDomainModel = new ValueDomainModel();
			// Set the ID of the value domain model...
			self.valueDomainModel.set({
				id : self.model.get('valueDomainID')
			});
			self.valueDomainModel.fetch({
				async : false,
				error : function(error) {
					alert('Nope');
				}
			});

			self.dataElementConceptModel = new DataElementConceptModel();
			// Set the ID of the data element concept model...
			self.dataElementConceptModel.set({
				id : self.model.get('dataElementConceptID')
			});
			self.dataElementConceptModel.fetch({
				async : false,
				error : function(error) {
					alert('Nope');
				}
			});

			self.conceptualDomainModel = new ConceptualDomainModel({
				method : 'get'
			});
			// Set the ID of the conceptual domain model...
			self.conceptualDomainModel.set({
				id : self.dataElementConceptModel.get('conceptualDomainID')
			});
			self.conceptualDomainModel.fetch({
				async : false,
				error : function(error) {
					alert('Nope');
				}
			});

			self.objectClassModel = new ObjectClassModel();
			// Set the ID of the object class model...
			self.objectClassModel.set({
				id : self.dataElementConceptModel.get('objectClassID')
			});
			self.objectClassModel.fetch({
				async : false,
				error : function(error) {
					alert('Nope');
				}
			});

			if (self.objectClassModel.get('parentConceptID')) {
				self.parentConcept = new ObjectClassModel();
				// Set the ID of parent concept model...
				self.parentConcept.set({
					id : self.objectClassModel.get('parentConceptID')
				});
				self.parentConcept.fetch({
					async : false,
					error : function(error) {
						alert('Nope');
					}
				});
			}

			self.subConcepts = self.options.app.getMDRModelLocator().getSubConceptCollection(self.objectClassModel.get('id'));
			var context = locator.getCurrentContext();
			self.options.app.vent.trigger('breadcrumb:change', {
				'contextID' : context.get('id'),
				'contextName' : context.get('name'),
				'objectClassID' : self.objectClassModel.get('id'),
				'objectClassName' : self.objectClassModel.get('name'),
				'dataElementID' : self.model.get('id'),
				'dataElementName' : self.model.get('name')
			});
		},

		onRender : function() {
			this.ui.conceptualDomainName.html(this.conceptualDomainModel.get('name'));

			this.ui.objectClassName.html('<a href="/semanticmdr/#oc/' + this.objectClassModel.get('id') + '/dec?offset=0">' + this.objectClassModel.get('name') + '</>');
			this.ui.objectClassDefinition.html(this.objectClassModel.get('definition'));
			if ( typeof this.parentConcept != 'undefined') {
				this.ui.parentConceptName.html(this.parentConcept.get('name'));
				this.ui.viewParentConcept.removeClass("hidden");
			}
			if (this.subConcepts.length) {
				for (var i = 0; i < this.subConcepts.length; i++) {
					this.ui.subConcepts.append("<tr><td>" + this.subConcepts.at(i).get('name') + "</td><td>" + this.subConcepts.at(i).get('definition') + "</td></tr>");
				}
				this.ui.viewSubConcepts.removeClass("hidden");
			}

			this.ui.propertyName.html(this.dataElementConceptModel.get('propertyName'));
			this.ui.propertyDefinition.html(this.dataElementConceptModel.get('propertyDefinition'));

			this.ui.datatypeName.html(this.valueDomainModel.get('dataType').datatypeName);
			this.ui.datatypeSchemeReference.html(this.valueDomainModel.get('dataType').schemeReference);

			if (this.conceptualDomainModel.get('name') == "ObjectClass") {
				// which meeans that value domain actually points to other object class, actually the datatype
				// like in omop person location, person and location are object classes
				// person location data elements has value domain, this value domain's datatype points to
				// location object Class, we will make this navigatable in ui
				this.ui.valueDomainName.html('<a href="/semanticmdr/#oc/' + this.valueDomainModel.get('dataType').schemeReference + '/dec?offset=0">' + this.valueDomainModel.get('name') + '</>');
			}

			this.ui.valueDomainDefinition.html(this.valueDomainModel.get('definition'));
			if (this.valueDomainModel.get('enumerated')) {
				var permissibleValues = this.valueDomainModel.get('permissibleValues');
				this.ui.valueDomainType.html("Enumerated");
				for (var i = 0; i < permissibleValues.length; i++) {
					var vmDescription = this.valueDomainModel.get('permissibleValues')[i].get('valueMeaning').description;
					this.ui.permissibleValues.append("<tr><td>" + vmDescription + "</td><td>" + permissibleValues[i].valueItem + "</td></tr>");
				}
				this.ui.viewPermissibleValues.removeClass("hidden");
			} else {
				this.ui.valueDomainType.html("Non Enumerated");
			}

			var extractionSpecs = this.model.get('extractionSpecs');
			var mappings = this.model.get('mappings');
			if (extractionSpecs.length != 0) {
				for (var i = 0; i < extractionSpecs.length; i++) {
					var value = extractionSpecs[i].value;
					var type = extractionSpecs[i].type;
					var model = extractionSpecs[i].modelName;
					this.ui.extractionSpecifications.append('<tr><td>ExtractionSpecification<br/>(' + model + ')</td><td>' + type + ':<br/>' + value + '</td></tr>');
				}
			} else {
				this.ui.viewExtractionSpecifications.find('thead').replaceWith('<div class="alert alert-error">No Extraction Specification Added Yet</div>');
			}
			if (mappings.length != 0) {
				for (var i = 0; i < mappings.length; i++) {
					var termSystem = mappings[i].termSystem;
					var matchType = mappings[i].matchType;
					var termName = mappings[i].termName;
					var termUUID = mappings[i].termUUID;
					this.ui.mappings.append('<tr>' + '<td>Mapping<br/>(' + termSystem + ')</td><td>' + matchType + ':<br/><a class="btn btn-link" href="/semanticmdr/#de/' + termUUID + '">' + termName + '</a></td></tr>');
				}
			} else {
				this.ui.viewMappings.find('thead').replaceWith('<div class="alert alert-error">No Mapping Added Yet</div>');
			}
		},
		addExtractionSpecification : function() {
			this.options.app.vent.trigger('modalWindow:show', new SpecificationModalView({
				dataElement : this.model,
				app : this.options.app,
				modalType : 'extractionSpecification'
			}));
		},
		addMapping : function() {
			this.options.app.vent.trigger('modalWindow:show', new SpecificationModalView({
				dataElement : this.model,
				app : this.options.app,
				modalType : 'mapping'
			}));
		},
		editDataElement : function() {
			this.options.app.vent.trigger('modalWindow:show', new DataElementModalView({
				model : this.model,
				dataElementConceptID : this.model.get('dataElementConceptID'),
				app : this.options.app
			}));
		},
		removeDataElement : function() {
			var self = this;
			var success = function() {
				alert('Deleted');
			};

			bootbox.confirm(self.model.get('name') + ' will be deleted', function(result) {
				if (result) {
					self.model.destroy({
						async : false,
						success : success,
						error : function(jxQHR, textStatus) {
							if (textStatus.status == 200) {
								success();
							}
						}
					});
				}
			});
		},
		getSerialization : function(e) {
			e.preventDefault();
			var self = this;
			self.options.app.vent.trigger('modalWindow:show', new DataElementSerializationModalView({
				app : self.options.app,
				dataElementID : self.model.get('id'),
			}));

		},
	});
	return DataElementDetailedView;
});
