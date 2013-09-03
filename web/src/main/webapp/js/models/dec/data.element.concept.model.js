define(['backbone', 'underscore', 'js/models/administered.item.model.js'], function(Backbone, _, AdministeredItemModel) {
	var DataElementConceptModel = AdministeredItemModel.extend({
		defaults : {
			propertyName : '',
			objectClassName : '',
			propertyID : '',
			objectClassID : '',
			objectClassQualifier : '',
			propertyQualifier : '',
			conceptualDomainID : '',
			propertyDefinition : ''
		},
		url : function() {

			if (this.get('objectClassID')) {
				return URL + 'oc/' + this.get('objectClassID') + '/dec';
			} else {
				return URL + 'dec/' + this.get('id');
			}

		}
	});
	_.extend(DataElementConceptModel.prototype.defaults, AdministeredItemModel.prototype.defaults);
	return DataElementConceptModel;
});
