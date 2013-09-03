package tr.com.srdc.mdr.core.api.composite;

import tr.com.srdc.mdr.core.api.MDRNode;
import tr.com.srdc.mdr.core.model.iso11179.composite.DatatypeResource;

/**
 * A Value Domain is associated with a Datatype — a set of distinct values,
 * characterized by properties of those values and by operations on those
 * values, for example the category used for the collection of letters, digits,
 * and/or symbols to depict values of a Data Element determined by the
 * operations that may be performed on the Data Element.<br>
 * A Datatype is designated by a data type name, and described by a datatype
 * description. The datatype name is usually drawn from some external source,
 * which is designated by a datatype scheme reference. Additional information
 * may optionally be provided using the datatype annotation
 * 
 * @author anil
 * 
 */
public interface Datatype extends MDRNode {

	@Override
	/**
	 * @return the {@link DatatypeResource} version this instance.
	 */
	DatatypeResource asMDRResource();

	String getName();

	String getSchemeReference();

}
