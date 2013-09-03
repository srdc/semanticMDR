define(['jquery', 'backbone', 'marionette'], function($, Backbone, Marionette) {
	var app;
	var MDRRouter = Marionette.AppRouter.extend({
		initialize : function(options) {
			this.options = options;
		},
		routes : {
			"" : "index",
			"context/:contextID/oc" : "openContext",
			"context/:contextID/oc?offset=:offset" : "openContext",
			"oc/:objectClassID/dec" : "openObjectClass",
			"oc/:objectClassID/dec?offset=:offset" : "openObjectClass",
			"search" : "searchHomePage",
			"query" : "queryHomePage",
			"cd/list?offset=:offset" : "listConceptualDomains",
			"cd/:conceptualDomainID/vm" : "listValueMeanings",
			"search" : "search",
			"search/:keyword(/:offset)" : "search",
			"context/:contextID/de" : "listDataElementsOfContext",
			"context/:contextID/de?offset=:offset" : "listDataElementsOfContext",
			"dec/:dataElementConceptID/de" : "listDataElementsOfDEC",
			"de/:dataElementID" : "showDetailedDataElementOfDEC",
			"*path" : "noRoute"
		},
		// index page of MDR
		index : function() {
			this.options.app.vent.trigger('menu:browse', null, 0);
		},

		// imitates click on a Context, shows its ObjectClass'es
		openContext : function(contextID, offset) {
			this.options.app.vent.trigger('menu:browse', contextID, offset);
		},

		// imitates click on an ObjcetClss, shows DataElementConcepts derived from it
		openObjectClass : function(objectClassID, offset) {
			this.options.app.vent.trigger('dataElementConcept:list', objectClassID, offset);
		},

		// index page of search
		searchHomePage : function() {
			this.options.app.vent.trigger('menu:search');
		},

		// index page of query
		queryHomePage : function() {
			this.options.app.vent.trigger('menu:query');
		},

		// lists conceptualDomains on the Repository, independent from the Context
		listConceptualDomains : function(offset) {
			this.options.app.vent.trigger("conceptualDomain:list", offset);
		},

		// imitates click on a ConceotualDomain, if enumerated lists its ValueMeanings
		listValueMeanings : function(conceptualDomainID) {
			this.options.app.vent.trigger('valueMeaning:list', conceptualDomainID);
		},

		// search result of keyword in current Context
		search : function(keyword, offset) {
			if (!keyword) {
				this.options.app.vent.trigger('menu:search');
			} else if (offset) {
				this.options.app.vent.trigger('dataElement:search', keyword, offset);
			}
		},
		
		// click on a browse/DataElements, lists DataElements on Context with given ID
		listDataElementsOfContext : function(contextID, offset) {
			this.options.app.vent.trigger('dataElement:list', null, contextID, offset);
		},

		// click on a DataElementConcept, show DataElement's with DEC with given ID
		listDataElementsOfDEC : function(dataElementConceptID) {
			this.options.app.vent.trigger('dataElement:list', dataElementConceptID);
		},
		showDetailedDataElementOfDEC : function(dataElementID) {
			this.options.app.vent.trigger('dataElement:detailed',dataElementID);
		},
		noRoute : function() {
			console.log("Not a valid URL");
			this.index();
		}
	});

	return MDRRouter;
});
