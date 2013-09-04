semanticMDR
===========

The ISO/IEC 11179 family of specifications introduces a standard model 
for meta-data registries to increase the interoperability of applications 
with the use of common data elements. Jena based implementation of a standard 
meta-data registry, brings semantic processing and reasoning capabilities 
on top of the common data elements and their consumer applications.

## Installation
===========

Apache Maven is required to build the Semantic MDR. Please visit
http://maven.apache.org/ in order to install Maven on your system.

Under the root directory of the semanticMDR project run the following:

	$ semanticMDR> mvn install

In order to make a clean install run the following:

	$ semanticMDR> mvn clean install

In order to run the built-in Jetty web server, go under the "web" directory of
semanticMDR and run the following:

	$ semanticMDR/web> mvn jetty:run 

Default web server will run on 8080 port of your machine. You can run the web server
on another port as in the following:

	$ semanticMDR/web> mvn -Djetty.port=9999 jetty:run

You can see the login page of the semanticMDR from your browser by going to:

	http://localhost:8080/semanticmdr

Default installation comes with a default test user.
	
	Username:test
	Password:test
	or
	Username:demo
	Password:demo

## Demo
===========

 To run this demo cd to demo folder
 
	$ semanticMDR> cd demo
	
 Start demo by deploying services to jetty
	
	$ semanticMDR/demo> mvn jetty:run
	
 As default value, service starts to run under port 9090, just point your web browser to
 
	http://localhost:9090
	
 Main functionality of this demo is to design a form and auto population of this form from a patient summary using MDR Annotations.
 - Click on add new field and enter a field label
 - Type a content model name and choose a content model from list
 - Select a data element by dragging onto form field
 - submit a valid CDA document by click on "upload patient summary" 
 - click on "Populate"
