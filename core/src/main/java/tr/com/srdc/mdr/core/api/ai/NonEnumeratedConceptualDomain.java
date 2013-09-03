package tr.com.srdc.mdr.core.api.ai;

import tr.com.srdc.mdr.core.api.composite.Datatype;
import tr.com.srdc.mdr.core.model.iso11179.NonEnumeratedConceptualDomainResource;

/**
 * A Conceptual Domain that cannot be expressed as a finite set of Value
 * Meanings is called a Non-enumerated Conceptual Domain. It may be expressed
 * via a description or specification, such as a rule, a procedure, or a range
 * (i.e., interval). As a sub-type of Conceptual Domain, a Non-enumerated
 * Conceptual Domain inherits the attributes and relationships of the former.
 * 
 * @author anil
 * 
 */
public interface NonEnumeratedConceptualDomain extends ConceptualDomain {

	@Override
	/**
	 * @return the {@link NonEnumeratedConceptualDomainResource} version this instance.
	 */
	NonEnumeratedConceptualDomainResource asMDRResource();

	/**
	 * @return Description of this NonEnumeratedConceptualDomain
	 */
	String getDomainDescription();

	/**
	 * Create a {@link NonEnumeratedValueDomain} out of this
	 * {@link NonEnumeratedConceptualDomain} with the given name, definition and
	 * dataType. Definition is copied to the description of the created
	 * {@link NonEnumeratedValueDomain}.
	 * 
	 * @param name
	 * @param definition
	 * @param dataType
	 * @return
	 */
	NonEnumeratedValueDomain createNonEnumeratedValueDomain(String name,
			String definition, Datatype dataType);
}
