package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.Calendar;
import java.util.List;

import tr.com.srdc.mdr.core.api.composite.ValueMeaning;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.EnumeratedConceptualDomainResource;



/**
 * 
 * Each member of an Enumerated Conceptual Domain has a Value Meaning that
 * provides its distinction from other members. In the example of ISO 3166, the
 * notion of each country as specified would be the Value Meanings. The
 * representation of Value Meanings in a registry shall be independent of (and
 * shall not constrain) their representation in any corresponding Value Domain.
 * A particular Value Meaning may have more than one means of representation by
 * Permissible Values — each from a distinct Enumerated Value Domain.
 * 
 * @author anil
 * 
 */
public interface ValueMeaningResource extends MDRResource, ValueMeaning {

	/**
	 * Sets {@link Vocabulary#valueMeaningIdentifier} property with given value
	 * 
	 * @param valueMeaningIdentifier
	 */
	void setValueMeaningIdentifier(String valueMeaningIdentifier);

	/**
	 * 
	 * @return value of {@link Vocabulary#valueMeaningIdentifier} property
	 */
	String getValueMeaningIdentifier();

	/**
	 * Sets {@link Vocabulary#valueMeaningDescription} property with given value
	 * 
	 * @param valueMeaningDescription
	 *            removes property if given <code>null</code>
	 */
	void setValueMeaningDescription(String valueMeaningDescription);

	/**
	 * 
	 * @return Value of {@link Vocabulary#valueMeaningDescription} property,
	 *         <code>null</code> if such property does not exist
	 */
	String getValueMeaningDescription();

	/**
	 * Sets {@link Vocabulary#valueMeaningBeginDate} property with given value
	 * 
	 * @param valueMeaningBeginDate
	 */
	void setValueMeaningBeginDate(Calendar valueMeaningBeginDate);

	/**
	 * Value of {@link Vocabulary#valueMeaningBeginDate} property
	 * 
	 * @return
	 */
	Calendar getValueMeaningBeginDate();

	/**
	 * Sets {@link Vocabulary#valueMeaningEndDate} property with given value
	 * 
	 * @param valueMeaningEndDate
	 *            removes property if given <code>null</code>
	 */
	void setValueMeaningEndDate(Calendar valueMeaningEndDate);

	/**
	 * 
	 * @return Value of {@link Vocabulary#valueMeaningEndDate} property,
	 *         <code>null</code> if such property does not exist
	 */
	Calendar getValueMeaningEndDate();

	/**
	 * Adds {@link Vocabulary#containedInValueMeaningSet} property with given
	 * value
	 * 
	 * @param containedInValueMeaningSet
	 */
	void addContainedInValueMeaningSet(
			EnumeratedConceptualDomainResource containedInValueMeaningSet);

	/**
	 * Removes {@link Vocabulary#containedInValueMeaningSet} property with given
	 * value
	 * 
	 * @param containedInValueMeaningSet
	 *            throws {@link IllegalStateException} when try to remove all
	 *            values of the property
	 */
	void removeContainedInValueMeaningSet(
			EnumeratedConceptualDomainResource containedInValueMeaningSet);

	/**
	 * 
	 * @return {@link List} of all {@link Vocabulary#containedInValueMeaningSet}
	 *         property values
	 * @throws MDRException
	 */
	List<EnumeratedConceptualDomainResource> getContainedInValueMeaningSets()
			throws MDRException;

	/**
	 * Adds {@link Vocabulary#usedInPermissibleValueMeaning} proprety with a
	 * given value
	 * 
	 * @param usedInPermissibleValueMeaning
	 */
	void addUsedInPermissibleValueMeaning(
			PermissibleValueResource usedInPermissibleValueMeaning);

	/**
	 * Removes {@link Vocabulary#usedInPermissibleValueMeaning} with a given
	 * Value
	 * 
	 * @param usedInPermissibleValueMeaning
	 */
	void removeUsedInPermissibleValueMeaning(
			PermissibleValueResource usedInPermissibleValueMeaning);

	/**
	 * -
	 * 
	 * @return {@link List} of all
	 *         {@link Vocabulary#usedInPermissibleValueMeaning} property values
	 * @throws MDRException
	 */
	List<PermissibleValueResource> getUsedInPermissibleValueMeanings()
			throws MDRException;

}