package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.api.composite.Organization;
import tr.com.srdc.mdr.core.impl.composite.OrganizationImpl;
import tr.com.srdc.mdr.core.impl.composite.StewardshipRelationshipImpl;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;



/**
 * 
 * An Organization can play one or more roles with respect to a Metadata
 * Registry. The roles currently recognized in this part of ISO/IEC 11179 are:
 * Registration Authority, reference organization, steward (of an Administered
 * Item) – represented by the relationship stewardship – and submitter (of an
 * Administered Item) – represented by the relationship submission
 * 
 * @author anil
 * 
 */
public interface OrganizationResource extends MDRResource, Organization {

	/**
	 * Sets {@link Vocabulary#organizationName} property with given value
	 * 
	 * @param organizationName
	 *            value of {@link Vocabulary#organizationName} property, <br>
	 */
	void setOrganizationName(String organizationName);

	/**
	 * 
	 * @return {@link String} value of {@link Vocabulary#organizationName}
	 *         property, <br>
	 */
	String getOrganizationName();

	/**
	 * Sets {@link Vocabulary#organizationMailAddress} property with given value
	 * 
	 * @param organizationMailAddress
	 *            value of {@link Vocabulary#organizationMailAddress} property, <br>
	 *            Removes property if given <code>null</code>
	 */
	void setOrganizationMailAddress(String organizationMailAddress);

	/**
	 * 
	 * @return {@link String} value of
	 *         {@link Vocabulary#organizationMailAddress} property, <br>
	 *         If property is not set, then returns <code>null</code>
	 */
	String getOrganizationMailAddress();

	/**
	 * Adds {@link Vocabulary#administering} property with given value
	 * 
	 * @param administrating
	 *            value of {@link Vocabulary#administering} property
	 */
	void addAdministrating(StewardshipRelationshipResource administrating);

	/**
	 * 
	 * @return {@link List} of {@link Vocabulary#administering} property values
	 */
	List<StewardshipRelationshipResource> getAdministrating() throws MDRException;

	/**
	 * Method for removing the administrating property to the
	 * {@link OrganizationImpl} with the value of
	 * {@link StewardshipRelationshipImpl}.
	 * 
	 * @param administrating
	 */
	void removeAdministrating(StewardshipRelationshipResource administrating);

	/**
	 * Adds {@link Vocabulary#submitting} property with given value
	 * 
	 * @param submitting
	 *            value of {@link Vocabulary#submitting} property
	 */
	void addSubmitting(SubmissionRelationshipResource submitting);

	/**
	 * 
	 * @return {@link List} of {@link Vocabulary#submitting} property values
	 */
	List<SubmissionRelationshipResource> getSubmitting() throws MDRException;

	/**
	 * Method for removing the submitting property to the
	 * {@link OrganizationResource} with the value of
	 * {@link SubmissionRelationshipResource}.
	 * 
	 * @param submitting
	 */
	void removeSubmitting(SubmissionRelationshipResource submitting);

	/**
	 * Adds {@link Vocabulary#providing} property with given value
	 * 
	 * @param providing
	 *            value of {@link Vocabulary#providing} property.
	 */
	void addProviding(ReferenceDocumentResource providing);

	/**
	 * Removes the {@link Vocabulary#providing} property with given value
	 * 
	 * @param providing
	 *            value of {@link Vocabulary#providing} property to be removed.
	 */
	void removeProviding(ReferenceDocumentResource providing);

	/**
	 * 
	 * @return {@link List} of {@link Vocabulary#providing} property values
	 */
	List<ReferenceDocumentResource> getProviding() throws MDRException;

}