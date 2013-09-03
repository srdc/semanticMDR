define(['jquery', 'backbone', 'marionette'], function($, Backbone, Marionette) {
	var ContentRegion = Marionette.Region.extend({
		el : '#content'
	});

	ContentRegion.prototype.open = function(view) {
		if (isBack) {
			this.$el.html(view.el);
			var width = this.$el.outerWidth();
			this.$el.css('margin-left', '-100%');
			this.$el.animate({
				marginLeft : 0
			});
		} else {
			this.$el.html(view.el);
			var width = this.$el.outerWidth();
			this.$el.css('margin-left', width)
			this.$el.animate({
				marginLeft : 0
			});
		}
		isBack = false;
	}

	return ContentRegion;
});