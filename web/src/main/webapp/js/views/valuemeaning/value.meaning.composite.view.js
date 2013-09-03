define(['jquery', 'backbone', 'marionette', 'bootstrap', 'js/views/valuemeaning/value.meaning.item.view', 'text!templates/valuemeaning/value.meaning.template.html', 'js/models/valuemeaning/value.meaning.model', 'js/views/paginator/paginator.composite.view'], function($, Backbone, Marionette, BootStrap, ValueMeaningItemView, ValueMeaningTemplate, ValueMeaningModel, PaginatorCompositeView) {
	var ValueMeaningCompositeView = Marionette.CompositeView.extend({
		template : ValueMeaningTemplate,
		itemView : ValueMeaningItemView,
		itemViewContainer : 'tbody',
		initialize : function() {
			var self = this;
			var locator = this.options.app.getMDRModelLocator();
			this.collection = locator.getValueMeaningCollection(this.options.conceptualDomainID);
			this.model = this.options.app.getMDRModelLocator().getConceptualDomain(this.options.conceptualDomainID);

			this.paginator = new PaginatorCompositeView({
				paginatedCollection : this.collection,
			});
			this.paginator.render();

			// now we will set the breadcrumb with conceptualDomain
			var conceptualDomain = locator.getConceptualDomain(this.options.conceptualDomainID);
			this.options.app.vent.trigger('breadcrumb:change', {
				'conceptualDomainID' : conceptualDomain.get('id'),
				'conceptualDomainName' : conceptualDomain.get('name')
			});
		},
		paginator : null,
		ui : {
			// Model inputs
			id : '#valueMeaningID',
			description : '#description',

			conceptualDomainName : 'legend',
			paginatorBar : '#paginatorBar'
		},
		events : {
			'click #addValueMeaning' : 'add'
		},
		fetchNewAttributes : function() {
			newAttributes = {};

			newAttributes['id'] = this.ui.id.val();
			newAttributes['description'] = this.ui.description.val();
			newAttributes['conceptualDomainID'] = this.options.conceptualDomainID;
			return newAttributes;
		},

		add : function() {
			var self = this;
			var newValueMeaning = new ValueMeaningModel();

			newValueMeaning.save(this.fetchNewAttributes(), {
				success : function(model, response) {
					self.collection.add(model);
					self.ui.id.val('');
					self.ui.description.val('');
				},
				error : function(error) {
					alert("Nope");
				},
				type : 'POST'
			});
		},
		buildItemView : function(item, ItemViewType, itemViewOptions) {
			var options = {
				model : item,
				collection : this.collection,
				conceptualDomainID : this.model.get('id'),
				app : this.options.app
			};
			var view = new ItemViewType(options);
			return view;
		},
		onRender : function() {
			this.ui.conceptualDomainName.html(this.model.get('name') + '\'s Value Meanings');
			if (this.collection.totalPages > 1) {
				this.ui.paginatorBar.append(this.paginator.el);
				this.paginator.delegateEvents();
			}
		}
	});
	return ValueMeaningCompositeView;
});
