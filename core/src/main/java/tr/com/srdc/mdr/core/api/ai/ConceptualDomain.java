package tr.com.srdc.mdr.core.api.ai;

import java.util.List;

import tr.com.srdc.mdr.core.model.iso11179.ConceptualDomainResource;


/**
 * 
 * A Conceptual Domain is a set of Value Meanings, which may either be
 * enumerated or expressed via a description.<br>
 * As an Administered Item, a Conceptual Domain carries its own Administration
 * Record information, allowing it to be identified, named, defined and
 * optionally classified within a Classification Scheme.<br>
 * A Conceptual Domain may be associated with other Conceptual Domains, via the
 * Conceptual Domain Relationship in Figure 9. The nature of the relationship is
 * described using the conceptual domain relationship type description. Through
 * the Conceptual Domain Relationship, a Conceptual Domain may be composed of
 * other Conceptual Domains or may be a member (component) of a larger
 * Conceptual Domain.<br>
 * A Conceptual Domain may specify a constraint such as “linear measure” as its
 * dimensionality. When a dimensionality is specified, any Value Domain that is
 * based on this Conceptual Domain shall specify a Unit of Measure that is
 * consistent with this dimensionality.
 * 
 * @author anil
 * 
 */
public interface ConceptualDomain extends AdministeredItem {

	@Override
	/**
	 * @return the {@link ConceptualDomainResource} version this instance.
	 */
	ConceptualDomainResource asMDRResource();

	/**
	 * @return Dimensionality value of this ConceptualDomain if there is any, if
	 *         not, <code>null</code>
	 */
	String getDimensionality();

	/**
	 * Set the dimensionality of this {@link ConceptualDomain}.
	 * 
	 * @param dimensionality
	 */
	void setDimensionality(String dimensionality);

	/**
	 * @return List of all {@link ValueDomain}s representing this
	 *         ConceptualDomain
	 */
	List<ValueDomain> getValueDomains();

	/**
	 * Returns a {@link ValueDomain} of which unique ID is given
	 * 
	 * @param valueDomainID
	 */
	ValueDomain getValueDomain(String valueDomainID);
	

	/**
	 * 
	 * @return whether the conceptual domain is enumerated.
	 */
	boolean isEnumeratedConceptualDomain();
}
