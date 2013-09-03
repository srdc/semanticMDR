define(['jquery', 'backbone', 'marionette', 'bootstrap', 'text!templates/dataelement/data.element.item.view.template.html', 'js/views/dataelement/data.element.modal.view'], function($, Backbone, Marionette, BootStrap, DataElementItemViewTemplate, DataElementModalView) {
	// TODO display edit modal

	var DataElementItemView = Marionette.ItemView.extend({
		template : DataElementItemViewTemplate,
		tagName : "tr",
		events : {
			'click .icon-edit' : 'edit',
			'click .icon-chevron-right' : 'openDetailed'
		},
		modelEvents : {
			"change" : 'render'
		},
		edit : function() {
			this.options.app.vent.trigger('modalWindow:show', new DataElementModalView({
				model : this.model,
				dataElementConceptID : this.options.dataElementConceptID,
				app : this.options.app
			}));
		},
		openDetailed : function() {
			this.options.app.vent.trigger('dataElement:detailed', this.model.get('id'));
		}
	});
	return DataElementItemView;
});
