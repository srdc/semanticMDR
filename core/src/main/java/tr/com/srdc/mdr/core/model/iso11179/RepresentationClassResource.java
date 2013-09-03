package tr.com.srdc.mdr.core.model.iso11179;

import java.util.List;

import tr.com.srdc.mdr.core.api.ai.RepresentationClass;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;



/**
 * @author anil
 * 
 */
public interface RepresentationClassResource extends AdministeredItemResource,
		RepresentationClass {

	/**
	 * @return the {@link AdministrationRecordResource} of a
	 *         {@link RepresentationClassResource}
	 * 
	 */
	AdministrationRecordResource getAdministrationRecord();

	/**
	 * Set the {@link AdministrationRecordResource} of {@link RepresentationClassResource}
	 * .
	 * 
	 * @param representationClassAdministrationRecord
	 * <br>
	 *            An {@link AdministrationRecordResource} of a
	 *            {@link RepresentationClassResource}.
	 */
	void setAdministrationRecord(
			AdministrationRecordResource representationClassAdministrationRecord);

	/**
	 * Adds {@link Vocabulary#typingValueDomainRepresentationClass} property
	 * with given value
	 * 
	 * @param typingValueDomainRepresentationClass
	 *            value of
	 *            {@link Vocabulary#typingValueDomainRepresentationClass}
	 *            property
	 */
	void addTypingValueDomainRepresentationClass(
			ValueDomainResource typingValueDomainRepresentationClass);

	/**
	 * 
	 * @return {@link List} of
	 *         {@link Vocabulary#typingValueDomainRepresentationClass} property
	 *         values
	 * 
	 */
	List<ValueDomainResource> getTypingValueDomainRepresentationClasses()
			throws MDRException;

	/**
	 * Removes the {@link Vocabulary#typingValueDomainRepresentationClass}
	 * property with given value
	 * 
	 * @param typingValueDomainRepresentationClass
	 *            value of
	 *            {@link Vocabulary#typingValueDomainRepresentationClass}
	 *            property to be removed.
	 */
	void removeTypingValueDomainRepresentationClasses(
			ValueDomainResource typingValueDomainRepresentationClass);

	/**
	 * Adds {@link Vocabulary#typingDataElementRepresentationClass} property
	 * with given value
	 * 
	 * @param typingDataElementRepresentationClass
	 */
	void addTypingDataElementRepresentationClass(
			DataElementResource typingDataElementRepresentationClass);

	/**
	 * Removes {@link Vocabulary#typingDataElementRepresentationClass} property
	 * with given value
	 * 
	 * @param typingDataElementRepresentationClass
	 *            if given <code>null</code>, throws
	 *            {@link IllegalArgumentException}
	 */
	void removeTypingDataElementRepresentationClass(
			DataElementResource typingDataElementRepresentationClass);

	/**
	 * 
	 * @return {@link List} of all
	 *         {@link Vocabulary#typingDataElementRepresentationClass} property
	 *         values
	 * @throws MDRException
	 */
	List<DataElementResource> getTypingDataElementRepresentationClasses()
			throws MDRException;

}