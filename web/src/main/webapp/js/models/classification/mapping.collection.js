define(['backbone', 'js/models/classification/mapping.model'], function(Backbone, MappingModel) {
	var MappingCollection = Backbone.Collection.extend({
		model : MappingModel
	});
	return MappingCollection;
}); 