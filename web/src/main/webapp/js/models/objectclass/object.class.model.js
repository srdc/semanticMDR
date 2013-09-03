define(['backbone', 'underscore', 'js/models/administered.item.model.js'], function(Backbone, _, AdministeredItemModel) {
	var ObjectClassModel = AdministeredItemModel.extend({
		defaults : {
			parentConceptID: ""
		},
		url : function() {

			if (this.get('contextID')) {
				return URL + 'context/' + this.get('contextID') + '/oc';
			} else {
				return URL + 'oc/' + this.get('id');
			}
		}
	});
	_.extend(ObjectClassModel.prototype.defaults, AdministeredItemModel.prototype.defaults);
	return ObjectClassModel;
});
