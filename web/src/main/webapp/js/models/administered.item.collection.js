define(['backbone', 'js/models/context/context.model', 'js/models/objectclass/object.class.model', 'js/models/conceptualdomain/conceptual.domain.model', 'js/models/valuedomain/value.domain.model', 'js/models/dec/data.element.concept.model', 'js/models/dataelement/data.element.model'], function(Backbone, ContextModel, ObjectClassModel, ConceptualDomainModel, ValueDomainModel, DataElementConceptModel, DataElementModel) {
	var AdministeredItemCollection = Backbone.Collection.extend({
		initialize : function(models, options) {
			this.options = options
			this.model = options.model;
			if (options && options.contextID) {
				this.contextID = options.contextID;
			}
			if (options.searchURL) {
				this.searchURL = options.searchURL;
			}
		},
		url : function() {
			if (this.options.url) {
				return URL + this.options.url;
			} else if (this.model == ObjectClassModel) {
				return URL + "context/" + this.contextID + "/oc";
			} else if (this.model == ContextModel) {
				return URL + 'repository';
			} else if (this.model == ConceptualDomainModel) {
				return URL + "repository/cd";
			} else if (this.model == DataElementConceptModel) {
				return URL + "oc/" + this.options.objectClassID + "/dec";
			} else if (this.model == DataElementModel) {
				// if some searchURL is given with DataElementModel
				if (this.searchURL) {
					return URL + this.searchURL;
				} else if (this.options.dataElementConceptID) {
					return URL + "dec/" + this.options.dataElementConceptID + "/de";
				} else if (this.options.contextID) {
					return URL + "context/" + this.options.contextID + "/de";
				}
			} else if (this.model == ValueDomainModel) {
				return URL + "cd/" + this.options.conceptualDomainID + "/vd";
			}
		}
	});
	return AdministeredItemCollection;
});
