define(['backbone', 'underscore', 'js/models/administered.item.model.js', 'js/models/classification/extraction.specification.model', 'js/models/classification/extraction.specification.collection', 'js/models/classification/mapping.model', 'js/models/classification/mapping.collection' ], function(Backbone, _, AdministeredItemModel, ExtractionSpecificationModel, ExtractionSpecificationCollection, MappingModel, MappingCollection) {
	var DataElementModel = AdministeredItemModel.extend({
		defaults : {
			dataElementConceptID : "",
			dataElementConceptName : "",
			valueDomainID : "",
			valueDomainName : "",
			extractionSpecs : [],
			mappings : [],
		},
		relations : [{
			type : Backbone.HasMany,
			relatedModel : ExtractionSpecificationModel,
			key : 'extractionSpecs',
			collectionType : ExtractionSpecificationCollection
		},
		{
			type: Backbone.HasMany,
			relatedModel: MappingModel,
			key : 'mappings',
			collectionType:  MappingCollection
		}],
		url : function() {
			if (this.isNew()) {
				return URL + 'context/' + this.get('contextID') + '/de';
			} else {
				return URL + 'de/' + this.get('id');
			}
		}
	});
	_.extend(DataElementModel.prototype.defaults, AdministeredItemModel.prototype.defaults);
	return DataElementModel;
});
