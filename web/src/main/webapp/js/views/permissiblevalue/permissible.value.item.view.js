define(['jquery', 'backbone', 'marionette', 'bootstrap', 'text!templates/permissiblevalue/permissible.value.item.template.html'], function($, Backbone, Marionette, BootStrap, PermissibleValueItemTemplate) {
	var PermissibleValueItemView = Marionette.ItemView.extend({
		template : PermissibleValueItemTemplate,
		tagName : 'tr',
		ui : {
			value : '#value'
		},
		setModel : function() {
			this.model.set('valueItem', this.ui.value.val());
		}
	});
	return PermissibleValueItemView;
});
