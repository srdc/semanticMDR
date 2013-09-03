//TODO: ClassificationSchemeItemRelationship created by Classification scheme, how to relate items other than constructor?
package tr.com.srdc.mdr.core.api.composite;

import tr.com.srdc.mdr.core.api.MDRNode;
import tr.com.srdc.mdr.core.model.iso11179.composite.ClassificationSchemeItemRelationshipResource;

/**
 * 
 * A Classification Scheme Item Relationship associates two or more
 * Classification Scheme Items within a Classification Scheme. Such
 * relationships serve to assist navigation through a large number of
 * Classification Scheme Items.
 * 
 * @author anil
 * 
 */
public interface ClassificationSchemeItemRelationship extends MDRNode {

	@Override
	/**
	 * @return the {@link ClassificationSchemeItemRelationshipResource} version this instance.
	 */
	ClassificationSchemeItemRelationshipResource asMDRResource();

}
