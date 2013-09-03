define(['jquery', 'backbone', 'marionette', 'bootstrap', 'js/views/search/search.result.item.view', 'text!templates/search/search.result.template.html', 'js/views/paginator/paginator.composite.view'], function($, Backbone, Marionette, BootStrap, SearchResultItemView, SearchResultTemplate, PaginatorCompositeView) {
	var SearchResultCompositeView = Marionette.CompositeView.extend({
		template : SearchResultTemplate,
		itemView : SearchResultItemView,
		itemViewContainer : 'tbody',
		initialize : function() {
			this.collection = this.options.app.getMDRModelLocator().searchDataElement(this.options.keyword, this.options.offset);

			this.paginator = new PaginatorCompositeView({
				paginatedCollection : this.collection,
				layer : this
			});
			this.paginator.render();
		},
		paginator : null,
		ui : {
			paginatorBar : '#paginatorBar'
		},
		buildItemView : function(item, ItemViewType, itemViewOptions) {
			var self = this;
			var options = {
				model : item,
				app : self.options.app,
			};
			var view = new ItemViewType(options);
			return view;
		},
		onRender : function() {
			if (this.collection.length == 0) {
				this.$('.alert').show();
				this.$('.table').hide();
			}
			if (this.collection.totalPages > 1) {
				this.ui.paginatorBar.append(this.paginator.el);
				this.paginator.delegateEvents();
			}
		},
		navigateHistory : function(offset) {
			var keyword = this.options.keyword;
			Backbone.history.navigate('repository/search/' + keyword + '/' + offset);
		}
	});
	return SearchResultCompositeView;
});
