package tr.com.srdc.mdr.core.api.composite;

import tr.com.srdc.mdr.core.api.MDRNode;
import tr.com.srdc.mdr.core.api.ai.ConceptualDomain;
import tr.com.srdc.mdr.core.model.iso11179.composite.ConceptualDomainRelationshipResource;

/**
 * A {@link ConceptualDomain} may be associated with other
 * {@link ConceptualDomain}s, via the Conceptual Domain Relationship. The nature
 * of the relationship is described using the conceptual domain relationship
 * type description. Through the Conceptual Domain Relationship, a
 * {@link ConceptualDomain} may be composed of other {@link ConceptualDomain} or
 * may be a member (component) of a larger {@link ConceptualDomain}.
 * 
 * @author anil
 * 
 */
public interface ConceptualDomainRelationship extends MDRNode {

	@Override
	/**
	 * @return the {@link ConceptualDomainRelationshipResource} version this instance.
	 */
	ConceptualDomainRelationshipResource asMDRResource();

}
