define(['backbone', 'underscore', 'js/models/administered.item.model.js'], function(Backbone, _, AdministeredItemModel) {
	var ContextModel = AdministeredItemModel.extend({
		defaults : {
			description : ""
		},
		urlRoot : URL + 'repository'
	});
	_.extend(ContextModel.prototype.defaults, AdministeredItemModel.prototype.defaults);
	return ContextModel;
});
