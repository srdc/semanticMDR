package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.Abbreviation;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;



/**
 * 
 * {@link SubmissionRelationshipResource} is used to represent the relation between
 * <ul>
 * <li>Submission</li>
 * <li>submitting - submittedBy Relation between {@link OrganizationResource} and
 * {@link AdministeredItemResource}</li>
 * </ul>
 * <br>
 * Since {@link Abbreviation} is backed by Jena - Triple Store, such an holder class
 * is required to represent n-ary relations
 * 
 * @author anil
 * 
 */
public interface SubmissionRelationshipResource extends MDRResource {

	/**
	 * Sets {@link Vocabulary#submissionOrganization} property with given value
	 * 
	 * @param submissionOrganization
	 *            value of {@link Vocabulary#submissionOrganization} property <br>
	 */
	void setSubmissionOrganization(OrganizationResource submissionOrganization);

	/**
	 * 
	 * @return {@link OrganizationResource} object, value of
	 *         {@link Vocabulary#submissionOrganization} property, <br>
	 */
	OrganizationResource getSubmissionOrganization();

	/**
	 * Sets {@link Vocabulary#submitter} property with given value
	 * 
	 * @param submitter
	 *            value of {@link Vocabulary#submitter} property <br>
	 */
	void setSubmitter(SubmissionResource submitter);

	/**
	 * 
	 * @return {@link SubmissionResource} object, value of
	 *         {@link Vocabulary#submitter} property, <br>
	 */
	SubmissionResource getSubmitter();

	/**
	 * Adds {@link Vocabulary#submittedItems} property with given value
	 * 
	 * @param submittedItems
	 *            value of {@link Vocabulary#submittedItems} property
	 */
	void addSubmittedItems(AdministeredItemResource submittedItems);

	/**
	 * 
	 * @return {@link List} of {@link Vocabulary#submittedItems} property values
	 */
	List<AdministeredItemResource> getSubmittedItems() throws MDRException;

	/**
	 * Method for removing the administratedItems property to the
	 * {@link SubmissionRelationshipResource} with the value of
	 * {@link AdministeredItemResource}.
	 * 
	 * @param submittedItems
	 */
	void removeSumittedItem(AdministeredItemResource submittedItems);

}