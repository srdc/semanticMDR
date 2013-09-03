package tr.com.srdc.mdr.core.model.iso11179.composite;

import tr.com.srdc.mdr.core.api.composite.Datatype;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;

/**
 * 
 * A Datatype is designated by a data type name, and described by a datatype
 * description. The datatype name is usually drawn from some external source,
 * which is designated by a datatype scheme reference. Additional information
 * may optionally be provided using the datatype annotation.
 * 
 * @author anil
 * @author mert
 * 
 */
public interface DatatypeResource extends MDRResource, Datatype {

	/**
	 * sets {@link Vocabulary#datatypeName} property with given value
	 * 
	 * @param datatypeName
	 */
	void setDatatypeName(String datatypeName);

	/**
	 * 
	 * @return value of {@link Vocabulary#datatypeName} property
	 */
	String getDatatypeName();

	/**
	 * sets {@link Vocabulary#datatypeDescription} proprety with given value
	 * 
	 * @param datatypeDescription
	 *            if given <code>null</code>, removes property
	 */
	void setDatatypeDescription(String datatypeDescription);

	/**
	 * 
	 * @return value of {@link Vocabulary#datatypeDescription} property,<br>
	 *         <code>null</code> if such property does not exist
	 */
	String getDatatypeDescription();

	/**
	 * sets {@link Vocabulary#datatypeSchemeReference} property with given value
	 * 
	 * @param datatypeSchemeReference
	 */
	void setDatatypeSchemeReference(String datatypeSchemeReference);

	/**
	 * 
	 * @return value of {@link Vocabulary#datatypeSchemeReference} property
	 */
	String getDatatypeSchemeReference();

	/**
	 * sets {@link Vocabulary#datatypeAnnotation} proprety with given value
	 * 
	 * @param datatypeAnnotation
	 *            if given <code>null</code>, removes property
	 */
	void setDatatypeAnnotation(String datatypeAnnotation);

	/**
	 * 
	 * @return value of {@link Vocabulary#datatypeAnnotation} property,<br>
	 *         <code>null</code> if such property does not exist
	 */
	String getDatatypeAnnotation();

}