define(['backbone', 'js/models/paginator/paginator.model'], function(Backbone, PaginatorModel) {
	var PaginatorCollection = Backbone.Collection.extend({
		model : PaginatorModel
	});
	return PaginatorCollection;
});
