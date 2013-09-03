define(['backbone', 'js/models/datatype/data.type.model'], function(Backbone, DataTypeModel) {
	var DatatypeCollection = Backbone.Collection.extend({
		model : DataTypeModel,
		url : URL + "repository/dt"
	});
	return DatatypeCollection;
});
