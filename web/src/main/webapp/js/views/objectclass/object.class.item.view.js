define(['jquery', 
		'backbone', 
		'marionette', 
		'bootstrap', 
		'text!templates/objectclass/object.class.item.view.template.html', 
		'js/views/objectclass/object.class.modal.view'], 
function($, Backbone, Marionette, BootStrap, ObjectClassItemViewTemplate, ObjectClassModalView) {
	// TODO display edit modal

	var ObjectClassItemView = Marionette.ItemView.extend({
		template : ObjectClassItemViewTemplate,
		tagName : "tr",
		events : {
			'click .icon-edit' : 'editOCClick',
			'dblclick' : 'listProperties',
			'click .icon-chevron-right' : 'listProperties'
		},
		modelEvents : {
			'change' : 'render'
		},

		editOCClick : function(e) {
			e.stopPropagation();
			// This function is called when an edit button of an object class is clicked
			// model of this view is ObjectClass to be updated, ModalWindow with this.model rendered
			var self = this;
			self.options.app.vent.trigger('modalWindow:show', new ObjectClassModalView({
				model : self.model,
				app : self.options.app
			}));
		},

		listProperties : function() {
			this.options.app.vent.trigger('dataElementConcept:list', this.model.get('id'), 0);
		}
	});
	return ObjectClassItemView;
});
