define(['backbone'], function(Backbone) {
	var LoginModel = Backbone.Model.extend({
		defaults: {
			username: "",
			password: "",
			staySignedIn: false	
		}
	});
	return LoginModel;
});
