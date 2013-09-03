package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.Calendar;
import java.util.List;

import tr.com.srdc.mdr.core.api.composite.PermissibleValue;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.EnumeratedValueDomainResource;



/**
 * 
 * A Permissible Value is an expression of a Value Meaning within an Enumerated
 * Value Domain. It is one of a set of such values that comprises an Enumerated
 * Value Domain. Each Permissible Value is associated with a Value Meaning.
 * 
 * @author anil
 * 
 */
public interface PermissibleValueResource extends MDRResource, PermissibleValue {

	/**
	 * Sets {@link Vocabulary#permissibleValueBeginDate} property with given
	 * value
	 * 
	 * @param permissibleValueBeginDate
	 */
	void setPermissibleValueBeginDate(Calendar permissibleValueBeginDate);

	/**
	 * 
	 * @return Value of {@link Vocabulary#permissibleValueBeginDate} property
	 */
	Calendar getPermissibleValueBeginDate();

	/**
	 * Sets {@link Vocabulary#permissibleValueEndDate} property with given value
	 * 
	 * @param permissibleValueEndDate
	 *            if given <code>null</code>, removes property
	 */
	void setPermissibleValueEndDate(Calendar permissibleValueEndDate);

	/**
	 * 
	 * @return Value of {@link Vocabulary#permissibleValueEndDate} property,
	 *         <code>null</code> if such property does not exist
	 */
	Calendar getPermissibleValueEndDate();

	/**
	 * Sets {@link Vocabulary#hasPermittedValue} property with given value
	 * 
	 * @param hasPermittedValue
	 */
	void setHasPermittedValue(ValueResource hasPermittedValue);

	/**
	 * 
	 * @return Value of {@link Vocabulary#hasPermittedValue} property
	 */
	ValueResource getHasPermittedValue();

	/**
	 * Sets {@link Vocabulary#hasPermissibleValueMeaning} property with given
	 * value
	 * 
	 * @param hasPermissibleValueMeaning
	 */
	void setHasPermissibleValueMeaning(
			ValueMeaningResource hasPermissibleValueMeaning);

	/**
	 * 
	 * @return Value of {@link Vocabulary#hasPermissibleValueMeaning} property
	 */
	ValueMeaningResource getHasPermissibleValueMeaning();

	/**
	 * Adds {@link Vocabulary#containedInPermissibleValueSet} property with
	 * given value
	 * 
	 * @param containedInPermissibleValueSet
	 */
	void addContainedInPermissibleValueSet(
			EnumeratedValueDomainResource containedInPermissibleValueSet);

	/**
	 * Removes {@link Vocabulary#containedInPermissibleValueSet} property with
	 * given value
	 * 
	 * @param containedInPermissibleValueSet
	 */
	void removeContainedInPermissibleValueSet(
			EnumeratedValueDomainResource containedInPermissibleValueSet);

	/**
	 * 
	 * @return {@link List} of all
	 *         {@link Vocabulary#containedInPermissibleValueSet} property values
	 * @throws MDRException
	 */
	List<EnumeratedValueDomainResource> getContainedInPermissibleValueSets()
			throws MDRException;

}