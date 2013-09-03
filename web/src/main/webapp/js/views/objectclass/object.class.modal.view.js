define(['jquery', 'backbone', 'marionette', 'bootstrap', 'js/models/objectclass/object.class.model', 'text!templates/objectclass/object.class.modal.template.html'], function($, Backbone, Marionette, BootStrap, ObjectClassModel, ObjectClassTemplate) {
	var ObjectClassModalView = Marionette.ItemView.extend({
		template : ObjectClassTemplate,
		events : {
			'click #sendObjectClassForm' : 'send',
			'click #conceptHierarchy div:first-child' : 'openParent',
			'click #conceptHierarchy div:last-child' : 'openSubConcept',
			'click #name' : 'hidePopover',
			'click #definition' : 'hidePopover'
		},
		ui : {
			modalWindow : '#modalWindow',
			name : '#name',
			definition : '#definition',
			registrationStatus : '#registrationStatus',
			administrativeStatus : '#administrativeStatus',
			administrativeNote : '#administrativeNote',
			explanatoryComment : '#explanatoryComment',
			sendButton : '#sendObjectClassForm',
			title : '.modal-header h3',
			subConcepts : '#conceptHierarchy div:last-child'
		},
		initialize : function() {
			var self = this;
			if (!self.model) {
				self.model = new ObjectClassModel();
			}

			var parentConcept = new ObjectClassModel();
			// means this is an edit window, and concept Has parent
			if (self.model.get('parentConceptID')) {
				parentConcept.set({
					id : self.model.get('parentConceptID')
				});
				parentConcept.fetch({
					async : false,
					error : function() {
						alert('Nope');
					}
				});
			}
			// if does not have it again put into model, to show its empty
			parentConcept.name = parentConcept.get('name');
			self.model.parentConcept = parentConcept;
			var parentConceptName = parentConcept.name;
			self.model.set({
				'parentConceptName' : parentConceptName
			});

			// sub concept collection of the object class viewed
			self.subConcepts = self.options.app.getMDRModelLocator().getSubConceptCollection(self.model.get('id'));
		},
		hidePopover : function(){
			this.$(this.ui.name).popover('hide');
		},
		fetchNewAttributes : function() {
			newAttributes = {};

			for (var field in this.ui) {
				if (!this.$(this.ui[field]).is(':disabled') && this.model.has(field)) {
					newAttributes[field] = this.ui[field].val();
				}
			}

			return newAttributes;
		},
		send : function() {
			// Checking for required fields. If empty ,popover pops up
			if (!this.ui.name.val()){
				this.$(this.ui.name).popover('show');
				return;
			}
			if (this.model.isNew()) {
				this.model.set('contextID', this.options.app.getMDRModelLocator().getCurrentContext().get('id'));
			}
			
			var self = this;
			var objectClassModelAttributes = this.fetchNewAttributes();
			if (this.model.changedAttributes(objectClassModelAttributes)) {
				$('#loadingOverlay').fadeIn();
				this.model.save(objectClassModelAttributes, {
					wait : true,
					dataType : this.model.isNew() ? 'json' : 'text',
					success : function(response) {
						// newly added or updated object class is added to current ObjectClass collection is locator
						// with merge:true, updates on model is reflected to model in collection
						this.$('#modalWindow').modal('hide');
						$('#loadingOverlay').fadeOut();
						self.options.app.getMDRModelLocator().getObjectClassCollection().fetch();
					},
					error : function(error) {
						alert('Nope');
					}
				});
			}
		},

		// this methods does the same thing with clicking on edit button
		openParent : function(e) {
			e.preventDefault();
			var self = this;
			var parentConcept = self.model.parentConcept;
			self.options.app.vent.trigger('modalWindow:show', new ObjectClassModalView({
				model : parentConcept,
				app : self.options.app
			}));
		},

		//this method does same thing with clicking on edit button, except modal behind changes
		openSubConcept : function(e) {
			e.preventDefault();
			var self = this;
			var id = $(e.target).attr('id');
			var subConcept = self.subConcepts.get(id);
			self.options.app.vent.trigger('modalWindow:show', new ObjectClassModalView({
				model : subConcept,
				app : self.options.app
			}));
		},

		onRender : function() {
			// if model is empty, this is create form, do not show optional
			if (!this.model.get('name') && !this.model.get('description')) {
				this.$('#collapseOptional').hide();
			}
			// if parent concept ID does not exist, no need to show
			if (!this.model.get('parentConceptID')) {
				this.$('#conceptHierarchy div:first-child').hide();
			}
			// if sub concepts does not exist, no need to show
			if (!this.subConcepts || !this.subConcepts.length) {
				this.$('#conceptHierarchy div:last-child').hide();
			} else {
				for (var i = 0; i < this.subConcepts.length; i++) {
					this.ui.subConcepts.append('<a id="' + this.subConcepts.at(i).get('id') + '" class="btn btn-link" href="#">' + this.subConcepts.at(i).get('name') + '</a>');
				}
			}

			if (this.model.get('id')) {
				this.ui.sendButton.html('Update');
				this.ui.title.html('Update Object Class');
			} else {
				this.ui.sendButton.html('Create');
				this.ui.title.html('Create Object Class');
			}
			
			this.$(this.ui.name).popover({
				content : 'Please fill out this field!',
				placement : 'bottom',
				trigger : 'manual'
			});

		}
	});
	return ObjectClassModalView;
});
