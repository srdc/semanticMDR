package tr.com.srdc.mdr.core.util;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

public class SKOS {
	public static final Property prefLabel = ResourceFactory
			.createProperty("http://www.w3.org/2004/02/skos/core#prefLabel");
	public static final Property notation = ResourceFactory
			.createProperty("http://www.w3.org/2004/02/skos/core#notation");

	public static final String Concept = "http://www.w3.org/2004/02/skos/core#Concept";
	public static final String ConceptScheme = "http://www.w3.org/2004/02/skos/core#ConceptScheme";
}
