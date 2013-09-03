package tr.com.srdc.mdr.core.api.composite;

import java.util.Calendar;

import tr.com.srdc.mdr.core.api.MDRNode;
import tr.com.srdc.mdr.core.model.iso11179.composite.PermissibleValueResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueMeaningResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueResource;



/**
 * A Permissible Value is an expression of a Value Meaning within an Enumerated
 * Value Domain. It is one of a set of such values that comprises an Enumerated
 * Value Domain. Each Permissible Value is associated with a Value Meaning.
 * 
 * @author anil
 * 
 */
public interface PermissibleValue extends MDRNode {

	@Override
	/**
	 * @return the {@link PermissibleValueResource} version this instance.
	 */
	PermissibleValueResource asMDRResource();

	Calendar getBeginDate();

	/**
	 * 
	 * @return the end date of {@link PermissibleValue}. If the attribute does
	 *         not exist return <code>null</code>
	 */
	Calendar getEndDate();

	/**
	 * 
	 * @return the value meaning identifier of a {@link ValueMeaningResource}
	 *         used in this {@link PermissibleValue}
	 */
	String getValueMeaningIdentifier();

	/**
	 * 
	 * @return the value item of a {@link ValueResource} used in this
	 *         {@link PermissibleValue}
	 */
	String getValueItem();

	/**
	 * 
	 * @return the value meaning description of a {@link ValueMeaningResource}
	 *         used in this {@link PermissibleValue}. If the attribute does not
	 *         exist return <code>null</code>
	 */
	String getValueMeaningDescription();
}
