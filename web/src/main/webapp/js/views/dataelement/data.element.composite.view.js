define(['jquery', 'backbone', 'marionette', 'bootstrap', 'js/views/dataelement/data.element.item.view', 'text!templates/dataelement/data.element.template.html', 'js/views/dataelement/data.element.modal.view', 'js/views/paginator/paginator.composite.view'], function($, Backbone, Marionette, BootStrap, DataElementItemView, DataElementTemplate, DataElementModalView, PaginatorCompositeView) {

	// SINCE WE ASSUME THERE IS ONLY ONE DE PER DEC, ONLY SOURCE OF THIS VIEW IS FROM CONTEXT
	// THIS VIEW IS TO DISPLAY DE'S OF CONTEXT
	var DataElementCompositeView = Marionette.CompositeView.extend({
		// this templates and itemViews can be overrided by the Search Part of the Application. In case of no overriding
		// below values are default values for all kinds of browsing. Hoewever, while displaying search results
		// itemView and template is overrided so that results are shown in proper way
		template : DataElementTemplate,
		itemView : DataElementItemView,
		itemViewContainer : 'tbody',
		initialize : function() {
			var modelLocator = this.options.app.getMDRModelLocator();

			// browse from the context, get current context from model locator
			var contextID = this.options.contextID;
			modelLocator.setCurrentContext(contextID);
			this.collection = modelLocator.getDataElementCollectionOfContext(contextID, this.options.offset);
			this.removeAddButton = true;

			this.paginator = new PaginatorCompositeView({
				paginatedCollection : this.collection,
				layer : this
			});
			this.paginator.render();
		},
		onRender : function() {
			if (this.collection.length == 0) {
				this.$('div table').parent().html('<div class="alert alert-error">No Data Elements Added Yet</div>');
			}
			if (this.collection.totalPages > 1) {
				this.ui.paginatorBar.append(this.paginator.el);
				this.paginator.delegateEvents();
			}
		},
		paginator : null,
		ui : {
			addButton : '#addDataElement',
			paginatorBar : '#paginatorBar'
		},
		events : {
			'click #addDataElement' : 'add'
		},
		add : function() {
			var locator = this.options.app.getMDRModelLocator();
			var conceptualDomainID = locator.getDataElementConcept(this.options.dataElementConceptID).get('conceptualDomainID');
			this.options.app.vent.trigger('modalWindow:show', new DataElementModalView({
				conceptualDomainID : conceptualDomainID,
				dataElementConceptID : this.options.dataElementConceptID,
				app : this.options.app
			}));
		},
		buildItemView : function(item, ItemViewType, itemViewOptions) {
			var options = {
				model : item,
				dataElementConceptID : this.options.dataElementConceptID,
				app : this.options.app,
			};
			var view = new ItemViewType(options);
			return view;
		},
		navigateHistory : function(offset) {
			Backbone.history.navigate('context/' + this.options.contextID + '/de?offset=' + offset);
		}
	});
	return DataElementCompositeView;
});
