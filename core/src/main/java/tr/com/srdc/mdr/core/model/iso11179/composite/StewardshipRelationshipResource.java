package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.Abbreviation;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;



/**
 * 
 * {@link StewardshipRelationshipResource} is used to represent the relation between
 * <ul>
 * <li>Stewardship</li>
 * <li>administering - administratedBy Relation between {@link OrganizationResource}
 * and {@link AdministeredItemResource}</li>
 * </ul>
 * <br>
 * Since {@link Abbreviation} is backed by Jena - Triple Store, such an holder class
 * is required to represent n-ary relations
 * 
 * @author anil
 * 
 */
public interface StewardshipRelationshipResource extends MDRResource {

	/**
	 * Sets {@link Vocabulary#stewardshipOrganization} property with given value
	 * 
	 * @param stewardshipOrganization
	 *            value of {@link Vocabulary#stewardshipOrganization} property <br>
	 */
	void setStewardshipOrganization(OrganizationResource stewardshipOrganization);

	/**
	 * 
	 * @return {@link OrganizationResource} object, value of
	 *         {@link Vocabulary#stewardshipOrganization} property, <br>
	 */
	OrganizationResource getStewardshipOrganization();

	/**
	 * Sets {@link Vocabulary#administer} property with given value
	 * 
	 * @param administer
	 *            value of {@link Vocabulary#administer} property <br>
	 */
	void setAdminister(StewardshipResource administer);

	/**
	 * 
	 * @return {@link StewardshipResource} object, value of
	 *         {@link Vocabulary#administer} property, <br>
	 */
	StewardshipResource getAdminister();

	/**
	 * Adds {@link Vocabulary#administeredItems} property with given value
	 * 
	 * @param administeredItems
	 *            value of {@link Vocabulary#administeredItems} property
	 */
	void addAdministratedItems(AdministeredItemResource administeredItem);

	/**
	 * 
	 * @return {@link List} of {@link Vocabulary#administeredItems} property
	 *         values
	 */
	List<AdministeredItemResource> getAdministratedItems() throws MDRException;

	/**
	 * Method for removing the administratedItems property to the
	 * {@link StewardshipRelationshipResource} with the value of
	 * {@link AdministeredItemResource}.
	 * 
	 * @param administratedItems
	 */
	void removeAdministratedItem(AdministeredItemResource administratedItems);

}