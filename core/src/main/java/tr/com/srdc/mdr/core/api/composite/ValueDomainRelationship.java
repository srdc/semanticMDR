package tr.com.srdc.mdr.core.api.composite;

import tr.com.srdc.mdr.core.api.MDRNode;
import tr.com.srdc.mdr.core.api.ai.ValueDomain;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueDomainRelationshipResource;

/**
 * A {@link ValueDomain} may be associated with other {@link ValueDomain}s, via
 * the Value Domain Relationship. The nature of the relationship is described
 * using the value domain relationship type description. Through the Value
 * Domain Relationship, a {@link ValueDomain} may be composed of other
 * {@link ValueDomain} or may be a member (component) of a larger
 * {@link ValueDomain}.
 * 
 * @author anil
 * 
 */
public interface ValueDomainRelationship extends MDRNode {

	@Override
	/**
	 * @return the {@link ValueDomainRelationshipResource} version this instance.
	 */
	ValueDomainRelationshipResource asMDRResource();

}
