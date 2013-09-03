package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.Calendar;

import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;



/**
 * The Administration Record instance provides a basis for identifying, naming,
 * defining, classifying and recording administrative information about the
 * Administered Item in the registry.
 * 
 * @author anil
 * 
 */
public interface AdministrationRecordResource extends MDRResource {

	/**
	 * Method for setting the {@link Vocabulary#administeredItemIdentifier} for
	 * {@link AdministrationRecordResource}.
	 * 
	 * @param administeredItemIdentifier
	 */
	void setAdministeredItemIdentifier(
			ItemIdentifierResource administeredItemIdentifier);

	/**
	 * Method for getting the {@link Vocabulary#administeredItemIdentifier} of
	 * {@link AdministrationRecordResource}.
	 * 
	 * @return {@link ItemIdentifierResource}
	 */
	ItemIdentifierResource getAdministeredItemIdentifier();

	/**
	 * Method for setting the {@link Vocabulary#registrationStatus} for
	 * {@link AdministrationRecordResource}.
	 * 
	 * @param registrationStatus
	 */
	void setRegistrationStatus(String registrationStatus);

	/**
	 * Method for getting the {@link Vocabulary#registrationStatus} of
	 * {@link AdministrationRecordResource}.
	 * 
	 * @return {@link Vocabulary#registrationStatus}
	 */
	String getRegistrationStatus();

	/**
	 * Method for setting the {@link Vocabulary#administrativeStatus} for
	 * {@link AdministrationRecordResource}.
	 * 
	 * @param administrativeStatus
	 */
	void setAdministrativeStatus(String administrativeStatus);

	/**
	 * Method for getting the {@link Vocabulary#administrativeStatus} of
	 * {@link AdministrationRecordResource}.
	 * 
	 * @return {@link Vocabulary#administrativeStatus}
	 */
	String getAdministrativeStatus();

	/**
	 * Method for setting the {@link Vocabulary#creationDate} for
	 * {@link AdministrationRecordResource}.
	 * 
	 * @param creationDate
	 */
	void setCreationDate(Calendar creationDate);

	/**
	 * Method for getting the {@link Vocabulary#creationDate} of
	 * {@link AdministrationRecordResource}.
	 * 
	 * @return {@link Vocabulary#creationDate}
	 */
	Calendar getCreationDate();

	/**
	 * Method for setting the {@link Vocabulary#lastChangeDate} and the
	 * {@link Vocabulary#changeDescription} for {@link AdministrationRecordResource}
	 * .
	 * 
	 * @param lastChangeDate
	 * @param changeDescription
	 */
	void setLastChangeDate(Calendar lastChangeDate, String changeDescription);

	/**
	 * Method for getting the {@link Vocabulary#lastChangeDate} of
	 * {@link AdministrationRecordResource}.
	 * 
	 * @return {@link Vocabulary#lastChangeDate}
	 */
	Calendar getLastChangeDate();

	/**
	 * Method for getting the {@link Vocabulary#changeDescription} of
	 * {@link AdministrationRecordResource}.
	 * 
	 * @return {@link Vocabulary#changeDescription}
	 */
	String getChangeDescription();

	/**
	 * Method for setting the {@link Vocabulary#effectiveDate} for
	 * {@link AdministrationRecordResource}.
	 * 
	 * @param effectiveDate
	 */
	void setEffectiveDate(Calendar effectiveDate);

	/**
	 * Method for getting the {@link Vocabulary#effectiveDate} of
	 * {@link AdministrationRecordResource}.
	 * 
	 * @return {@link Vocabulary#effectiveDate}
	 */
	Calendar getEffectiveDate();

	/**
	 * Method for setting the {@link Vocabulary#untilDate} for
	 * {@link AdministrationRecordResource}.
	 * 
	 * @param untilDate
	 */
	void setUntilDate(Calendar untilDate);

	/**
	 * Method for getting the {@link Vocabulary#untilDate} of
	 * {@link AdministrationRecordResource}.
	 * 
	 * @return {@link Vocabulary#untilDate}
	 */
	Calendar getUntilDate();

	/**
	 * Method for setting the {@link Vocabulary#administrativeNote} for
	 * {@link AdministrationRecordResource}.
	 * 
	 * @param administrativeNote
	 */
	void setAdministrativeNote(String administrativeNote);

	/**
	 * Method for getting the {@link Vocabulary#administrativeNote} of
	 * {@link AdministrationRecordResource}.
	 * 
	 * @return {@link Vocabulary#administrativeNote}
	 */
	String getAdministrativeNote();

	/**
	 * Method for setting the {@link Vocabulary#explanatoryComment} for
	 * {@link AdministrationRecordResource}.
	 * 
	 * @param explanatoryComment
	 */
	void setExplanatoryComment(String explanatoryComment);

	/**
	 * Method for getting the {@link Vocabulary#explanatoryComment} of
	 * {@link AdministrationRecordResource}.
	 * 
	 * @return {@link Vocabulary#explanatoryComment}
	 */
	String getExplanatoryComment();

	/**
	 * Method for setting the {@link Vocabulary#unresolvedIssue} for
	 * {@link AdministrationRecordResource}.
	 * 
	 * @param unresolvedIssue
	 */
	void setUnresolvedIssue(String unresolvedIssue);

	/**
	 * Method for getting the {@link Vocabulary#unresolvedIssue} of
	 * {@link AdministrationRecordResource}.
	 * 
	 * @return {@link Vocabulary#unresolvedIssue}
	 */
	String getUnresolvedIssue();

	/**
	 * Method for setting the {@link Vocabulary#origin} for
	 * {@link AdministrationRecordResource}.
	 * 
	 * @param origin
	 */
	void setOrigin(String origin);

	/**
	 * Method for getting the {@link Vocabulary#origin} of
	 * {@link AdministrationRecordResource}.
	 * 
	 * @return {@link Vocabulary#origin}
	 */
	String getOrigin();

}