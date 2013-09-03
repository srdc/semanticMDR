define(['jquery', 'backbone', 'marionette', 'bootstrap', 'text!templates/valuemeaning/value.meaning.item.view.template.html'], function($, Backbone, Marionette, BootStrap, ValueMeaningItemViewTemplate) {
	var ValueMeaningtItemView = Marionette.ItemView.extend({
		template : ValueMeaningItemViewTemplate,
		tagName : 'tr',
		ui : {
			valueDomainID : '.valueDomainID',
			valueDomainDescription : '.valueDomainDescription'
		},
		events : {
			'click .icon-remove' : 'deleteValueDomain'
		},
		deleteValueDomain : function() {
			this.model.destroy({
				wait : true,
				dataType : 'text',
				headers : {
					'Accept' : 'application/json',
					'Content-Type' : 'application/json'
				},
				data : JSON.stringify(this.model)
			});
		}
	});
	return ValueMeaningtItemView;
});
