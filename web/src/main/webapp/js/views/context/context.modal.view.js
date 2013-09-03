define(['jquery', 'backbone', 'marionette', 'bootstrap', 'js/models/context/context.model', 'text!templates/context/context.modal.template.html'], function($, Backbone, Marionette, BootStrap, ContextModel, ContextModalTemplate) {
	var ContextModalView = Marionette.ItemView.extend({
		template : ContextModalTemplate,
		events : {
			'click #sendContextModelForm' : 'send',
			'click #name' : 'hidePopover',
			'click #definition' : 'hidePopover'
		},
		ui : {
			name : '#name',
			description : '#description',
			definition : '#definition',
			registrationStatus : '#registrationStatus',
			administrativeStatus : '#administrativeStatus',
			administrativeNote : '#administrativeNote',
			explanatoryComment : '#explanatoryComment',
			sendButton : '#sendContextModelForm',
			title : '.modal-header h3'
		},
		initialize : function() {
			if (!this.model) {
				this.model = new ContextModel();
			}
		},
		hidePopover : function(){
			this.$(this.ui.name).popover('hide');
			this.$(this.ui.definition).popover('hide');
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
			if (!this.ui.name.val()) {
				this.$(this.ui.name).popover('show');
				return;
			}
			if(!this.ui.definition.val()) {
				this.$(this.ui.definition).popover('show');
				return;
			}
			var self = this;
			this.model.save(this.fetchNewAttributes(), {
				wait : true,
				dataType : this.model.isNew() ? 'json' : 'text',
				success : function(response) {
					// new context should be added into collection
					// with merge:true - existing item is updated in the collection
					self.options.app.getMDRModelLocator().getContextCollection().add(self.model, {
						merge : true
					});

					// with this event, all main layout is rendered from scratch for this context,
					// objectClass - dataElement ... collections are deleted and refetched for this context
					$('#' + self.model.get('id') + ' span').click();
				},
				error : function(error) {
					alert('Nope');
				}
			});
			this.$('#modalWindow').modal('hide');
		},
		onRender : function() {
			if (this.model.isNew()) {
				this.ui.sendButton.html('Create');
				this.ui.title.html('Create Context');
				this.$('#collapseOptional').hide();
			} else {
				this.ui.sendButton.html('Update');
				this.ui.title.html('Update Context');
			}
			this.$('#registrationStatus').val(this.model.get('registrationStatus'));
			this.$('#administrativeStatus').val(this.model.get('administrativeStatus'));
			
			this.$(this.ui.name).popover({
				content : 'Please fill out this field!',
				placement : 'bottom',
				trigger : 'manual'
			});
			
			this.$(this.ui.definition).popover({
				content : 'Please fill out this field!',
				placement : 'bottom',
				trigger : 'manual'
			});
		}
	});
	return ContextModalView;
});
