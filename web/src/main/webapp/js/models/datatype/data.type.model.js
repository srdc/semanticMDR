define(['backbone', 'relational'], function(Backbone) {
	var DataTypeModel = Backbone.RelationalModel.extend({
		defaults : {// TODO fix the defaults
			datatypeName : 'default',
			schemeReference : 'default'
		}
	});
	return DataTypeModel;
}); 
