define(['jquery', 'backbone', 'marionette', 'bootstrap', 'js/models/dec/data.element.concept.model', 'text!templates/dec/data.element.concept.modal.template.html', 'js/models/property/property.model'], function($, Backbone, Marionette, BootStrap, DataElementConceptModel, DataElementConceptTemplate, PropertyModel) {
	var DataElementConceptModalView = Marionette.ItemView.extend({
		template : DataElementConceptTemplate,
		events : {
			'click #sendDataElementConceptForm' : 'send',
			'click #propertyName' : 'hidePopover',
			'click #propertyDefinition' : 'hidePopover',
			'click #conceptualDomain' : 'hidePopover'
		},
		ui : {
			// Model inputs
			name : '#name',
			definition : '#definition',
			registrationStatus : '#registrationStatus',
			administrativeStatus : '#administrativeStatus',
			administrativeNote : '#administrativeNote',
			explanatoryComment : '#explanatoryComment',
			propertyName : '#propertyName',
			propertyDefinition : '#propertyDefinition',
			conceptualDomain : '#conceptualDomain',

			// Other UI elements
			sendButton : '#sendDataElementConceptForm',
			title : '.modal-header h3',
			createInputs : '#createInputs',
		},
		initialize : function() {
			if (!this.model) {
				this.model = new DataElementConceptModel();
			}
		},
		hidePopover : function() {
			this.$(this.ui.propertyName).popover('hide');
			this.$(this.ui.propertyDefinition).popover('hide');
			this.$(this.ui.conceptualDomain).popover('hide');
		},
		fetchNewAttributes : function() {
			newAttributes = {};

			for (var field in this.ui) {
				if (!this.$(this.ui[field]).is(':disabled') && this.model.has(field)) {
					newAttributes[field] = this.ui[field].val();
				}
			}

			if (this.model.isNew()) {
				this.model.set('contextID', this.options.app.getMDRModelLocator().getCurrentContext().get('id'));
				this.model.set('objectClassID', this.options.objectClassID);
			}

			return newAttributes;
		},
		send : function() {
			if (this.model.isNew()) {
				// Checking for required fields. If empty ,popover pops up
				if (!this.ui.propertyName.val()) {
					this.$(this.ui.propertyName).popover('show');
					return;
				}
				if (!this.ui.propertyDefinition.val()) {
					this.$(this.ui.propertyDefinition).popover('show');
					return;
				}
				if (!this.ui.conceptualDomain.val()) {
					this.$(this.ui.conceptualDomain).popover('show');
					return;
				}
			}
			var self = this;
			var decModelAttributes = this.fetchNewAttributes();
			if (this.model.changedAttributes(decModelAttributes)) {
				$('#loadingOverlay').fadeIn();
				this.model.save(decModelAttributes, {
					wait : true,
					dataType : this.model.isNew() ? 'json' : 'text',
					success : function(response) {
						self.options.app.getMDRModelLocator().getDataElementConceptCollection().fetch({
							async : false
						});
						this.$('#modalWindow').modal('hide');
						$('#loadingOverlay').fadeOut();
					},
					error : function(error) {
						alert('Nope');
					}
				});
			}
		},
		onRender : function() {
			var self = this;
			if (this.model.isNew()) {
				this.ui.sendButton.html('Create');
				this.ui.title.html('Create Data Element Concept');
				this.ui.name.parent().hide();
				this.$('#collapseOptional').hide();

				this.ui.propertyName.typeahead({
					minLength : 2,
					source : function(query, process) {
						var contextID = self.options.app.getMDRModelLocator().getCurrentContext().get('id');
						self.propertyCollection = self.options.app.getMDRModelLocator().searchProperty(contextID, self.ui.propertyName.val());
						var results = _.map(self.propertyCollection.models, function(property) {
							return property.get('name');
						});
						process(results);
					},
					updater : function(name) {
						var property = _.find(self.propertyCollection.models, function(p) {
							return p.get('name') == name;
						});
						self.setProperty(property);
						return property.get('name');
					}
				});

				this.ui.conceptualDomain.typeahead({
					minLength : 2,
					source : function(query, process) {
						self.cdCollection = self.options.app.getMDRModelLocator().searchConceptualDomain(self.ui.conceptualDomain.val());
						var results = _.map(self.cdCollection.models, function(cd) {
							return cd.get('name');
						});
						process(results);
					},
					updater : function(name) {
						var conceptualDomain = _.find(self.cdCollection.models, function(cd) {
							return cd.get('name') == name;
						});
						self.setConceptualDomain(conceptualDomain);
						return conceptualDomain.get('name');
					}
				});

			} else {
				this.ui.sendButton.html('Update');
				this.ui.title.html('Update Data Element Concept');
				this.ui.createInputs.remove();
			}

			this.ui.registrationStatus.val(this.model.get('registrationStatus'));
			this.ui.administrativeStatus.val(this.model.get('administrativeStatus'));

			this.$(this.ui.propertyName).popover({
				content : 'Please fill out this field!',
				placement : 'bottom',
				trigger : 'manual'
			});
			this.$(this.ui.propertyDefinition).popover({
				content : 'Please fill out this field!',
				placement : 'bottom',
				trigger : 'manual'
			});
			this.$(this.ui.conceptualDomain).popover({
				content : 'Please fill out this field!',
				placement : 'bottom',
				trigger : 'manual'
			});
		},

		setProperty : function(property) {
			this.model.set('propertyID', property.get('id'));
		},
		setConceptualDomain : function(cd) {
			this.model.set('conceptualDomainID', cd.get('id'));
		},
		showPropertyDefinition : function(e) {
			if (this.ui.propertyDefinition.is(':hidden')) {
				this.dismissPropertySearchResult();
				this.model.set('propertyID', '');
			}
		}
	});
	return DataElementConceptModalView;
});
