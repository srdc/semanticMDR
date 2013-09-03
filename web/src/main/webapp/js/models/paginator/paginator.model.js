define(['backbone'], function(Backbone) {
	var AdministeredItemModel = Backbone.Model.extend({
		defaults : {
			pageNumber : 0
		}
	});
	return AdministeredItemModel;
});
