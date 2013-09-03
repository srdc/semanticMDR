package tr.com.srdc.mdr.core.api.ai;

import tr.com.srdc.mdr.core.api.composite.Datatype;
import tr.com.srdc.mdr.core.api.composite.UnitOfMeasure;
import tr.com.srdc.mdr.core.model.iso11179.ValueDomainResource;

/**
 * One of the key components of a representation is the Value Domain. A Value
 * Domain provides representation, but has no implication as to what Data
 * Element Concept the values are associated nor what the values mean.<br>
 * A Value Domain is associated with a Conceptual Domain. A Value Domain
 * provides a representation for the Conceptual Domain. An example of a
 * Conceptual Domain and a set of Value Domains is ISO 3166, Codes for the
 * representation of names of countries. For instance, ISO 3166 describes the
 * set of seven Value Domains: short name in English, official name in English,
 * short name in French, official name in French, alpha-2 code, alpha-3 code,
 * and numeric code.<br>
 * As an Administered Item, a Value Domain carries its own Administration Record
 * information, allowing it to be identified, named, defined and optionally
 * classified in a Classification Scheme.<br>
 * A Value Domain may be associated with other Value Domains, via the Value
 * Domain Relationship. The nature of the relationship is described using the
 * value domain relationship type description. Through the Value Domain
 * Relationship, a Value Domain may be composed of other Value Domains or may be
 * a member (component) of a larger Value Domain.
 * 
 * @author anil
 * 
 */

public interface ValueDomain extends AdministeredItem {

	@Override
	/**
	 * @return the {@link ValueDomainResource} version this instance.
	 */
	ValueDomainResource asMDRResource();

	/**
	 * @return {@link Datatype} object used by this ValueDomain
	 */
	Datatype getDatatype();

	/**
	 * @return the {@link ConceptualDomain} which this ValueDomain is
	 *         representing
	 */
	ConceptualDomain getConceptualDomain();
	
	/**
	 * 
	 * @return {@link UnitOfMeasure} object used of this ValueDomain
	 */
	UnitOfMeasure getUnitOfMeasure();

	// /**
	// * @return {@link ConceptualDomain} represented by this ValueDomain
	// */
	// ConceptualDomain getConceptualDomain();
	//
	// /**
	// * @return {@link Datatype} attribute, which is the type for all values in
	// * the domain.
	// */
	// Datatype getDataType();

}
