define(['backbone', 'js/models/classification/extraction.specification.model'], function(Backbone,ExtractionSpecificationModel) {
	var ExtractionSpecificationCollection = Backbone.Collection.extend({
		model : ExtractionSpecificationModel
	});
	return ExtractionSpecificationCollection;
}); 