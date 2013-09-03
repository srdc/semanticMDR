define(['jquery', 'backbone', 'marionette', 'bootstrap', 'text!templates/dec/data.element.concept.item.view.template.html', 'js/views/dec/data.element.concept.modal.view', 'js/views/dataelement/data.element.modal.view'], function($, Backbone, Marionette, BootStrap, DataElementConceptItemViewTemplate, DataElementConceptModalView, DataElementModalView) {
	var DataElementConceptItemView = Marionette.ItemView.extend({
		template : DataElementConceptItemViewTemplate,
		tagName : "tr",
		events : {
			'click .icon-edit' : 'editDECClick',
			'dblclick' : 'showDetailedDataElement',
			'click .icon-chevron-right' : 'showDetailedDataElement'
		},
		modelEvents : {
			"change" : 'render'
		},
		editDECClick : function(e) {
			e.stopPropagation();
			// This function is called when an edit button of an data element concept is clicked
			// model of this view is DataElementConcept to be updated, ModalWindow with this.model rendered
			var self = this;
			self.options.app.vent.trigger('modalWindow:show', new DataElementConceptModalView({
				model : self.model,
				app : self.options.app
			}));
		},
		showDetailedDataElement : function() {
			var self = this;
			var locator = this.options.app.getMDRModelLocator();
			var de = locator.getDataElementCollectionOfDEC(this.model.get("id")).at(0);
			if (de) {
				this.options.app.vent.trigger('dataElement:detailed', de.get("id"));
			} else {
				alert('No Data Element to detail, first add one');
				this.options.app.vent.trigger('modalWindow:show', new DataElementModalView({
					conceptualDomainID : self.model.get('conceptualDomainID'),
					dataElementConceptID : self.model.get('id'),
					app : self.options.app
				}));
			}
		}
	});
	return DataElementConceptItemView;
});
