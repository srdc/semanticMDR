package tr.com.srdc.mdr.core.api.composite;

import tr.com.srdc.mdr.core.api.MDRNode;
import tr.com.srdc.mdr.core.model.iso11179.composite.DefinitionResource;

/**
 * 
 * The Definition class provides the defining entry of a Language Section in the
 * Terminological Entry for an Administered Item in a particular Context. In
 * other words, it is where the definition for an Administered Item is specified
 * in a particular language for a particular Context. Where multiple Definitions
 * are provided within the same Language Section, one of them may be specified
 * as the preferred definition.
 * 
 * @author anil
 * 
 */
public interface Definition extends MDRNode {

	@Override
	/**
	 * @return the {@link DefinitionResource} version this instance.
	 */
	DefinitionResource asMDRResource();

}
