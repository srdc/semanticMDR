define(['backbone', 'relational', 'js/models/auth/organization.model'], function(Backbone, Relational, OrganizationModel) {
	var UserModel = Backbone.RelationalModel.extend({
		url : URL + 'auth',
		defaults : {
			id : '',
			email : '',
			password : '',
			title : '',
			name : '',
			surname : '',
			accessLevel : '',
			affiliation : '',
			registrationTime : ''
		},
		relations : [{
			type : Backbone.HasOne,
			key : 'affiliation',
			relatedModel : OrganizationModel
		}]
	});
	return UserModel;
});
