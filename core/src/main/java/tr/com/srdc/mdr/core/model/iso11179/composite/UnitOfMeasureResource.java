package tr.com.srdc.mdr.core.model.iso11179.composite;

import tr.com.srdc.mdr.core.api.composite.UnitOfMeasure;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;

/**
 * 
 * If meaningful, a Value Domain may be associated with a Unit of Measure — the
 * unit in which any associated Data Element values are specified. The unit is
 * designated by a unit of measure name. When specified, the unit must be
 * consistent with the dimensionality specified in the corresponding Conceptual
 * Domain. Optionally, a unit of measure precision may be specified, as the
 * number of decimal places to be supported in the associated Data Element
 * values. This precision shall be considered a default that may be overridden
 * for any particular Data Element.
 * 
 * @author anil
 * 
 */
public interface UnitOfMeasureResource extends MDRResource, UnitOfMeasure {

	/**
	 * Sets {@link Vocabulary#unitOfMeasureName} property with given value
	 * 
	 * @param unitOfMeasureName
	 */
	void setUnitOfMeasureName(String unitOfMeasureName);

	/**
	 * 
	 * @return value of {@link Vocabulary#unitOfMeasureName} property
	 */
	String getUnitOfMeasureName();

	/**
	 * Sets {@link Vocabulary#unitOfMeasurePrecision} with given value
	 * 
	 * @param unitOfMeasurePrecision
	 */
	void setUnitOfMeasurePrecision(Integer unitOfMeasurePrecision);

	/**
	 * 
	 * @return value of {@link Vocabulary#unitOfMeasurePrecision} property
	 */
	Integer getUnitOfMeasurePrecision();

}