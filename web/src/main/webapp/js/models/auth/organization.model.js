define(['backbone', 'relational'], function(Backbone) {
	var OrganizationModel = Backbone.RelationalModel.extend({
		defaults : {
			id : '',
			name : '',
			address : '',
			phone : '',
			fax : '',
			email : '',
			country : '',
			registrationTime : ''
		}
	});
	return OrganizationModel;
});
