require.config({
	baseUrl : '',
	paths : {
		jquery : 'js/libs/jquery/jquery-1.9.0',
		jquery_cookie : 'js/libs/jquery/jquery.cookie',
		underscore : 'js/libs/underscore/underscore',
		backbone : 'js/libs/backbone/backbone',
		relational : 'js/libs/backbone/backbone-relational',
		marionette : 'js/libs/backbone/backbone.marionette',
		text : 'js/libs/require/text',
		bootstrap : 'js/libs/bootstrap/bootstrap',
		bootbox : 'js/libs/bootstrap/bootbox',
		typeahead : 'js/libs/bootstrap/bootstrap-better-typeahead',
		paginator : 'js/libs/backbone/backbone.paginator'
	},

	shim : {
		'jquery' : {
			exports : '$'
		},
		'jquery_cookie' : {
			deps : ['jquery'],
			exports : '$'
		},
		'underscore' : {
			deps : ['jquery'],
			exports : '_'
		},
		'backbone' : {
			deps : ['underscore', 'jquery'],
			exports : 'Backbone'
		},
		'marionette' : {
			deps : ['backbone'],
			exports : 'Backbone.Marionette'
		},
		'relational' : {
			deps : ['backbone', 'underscore']
		},
		'bootstrap' : {
			deps : ['jquery'],
			exports : '$'
		},
		'bootbox' : {
			deps : ['jquery', 'bootstrap'],
			exports : 'bootbox'
		},
		'typeahead' : {
			deps : ['jquery', 'bootstrap']
		},
		'paginator' : {
			deps : ['backbone'],
			exports : 'Backbone.Paginator'
		}
	}
});

// Global Variables
// instead of http://localhost:8080/rest so services could be call from other resources
var URL = 'html/';
var URLRest = 'rest/';
var URLDex = 'dex/dex/';
var isBack = false;

var mimeTypes = {};
mimeTypes['N3'] = 'text/n3';
mimeTypes['N-TRIPLE'] = 'application/n-triple';
mimeTypes['RDF/XML'] = 'application/rdf+xml';
mimeTypes['RDF/JSON'] = 'application/rdf+json';
mimeTypes['RDF/XML-ABBREV'] = 'application/rdf+xml-abbrev';
mimeTypes['TURTLE'] = 'text/turtle';
mimeTypes['TTL'] = 'text/ttl';

var xml2string = function(e) {
	if ( typeof (XMLSerializer) !== 'undefined') {
		var serializer = new XMLSerializer();
		return serializer.serializeToString(e);
	} else if (e.xml) {
		return e.xml;
	}
}
/////////////////////////////////////////////

require(['js/mdr/mdr.application', 'jquery', 'underscore', 'backbone', 'marionette', 'bootstrap', 'typeahead'], function(MDRApplication) {
	MDRApplication.start();
});
