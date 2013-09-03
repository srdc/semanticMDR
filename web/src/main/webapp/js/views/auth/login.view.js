define(['jquery', 'backbone', 'marionette', 'js/models/auth/user.model', 'text!templates/auth/login.form.html'], function($, Backbone, Marionette, UserModel, loginForm) {
	var LoginView = Marionette.ItemView.extend({
		initialize : function() {
			this.locator = this.options.app.getMDRModelLocator();
			this.model = this.locator.getLoginModel();
		},
		template : loginForm, 
		events : {
			'click button' : 'signIn',
			'keydown #password' : 'submitForm',
			'keydown #username' : 'submitForm',
			'click #username' : 'hidePopover',
			'click #password' : 'hidePopover'
		},
		ui : {
			username : '#username',
			password : '#password',
			staySignedIn : '#staySignedIn',
		},
		onRender : function(){
			this.$('button').popover({
				content : 'Please check your username/password!',
				placement : 'right',
				trigger : 'manual'
			});
		},
		hidePopover : function(){
			this.$('button').popover('hide');
		},
		signIn : function() {
			if (!this.ui.username.val() || !this.ui.password.val()) {
				//username password should be entered to login
				this.$('button').popover('show');
			} else {
				$('#loadingOverlay').fadeIn();
				this.model.set({
					'username' : this.ui.username.val(),
					'password' : this.ui.password.val(),
					'staySignedIn' : this.ui.staySignedIn.is(':checked')
				});
				var self = this;
				$.ajax({
					type : 'PUT',
					url : URL + 'auth',
					headers : {
						"Accept" : "application/json",
						"Content-Type" : "application/json"
					},
					data : JSON.stringify(this.model),
					success : function(response) {
						$('#loadingOverlay').fadeOut();
						//session obtaiened, set a cookie, then trigger a loggedIn in event
						var exp = '';
						if (response.until) {
							var now = new Date();
							var unt = new Date(response.until);
							exp = Math.floor((unt - now) / (24 * 60 * 60 * 1000));
						}
						$.cookie("SID", response.sessionID, {
							expires : exp
						});
						// now user will be obtained with SID
						var user = self.locator.getUserModel();
						user.fetch({
							success : function(response) {
								//user created according to response, will be sent with event
								self.options.app.vent.trigger('auth:loggedIn', self.options.urlDash);
							},
							error : function(response) {

							}
						});

					},
					error : function(error) {
						$('#loadingOverlay').fadeOut();
						//username password should be entered to login
						self.$('button').popover('show');
					}
				});
			}
		},
		submitForm : function(event) {
			if (event.keyCode == 13 && this.ui.username.val() && this.ui.password.val()) {
				this.signIn();
			}
		}
	});
	return LoginView;
});
