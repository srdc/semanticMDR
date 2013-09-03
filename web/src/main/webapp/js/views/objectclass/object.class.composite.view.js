define(['jquery', 'backbone', 'marionette', 'bootstrap', 'js/views/objectclass/object.class.item.view', 'text!templates/objectclass/object.class.template.html', 'js/views/objectclass/object.class.modal.view', 'js/views/paginator/paginator.composite.view'], function($, Backbone, Marionette, BootStrap, ObjectClassItemView, ObjectClassTemplate, ObjectClassModalView, PaginatorCompositeView) {
	var ObjectClassCompositeView = Marionette.CompositeView.extend({
		template : ObjectClassTemplate,
		itemView : ObjectClassItemView,
		itemViewContainer : 'tbody',
		initialize : function() {
			var locator = this.options.app.getMDRModelLocator();
			this.collection = locator.getObjectClassCollection(this.options.offset);

			this.paginator = new PaginatorCompositeView({
				paginatedCollection : this.collection,
				layer : this
			});
			this.paginator.render();
			var context = this.options.app.getMDRModelLocator().getCurrentContext();
			this.options.app.vent.trigger('breadcrumb:change', {
				'contextID' : context.get('id'),
				'contextName' : context.get('name')
			});
		},
		paginator : null,
		ui : {
			paginatorBar : '#paginatorBar',
			contextName : '#contextName'
		},
		events : {
			'click #addObjectClass' : 'add'
		},
		add : function() {
			this.options.app.vent.trigger('modalWindow:show', new ObjectClassModalView({
				app : this.options.app,
			}));
		},
		collectionEvents : {
			"add" : "modelAdded"
		},
		modelAdded : function() {
			this.onRender();
		},
		buildItemView : function(item, ItemViewType, itemViewOptions) {
			var self = this;
			var options = {
				model : item,
				app : self.options.app
			};
			var view = new ItemViewType(options);
			return view;
		},
		onRender : function() {
			if (this.collection.length == 0) {
				this.$('div .alert').show();
				this.$('div .table').hide();
				//this.$('div table').parent().html('<div class="alert alert-error">No Object Class Added Yet</div>');
			} else {
				this.$('div .alert').hide();
				this.$('div .table').show();
			}
			if (this.collection.totalPages > 1) {
				this.ui.paginatorBar.append(this.paginator.el);
				this.paginator.delegateEvents();
			}
			var context = this.options.app.getMDRModelLocator().getCurrentContext();
			this.ui.contextName.html(context.get('name'));
		},
		navigateHistory : function(offset) {
			var contextID = this.options.app.getMDRModelLocator().getCurrentContext().id;
			Backbone.history.navigate('context/' + contextID + '/oc?offset=' + offset);
		}
	});
	return ObjectClassCompositeView;
});
