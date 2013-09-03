package tr.com.srdc.mdr.core.model.iso11179;

import java.util.List;

import tr.com.srdc.mdr.core.api.ai.DataElement;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementDerivationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementExampleResource;



/**
 * A Data Element is considered to be a basic unit of data of interest to an
 * organization. It is a unit of data for which the definition, identification,
 * representation, and permissible values are specified by means of a set of
 * attributes.
 * 
 * @author pacaci
 * @author mert
 * @author serike
 * @author alp
 * @author anil
 * 
 */
public interface DataElementResource extends AdministeredItemResource, DataElement {

	/**
	 * Set the {@link AdministrationRecordResource} of {@link DataElementResource}.
	 * 
	 * @param propertyAdministrationRecord
	 * <br>
	 *            An {@link AdministrationRecordResource} of a {@link DataElementResource}
	 *            .
	 */
	void setAdministrationRecord(
			AdministrationRecordResource dataElementAdministrationRecord);

	/**
	 * @return the {@link AdministrationRecordResource} of a {@link DataElementResource} .
	 */
	AdministrationRecordResource getAdministrationRecord();

	/**
	 * Set the {@link Vocabulary#representationClassQualifier} of
	 * {@link DataElementResource}.
	 * 
	 * @param representationClassQualifier
	 */
	void setRepresentationClassQualifier(String representationClassQualifier);

	/**
	 * @return the {@link Vocabulary#representationClassQualifier} of a
	 *         {@link DataElementResource}.
	 */
	String getRepresentationClassQualifier();

	/**
	 * Set the {@link Vocabulary#dataElementPrecision} of this
	 * {@link DataElementResource}.
	 * 
	 * @param dataElementPrecision
	 * <br>
	 *            The degree of specificity for a {@link DataElementResource}.
	 */
	void setDataElementPrecision(Integer dataElementPrecision);

	/**
	 * 
	 * @return the {@link Vocabulary#dataElementPrecision} of a
	 *         {@link DataElementResource}.<br>
	 */
	Integer getDataElementPrecision();

	/**
	 * Set the {@link Vocabulary#expressingDataElementConceptExpression} of this
	 * {@link DataElementResource}.
	 * 
	 * @param expressingDataElementConceptExpression
	 */
	void setExpressingDataElementConceptExpression(
			DataElementConceptResource expressingDataElementConceptExpression);

	/**
	 * 
	 * @return {@link Vocabulary#expressingDataElementConceptExpression} of a
	 *         {@link DataElementResource}.<br>
	 */
	DataElementConceptResource getExpressingDataElementConceptExpression();

	/**
	 * Set the {@link Vocabulary#representedByDataElementRepresentation} of this
	 * {@link DataElementResource}.
	 * 
	 * @param representedByDataElementRepresentation
	 */
	void setRepresentedByDataElementRepresentation(
			ValueDomainResource representedByDataElementRepresentation);

	/**
	 * 
	 * @return the {@link Vocabulary#representedByDataElementRepresentation} of
	 *         a {@link DataElementResource}.<br>
	 */
	ValueDomainResource getRepresentedByDataElementRepresentation();

	/**
	 * Adds {@link Vocabulary#inputToDerivationInput} property with given value
	 * 
	 * @param inputToDerivationInput
	 */
	void addInputToDerivationInput(
			DataElementDerivationResource inputToDerivationInput);

	/**
	 * Removes {@link Vocabulary#inputToDerivationInput} with given value
	 * 
	 * @param inputToDerivationInput
	 *            if given <code>null</code>, throws
	 *            {@link IllegalArgumentException}
	 */
	void removeInputToDerivationInput(
			DataElementDerivationResource inputToDerivationInput);

	/**
	 * 
	 * @return {@link List} of all {@link Vocabulary#inputToDerivationInput}
	 *         property values
	 * @throws MDRException
	 */
	List<DataElementDerivationResource> getInputToDerivationInputs()
			throws MDRException;

	/**
	 * Sets {@link Vocabulary#derivedFromDerivationOutput} property with given
	 * value
	 * 
	 * @param derivedFromDerivationOutput
	 *            if given <code>null</code>, removes property
	 */
	void setDerivedFromDerivationOutput(
			DataElementDerivationResource derivedFromDerivationOutput);

	/**
	 * 
	 * @return value of {@link Vocabulary#derivedFromDerivationOutput} property,
	 *         <code>null</code> if such property does not exist
	 */
	DataElementDerivationResource getDerivedFromDerivationOutput();

	/**
	 * Add {@link Vocabulary#exemplifiedByExemplification} property with given
	 * value
	 * 
	 * @param exemplifiedByExemplification
	 */
	void addExemplifiedByExemplification(
			DataElementExampleResource exemplifiedByExemplification);

	/**
	 * Removes {@link Vocabulary#exemplifiedByExemplification} with given value
	 * 
	 * @param exemplifiedByExemplification
	 *            if given <code>null</code> throws
	 *            {@link IllegalArgumentException}
	 */
	void removeExemplifiedByExemplification(
			DataElementExampleResource exemplifiedByExemplification);

	/**
	 * 
	 * @return {@link List} of all
	 *         {@link Vocabulary#exemplifiedByExemplification} property values
	 * @throws MDRException
	 */
	List<DataElementExampleResource> getExemplifiedByExemplifications()
			throws MDRException;

	/**
	 * Sets {@link Vocabulary#typedByDataElementRepresentationClass} property
	 * with given value
	 * 
	 * @param typedByDataElementRepresentationClass
	 *            if given null, effectively removes property
	 */
	void setTypedByDataElementRepresentationClass(
			RepresentationClassResource typedByDataElementRepresentationClass);

	/**
	 * 
	 * @return Value of {@link Vocabulary#typedByDataElementRepresentationClass}
	 *         property
	 */
	RepresentationClassResource getTypedByDataElementRepresentationClass();

}