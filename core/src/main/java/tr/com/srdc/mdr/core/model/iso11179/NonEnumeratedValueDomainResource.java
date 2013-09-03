package tr.com.srdc.mdr.core.model.iso11179;

import tr.com.srdc.mdr.core.api.ai.NonEnumeratedValueDomain;
import tr.com.srdc.mdr.core.model.Vocabulary;

/**
 * 
 * A Value Domain that is specified by a description rather than a list of all
 * Permissible Values.
 * 
 * @author serike
 * 
 */
public interface NonEnumeratedValueDomainResource extends ValueDomainResource,
		NonEnumeratedValueDomain {

	/**
	 * Set the {@link Vocabulary#nonEnumeratedDomainDescription} of
	 * {@link NonEnumeratedValueDomainResource}.
	 * 
	 * @param nonEnumeratedDomainDescription
	 * <br>
	 *            An {@link Vocabulary#nonEnumeratedDomainDescription} of a
	 *            {@link NonEnumeratedValueDomainResource}.
	 */
	void setNonEnumeratedDomainDescription(String nonEnumeratedDomainDescription);

	/**
	 * @return the {@link Vocabulary#nonEnumeratedDomainDescription} of a
	 *         {@link NonEnumeratedValueDomainResource}
	 */
	String getNonEnumeratedDomainDescription();

	/**
	 * Set the
	 * {@link Vocabulary#representingNonEnumeratedConceptualDomainRepresentation}
	 * of this {@link NonEnumeratedValueDomainResource}.
	 * 
	 * @param representingNonEnumeratedConceptualDomainRepresentation
	 */
	void setRepresentingNonEnumeratedConceptualDomainRepresentation(
			NonEnumeratedConceptualDomainResource representingNonEnumeratedConceptualDomainRepresentation);

	/**
	 * @return the
	 *         {@link Vocabulary#representingNonEnumeratedConceptualDomainRepresentation}
	 *         of a {@link NonEnumeratedValueDomainResource}.
	 * 
	 */
	NonEnumeratedConceptualDomainResource getRepresentingNonEnumeratedConceptualDomainRepresentation();

}