define(['jquery', 'backbone', 'marionette', 'js/regions/content.region.js', 'js/models/administered.item.collection', 'js/views/context/context.list.view', 'text!templates/app/main.layout.template.html', 'js/models/context/context.model', 'js/views/context/context.modal.view', 'js/models/objectclass/object.class.model', 'js/views/objectclass/object.class.composite.view', 'js/models/conceptualdomain/conceptual.domain.model', 'js/views/conceptualdomain/conceptual.domain.composite.view', 'js/views/dec/data.element.concept.composite.view', 'js/views/valuemeaning/value.meaning.composite.view', 'js/models/dataelement/data.element.model', 'js/views/dataelement/data.element.composite.view', 'js/views/dataelement/data.element.detailed.view', 'js/views/search/search.result.composite.view', 'js/views/search/search.view', 'js/views/search/search.result.item.view', 'text!templates/search/search.result.template.html', 'js/views/query/query.view', 'js/views/query/query.result.view', 'js/views/breadcrumb/breadcrumb.view'], function($, Backbone, Marionette, ContentRegion, AdministeredItemCollection, ContextListView, MainLayoutTemplate, ContextModel, ContextModalView, ObjectClassModel, ObjectClassCompositeView, ConceptualDomainModel, ConceptualDomainCompositeView, DataElementConceptCompositeView, ValueMeaningCompositeView, DataElementModel, DataElementCompositeView, DataElementDetailedView, SearchResultCompositeView, SearchView, SearchResultItemView, SearchResultTemplate, QueryView, QueryResultView, BreadCrumbView) {
	var MainLayout = Marionette.Layout.extend({

		showContextList : function() {
			var self = this;
			self.contextRegion.show(new ContextListView({
				app : self.options.app
			}));
			this.$('#contextSection div').append('<li><a href="#" id="addContext"><span> Add New Context</span> </a></li>');
		},

		initialize : function() {
			var vent = this.options.app.vent;
			var self = this;
			var locator = this.options.app.getMDRModelLocator();

			//app layout listens menu:* events, changes the layout view accordingly
			// if name of a context is given, then taht context comes to screen by default
			// id is for determining selected context and will be set in record locator
			this.listenTo(vent, 'menu:browse', function(id, offset) {
				self.content.close();
				var contextList = locator.getContextCollection();
				var isHomePage = !id;
				// TODO which one will be displayed as default
				if (contextList.length > 0) {
					if (!id) {
						id = contextList.at(0).get('id');
					}
					locator.setCurrentContext(id);

					vent.trigger('objectClass:list', id, offset, isHomePage);
				} else {
					self.options.app.vent.trigger('breadcrumb:change', {
						'location' : 'home'
					});
					$(self.content.el).html('<div class="alert alert-error">No Context to Browse</div>');
					self.activateBrowseTab();
				}

				self.showContextList();

			});

			/**
			 *When this event is triggered, chosen Context on the menu is changed,
			 * It is triggered from the ModelLocator in every SetCurrentContext
			 */
			this.listenTo(vent, 'menu:contextChange', function(id) {
				var contextList = locator.getContextCollection();
				// below line is just to show chosen context on the menu
				self.$('#contextHeader').html(contextList.get(id).get('name') + '  <b class="caret"></b>');
			});

			this.listenTo(vent, 'menu:search', function() {
				self.content.close();
				var searchView = new SearchView({
					app : self.options.app
				});
				self.content.show(searchView);
				Backbone.history.navigate('search');
				self.activateSearchTab();
			});

			this.listenTo(vent, 'menu:query', function() {
				self.content.close();
				var queryView = new QueryView({
					app : self.options.app
				});
				self.content.show(queryView);
				Backbone.history.navigate('query');
				self.activateQueryTab();
			});

			// event triggered when an ObjectClass tab clicked, list object class in content region
			this.listenTo(vent, 'objectClass:list', function(contextID, offset, isHomePage) {
				// if offset not specified, then set to 0 so that it'll show first page
				if (!offset) {
					offset = 0;
				}
				if (locator.getCurrentContext()) {
					self.content.show(new ObjectClassCompositeView({
						app : self.options.app,
						offset : offset
					}));
					/*
					 * if event is triggered from home page, then navigation calls router with trigger:true
					 * and previous state is replaced with this new nonw with replace:true
					 * since hoem page is like redirection to browsing of first context,
					 * that redirection page causes infinite loop on browser back button
					 * in this way, its removed from history, so browser cant go back to redicrection page
					 */
					if (isHomePage) {
						Backbone.history.navigate('context/' + contextID + "/oc?offset=" + offset, {
							//trigger : true,
							replace : true
						});
					} else {
						Backbone.history.navigate('context/' + contextID + "/oc?offset=" + offset);
					}
					self.activateBrowseTab();
				}
			});
			// event triggered when an ConceptualDomain tab clicked, list conceptual domains in content region
			this.listenTo(vent, 'conceptualDomain:list', function(offset) {
				self.content.show(new ConceptualDomainCompositeView({
					app : self.options.app,
					offset : offset
				}));
				Backbone.history.navigate('cd/list?offset=' + offset);
				self.activateBrowseTab();
			});

			// will be fired when user wants to explore objectClass
			this.listenTo(vent, 'dataElementConcept:list', function(objectClassID, offset) {
				self.content.show(new DataElementConceptCompositeView({
					app : self.options.app,
					objectClassID : objectClassID,
					offset : offset
				}));
				self.showContextList();
				Backbone.history.navigate('oc/' + objectClassID + '/dec?offset=' + offset);
				self.activateBrowseTab();
			});

			this.listenTo(vent, 'valueDomain:list', function() {
				self.content.close();
			});

			// will be fired when user wants to explore valueMeanings of a ConceptualDomain
			this.listenTo(vent, 'valueMeaning:list', function(conceptualDomainID) {
				var locator = self.options.app.getMDRModelLocator();
				self.content.show(new ValueMeaningCompositeView({
					app : self.options.app,
					conceptualDomainID : conceptualDomainID
				}));
				Backbone.history.navigate('cd/' + conceptualDomainID + '/vm');
				self.activateBrowseTab();
			});

			/**
			 * Two ids are the ids that is used to identify which DataElements will be retrieved from Server,
			 * if DEC ID is given then DataElements of that Concept
			 * if contextID is given, DataELemnts on given Context
			 */
			this.listenTo(vent, 'dataElement:list', function(dataElementConceptID, contextID, offset) {
				if (contextID) {
					self.content.show(new DataElementCompositeView({
						app : self.options.app,
						contextID : contextID,
						offset : offset
					}));
					Backbone.history.navigate('context/' + contextID + '/de?offset=' + offset);
					var locator = self.options.app.getMDRModelLocator();
					var context = locator.getCurrentContext();
					self.options.app.vent.trigger('breadcrumb:change', {
						'location' : 'browseDataElement',
						'contextID' : context.get('id'),
						'contextName' : context.get('name')
					});
					self.activateBrowseTab();
				} else if (dataElementConceptID) {
					self.content.show(new DataElementCompositeView({
						app : self.options.app,
						dataElementConceptID : dataElementConceptID,
						offset : offset
					}));
					Backbone.history.navigate('dec/' + dataElementConceptID + '/de?offset=' + offset);
					self.activateBrowseTab();
				} else {
				}
			});

			this.listenTo(vent, 'dataElement:detailed', function(dataElementID) {
				self.content.show(new DataElementDetailedView({
					app : self.options.app,
					id : dataElementID
				}));
				Backbone.history.navigate('de/' + dataElementID);
				self.activateBrowseTab();
			});

			this.listenTo(vent, 'dataElement:search', function(searchKeyword, offset, searchOptions) {
				// template and itemView is given because search result view should be different then the browsing view
				self.content.show(new SearchResultCompositeView({
					app : self.options.app,
					keyword : searchKeyword,
					offset : offset
				}));
				Backbone.history.navigate('search/' + searchKeyword + '/' + offset);
				self.activateSearchTab();
			});

			this.listenTo(vent, 'menu:queryresult', function(queryResult) {
				// template and itemView is given because search result view should be different then the browsing view
				self.content.show(new QueryResultView({
					app : self.options.app,
					qresult : queryResult
				}));
				//Backbone.history.navigate('query');
				self.activateQueryTab();
			});

		},

		onRender : function() {
			// right after layout initialized, browse tab selected as default
			var self = this;

			//shows breadcrumbs on the page
			this.breadcrumbs.show(new BreadCrumbView({
				app : self.options.app
			}));

			//this.options.app.vent.trigger('menu:browse', null, 0);

			// to make already imported Context's disable
			var locator = this.options.app.getMDRModelLocator();
			var importedContexts = locator.getContextCollection().pluck('name');
			for (i in importedContexts) {
				var contextName = importedContexts[i];
				this.$('#import' + contextName.replace(" ", "")).append('<i class="icon-ok pull-right"></i>').parent().addClass('disabled');
			}

			if (!importedContexts || importedContexts.length == 0) {
				this.$('#content').html('<div class="alert alert-error">No Context to Browse</div>');
			}

		},

		template : MainLayoutTemplate,
		regions : {
			menu : '#menu',
			// ContentRegion is specialized to add slide effect
			content : ContentRegion,
			contextRegion : '#contextSection',
			breadcrumbs : '#breadcrumbRegion'

		},
		events : {
			'click #menu li' : 'menuTabClick',
			'click #addContext' : 'contextAddClick'
		},
		contextAddClick : function() {
			var self = this;
			self.options.app.vent.trigger('modalWindow:show', new ContextModalView({
				app : self.options.app
			}));
		},
		menuTabClick : function(e) {
			e.preventDefault();
			var self = this;

			var locator = this.options.app.getMDRModelLocator();
			var id = $(e.target).parent().find('a').attr('id');
			var context = locator.getCurrentContext();
			var contextNames = locator.getContextCollection().pluck('name');
			// if context already exists, simulates unclickable link
			if (id && $.inArray(id.substring(6), contextNames) >= 0) {
				return false;
			}
			//menu namespace is for events under menu, when a menu item clicked, event menu:* is fired
			if (id == 'searchTab') {
				this.options.app.vent.trigger('menu:search');
			} else if (id == 'queryTab') {
				this.options.app.vent.trigger('menu:query');
			} else if (id == 'browseDataElement') {
				if (context) {
					this.options.app.vent.trigger('dataElement:list', null, context.get('id'), 0);
				} else {
					$(self.content.el).html('<div class="alert alert-error">No Context to Browse</div>');
					self.activateBrowseTab();
				}
			} else if (id == 'browseObjectClass') {
				if (context) {
					this.options.app.vent.trigger('objectClass:list', context.get('id'), 0, null);
				} else {
					$(self.content.el).html('<div class="alert alert-error">No Context to Browse</div>');
					self.activateBrowseTab();
				}
			} else if (id == 'browseConceptualDomain') {
				this.options.app.vent.trigger('conceptualDomain:list', 0);
			} else if (id == "importALL") {
				// sends a request to all importers
				$('#loadingOverlay').fadeIn();
				locator.importModel('all');
			} else if (id == "importOMOP") {
				// sends a request to omop importer, which will run ddl importer on OMOP Model
				$('#loadingOverlay').fadeIn();
				locator.importModel('omop');
			} else if (id == "importSDTM") {
				// sends a request to omop importer, which will run ddl importer on OMOP Model
				$('#loadingOverlay').fadeIn();
				locator.importModel('sdtm');
			} else if (id == "importCDASH") {
				// sends a request to omop importer, which will run ddl importer on OMOP Model
				$('#loadingOverlay').fadeIn();
				locator.importModel('cdash');
			} else if (id == "importHITSPC154") {
				// sends a request to cdash importer, which will run ontology importer on HITSP Model
				$('#loadingOverlay').fadeIn();
				locator.importModel('hitsp');
			} else if (id == "importBRIDG") {
				// sends a request to cdash importer, which will run ontology importer on HITSP Model
				$('#loadingOverlay').fadeIn();
				locator.importModel('bridg');
			} else if (id == "importMappings") {
				// sends a request to cdash importer, which will run ontology importer on HITSP Model
				$('#loadingOverlay').fadeIn();
				locator.importModel('mappings');
			} else if (id == "importSALUSCIM") {
				$('#loadingOverlay').fadeIn();
				locator.importModel('saluscim');
			} else if (id == "export-rdf-xml") {
				locator.exportAs('RDF/XML');
			} else if (id == "export-rdf-xml-abbrev") {
				locator.exportAs('RDF/XML-ABBREV');
			} else if (id == "export-n-triple") {
				locator.exportAs('N-TRIPLE');
			} else if (id == "export-turtle") {
				locator.exportAs('TURTLE');
			} else if (id == "export-n3") {
				locator.exportAs('N3');
			}
			// if an import clicked, stopPropagation. Because in firefox it acts like clicked twice, so second click gives error
			// actually it happens on all buttons but doesn't make difference at other
			// when it stopped dropdown does not close however since we refreshed page it does not matter either
			try {
				if (id.substring(0, 6) === "import") {
					e.stopPropagation();
				}
			} catch (err) {
				console.log(err);
			}
		},
		goBack : function(e) {
			e.preventDefault();
			isBack = true;
			window.history.back();
		},
		activateBrowseTab : function() {
			$('#browseTab').parent().siblings('.active').removeClass('active');
			$('#browseTab').parent().addClass('active');
		},
		activateSearchTab : function() {
			$('#searchTab').parent().siblings('.active').removeClass('active');
			$('#searchTab').parent().addClass('active');
		},
		activateQueryTab : function() {
			$('#queryTab').parent().siblings('.active').removeClass('active');
			$('#queryTab').parent().addClass('active');
		}
	});
	return MainLayout;
});
