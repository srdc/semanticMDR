Request:
Parameter:
Produces:
Description:
RESTful Service Interface
===========================

	To be able to use Web Interface and Jersey Endpoints of the SALUS CDE Repository one should have installed maven
	and the checkout the source code from the repository
	
Build and run the tests::
	% cd mdr
	% mvn clean install

Launch Jetty Server::
	% cd web
	% mvn jetty:run

	
RESTful Service Interface
===========================


Initial Notes
--------------------------------------------

	This describes the first release of the SALUS Common Data Element Repository RESTful API and Web Interface. After running the Jetty server;
default location for the web page is 
	http://localhost:8080
	After signing-up and login with proper credentials, one can achieve the whole functionality of Wen Interface from this single web page.
	
	There are couple of services providing REST endpoints from CDE Repository manipulation. All services and their brief descriptions given below.
	
	To be able to use any of the services below, a cookie should be sent with each request. Cookie should contain SID value with valid 
session identifier to uniqely identify active users. As explained below, one can obtain a session id with credentials
to save as c cookie from the Authentication Service Endpoint.


AUTHENTICATION Service Endpoint "/auth"
--------------------------------------------

	/auth
	
Request: PUT /auth
Parameter: Json Serialized LoginModel { username  : '' , password : '' , isStaySignedIn : true / false}
Produces: application/json  - SID required for all operations
Description: This service is responsible of creating Session's for user. If staySignedIn is false, then browser session will be created; if true
	then session for 10 year will be created. Returned sessionID will be saved as Cookie SID, so that it will be user as a authentication
	mechanism for all other operations

Request: DELETE /auth
Parameter: none
Produces: none
Description: deletes user's current session so that user is not logged in anymore
 
Request: GET /auth
Parameter: none
Produces: application/json
Description: This service returns the Json Serialized user object of authenticated user


CONCEPTUAL DOMAIN Service Endpoint "/cd/{cid}" 
--------------------------------------------

	/cd/{cid}

Request: GET /cde/{cid}
Parameter:
	cid: Unique Identifier of the ConceptualDomain
Produces: application/json
Description: This service is responsible of returning Json serialization of ConceptualDomain specified with {cid}

	/cd/{cid}/vm
	
Request: GET /cd/{cid}/vm
Parameter:
	cid: Unique Identifier of the ConceptualDomain
	limit: Total number of result to be returned, default value is 10
	offset: The initial point of the results to be showed, default value is 0
Produces: application/json
Description: This service returns json array consisting of all ValueMeaning's of the ConceptualDoamin specified by {cid}

	/cd/{cid}/vm
	
Request: GET /cd/{cid}/vm
Parameter:
	cid: Unique Identifier of the ConceptualDomain
Produces: text/plain
Description: This service returns the number of all ValueMeaning's of the ConceptualDoamin specified by {cid}

Request: POST /cd/{cid}/vm
Parameter:
	cid: Unique Identifier of the ConceptualDomain
	json serialized ValueMeaning 
Produces: none
Description: This service is responsible of creating given ValueMeaning under ConceptualDomain specified by {cid}

Request: DELETE /cd/{cid}/vm
Parameter: 
	cid: Unique Identifier of the ConceptualDomain
	json serialized ValueMeaning
Produces: none 
Description: This service is responsible of deleting given ValueMeaning under ConceptualDomain specified by {cid}

	/cd/{cid}/vd
	
Request: GET /cd/{cid}/vd
Parameter: 
	cid: Unique Identifier of the ConceptualDomain
Produces: application/json - Json serialied ValueDomain List
Description: This service returns json array consisting of all ValueDomain's representing ConceptualDomain specified by {cid}

Request: POST /cd/{cid}/vd
Parameter:
	cid: Unique Identifier of the ConceptualDomain
	json serialized ValueDomain
Produces: application/json
Description: This service is responsible of creating a ValueDomain representing the ConceptualDomain specified by {cid}

Request: PUT /cd/{cid}/vd
Parameter:
	cid: Unique Identifier of the ConceptualDomain
	json serialized ValueMeaning
Produces: none
Description: This service is responsible of updating the given ValueDomain representing the ConceptualDomain specified by {cid}


CONTEXT Service Endpoint "/context/{contextid}"
--------------------------------------------

	/context/{contextid}

Request: GET /context/{contextid}
Parameter:
	contextid: Unique Identifier of the Context
Produces: application/json
Description: This service returns json serialization of the Context specified by {contextid}

	/context/{contextid}/oc

Request: GET /context/{contextid}/oc
Parameter:
	contextid: Unique Identifier of the Context
	limit: Total number of result to be returned, default value is 10
	offset: The initial point of the results to be showed, default value is 0
Produces: application/json
Description: This service returns a json array of ObjectClass'es registered on the Context which is specified by {contextid}

	/context/{contextid}/oc

