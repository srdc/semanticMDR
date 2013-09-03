define(['marionette', 'js/views/permissiblevalue/permissible.value.item.view', 'text!templates/permissiblevalue/permissible.value.template.html'], function(Marionette, PermissibleValueItemTemplate, PermissibleValueTemplate) {
	var PermissibleValueCompositeView = Marionette.CompositeView.extend({
		template : PermissibleValueTemplate,
		itemView : PermissibleValueItemTemplate,
		itemViewContainer : 'tbody',
		buildItemView : function(item, ItemViewType, itemViewOptions) {
			var options = {
				model : item,
				vent : this.options.vent,
				collection : this
			};
			var view = new ItemViewType(options);
			return view;
		},
		fetchForm : function() {
			this.children.each(function(view) {
				view.setModel();
			});
			return this.collection;
		}
	});
	return PermissibleValueCompositeView;
});
