package tr.com.srdc.mdr.core.api;


import tr.com.srdc.mdr.core.model.MDRResource;

import com.hp.hpl.jena.ontology.OntClass;


/**
 * @author anil
 * 
 */
public interface MDRNode {

	/**
	 * @return the {@link OntClass} version of this instance.
	 */
	OntClass asJenaClass();

	/**
	 * @return the {@link MDRResource} version of this instance.
	 */
	MDRResource asMDRResource();

	/**
	 * Deletes this node from the repository and all the references referring to
	 * this,<br>
	 * if node acts as a container for other nodes which are not referenced from
	 * any other node, also contained nodes will be deleted from the repository
	 */
	void delete();

}
