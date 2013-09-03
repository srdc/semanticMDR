package tr.com.srdc.mdr.core.api.composite;

import tr.com.srdc.mdr.core.api.MDRNode;
import tr.com.srdc.mdr.core.api.ai.DataElementConcept;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementConceptRelationshipResource;

/**
 * A {@link DataElementConcept} may be associated with other
 * {@link DataElementConcept}s, via the Data Element Concept Relationship. The
 * nature of the relationship is described using the data element concept
 * relationship type description.
 * 
 * @author anil
 * 
 */
public interface DataElementConceptRelationship extends MDRNode {

	@Override
	/**
	 * @return the {@link DataElementConceptRelationshipResource} version this instance.
	 */
	DataElementConceptRelationshipResource asMDRResource();

}
