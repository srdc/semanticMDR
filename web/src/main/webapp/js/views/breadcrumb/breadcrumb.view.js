define(['jquery', 'backbone', 'marionette', 'text!templates/breadcrumb/breadcrumb.template.html'], function($, Backbone, Marionette, BreadCrumbTemplate) {
	var BreadCrumbView = Marionette.ItemView.extend({
		initialize : function() {
			var self = this;
			self.listenTo(self.options.app.vent, 'breadcrumb:change', self.pageChange);
			var locator = this.options.app.getMDRModelLocator();
			var context = locator.getCurrentContext();
		},
		template : BreadCrumbTemplate,
		events : {
			'click li' : 'click'
		},
		onRender : function() {
		},
		// this function is responsible of rendering breadcrumb according the current location
		//options should be a JSON object indicating the current location, format should be
		//{
		//	location : 'conceptualDomainHome' || 'home' || 'search' || 'query' || 'browseDataElement'
		//	contextName : '',
		//	contextID : '',
		//	objectClassName : '',
		//	objectClassID : '',
		//	dataElementName : '',
		//	dataElementID : '',
		//	conceptualDomainName : '',
		//	conceptualDomainID : ''

		//}, according what is supplied, breadcrumb is updated
		// if any id - name couple is supplied, then no need for location field
		//, if not then location should be supplied for proper render
		pageChange : function(options) {
			this.render();
			if (options.contextID && options.contextName) {
				this.$('ul').append('<li id="' + options.contextID + '" type="context" ><a href="#">' + options.contextName + '</a><span class="divider">/</span</li>');
			}
			if (options.objectClassID && options.objectClassName) {
				this.$('ul').append('<li id="' + options.objectClassID + '" type="objectClass" ><a href="#">' + options.objectClassName + '</a><span class="divider">/</span</li>');
			}
			if (options.dataElementID && options.dataElementName) {
				this.$('ul').append('<li id="' + options.dataElementID + '" type="dataElement" ><a href="#">' + options.dataElementName + '</a><span class="divider">/</span</li>');
			}
			if (options.conceptualDomainID && options.conceptualDomainName) {
				this.$('ul').append('<li type="conceptualDomainHome" ><a href="#">Conceptual Domains</a><span class="divider">/</span</li>');
				this.$('ul').append('<li id="' + options.conceptualDomainID + '" type="conceptualDomain" ><a href="#">' + options.conceptualDomainName + '</a><span class="divider">/</span</li>');
			}
			if (options.location == "conceptualDomainHome") {
				this.$('ul').append('<li type="conceptualDomainHome" ><a href="#">Conceptual Domains</a><span class="divider">/</span</li>');
			}
			if (options.location == "search") {
				this.$('ul').append('<li type="search" ><a href="#">Search</a><span class="divider">/</span</li>');
			}
			if (options.location == "query") {
				this.$('ul').append('<li type="query" ><a href="#">Query</a><span class="divider">/</span</li>');
			}
			if (options.location == 'browseDataElement') {
				this.$('ul').append('<li id="' + options.contextID + '" type="browseDataElement" ><a href="#">Data Elements</a><span class="divider">/</span</li>');
			}

			// now last of this items should be activated
			this.$('#breadcrumbs li').last().addClass('active');
			this.$('#breadcrumbs li.active span').remove();
			var text = this.$('#breadcrumbs li.active a').text();
			this.$('#breadcrumbs li.active a').remove();
			this.$('#breadcrumbs li.active').text(text);

		},
		// with this click event handler, click on the items of breadcrumb will be handled
		click : function(e) {
			e.stopPropagation();
			e.preventDefault();
			var type = $(e.target).closest('li').attr('type');
			var id = $(e.target).closest('li').attr('id');

			if (type == 'home') {
				Backbone.history.navigate('', {
					'trigger' : true
				});
			} else if (type == 'conceptualDomainHome') {
				Backbone.history.navigate('cd/list?offset=0', {
					'trigger' : true
				});
			} else if (type == 'search') {
				Backbone.history.navigate('search', {
					'trigger' : true
				});
			} else if (type == 'query') {
				Backbone.history.navigate('query', {
					'trigger' : true
				});
			} else if (type == 'context') {
				Backbone.history.navigate('context/' + id + '/oc?offset=0', {
					'trigger' : true
				});
			} else if (type == 'objectClass') {
				Backbone.history.navigate('oc/' + id + '/dec?offset=0', {
					'trigger' : true
				});
			} else if (type == 'dataElement') {
				Backbone.history.navigate('de/' + id, {
					'trigger' : true
				});
			} else if (type == 'conceptualDomain') {
				Backbone.history.navigate('cd/' + id + '/vm', {
					'trigger' : true
				});
			} else if (type == 'browseDataElements') {
				Backbone.history.navigate('context/' + id + '/de?offset=0', {
					'trigger' : true
				});
			}
		}
	});
	return BreadCrumbView;
});