Request: GET /context/{contextid}/oc
Parameter:
	contextid: Unique Identifier of the Context
Produces: text/plain
Description: This service returns the number of ObjectClass'es registered on the Context which is specified by {contextid}

Request: POST /context/{contextid}/oc
Parameter:
	contextid: Unique Identifier of the Context
	json serialized ObjectClass
Produces: application/json
Description: This service is responsible of creating ObjectClass on the Context which is specified by {contextid}

Request: PUT /context/{contextid}/oc
Parameter:
	contextid: Unique Identifier of the Context
	json serialized ObjectClass
Produces: none
Description: This service is responsible of updating given ObjectClass on the Context which is specified by {contextid}

	/context/{contextid}/oc/search?q={keyword}

Request: GET /context/{contextid}/oc/search
Parameter:
	contextid: Unique Identifier of the Context
	keyword: Search String to be matched 
Produces: application/json
Description: This method returns a json array of resulting ObjectClass'es on the Context specified by {contextid} 
	
	context/{contextid}/property

Request: GET context/{contextid}/property
Parameter:
	contextid: Unique Identifier of the Context
Produces: application/json
Description: This service returns a json array consisting of all Property's on the Context which is specified by {contextid}

Request: PUT context/{contextid}/property
Parameter:
	contextid: Unique Identifier of the Context
	json serialization of Property to be updated
Produces: none
Description: This service is responsible of updating given Property on the Context which is specified by {contextid}

	context/{contextid}/property/search?q={keyword}

Request: GET context/{contextid}/property/search?q={keyword}
Parameter:
	contextid: Unique Identifier of the Context
	keyword: Query String to be matched with Property Designation
Produces: application/json
Description: This service returns a json array consisting of resulting Property's on the Context which is specified by given {contextid}

	/context/{contextid}/de

Request: GET /context/{contextid}/de
Parameter:
	contextid: Unique Identifier of the Context
Produces: application/json
Description: This method return json array of DataElement's on the Context which is specified by {contextid}

Request: POST /context/{contextid}/de
Parameter:
	contextid: Unique Identifier of the Context
	json serialization of DataElement to be created
Produces: application/json
Description: This method is responsible of creating given DataElement on the Context which is specified by {contextid}

Request: PUT /context/{contextid}/de
Parameter: none
	contextid: Unique Identifier of the Context
	json serialization of DataElement to be updated
Produces: none
Description: This method is responsible of updating given DataElement on the Context which is specified by {contextid}

Request: DELETE /context/{contextid}/de
Parameter:
	contextid: Unique Identifier of the Context
	json serialization of DataElement to be removed
Produces: none
Description: This method is responsible of deleting given DataElement on the Context which is specified by {contextid}


	/context/{contextid}/de/search?q={keyword}&limit={limit}&offset={offset}
	
Request: GET /context/{contextid}/de/search?q={keyword}&limit={limit}&offset={offset}
Parameter:
	contextid: Unique Identifier of the Context
	keyword: Query String to be mathed with DataElement's Designation
	limit: Total number of results to be displayed, default value is 10
	offset: Starting indice of the results displayed, default value is 0
Produces: application/json
Description: This service executes a text search on the Context which is specified by {contextid} and returns json array of matching DataElement's

	/context/{contextid}/de/search?q={keyword}

Request: GET /context/{contextid}/de/search?q={keyword}
Parameter:
	contextid: Unique Identifier of the Context
	keyword: Query String to be matched with DataElement's Designation
Procudes: text/plain
Description: This service calculates the total number of results to be returned by executing text search with given keyword

	/context/{contextid}/vd/search?q={keyword}

Request: GET /context/{contextid}/vd/search?q={keyword}
Parameter:
	contextid: Unique Identifier of the Context
	keyword: Query String to be matched with ValueDomains's Designation
Procudes: text/plain
Description: This service returns all the value domains on Context specified ny given contextid. Used by autocomplete features in Web GUI

DATA ELEMENT CONCEPT Service Endpoint "/dec/{decid}"
--------------------------------------------

	/dec/{decid}
	
Request: GET /dec/{decid}
Parameter: 
	decid: Unique Identifier of the DataElementConcept
Produces: application/json
Description: This method returns the json serialization of DataElementConcept which is specified by {decid}
	
	/dec/{decid}/de

Request: GET /dec/{decid}/de
Parameter:
	decid: Unique Identifier of the DataElementConcept
Produces: application/json
Description: This method returns json array of all DataElement's expressing the DataElementConcept which is specified by {decid}

DATA ELEMENT Service Endpoint "/de/{deid}"
--------------------------------------------

	/de/{deid}
	
Request: GET /de/{deid}
Parameter: 
	deid: Unique Identifier of the DataElement
Produces: application/json
Description: This method returns the json serialization of DataElement which is specified by {deid}
	
