define(['jquery', 'backbone', 'marionette', 'text!templates/auth/user.panel.html'], function($, Backbone, Marionette, userPanel) {
	var UserPanelView = Marionette.ItemView.extend({
		initialize: function() {
			this.locator = this.options.app.getMDRModelLocator();
			this.model = this.locator.getUserModel();
		},
		template : userPanel,
		events : {
			'click button' : 'logout'
		},

		logout : function() {
			this.model.clear();
			$.ajax({
				type : 'DELETE',
				url : URL + 'auth',
                success : function () {}
			});
			$.removeCookie('SID');
			this.options.app.vent.trigger('auth:logout');
		}
	});
	return UserPanelView;
});
