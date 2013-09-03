package tr.com.srdc.mdr.core.model.iso11179;

import java.util.List;

import tr.com.srdc.mdr.core.api.ai.AdministeredItem;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministeredItemContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ClassificationSchemeItemResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ReferenceDocumentResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionRelationshipResource;



/**
 * 
 * An Administered Item may be any one of those types listed in Figure 2 which
 * is specified in ISO/IEC 11179-3. Each instance of an Administered Item
 * encapsulates its own Administration Record.
 * 
 * @author anil
 * 
 */

public interface AdministeredItemResource extends MDRResource, AdministeredItem {

	/**
	 * Abstract method for setting the specific {@link AdministrationRecordResource}
	 * for the {@link AdministeredItemResource}.
	 * 
	 * @param administrationRecord
	 */
	void setAdministrationRecord(AdministrationRecordResource administrationRecord);

	/**
	 * Abstract method for getting the specific {@link AdministrationRecordResource}
	 * for the {@link AdministeredItemResource}.
	 * 
	 * @return AdministrationRecord
	 */
	AdministrationRecordResource getAdministrationRecord();

	/**
	 * Method for setting the value of the submittedBy property of the
	 * {@link AdministeredItemResource} with the value of
	 * {@link SubmissionRelationshipResource} .
	 * 
	 * @param SubmissionRelationshipResource
	 */
	void setSubmittedBy(SubmissionRelationshipResource submittedBy);

	/**
	 * Method for getting the value of submittedBy property of the
	 * {@link AdministeredItemResource}
	 * 
	 * @return value of the submittedBy property
	 */
	SubmissionRelationshipResource getSubmittedBy();

	/**
	 * Method for adding the classifiedBy property to the
	 * {@link AdministeredItemResource} with the value of
	 * {@link ClassificationSchemeItemResource}.
	 * 
	 * @param classificationSchemeItem
	 */
	void addClassifiedBy(ClassificationSchemeItemResource classificationSchemeItem);

	/**
	 * Method for getting the list of classifiedBy property values of the
	 * {@link AdministeredItemResource}
	 * 
	 * @return List of all classifiedBy property values
	 */
	List<ClassificationSchemeItemResource> getClassifiedBy() throws MDRException;

	/**
	 * Method for removing the classifiedBy property to the
	 * {@link AdministeredItemResource} with the value of
	 * {@link ClassificationSchemeItemResource}.
	 * 
	 * @param classificationSchemeItem
	 */
	void removeClassifiedBy(ClassificationSchemeItemResource classificationSchemeItem);

	/**
	 * Method for setting the value of the administeredBy property of the
	 * {@link AdministeredItemResource} with the value of
	 * {@link StewardshipRelationshipResource}.
	 * 
	 * @param StewardshipRelationshipResource
	 */
	void setAdministeredBy(StewardshipRelationshipResource administeredBy);

	/**
	 * Method for getting the value of administeredBy property of the
	 * {@link AdministeredItemResource}
	 * 
	 * @return value of the administeredBy property
	 */
	StewardshipRelationshipResource getAdministeredBy();

	/**
	 * Method for setting the value of the registeredBy property of the
	 * {@link AdministeredItemResource} with the value of
	 * {@link RegistrationAuthorityResource}.
	 * 
	 * @param RegistrationAuthorityResource
	 */
	void setRegisteredBy(RegistrationAuthorityResource registeredBy);

	/**
	 * Method for getting the value of registeredBy property of the
	 * {@link AdministeredItemResource}
	 * 
	 * @return value of the registeredBy property
	 */
	RegistrationAuthorityResource getRegisteredBy();

	/**
	 * Method for adding the having property to the {@link AdministeredItemResource}
	 * with the value of {@link AdministeredItemContextResource}.
	 * 
	 * @param AdministeredItemContextResource
	 */
	void addHaving(AdministeredItemContextResource having);

	/**
	 * Method for getting the list of having property values of the
	 * {@link AdministeredItemResource}
	 * 
	 * @return List of all having property values
	 */
	List<AdministeredItemContextResource> getHavings() throws MDRException;

	/**
	 * Method for removing the having property to the
	 * {@link AdministeredItemResource} with the value of
	 * {@link AdministeredItemContextResource}.
	 * 
	 * @param AdministeredItemContextResource
	 */
	void removeHaving(AdministeredItemContextResource having);

	/**
	 * Adds {@link Vocabulary#describedBy} property with given value
	 * 
	 * @param describedBy
	 *            value of {@link Vocabulary#describedBy} property.
	 */
	void addDescribedBy(ReferenceDocumentResource describedBy);

	/**
	 * Removes the {@link Vocabulary#describedBy} property with given value
	 * 
	 * @param describedBy
	 *            value of {@link Vocabulary#describedBy} property to be
	 *            removed.
	 */
	void removeDescribedBY(ReferenceDocumentResource describedBy);

	/**
	 * 
	 * @return {@link List} of {@link Vocabulary#describedBy} property values
	 */
	List<ReferenceDocumentResource> getDescribedBy() throws MDRException;

}