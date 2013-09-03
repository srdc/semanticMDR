define(['backbone', 'relational', 'js/models/valuemeaning/value.meaning.model'], function(Backbone, Realational, ValueMeaningModel) {
	var PermissibleValueModel = Backbone.RelationalModel.extend({
		defaults : {
			valueMeaning : '',
			valueItem : ''
		},
		relations : [{
			type : Backbone.HasOne,
			key : 'valueMeaning',
			relatedModel : ValueMeaningModel
		}]
	});
	return PermissibleValueModel;
});
