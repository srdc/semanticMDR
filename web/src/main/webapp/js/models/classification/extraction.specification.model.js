define(['backbone', 'relational'], function(Backbone, Realational) {
	var ExtractionSpecificationModel = Backbone.RelationalModel.extend({
		defaults : {
			type : '',
			value : '',
			modelName : ''
		},
		url : function() {
			return URL + 'de/' + this.get('dataElementID') + '/extractionspecification';
		}
	});
	return ExtractionSpecificationModel;
});