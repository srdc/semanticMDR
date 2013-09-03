define(['backbone', 'jquery', 'paginator'], function(Backbone, $, Paginator) {
	var PaginatedCollection = Backbone.Paginator.requestPager.extend({
		initialize : function(models, options) {
			this.paginator_core.url = options.url;
			// || options.offset === 0 is added to condition. Because when offset is 0, it evaluates to 0
			if (options.offset || options.offset === 0) {
				this.paginator_ui.currentPage = Math.floor(options.offset / this.paginator_ui.perPage);
			}
		},
		paginator_core : {
			dataType : 'json',
		},
		getCurrentOffset : function() {
			return this.currentPage * this.perPage;
		},
		paginator_ui : {
			// the lowest page index your API allows to be accessed
			firstPage : 0,

			// which page should the paginator start from
			// (also, the actual page the paginator is on)
			currentPage : 0,

			// how many items per page should be shown
			perPage : 10,

			totalPages : 0
		},
		server_api : {
			'limit' : function() {
				return this.perPage;
			},
			'offset' : function() {
				return this.currentPage * this.perPage;
			},
		}
	});
	return PaginatedCollection;
});
