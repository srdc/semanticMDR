define(['jquery', 'backbone', 'marionette', 'bootstrap', 'js/views/dec/data.element.concept.item.view', 'text!templates/dec/data.element.concept.template.html', 'js/views/dec/data.element.concept.modal.view', 'js/views/paginator/paginator.composite.view'], function($, Backbone, Marionette, BootStrap, DataElementConceptItemView, DataElementConceptTemplate, DataElementConceptModalView, PaginatorCompositeView) {
	var DataElementConceptCompositeView = Marionette.CompositeView.extend({
		template : DataElementConceptTemplate,
		itemView : DataElementConceptItemView,
		itemViewContainer : 'tbody',
		initialize : function() {
			var locator = this.options.app.getMDRModelLocator();
			var self = this;
			// currentContext is setted according to ObjectClass'es Context beause it can previous page back form another Context
			this.collection = locator.getDataElementConceptCollection(this.options.objectClassID, this.options.offset);
			this.objectClass = locator.getObjectClass(this.options.objectClassID);
			locator.setCurrentContext(this.objectClass.get('contextID'));

			this.paginator = new PaginatorCompositeView({
				paginatedCollection : this.collection,
				layer : this
			});
			this.paginator.render();

			var context = locator.getCurrentContext();
			this.options.app.vent.trigger('breadcrumb:change', {
				'contextID' : context.get('id'),
				'contextName' : context.get('name'),
				'objectClassID' : self.objectClass.get('id'),
				'objectClassName' : self.objectClass.get('name')
			});
		},
		paginator : null,
		ui : {
			paginatorBar : '#paginatorBar',
			objectClassName : "#objectClassName"
		},
		collectionEvents : {
			"add" : "modelAdded"
		},
		events : {
			'click #addDataElementConcept' : 'add'
		},
		add : function() {
			this.options.app.vent.trigger('modalWindow:show', new DataElementConceptModalView({
				objectClassID : this.options.objectClassID,
				app : this.options.app,
			}));
		},
		modelAdded : function(){
			this.onRender();
		},
		buildItemView : function(item, ItemViewType, itemViewOptions) {
			var self = this;
			var options = {
				model : item,
				objectClassID : self.options.objectClassID,
				app : self.options.app
			};
			var view = new ItemViewType(options);
			return view;
		},
		onRender : function() {
			if (this.collection.length == 0) {
				this.$('div .alert').show();
				this.$('div .table').hide();
			} else {
				this.$('div .alert').hide();
				this.$('div .table').show();
			}
			if (this.collection.totalPages > 1) {
				this.ui.paginatorBar.append(this.paginator.el);
				this.paginator.delegateEvents();
			}
			this.ui.objectClassName.html(this.objectClass.get('name'));
		},
		navigateHistory : function(offset) {
			var objectClassID = this.options.objectClassID;
			Backbone.history.navigate('oc/' + objectClassID + '/dec?offset=' + offset);
		}
	});
	return DataElementConceptCompositeView;
});
