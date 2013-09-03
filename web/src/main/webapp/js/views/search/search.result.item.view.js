define(['jquery', 'backbone', 'marionette', 'bootstrap', 'text!templates/search/search.result.item.view.template.html', 'js/views/dataelement/data.element.modal.view'], function($, Backbone, Marionette, BootStrap, SearchResultItemViewTemplate, DataElementModalView) {
	// TODO display edit modal

	var SearchResultItemView = Marionette.ItemView.extend({
		template : SearchResultItemViewTemplate,
		tagName : "tr",
		initialize : function() {
			// contextName is set so that can be displayed on search result
			var modelLocator = this.options.app.getMDRModelLocator();
			var contextList = modelLocator.getContextCollection();
			var contextID = this.model.get('contextID');
			this.model.set('contextName', contextList.get(contextID).get('name'));
		},
		modelEvents : {
			"change" : 'render'
		},
		events : {
			'click .icon-chevron-right' : 'openDetailed',
			'dblclick' : 'openDetailed'
		},
		openDetailed : function() {
			this.options.app.vent.trigger('dataElement:detailed', this.model.get('id'));
		}
	});
	return SearchResultItemView;
});
