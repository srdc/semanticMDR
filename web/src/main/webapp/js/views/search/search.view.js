define(['jquery', 'backbone', 'marionette', 'text!templates/search/search.template.html', 'js/models/administered.item.collection', 'js/models/dataelement/data.element.model'], function($, Backbone, Marionette, SearchTemplate, AdministeredItemCollection, DataElementModel) {
	var SearchView = Marionette.ItemView.extend({
		initialize : function() {
			this.options.app.vent.trigger('breadcrumb:change', {
				'location' : 'search'
			});
		},
		template : SearchTemplate,
		events : {
			'click button' : 'search',
			'keyup input' : 'keyup'
		},
		search : function() {
			var keyword = this.$('input').val();
			var app = this.options.app;
			$('#loadingOverlay').fadeIn({
				complete : function() {
					app.vent.trigger('dataElement:search', keyword, 0);
				}
			});

		},
		keyup : function(e) {
			// if key is the enter button
			if (e.keyCode == 13) {
				this.$('button').click();
			}
		}
	});

	return SearchView;
});
