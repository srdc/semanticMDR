define(['jquery', 'underscore', 'backbone', 'marionette', 'js/models/auth/login.model', 'js/models/auth/user.model', 'js/models/administered.item.collection', 'js/models/context/context.model', 'js/models/objectclass/object.class.model', 'js/models/property/property.model', 'js/models/conceptualdomain/conceptual.domain.model', 'js/models/valuedomain/value.domain.model', 'js/models/dec/data.element.concept.model', 'js/models/dataelement/data.element.model', 'js/models/paginated.collection', 'js/models/valuemeaning/value.meaning.model', 'js/models/datatype/data.type.collection'], function($, _, Backbone, Marionette, LoginModel, UserModel, AdministeredItemCollection, ContextModel, ObjectClassModel, PropertyModel, ConceptualDomainModel, ValueDomainModel, DataElementConceptModel, DataElementModel, PaginatedCollection, ValueMeaningModel, DataTypeCollection) {

	var MDRModelLocator = function(app) {

		var loginModel = new LoginModel();
		var userModel = new UserModel();
		var currentContextID = null;

		var contextList = null;
		var objectClassPaginatedCollection = null;
		var subConceptCollection = null;
		var conceptualDomainPaginatedCollection = null;
		var dataElementConceptPaginatedCollection = null;
		var dataElementPaginatedCollection = null;
		var valueMeaningPaginatedCollection = null;

		var collectionURLs = {
			'ObjectClass' : function(contextID) {
				return URL + 'context/' + contextID + '/oc';
			},
			'ValueMeaning' : function(conceptualDomainID) {
				return URL + "cd/" + conceptualDomainID + "/vm";
			},
			'DataElementConcept' : function(objectClassID) {
				return URL + "oc/" + objectClassID + "/dec";
			},
			'DataElement' : function(contextID) {
				return URL + "context/" + contextID + "/de"
			},
			'ConceptualDomain' : function() {
				return URL + 'repository/cd';
			}
		}

		var dataTypeList = null;

		return {
			getLoginModel : function() {
				return loginModel;
			},
			getUserModel : function() {
				return userModel;
			},
			getContextCollection : function() {
				if (contextList == null) {
					contextList = new AdministeredItemCollection([], {
						model : ContextModel
					});
					contextList.fetch({
						async : false
					});
				}
				return contextList;
			},
			setCurrentContext : function(contextID) {
				if (contextID != currentContextID) {
					currentContextID = contextID;
					// every time currentContext is set, this event is triggered so that
					// chosen context displayed on the menu will change
					app.vent.trigger('menu:contextChange', contextID);
				}
			},

			getCurrentContext : function() {
				if (!contextList) {
					return;
				}
				return contextList.get(currentContextID);
			},

			// returns ObjectClass with given ID, if not exist in cache, fetches from server
			getObjectClass : function(objectClassID) {
				// TODO always gets from rest api, the old version getting from cache is commented
				objectClass = new ObjectClassModel({
					id : objectClassID
				});
				objectClass.fetch({
					url : URL + 'oc/' + objectClassID,
					async : false
				});
				return objectClass;

				//var objectClass = null;
				//_.each(objectClassListMap, function(ocList) {
				// var oc = ocList.get(objectClassID);
				// if (oc) {
				// objectClass = oc;
				// }
				// });
				//
				// // TODO ObjectClass could be fetched from server
				// return objectClass;
			},

			getObjectClassCollection : function(offset) {
				if (arguments.length == 0 && objectClassPaginatedCollection) {
					return objectClassPaginatedCollection;
				}
				objectClassPaginatedCollection = new PaginatedCollection([], {
					model : ObjectClassModel,
					url : collectionURLs.ObjectClass(currentContextID),
					offset : offset
				});
				objectClassPaginatedCollection.fetch({
					async : false
				});

				this.fetchTotalPages(collectionURLs.ObjectClass(currentContextID), objectClassPaginatedCollection);

				return objectClassPaginatedCollection;
			},
			// returns the collection of ObjectCLlasses which are subConpcets of given object Class
			getSubConceptCollection : function(objectClassID) {
				if (arguments.length == 0 && subConceptCollection) {
					return subConceptCollection;
				}
				if (!objectClassID) {
					return null;
				}
				subConceptCollection = new AdministeredItemCollection([], {
					model : ObjectClassModel,
					url : URL + 'oc/' + objectClassID + '/subconcept'
				});
				subConceptCollection.fetch({
					async : false
				});
				return subConceptCollection;
			},
			getConceptualDomain : function(conceptualDomainID) {
				var conceptualDomain = new ConceptualDomainModel({
					id : conceptualDomainID
				});
				conceptualDomain.fetch({
					url : URL + 'cd/' + conceptualDomainID,
					async : false
				})
				return conceptualDomain;
			},

			getConceptualDomainCollection : function(offset) {

				if (arguments.length == 0 && conceptualDomainPaginatedCollection) {
					return conceptualDomainPaginatedCollection;
				}

				conceptualDomainPaginatedCollection = new PaginatedCollection([], {
					model : ConceptualDomainModel,
					url : collectionURLs.ConceptualDomain(),
					offset : offset
				});
				conceptualDomainPaginatedCollection.fetch({
					async : false
				});

				this.fetchTotalPages(collectionURLs.ConceptualDomain(), conceptualDomainPaginatedCollection);

				return conceptualDomainPaginatedCollection;
			},

			// @return a DataElementConcept with given ID if exist in cache
			getDataElementConcept : function(dataElementConceptID) {

				// TODO always gets from rest api, the old version getting from cache is commented
				dataElementConcept = new DataElementConceptModel({
					id : dataElementConceptID
				});
				dataElementConcept.fetch({
					url : URL + 'dec/' + dataElementConceptID,
					async : false
				});
				return dataElementConcept;

				// var dataElementConcept = null;
				// _.each(dataElementConceptListMap, function(decList) {
				// var dec = decList.get(dataElementConceptID);
				// if (dec) {
				// dataElementConcept = dec;
				// }
				// });
				//
				// // TODO DataElementConcept could be fetched from server
				// return dataElementConcept;
			},

			/**
			 * If collection with such id exists in the map, returns it, otherwise fetches from server
			 */
			getDataElementConceptCollection : function(objectClassID, offset) {
				if (arguments.length == 0 && dataElementConceptPaginatedCollection) {
					return dataElementConceptPaginatedCollection;
				}

				dataElementConceptPaginatedCollection = new PaginatedCollection([], {
					model : DataElementConceptModel,
					url : collectionURLs.DataElementConcept(objectClassID),
					objectClassID : objectClassID,
					offset : offset
				});
				dataElementConceptPaginatedCollection.fetch({
					async : false
				});

				this.fetchTotalPages(collectionURLs.DataElementConcept(objectClassID), dataElementConceptPaginatedCollection);

				return dataElementConceptPaginatedCollection;
			},

			/*
			 * Given a dataElementConceptID, this method returns a collection of DataElement's
			 */
			getDataElementCollectionOfDEC : function(dataElementConceptID) {
				var dataElementList = new AdministeredItemCollection([], {
					model : DataElementModel,
					dataElementConceptID : dataElementConceptID
				});
				dataElementList.fetch({
					async : false
				});
				return dataElementList;
			},
			/*
			 * Given a contextID, this method returns a collection of DataElement's
			 */
			getDataElementCollectionOfContext : function(contextID, offset) {
				if (arguments.length == 0 && dataElementPaginatedCollection) {
					return dataElementPaginatedCollection;
				}

				dataElementPaginatedCollection = new PaginatedCollection([], {
					model : DataElementModel,
					url : collectionURLs.DataElement(contextID),
					offset : offset
				});
				dataElementPaginatedCollection.fetch({
					async : false
				});

				this.fetchTotalPages(collectionURLs.DataElement(contextID), dataElementPaginatedCollection);
				return dataElementPaginatedCollection;
			},

			/*
			 * Given a conceptualDomainID, returns the collection of value domains which
			 * represent the given ConceptualDomain
			 */
			getValueDomainsofConceptualDomain : function(conceptualDomainID) {
				var valueDomainList = new AdministeredItemCollection([], {
					model : ValueDomainModel,
					conceptualDomainID : conceptualDomainID
				});
				valueDomainList.fetch({
					async : false
				});
				return valueDomainList;
			},

			/*
			 * Given a keyword and a offset, executes text search on repository for data elements and return the DataElementCollection
			 */
			searchDataElement : function(keyword, offset) {
				var searchURL = URL + 'repository/search?q=' + keyword;

				if (arguments.length == 0 && dataElementPaginatedCollection) {
					return dataElementPaginatedCollection;
				}

				dataElementPaginatedCollection = new PaginatedCollection([], {
					model : DataElementModel,
					url : searchURL,
					offset : offset
				});
				dataElementPaginatedCollection.fetch({
					complete : function() {
						$('#loadingOverlay').fadeOut();
					},
					async : false
				});

				this.fetchTotalPages(searchURL, dataElementPaginatedCollection);
				return dataElementPaginatedCollection;
			},

			getValueMeaningCollection : function(conceptualDomainID) {

				if (arguments.length == 0 && valueMeaningPaginatedCollection) {
					return valueMeaningPaginatedCollection;
				}

				valueMeaningPaginatedCollection = new PaginatedCollection([], {
					model : ValueMeaningModel,
					url : collectionURLs.ValueMeaning(conceptualDomainID)
				});
				valueMeaningPaginatedCollection.fetch({
					async : false
				});
				this.fetchTotalPages(collectionURLs.ValueMeaning(conceptualDomainID), valueMeaningPaginatedCollection);
				return valueMeaningPaginatedCollection;
			},

			fetchTotalPages : function(url, paginatedCollection) {
				$.ajax({
					url : url,
					dataType : 'text',
					async : false,
					success : function(response) {
						paginatedCollection.totalItems = response;
						paginatedCollection.totalPages = Math.ceil(response / paginatedCollection.perPage);
					}
				});
			},

			getDataTypes : function() {
				if (!dataTypeList) {
					dataTypeList = new DataTypeCollection();
					dataTypeList.fetch({
						async : false
					});
				}
				return dataTypeList;
			},
			searchProperty : function(contextID, keyword) {
				var searchURL = URL + 'context/' + contextID + '/property/search?q=' + keyword;
				var propertyCollection = new AdministeredItemCollection([], {
					model : PropertyModel,
					url : searchURL
				});
				propertyCollection.fetch({
					async : false
				});

				return propertyCollection;
			},
			searchValueDomain : function(contextID, keyword) {
				var searchURL = URL + 'context/' + contextID + '/vd/search?q=' + keyword;
				var valueDomainCollection = new AdministeredItemCollection([], {
					model : ValueDomainModel,
					url : searchURL
				});
				valueDomainCollection.fetch({
					async : false
				});

				return valueDomainCollection;
			},
			searchConceptualDomain : function(keyword) {
				var searchURL = URL + 'repository/cd/search?q=' + keyword;
				var conceptualDomainCollection = new AdministeredItemCollection([], {
					model : ConceptualDomainModel,
					url : searchURL
				});
				conceptualDomainCollection.fetch({
					async : false
				});

				return conceptualDomainCollection;
			},

			importModel : function(model) {
				$.ajax({
					type : 'POST',
					url : URL + 'importer/' + model,
					success : function(response) {
						$('#loadingOverlay').fadeOut();
						window.location.reload();
					},
					error : function(xhr, ajaxOptions, thrownError) {
						if (xhr.status == 455) {
							alert("Context already exists");
							return;
						} else {
							alert("Error at importing Context Model");
						}
						$('#loadingOverlay').fadeOut();
						window.location.reload();
					}
				});
			},

			exportAs : function(exportType) {
				window.location = URL + 'exporter/' + encodeURIComponent(mimeTypes[exportType]);
			}
		};
	};
	return MDRModelLocator;
});
