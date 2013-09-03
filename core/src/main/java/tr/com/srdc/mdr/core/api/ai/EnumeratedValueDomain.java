package tr.com.srdc.mdr.core.api.ai;

import java.util.List;

import tr.com.srdc.mdr.core.api.composite.PermissibleValue;
import tr.com.srdc.mdr.core.model.iso11179.EnumeratedValueDomainResource;


/**
 * An Enumerated Value Domain is one where the Value Domain is expressed as an
 * explicit set of two or more Permissible Values. As a sub-type of Value
 * Domain, an Enumerated Value Domain inherits the attributes and relationships
 * of the former.
 * 
 * @author anil
 * 
 */
public interface EnumeratedValueDomain extends ValueDomain {

	@Override
	/**
	 * @return the {@link EnumeratedValueDomainResource} version this instance.
	 */
	EnumeratedValueDomainResource asMDRResource();

	/**
	 * @return Returns a list of all {@link PermissibleValue}s of this
	 *         EnumeratedValueDomain
	 */
	List<PermissibleValue> getPermissibleValues();

	/**
	 * Adds given {@link PermissibleValue}s to the value set of
	 * EnumeratedValueDomain
	 * 
	 * @param pv
	 */
	void addPermissibleValue(PermissibleValue... pv);

	// /**
	// * Each EnumeratedValueDomain has a set of {@link PermissibleValue}s, at
	// * least size of 2, from which {@link DataElement}s formed by this
	// * ValueDoamin can take a value
	// *
	// * @return unmodifiable List of all PermissibleValues contained by this
	// * ValueDomain
	// */
	// List<PermissibleValue> getPermissibleValues() throws MDRException;

}
