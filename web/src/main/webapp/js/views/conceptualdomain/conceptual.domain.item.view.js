define(['jquery', 'backbone', 'marionette', 'bootstrap', 'text!templates/conceptualdomain/conceptual.domain.item.view.template.html', 'js/views/conceptualdomain/conceptual.domain.modal.view'], function($, Backbone, Marionette, BootStrap, ConceptualDomainItemViewTemplate, ConceptualDomainModalView) {
	// TODO display edit modal

	var ConceptualDomainItemView = Marionette.ItemView.extend({
		template : ConceptualDomainItemViewTemplate,
		tagName : 'tr',
		events : {
			'click .icon-edit' : 'editCDClick',
			'dblclick' : 'listValueMeanings',
			'click .icon-chevron-right' : 'listValueMeanings'
		},
		modelEvents : {
			"change" : 'render'
		},
		ui : {
			viewValueMeanings : '.icon-chevron-right'
		},
		editCDClick : function(e) {
			e.stopPropagation();
			// This function is called when an edit button of an conceptual domain is clicked
			// model of this view is conceptualDomain to be updated, ModalWindow with this.model rendered
			var self = this;
			self.options.app.vent.trigger('modalWindow:show', new ConceptualDomainModalView({
				model : self.model,
				app : self.options.app
			}));
		},

		listValueMeanings : function() {
			if(!this.model.get('enumerated')) {
				// if not enumerated nothing to open
				return;
			}
			this.options.app.vent.trigger('valueMeaning:list', this.model.get('id'));
		},

		onRender : function() {
			if (!this.model.get('enumerated')) {
				this.ui.viewValueMeanings.hide();
			}
		}
	});
	return ConceptualDomainItemView;
});
