package tr.com.srdc.mdr.core.model;


import tr.com.srdc.mdr.core.api.MDRNode;

import com.hp.hpl.jena.ontology.OntClass;


/**
 * 
 * Representation of the ontological classes within the MDR Model.
 * Low-level, Semantic Data Manipulation API is based on Jena API, hence this
 * OWL class extends {@link OntClass}.
 * 
 * @author anil
 * 
 */
public interface MDRResource extends OntClass, MDRNode {

}
