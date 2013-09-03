package tr.com.srdc.mdr.core.api.ai;

import tr.com.srdc.mdr.core.model.iso11179.NonEnumeratedValueDomainResource;

/**
 * A Value Domain may be expressed via a description or specification, such as a
 * rule, a procedure, or a range (i.e., interval), rather than as an explicit
 * set of Permissible Values. Such a Value Domain is call a Nonenumerated Value
 * Domain. As a sub-type of Value Domain, a Non-enumerated Value Domain inherits
 * the attributes and relationships of the former.
 * 
 * @author anil
 * 
 */
public interface NonEnumeratedValueDomain extends ValueDomain {

	@Override
	/**
	 * @return the {@link NonEnumeratedValueDomainResource} version this instance.
	 */
	NonEnumeratedValueDomainResource asMDRResource();

	/**
	 * @return Domain description of this NonEnumeratedConceptualDomain
	 */
	String getDomainDescription();

	// /**
	// * @return Domain description of the ValueDomain, which describes the
	// values
	// * that DataElement can take, instead of specifying all values
	// * explicitly.
	// */
	// String getDomainDescription();

}
