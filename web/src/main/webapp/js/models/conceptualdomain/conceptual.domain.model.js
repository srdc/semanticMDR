define(['backbone', 'underscore', 'js/models/administered.item.model.js'], function(Backbone, _, AdministeredItemModel) {
	var ConceptualDomainModel = AdministeredItemModel.extend({
		defaults : {
			dimensionality : "",
			enumerated : ""
		},

		url : function() {
			if (this.get('method') == 'get') {
				return URL + 'cd/' + this.get('id');
			} else {
				return URL + "repository/cd";
			}
		}
	});
	_.extend(ConceptualDomainModel.prototype.defaults, AdministeredItemModel.prototype.defaults);
	return ConceptualDomainModel;
});
