define(['marionette', 'js/views/context/context.list.item.view'], function(Marionette, ContextListItemView) {
	var ContextListView = Marionette.CollectionView.extend({
		//tagName : 'ul',
		//className : 'dropdown-menu',
		initialize : function() {
			this.collection = this.options.app.getMDRModelLocator().getContextCollection();
		},
		itemView : ContextListItemView,
		buildItemView : function(item, ItemViewType, itemViewOptions) {
			var options = {
				model : item,
				app : this.options.app,
				collection : this
			};
			var view = new ItemViewType(options);
			return view;
		}
	});
	return ContextListView;
});
