define(['jquery', 'backbone', 'marionette', 'bootstrap', 'js/models/dataelement/data.element.model', 'text!templates/dataelement/data.element.modal.template.html', 'js/models/valuemeaning/value.meaning.collection.js', 'js/models/permissiblevalue/permissible.value.collection', 'js/views/permissiblevalue/permissible.value.composite.view', 'js/models/valuedomain/value.domain.model', 'js/models/datatype/data.type.model'], function($, Backbone, Marionette, BootStrap, DataElementModel, DataElementTemplate, ValueMeaningCollection, PermissibleValueCollection, PermissibleValueCompositeView, ValueDomainModel, DataTypeModel) {
	var DataElementModalView = Marionette.ItemView.extend({
		template : DataElementTemplate,
		events : {
			'click #sendDataElementForm' : 'send'
		},
		ui : {
			// Model inputs
			name : '#name',
			definition : '#definition',
			registrationStatus : '#registrationStatus',
			administrativeStatus : '#administrativeStatus',
			administrativeNote : '#administrativeNote',
			explanatoryComment : '#explanatoryComment',
			valueDomainName : '#valueDomainName',
			valueDomainDefinition : '#valueDomainDefinition',
			valueDomainDataType : '#valueDomainDataType',
			// Other UI elements
			sendButton : '#sendDataElementForm',
			title : '.modal-header h3',

			// AJAX effects
			loadValueMeaning : '#loadValueMeaning'
		},
		initialize : function() {
			this.valueDomainModel = new ValueDomainModel();

			if (!this.model) {
				this.model = new DataElementModel();

				var conceptualDomain = this.options.app.getMDRModelLocator().getConceptualDomain(this.options.conceptualDomainID);

				// Set enumerated attribute of the value domain as same as value domain's
				this.valueDomainModel.set('enumerated', conceptualDomain.get('enumerated'));

				// Set the value domain request url
				this.valueDomainURL = URL + 'cd/' + this.options.conceptualDomainID + '/vd';

			} else {
				// Set the ID of the value domain model...
				this.valueDomainModel.set({
					id : this.model.get('valueDomainID')
				});

				this.valueDomainModel.fetch({
					async : false,
					error : function(error) {
						alert('Nope');
					}
				});

				// Set the value domain request url
				this.valueDomainURL = URL + 'cd/' + this.valueDomainModel.get('conceptualDomainID') + '/vd';
			}

		},
		fetchNewAttributes : function(valueDomainID, valueDomainName) {
			newAttributes = {};

			for (var field in this.ui) {
				if (!this.$(this.ui[field]).is(':disabled') && this.model.has(field)) {
					newAttributes[field] = this.ui[field].val();
				}
			}

			if (this.model.isNew()) {
				newAttributes['conceptualDomainID'] = $('input[name="conceptualDomainSearchResult"]:checked').val();
			}
			newAttributes['valueDomainID'] = valueDomainID;
			newAttributes['valueDomainName'] = valueDomainName;

			if (this.model.isNew()) {
				newAttributes['contextID'] = this.options.app.getMDRModelLocator().getCurrentContext().get('id');
				newAttributes['dataElementConceptID'] = this.options.dataElementConceptID;
			}

			return newAttributes;
		},
		fetchNewValueDomainModelAttributes : function() {
			var newAtrributes = {
				name : this.ui.valueDomainName.val(),
				definition : this.ui.valueDomainDefinition.val(),
				contextID : this.options.app.getMDRModelLocator().getCurrentContext().get('id'),
				dataType : this.valueDomainModel.get('dataType')
			};

			newAtrributes.dataType = {
				schemeReference : this.ui.valueDomainDataType.val(),
				datatypeName : this.ui.valueDomainDataType.find(":selected").text()
			};

			// If this is a creation modal, set conceptual domain ID as given in options.
			if (this.model.isNew()) {
				newAtrributes['conceptualDomainID'] = this.options.conceptualDomainID;
			}
			return newAtrributes;
		},
		send : function() {
			var self = this;
			var valueDomainModelAttributes = this.fetchNewValueDomainModelAttributes();
			// check for value domain model attributes whether changed or not
			if (this.valueDomainModel.changedAttributes(valueDomainModelAttributes)) {
				this.valueDomainModel.save(valueDomainModelAttributes, {
					url : this.valueDomainURL,
					dataType : this.valueDomainModel.isNew() ? 'json' : 'text',
					success : function(model, response) {
						// If a response is returned -which means this is a creation model- use the ID
						// and name of it
						var dataElementModelAttributes = self.fetchNewAttributes(self.valueDomainModel.get('id'), self.valueDomainModel.get('name'));
						// check for data element model attributes whether changed or not
						if (self.model.changedAttributes(dataElementModelAttributes)) {
							if (response) {
								self.sendDataElementForm(response.id, response.name);
							} else {
								// Otherwise, use the ones in the current value domain model
								self.sendDataElementForm(self.valueDomainModel.get('id'), self.valueDomainModel.get('name'));
							}
						}
					},
					error : function(error) {
						alert('Nope');
					}
				});
			} else {
				var dataElementModelAttributes = this.fetchNewAttributes(self.valueDomainModel.get('id'), this.valueDomainModel.get('name'));
				if (this.model.changedAttributes(dataElementModelAttributes)) {
					// Otherwise, use the ones in the current value domain model
					this.sendDataElementForm(this.valueDomainModel.get('id'), this.valueDomainModel.get('name'));
				}
			}
		},
		onRender : function() {
			var self = this;
			// if model is empty, this is create form, do not show optional
			if (!this.model.get('name') && !this.model.get('description')) {
				this.$('#collapseOptional').hide();
			}

			this.ui.registrationStatus.val(this.model.get('registrationStatus'));
			this.ui.administrativeStatus.val(this.model.get('administrativeStatus'));

			if (this.model.isNew()) {
				this.$('#collapseOptional').hide();

				this.ui.sendButton.html('Create');
				this.ui.title.html('Create Data Element');
			} else {
				this.ui.sendButton.html('Update');
				this.ui.title.html('Update Data Element');

				this.ui.valueDomainName.val(this.valueDomainModel.get('name'));
				this.ui.valueDomainDefinition.val(this.valueDomainModel.get('definition'));
			}
			self.valueDoaminCollection = self.options.app.getMDRModelLocator().getValueDomainsofConceptualDomain(self.options.conceptualDomainID);
			var chosen = new Backbone.Collection();
			this.ui.valueDomainName.typeahead({
				minLength : 1,
				source : function(query, process) {
					var count = chosen.size();
					for (var i = count - 1; i > -1; i--) {
						chosen.remove(chosen.at(i));
					}
					var contextID = self.options.app.getMDRModelLocator().getCurrentContext().get('id');
					var contextVDs = self.options.app.getMDRModelLocator().searchValueDomain(contextID, query);
					_.map(contextVDs.models, function(vd) {
						if (self.valueDoaminCollection.get(vd.get('id'))) {
							chosen.add(vd);
						}
					});
					return chosen.pluck('name');
				},
				updater : function(name) {
					var vd = chosen.findWhere({
						name : name
					});
					self.model.set('valueDomainID', vd.get('id'));
					return vd.get('name');
				}
			});

			this.setDataTypeDropDown();

		},
		sendDataElementForm : function(valueDomainID, valueDomainName) {
			var self = this;

			this.model.save(this.fetchNewAttributes(valueDomainID, valueDomainName), {
				wait : true,
				dataType : this.model.isNew() ? 'json' : 'text',
				success : function(model, response, options) {
					// newly added or updated data element is added to current DataElement collection in locator
					// IMPORTANT! also they will be added to data elements of current Context
					// with merge:true , updates on model is reflected to model in collection
					var dataElementConceptID = self.options.dataElementConceptID || self.model.get('dataElementConceptID');
					self.options.app.getMDRModelLocator().getDataElementCollectionOfDEC(dataElementConceptID).remove(model);
					self.options.app.getMDRModelLocator().getDataElementCollectionOfDEC(dataElementConceptID).add(model);

					self.options.app.getMDRModelLocator().getDataElementCollectionOfContext(self.model.get('contextID')).remove(model);
					self.options.app.getMDRModelLocator().getDataElementCollectionOfContext(self.model.get('contextID')).add(model);
				},
				error : function(error) {
					alert('Nope');
				}
			});
		},
		setDataTypeDropDown : function() {
			var dataTypes = this.options.app.getMDRModelLocator().getDataTypes();
			for (var dt in dataTypes.models) {
				this.ui.valueDomainDataType.append('<option value="' + dataTypes.at(dt).get('schemeReference') + '">' + dataTypes.at(dt).get('datatypeName') + '</option>');
			}
			var self = this;
			if (!this.valueDomainModel.isNew()) {
				this.ui.valueDomainDataType.children().each(function() {
					if (this.text == self.valueDomainModel.get('dataType').datatypeName) {
						this.selected = 'selected';
					}
				});
			}
		}
	});
	return DataElementModalView;
});
