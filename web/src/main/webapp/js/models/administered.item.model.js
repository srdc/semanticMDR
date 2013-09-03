define(['backbone'], function(Backbone) {
	var AdministeredItemModel = Backbone.Model.extend({
		defaults : {
			id : null,
			name : "",
			definition : "",
			registrationStatus : "",
			administrativeStatus : "",
			administrativeNote : "",
			explanatoryComment : "",
			contextID : ""
		}
	});
	return AdministeredItemModel;
});
