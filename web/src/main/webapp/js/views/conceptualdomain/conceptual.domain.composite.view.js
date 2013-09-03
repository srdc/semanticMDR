define(['jquery', 'backbone', 'marionette', 'bootstrap', 'js/views/conceptualdomain/conceptual.domain.item.view', 'text!templates/conceptualdomain/conceptual.domain.template.html', 'js/views/conceptualdomain/conceptual.domain.modal.view', 'js/views/paginator/paginator.composite.view'], function($, Backbone, Marionette, BootStrap, ConceptualDomainItemView, ConceptualDomainTemplate, ConceptualDomainModalView, PaginatorCompositeView) {
	var ConceptualDomainCompositeView = Marionette.CompositeView.extend({
		template : ConceptualDomainTemplate,
		itemView : ConceptualDomainItemView,
		itemViewContainer : 'tbody',
		initialize : function() {
			this.collection = this.options.app.getMDRModelLocator().getConceptualDomainCollection(this.options.offset);
			this.paginator = new PaginatorCompositeView({
				paginatedCollection : this.collection,
				layer : this
			});
			this.paginator.render();
			var self = this;
			this.options.app.vent.trigger('breadcrumb:change', {
				location : 'conceptualDomainHome'
			});
		},
		paginator : null,
		ui : {
			paginatorBar : '#paginatorBar'
		},
		collectionEvents : {
			"add" : "modelAdded"
		},
		events : {
			'click #addConceptualDomain' : 'add'
		},
		add : function() {
			this.options.app.vent.trigger('modalWindow:show', new ConceptualDomainModalView({
				app : this.options.app,
			}));
		},
		modelAdded : function(){
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
		},
		navigateHistory : function(offset) {
			Backbone.history.navigate('cd/list?offset=' + offset);
		}
	});
	return ConceptualDomainCompositeView;
});
