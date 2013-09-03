define(['backbone', 'relational', 'underscore', 'js/models/administered.item.model.js', 'js/models/datatype/data.type.model', 'js/models/permissiblevalue/permissible.value.model', 'js/models/permissiblevalue/permissible.value.collection'], function(Backbone, Relational, _, AdministeredItemModel, DataTypeModel, PermissibleValueModel, PermissibleValueCollection) {
	var ValueDomainModel = AdministeredItemModel.extend({
		defaults : {
			dataType : '',
			conceptualDomainID : '',
			permissibleValues : [],
			enumerated : ''
		},
		relations : [{
			type : Backbone.HasOne,
			relatedModel : DataTypeModel,
			key : 'dataType'
		}, {
			type : Backbone.HasMany,
			relatedModel : PermissibleValueModel,
			key : 'permissibleValues',
			collectionType : PermissibleValueCollection
		}],
		urlRoot : function() {
			return URL + 'vd';
		}
	});
	_.extend(ValueDomainModel.prototype.defaults, AdministeredItemModel.prototype.defaults);
	return ValueDomainModel;
});
