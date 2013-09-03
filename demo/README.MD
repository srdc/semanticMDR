 To run this demo cd to demo folder
	> cd mdr/demo
	
 Start demo by deploying services to jetty
	> mvn jetty:run
	
 As default value, service starts to run under port 9090, just point your web browser to
	http://localhost:9090
	
 Main functionality of this demo is to design a form and auto population of this form from a patient summary using MDR Annotations.
 - Click on add new field and enter a field label
 - Type a content model name and choose a content model from list
 - Select a data element by dragging onto form field
 - submit a valid CDA document by click on "upload patient summary" 
 - click on "Populate"