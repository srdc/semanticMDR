define(['jquery', 'backbone', 'marionette', 'bootstrap', 'text!templates/query/query.result.view.template.html'], function($, Backbone, Marionette, BootStrap, QueryResultViewTemplate) {
	var QueryResultItemView = Marionette.ItemView.extend({
		template : QueryResultViewTemplate,
		onRender: function() {
			this.$el.find("#qresult")[0].innerHTML = (this.options.qresult)
		},
		modelEvents : {
			"change" : 'render'
		},
		events : {
			'click button' : 'newquery'
		},
		newquery : function() {
			this.options.app.vent.trigger('menu:query');
		},
	});
	return QueryResultItemView;
});
