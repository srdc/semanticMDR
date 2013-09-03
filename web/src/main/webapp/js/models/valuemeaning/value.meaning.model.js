define(['backbone', 'relational'], function(Backbone, Relational) {
	var ValueMeaningModel = Backbone.RelationalModel.extend({
		defaults : {
			id : null,
			description : "",
			conceptualDomainID : ""
		},
		url : function() {
			return URL + "cd/" + this.get('conceptualDomainID') + "/vm";
		}
	});
	return ValueMeaningModel;
});
