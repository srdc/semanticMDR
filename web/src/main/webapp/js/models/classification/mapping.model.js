	define(['backbone', 'relational'], function(Backbone, Realational) {
	var MappingModel = Backbone.RelationalModel.extend({
		defaults : {
			termSystem : '',
			matchType : '',
			termUUID : '',
			termName : '',
			termSystemOID : ''
		},
		url : function() {
			return URL + 'de/' + this.get('dataElementID') + '/mapping';
		}
	});
	return MappingModel;
});
