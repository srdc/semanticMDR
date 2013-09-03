define(['jquery', 'underscore', 'backbone', 'marionette', 'js/mdr/mdr.model.locator', 'js/router/mdr.router', 'js/views/auth/login.view', 'js/views/auth/user.panel.view', 'js/layouts/main/main.layout', 'js/views/context/context.modal.view', 'js/views/objectclass/object.class.modal.view', 'js/views/conceptualdomain/conceptual.domain.modal.view', 'jquery_cookie'], function($, _, Backbone, Marionette, MDRModelLocator, MDRRouter, LoginView, UserPanelView, MainLayout, ContextModalView, ObjectClassModalView, ConceptualDomainModalView) {

	/**
	 * SOME GLOBAL FUNCTIONS THAT ARE OVERRIDED *
	 */

	//Override Marionette Template Loader to use RequireJS text loader
	Marionette.TemplateCache.prototype.loadTemplate = function(templateId) {
		// Marionette expects "templateId" to be the ID of a DOM element.
		// But with RequireJS, templateId is actually the full text of the template.
		var template = templateId;

		// Make sure we have a template before trying to compile it
		if (!template || template.length === 0) {
			var msg = "Could not find template: '" + templateId + "'";
			var err = new Error(msg);
			err.name = "NoTemplateError";
			throw err;
		}
		return template;
	}
	/////////////////////////////////////////////

	var MDRApplication = new Marionette.Application();

	var mdrModelLocator = null;
	var mdrRouter = null;

	MDRApplication.addInitializer(function(options) {

		// this urlDash is used to deternine where to go after initialization
		var urlDash = window.location.hash;

		//Initialize Record Locator module
		mdrModelLocator = new MDRModelLocator(MDRApplication);

		var SID = $.cookie('SID');
		if (!SID) {
			this.vent.trigger('auth:logout', urlDash);
		}// there is cookie, verify SID from server, direct home or login
		else {
			$('#loadingOverlay').fadeIn();
			mdrModelLocator.getUserModel().fetch({
				//SID verified, user returned, directing home page
				success : function(response) {
					MDRApplication.vent.trigger('auth:loggedIn', urlDash);
				},
				//SID unverified, direct to login page
				error : function(response) {
					MDRApplication.vent.trigger('auth:logout', urlDash);
				}
			});
		}

	});

	MDRApplication.getMDRModelLocator = function() {
		return mdrModelLocator;
	}

	MDRApplication.getMDRRouter = function() {
		return mdrRouter;
	}
	// if urlDash is provided, application routes there after logging in
	MDRApplication.listenTo(MDRApplication.vent, 'auth:loggedIn', function(urlDash) {
		var mainLayout = new MainLayout({
			app : MDRApplication
		});
		MDRApplication.mainRegion.show(mainLayout);

		var userPanelView = new UserPanelView({
			app : MDRApplication
		});
		MDRApplication.userPanelRegion.show(userPanelView);

		//Inıtıalize Router module
		mdrRouter = new MDRRouter({
			app : MDRApplication
		});
		//Start Backbone History for Auto Route Management
		Backbone.history.stop();
		Backbone.history.start();

		if (urlDash) {
			Backbone.history.navigate(urlDash, true);
		}

		$('#loadingOverlay').fadeOut();
	});

	/* With below event, we font have to create all the modal windows view in MDRApplicaton,
	 * for example objectClassItemView creates the model views and sends it to this event,
	 * in this way MDRApplication is just responsible of rendering modalViews it received
	 */
	MDRApplication.listenTo(MDRApplication.vent, 'modalWindow:show', function(displayView) {
		$('#modalWindow').modal('hide');
		MDRApplication.modalRegion.show(displayView);
		$('#modalWindow').modal('show');
	});

	// if user successfully logins, redirects to route given by urlDash
	MDRApplication.listenTo(MDRApplication.vent, 'auth:logout', function(urlDash) {
		MDRApplication.userPanelRegion.close();
		MDRApplication.mainRegion.show(new LoginView({
			app : MDRApplication,
			urlDash : urlDash
		}));
		$('#loadingOverlay').fadeOut();
		Backbone.history.navigate('');
	});

	MDRApplication.addRegions({
		menuRegion : "#menu",
		userPanelRegion : "#userPanel",
		mainRegion : "#mainSection",
		modalRegion : "#modalSection"
	});

	return MDRApplication;
});
