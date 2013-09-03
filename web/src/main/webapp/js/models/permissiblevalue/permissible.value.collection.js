define(['backbone', 'js/models/permissiblevalue/permissible.value.model'], function(Backbone, PermissibleValueModel) {
	var PermissibleValueCollection = Backbone.Collection.extend({
		model : PermissibleValueModel
	});
	return PermissibleValueCollection;
}); 