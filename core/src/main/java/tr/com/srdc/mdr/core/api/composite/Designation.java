package tr.com.srdc.mdr.core.api.composite;

import tr.com.srdc.mdr.core.api.MDRNode;
import tr.com.srdc.mdr.core.model.iso11179.composite.DesignationResource;

/**
 * The Designation class provides the naming entry of a Language Section in the
 * Terminological Entry for an Administered Item in a particular Context. In
 * other words, it is where the name for an Administered Item is specified in a
 * particular language for a particular Context. Where multiple Designations are
 * provided within the same Language Section, one of them may be specified as
 * the preferred designation.
 * 
 * @author anil
 * 
 */
public interface Designation extends MDRNode {

	@Override
	/**
	 * @return the {@link DesignationResource} version this instance.
	 */
	DesignationResource asMDRResource();

}
