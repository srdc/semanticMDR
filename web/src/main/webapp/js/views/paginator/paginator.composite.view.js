define(['jquery', 'backbone', 'marionette', 'bootstrap', 'js/views/paginator/paginator.item.view', 'text!templates/paginator/paginator.template.html', 'js/models/paginator/paginator.collection'], function($, Backbone, Marionette, BootStrap, PaginatorItemView, PaginatorTemplate, PaginatorCollection) {
	var PaginatorCompositeView = Marionette.CompositeView.extend({
		template : PaginatorTemplate,
		itemView : PaginatorItemView,
		itemViewContainer : 'ul',
		initialize : function(options) {
			this.collection = new PaginatorCollection();
			this.first = 1;
			for (var i = 0; i < this.options.paginatedCollection.totalPages && i < 10; i++) {
				this.collection.add({
					pageNumber : i + 1,
					layer : this.options.layer
				})
			}
			this.last = i;
		},
		events : {
			'click #prev' : 'prev',
			'click #next' : 'next'
		},
		ui : {
			prev : '#prev',
			next : '#next'
		},
		prev : function(e) {
			e.preventDefault();
			if (this.options.paginatedCollection.currentPage != 0) {
				var self = this;
				this.options.paginatedCollection.requestPreviousPage({
					update : true,
					remove : true
				}).done(function(data, textStatus, jqXHR) {
					self.navigateHistory(data, textStatus, jqXHR);
				});
				this.setButtonEffects();
			}
		},
		next : function(e) {
			e.preventDefault();
			if (this.options.paginatedCollection.totalPages - 1 > this.options.paginatedCollection.currentPage) {
				var self = this;
				this.options.paginatedCollection.requestNextPage().done(function(data, textStatus, jqXHR) {
					self.navigateHistory(data, textStatus, jqXHR);
				});
				this.setButtonEffects();
			}
		},
		onRender : function() {
			if (this.options.paginatedCollection.currentPage == 0) {
				this.ui.prev.addClass('disabled');
			} else {
				this.ui.prev.removeClass('disabled');
			}

			if (this.options.paginatedCollection.totalPages - 1 == this.options.paginatedCollection.currentPage) {
				this.ui.next.addClass('disabled');
			} else {
				this.ui.next.removeClass('disabled');
			}
			var self = this;
			this.children.each(function(view) {
				if (view.model.get('pageNumber') == self.options.paginatedCollection.currentPage + 1) {
					view.$el.addClass('active');
				} else {
					view.$el.removeClass('active');
				}
			});
		},
		appendHtml : function(collectionView, itemView, index) {
			collectionView.$('ul li:last-child').before(itemView.el);
		},
		buildItemView : function(item, ItemViewType, itemViewOptions) {
			var options = {
				model : item,
				paginatedCollection : this.options.paginatedCollection,
				paginator : this
			};
			var view = new ItemViewType(options);
			return view;
		},
		setButtonEffects : function() {
			if (this.options.paginatedCollection.currentPage == 0) {
				this.ui.prev.addClass('disabled');
			} else {
				this.ui.prev.removeClass('disabled');
			}

			if (this.options.paginatedCollection.totalPages - 1 == this.options.paginatedCollection.currentPage) {
				this.ui.next.addClass('disabled');
			} else {
				this.ui.next.removeClass('disabled');
			}

			var self = this;
			var shiftAmount = self.options.paginatedCollection.currentPage + 1 - self.first - 5;
			self.shiftPaginatorItemCollection(shiftAmount);

			this.children.each(function(view) {
				if (view.model.get('pageNumber') == self.options.paginatedCollection.currentPage + 1) {
					view.$el.addClass('active');
				} else {
					view.$el.removeClass('active');
				}
			});
		},
		// with this function, collection of this composite view is shifted according to the page we are in
		// all the time 10 elements are shown, until 7 is clicked 1-10 is shown, 7 clicked 2-11 is shown etc.
		shiftPaginatorItemCollection : function(shiftAmount) {
			var self = this;
			var current = self.options.paginatedCollection.currentPage + 1;
			var total = this.options.paginatedCollection.totalPages

			if (shiftAmount > 0) {
				if (total - current < 4) {
					shiftAmount = total - self.last;
				}
			} else {
				if (current < 6) {
					shiftAmount = -self.first + 1;
				}
			}
			self.first = self.first + shiftAmount;
			self.last = self.last + shiftAmount;
			self.collection.reset();
			self.collection.pop();
			for (var i = 0; i < 10 && self.first + i <= self.last; i++) {
				self.collection.add({
					pageNumber : self.first + i,
					layer : self.options.layer
				});
			}

		},
		navigateHistory : function(data, textStatus, jqXHR) {
			this.options.layer.navigateHistory(this.options.paginatedCollection.getCurrentOffset());
		}
	});
	return PaginatorCompositeView;
});
