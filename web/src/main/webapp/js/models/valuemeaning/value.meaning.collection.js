define(['backbone', 'js/models/valuemeaning/value.meaning.model'], function(Backbone, ValueMeaningModel) {
	var ValueMeaningCollection = Backbone.Collection.extend({
		initialize : function(models, options) {
			this.options = options;
			this.model = ValueMeaningModel;
		},
		url : function() {
			return URL + "cd/" + this.options.conceptualDomainID + "/vm";
		}
	});
	return ValueMeaningCollection;
});
