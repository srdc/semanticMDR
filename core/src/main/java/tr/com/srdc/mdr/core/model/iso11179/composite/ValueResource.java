package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;



/**
 * 
 * This is the actual value associated with a Permissible Value in an Enumerated
 * Value Domain.
 * 
 * @author anil
 * 
 */
public interface ValueResource extends MDRResource {

	/**
	 * Sets {@link Vocabulary#valueItem} property with given value
	 * 
	 * @param valueItem
	 */
	void setValueItem(String valueItem);

	/**
	 * 
	 * @return Value of {@link Vocabulary#valueItem} property
	 */
	String getValueItem();

	/**
	 * Adds {@link Vocabulary#usedInPermittedValue} property with given value
	 * 
	 * @param usedInPermittedValue
	 */
	void addUsedInPermittedValue(PermissibleValueResource usedInPermittedValue);

	/**
	 * Removes {@link Vocabulary#usedInPermittedValue} property with given value
	 * 
	 * @param usedInPermittedValue
	 */
	void removeUsedInPermittedValue(PermissibleValueResource usedInPermittedValue);

	/**
	 * 
	 * @return {@link List} of all {@link Vocabulary#usedInPermittedValue}
	 *         property values
	 * @throws MDRException
	 */
	List<PermissibleValueResource> getUsedInPermittedValues() throws MDRException;

}