package tr.com.srdc.mdr.core.model.iso11179.composite;

import tr.com.srdc.mdr.core.api.composite.Designation;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;

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
public interface DesignationResource extends MDRResource, Designation {

	/**
	 * Sets {@link Vocabulary#namingEntryOf} property with given value
	 * 
	 * @param namingEntryOf
	 */
	void setNamingEntryOf(LanguageSectionResource namingEntryOf);

	/**
	 * 
	 * @return value of {@link Vocabulary#namingEntryOf} property
	 */
	LanguageSectionResource getNamingEntryOf();

	/**
	 * Sets {@link Vocabulary#name} property with given String
	 * 
	 * @param name
	 *            Name of the Designation
	 */
	void setName(String name);

	/**
	 * 
	 * @return value of the {@link Vocabulary#name} property
	 */
	String getName();

	/**
	 * Sets {@link Vocabulary#preferredDesignation} property with given value
	 * 
	 * @param preferredDesignation
	 *            <code>true</code> or <code>false</code>
	 */
	void setPreferredDesignation(boolean preferredDesignation);

	/**
	 * 
	 * @return <code>true</code> if Designation is selected as Preferred
	 *         Designation, <br>
	 *         <code>false</code> otherwise
	 */
	boolean isPreferredDesignation();

	/**
	 * Sets {@link Vocabulary#specificallyReferencing}
	 * 
	 * @param specificallyReferencing
	 *            if given <code>null</code>, removes property
	 */
	void setSpecificallyReferencing(DefinitionResource specificallyReferencing);

	/**
	 * 
	 * @return value of the {@link Vocabulary#specificallyReferencing} property,
	 *         <code>null</code> if such property does not exist
	 */
	DefinitionResource getSpecificallyReferencing();

}