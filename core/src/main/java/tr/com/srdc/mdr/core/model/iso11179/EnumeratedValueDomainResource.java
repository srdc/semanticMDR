package tr.com.srdc.mdr.core.model.iso11179;

import java.util.List;

import tr.com.srdc.mdr.core.api.ai.EnumeratedValueDomain;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.composite.PermissibleValueResource;



/**
 * 
 * An Enumerated Value Domain is one where the Value Domain is expressed as an
 * explicit set of two or more Permissible Values. As a sub-type of Value
 * Domain, an Enumerated Value Domain inherits the attributes and relationships
 * of the former.
 * 
 * @author anil
 * 
 */
public interface EnumeratedValueDomainResource extends ValueDomainResource,
		EnumeratedValueDomain {

/**
	 * Adds {@link Vocabulary#containingPermissibleValueSet with given value 
	 * @param containingPermissibleValueSet
	 */
	void addContainingPermissibleValueSet(
			PermissibleValueResource containingPermissibleValueSet);

	/**
	 * Removes {@link Vocabulary#containingPermissibleValueSet} with given value
	 * 
	 * @param containingPermissibleValueSet
	 *            throws {@link IllegalStateException} when try to last 2
	 *            property
	 */
	void removeContaningPermissibleValueSet(
			PermissibleValueResource containingPermissibleValueSet);

	/**
	 * 
	 * @return {@link List} of all
	 *         {@link Vocabulary#containingPermissibleValueSet} property values
	 * @throws MDRException
	 */
	List<PermissibleValueResource> getContainingPermissibleValueSets()
			throws MDRException;

}