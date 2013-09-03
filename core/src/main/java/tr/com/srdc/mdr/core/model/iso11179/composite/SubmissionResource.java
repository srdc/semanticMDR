package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;



/**
 * 
 * For each Administered Item, an Organization shall be identified as the
 * submitter. This relationship identifies a submission contact for the
 * Administered Item
 * 
 * @author anil
 * 
 */
public interface SubmissionResource extends MDRResource {

	/**
	 * Sets {@link Vocabulary#submissionContact} property with given value
	 * 
	 * @param submissionContact
	 *            value of {@link Vocabulary#submissionContact} property, <br>
	 */
	void setSubmissionContact(ContactResource submissionContact);

	/**
	 * 
	 * @return {@link ContactResource} object, value of
	 *         {@link Vocabulary#submissionContact} property, <br>
	 */
	ContactResource getSubmissionContact();

	/**
	 * Adds {@link Vocabulary#submits} property with given value
	 * 
	 * @param submits
	 *            value of {@link Vocabulary#submits} property
	 */
	void addSubmits(SubmissionRelationshipResource submits);

	/**
	 * 
	 * @return {@link List} of {@link Vocabulary#submits} property values
	 */
	List<SubmissionRelationshipResource> getSubmits() throws MDRException;

	/**
	 * Method to remove submits property of {@link SubmissionResource}
	 * 
	 * @param submits
	 */
	void removeSubmits(SubmissionRelationshipResource submits);

}