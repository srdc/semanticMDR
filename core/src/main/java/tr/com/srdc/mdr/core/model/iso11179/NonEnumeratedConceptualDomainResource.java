package tr.com.srdc.mdr.core.model.iso11179;

import java.util.List;

import tr.com.srdc.mdr.core.api.ai.NonEnumeratedConceptualDomain;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.Vocabulary;



/**
 * 
 * A Conceptual Domain that is not specified by a list of all valid Value
 * Meanings.
 * 
 * @author serike
 * 
 */
public interface NonEnumeratedConceptualDomainResource extends ConceptualDomainResource,
		NonEnumeratedConceptualDomain {

	/**
	 * Set the {@link Vocabulary#nonEnumeratedConceptualDomainDescription} of
	 * {@link NonEnumeratedConceptualDomainResource}.
	 * 
	 * @param nonEnumeratedConceptualDomainDescription
	 * <br>
	 *            An {@link Vocabulary#nonEnumeratedConceptualDomainDescription}
	 *            of a {@link NonEnumeratedConceptualDomainResource}.
	 */
	void setNonEnumeratedConceptualDomainDescription(
			String nonEnumeratedConceptualDomainDescription);

	/**
	 * @return the {@link Vocabulary#nonEnumeratedConceptualDomainDescription}
	 *         of a {@link NonEnumeratedConceptualDomainResource}
	 */
	String getNonEnumeratedConceptualDomainDescription();

	/**
	 * Adds
	 * {@link Vocabulary#representedByNonEnumeratedConceptualDomainRepresentation}
	 * property with given value
	 * 
	 * @param representedByNonEnumeratedConceptualDomainRepresentation
	 *            value of
	 *            {@link Vocabulary#representedByNonEnumeratedConceptualDomainRepresentation}
	 *            property
	 */
	void addRepresentedByNonEnumeratedConceptualDomainRepresentation(
			NonEnumeratedValueDomainResource representedByNonEnumeratedConceptualDomainRepresentation);

	/**
	 * 
	 * @return @return {@link List} of
	 *         {@link Vocabulary#representedByNonEnumeratedConceptualDomainRepresentation}
	 *         property values
	 */
	List<NonEnumeratedValueDomainResource> getRepresentedByNonEnumeratedConceptualDomainRepresentations()
			throws MDRException;

	/**
	 * Removes the
	 * {@link Vocabulary#representedByNonEnumeratedConceptualDomainRepresentation}
	 * property with given value
	 * 
	 * @param representedByNonEnumeratedConceptualDomainRepresentation
	 *            value of
	 *            {@link Vocabulary#representedByNonEnumeratedConceptualDomainRepresentation}
	 *            property to be removed.
	 */
	void removeRepresentedByNonEnumeratedConceptualDomainRepresentation(
			NonEnumeratedValueDomainResource representedByNonEnumeratedConceptualDomainRepresentation);

}