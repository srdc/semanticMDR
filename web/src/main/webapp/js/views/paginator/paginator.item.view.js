define(['jquery', 'backbone', 'marionette', 'bootstrap', 'text!templates/paginator/paginator.item.view.template.html'], function($, Backbone, Marionette, BootStrap, PaginatorItemViewTemplate) {
	var PaginatorItemView = Marionette.ItemView.extend({
		template : PaginatorItemViewTemplate,
		tagName : "li",
		events : {
			'click a' : 'goToPage'
		},
		goToPage : function(e) {
			e.preventDefault();
			if (this.options.paginatedCollection.currentPage != this.model.get('pageNumber') - 1) {
				var self = this;
				document.body.style.cursor = 'wait';
				this.options.paginatedCollection.goTo(this.model.get('pageNumber') - 1, {
					success : function() {
						self.options.paginator.navigateHistory(self.options.paginatedCollection.server_api.offset());
						self.options.paginator.setButtonEffects();
						document.body.style.cursor = 'default';
					}
				});
			}
		}
	});
	return PaginatorItemView;
});