OBJECT CLASS Service Endpoint "/oc/{ocid}"
--------------------------------------------

	/oc/{ocid}

Request: GET /oc/{ocid}
Parameter:
	ocid: Unique Identifier of the ObjectClass
Produces: application/json
Description: This servide returns json serialization of the Object Class which is specified by {ocid}

	/oc/{ocid}/parent
	
Request: GET /oc/{ocid}/parent
Parameter:
	ocid: Unique Identifier for the ObjectClass
Produces: application/json
Description: This service returns the json serialization of parent of the ObjectClass which is specified by {ocid}

	/oc/{ocid}/subconcept
	
Request: GET /oc/{ocid}/subconcept
Parameter:
	ocid: Unique Identifier for the ObjectClass
Produces: application/json
Description: This service return the json serializations of ObjectClass List whose parent Concept is specified by given {ocid}

	/oc/{ocid}/dec
	
Request: GET /oc/{ocid}/dec
Parameter:
	ocid: Unique Identifier of the ObjectClass
	limit: Total number of result to be returned, default value is 10
	offset: The initial point of the results to be showed, default value is 0
Produces: application/json
Description: This service return json array of all DataElement's created with the ObjectClass which is specified by {ocid}

	/oc/{ocid}/dec
	
Request: GET /oc/{ocid}/dec
Parameter:
	ocid: Unique Identifier of the ObjectClass
Produces: text/plain
Description: This service return the number of all DataElement's created with the ObjectClass which is specified by {ocid}

Request: POST /oc/{ocid}/dec
Parameter:
	ocid: Unique Identifier of the ObjectClass
	json serialization of ObjectClass to be created
Produces: application/json
Description: This service is responsible of creating DataElementConcept with the ObjectClass which is specified by {ocid}

Request: PUT /oc/{ocid}/dec
Parameter:
	ocid: Unique Identifier of the ObjectClass
	json serialization of ObjectClass to be updated
Produces: none
Description: This service is responsible of updating given DataElementConcept with the ObjectClass which specified by {ocid}


PROPERTY Service Endpoint
--------------------------------------------

	/property/{propertyid}
	
Request: GET /property/{propertyid}
Parameter:
	propertyid: Unique Identifier of the Property
Produces: application/json
Description: This service return Property which is specified by {propertyid}

	
REPOSITORY Service Endpoint "/repository"
--------------------------------------------

	/repository
	
Request: GET /repository
Parameter: none
Produces: application/json
Description : This service returns a Json array consisting of the all ContextModel's which are registered in repository

Request: POST /repository
Parameter: JSON serialized ContextModel
Produces: application/json
Description: This service is responsible of creating given ContextModel in repository

Request: PUT /repository
Parameter: JSON serialized ContextModel
Produces: none
Description: This service is responsible of updating existing Context's on Repository

	/repository/search?q={keyword}&limit={limit}&offset={offset}
	
Request: GET /repository/search?q={keyword}&limit={limit}&offset={offset}
Parameter:
	keyword: Keyword string to be matched with DataElement's Designation
	limit: Total number of results to be displayed, default value is 10
	offset: Starting indice of the results displated, default value is 0
Produces: application/json
Description: Executes text search with Wild card on Repository and returns json array of resulting DataElement's

	/repository/search?q={keyword}

Request: GET /repository/search?q={keyword}
Parameter:
	keyword: Keyword string to be matched on DataElement's Designation
Produces: text/plain
Description: Calculates the total number of results by executing text search with given keyword

	/repository/cd

Request: GET /repository/cd
Parameter:
	limit: Total number of result to be returned, default value is 10
	offset: The initial point of the results to be showed, default value is 0
Produces: application/json
Description: This service returns a Json array of ConceptualDomain's registered on Repository

	/repository/cd

Request: GET /repository/cd
Parameter: none
Produces: text/plain
Description: This service returns the number of ConceptualDomain's registered on Repository

Request: POST /repository/cd
Parameter: Json serialized ConceptualDomain
Produces: application/json 
Description: This service is responsible of creating ConceptualDomain given with ConceptualDomainModel

Request: PUT /repository/cd
Parameter: Json serialized ConceptualDomain
Produces:  none
Description: This service is responsible of updating ConceptualDomain given with ConceptualDomainModel

	/repository/cd/search?q={keyword}
	
Request: GET /repository/cd/searchq={keyword}
Parameter: 
	keyword: Keyword string to be matched with ConceptualDomain's designation
Produces: application/json
Description: Executes text search with wild card on repository and returns a json array of resulting ConceptualDomain's


VALUE DOMAIN Service Endpoint "/vd/{vdid}"
--------------------------------------------

	/vd/{vdid}
	
Request: GET /vd/{vdid}
Parameter:
	vdid: Unique Identifier of the ValueDomain
Produces: application/json 
Description: This service returns the Json serialization of the ValueDomain which is specified by {vdid}
