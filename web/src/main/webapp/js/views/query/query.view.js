define(['jquery', 'backbone', 'marionette', 'text!templates/query/query.template.html', 'js/models/administered.item.collection', 'js/models/dataelement/data.element.model'], function($, Backbone, Marionette, QueryTemplate, AdministeredItemCollection, DataElementModel) {

	var QueryView = Marionette.ItemView.extend({
		initialize : function() {
			this.locator = this.options.app.getMDRModelLocator();
			this.options.app.vent.trigger('breadcrumb:change', {
				'location' : 'query'
			});
		},
		template : QueryTemplate,
		events : {
			'click button' : 'query'
		},
		onRender: function() {
			var prefixes = 'PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n';
			prefixes += 'PREFIX owl: <http://www.w3.org/2002/07/owl#>\n';
			prefixes += 'PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n';
			prefixes += 'PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n';
			prefixes += 'PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n';
			prefixes += 'PREFIX schema: <http://schema.org/>\n';
			prefixes += 'PREFIX skos:<http://www.w3.org/2004/02/skos/core#>\n';
			prefixes += 'PREFIX dcterms:<http://purl.org/dc/terms/>\n';
			prefixes += 'PREFIX dc:<http://purl.org/dc/elements/1.1/>\n';
			prefixes += 'PREFIX mdr:<http://www.salusproject.eu/iso11179-3/mdr#>\n';
			prefixes += 'PREFIX dex:<urn:ihe:qrph:dex:2013#>\n'; 
			this.$('#qinput').val(prefixes);
		},
		query : function() {
			var app = this.options.app;
			var queryText = this.$('#qinput').val();
			var lang = this.$('#lang').val();
				
			if (!queryText) {
				//empty query text
			} else {
				$('#loadingOverlay').fadeIn();
				var self = this;
				$.ajax({
					type : 'PUT',
					url : URLRest + 'sparql',
					headers : {
						"Accept" : mimeTypes[lang], 
					},
					data : queryText,
					success : function(response) {
						if(typeof response == "object") {
							response = xml2string(response);
						}
						$('#loadingOverlay').fadeOut();
						app.vent.trigger('menu:queryresult', response);
					},
					error : function(error) {
						$('#loadingOverlay').fadeOut();
						app.vent.trigger('menu:queryresult', error.statusText);
					}
				});
			}
		},
	});

	return QueryView;
});
