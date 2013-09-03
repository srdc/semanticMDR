package tr.com.srdc.mdr.core.model.iso11179;

import java.util.List;

import tr.com.srdc.mdr.core.api.ai.ValueDomain;
import tr.com.srdc.mdr.core.model.Abbreviation;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DatatypeResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.UnitOfMeasureResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueDomainRelationshipAssociationResource;



/**
 * A set of Permissible Values. The Value Domain provides representation, but
 * has no INDication as to what Data Element Concept the Values may be
 * associated with nor what the Values mean. The Permissible Values may either
 * be enumerated or expressed via a description.
 * 
 * @author pacaci
 * @author mert
 * @author serike
 * @author alp
 * 
 */
public interface ValueDomainResource extends AdministeredItemResource, ValueDomain {

	/**
	 * @return the {@link AdministrationRecordResource} of a {@link ValueDomainResource} .
	 */
	AdministrationRecordResource getAdministrationRecord();

	/**
	 * Set the {@link AdministrationRecordResource} of {@link ValueDomainResource}.
	 * 
	 * @param valueDomainAdministrationRecord
	 * <br>
	 *            An {@link AdministrationRecordResource} of a {@link ValueDomainResource}
	 *            .
	 */
	void setAdministrationRecord(
			AdministrationRecordResource valueDomainAdministrationRecord);

	/**
	 * Set the {@link Vocabulary#valueDomainDatatype} of this
	 * {@link ValueDomainResource}.
	 * 
	 * @param valueDomainDatatype
	 * <br>
	 *            An {@link DatatypeResource} of a {@link ValueDomainResource}.
	 */
	void setValueDomainDatatype(DatatypeResource valueDomainDatatype);

	/**
	 * 
	 * @return the {@link DatatypeResource} of a {@link ValueDomainResource}.
	 */
	DatatypeResource getValueDomainDatatype();

	/**
	 * Set the {@link Vocabulary#valueDomainUnitOfMeasure} of this
	 * {@link ValueDomainResource}.
	 * 
	 * @param valueDomainUnitOfMeasure
	 * <br>
	 *            An {@link UnitOfMeasureResource} of a {@link ValueDomainResource}.
	 */
	void setValueDomainUnitOfMeasure(UnitOfMeasureResource valueDomainUnitOfMeasure);

	/**
	 * 
	 * @return the {@link UnitOfMeasureResource} of a {@link ValueDomainResource}.
	 */
	UnitOfMeasureResource getValueDomainUnitOfMeasure();

	/**
	 * Set the {@link Vocabulary#valueDomainMaximumCharacterQuantity} of this
	 * {@link ValueDomainResource}.
	 * 
	 * @param valueDomainMaximumCharacterQuantity
	 */
	void setValueDomainMaximumCharacterQuantity(
			Integer valueDomainMaximumCharacterQuantity);

	/**
	 * 
	 * @return the {@link Vocabulary#valueDomainMaximumCharacterQuantity} of a
	 *         {@link ValueDomainResource}.
	 */
	Integer getValueDomainMaximumCharacterQuantity();

	/**
	 * Set the {@link Vocabulary#valueDomainFormat} of this
	 * {@link ValueDomainResource}
	 * 
	 * @param valueDomainFormat
	 */
	void setValueDomainFormat(String valueDomainFormat);

	/**
	 * 
	 * @return the {@link Vocabulary#valueDomainFormat} of a
	 *         {@link ValueDomainResource}
	 */
	String getValueDomainFormat();

	/**
	 * Set the {@link ConceptualDomainResource} of {@link ValueDomainResource}.
	 * 
	 * @param representingConceptualDomainRepresentation
	 */
	void setRepresentingConceptualDomainRepresentation(
			ConceptualDomainResource representingConceptualDomainRepresentation);

	/**
	 * @return the {@link ConceptualDomainResource} of a {@link ValueDomainResource}.
	 */
	ConceptualDomainResource getRepresentingConceptualDomainRepresentation();

	/**
	 * Adds {@link Vocabulary#representingDataElementRepresentation} property
	 * with given value
	 * 
	 * @param submitting
	 *            value of
	 *            {@link Vocabulary#representingDataElementRepresentation}
	 *            property
	 */
	void addRepresentingDataElementRepresentation(
			DataElementResource representingDataElementRepresentation);

	/**
	 * 
	 * @return {@link List} of
	 *         {@link Vocabulary#representingDataElementRepresentation} property
	 *         values
	 */
	List<DataElementResource> getRepresentingDataElementRepresentations()
			throws MDRException;

	/**
	 * Removes the {@link Vocabulary#representingDataElementRepresentation}
	 * property with given value
	 * 
	 * @param providing
	 *            value of
	 *            {@link Vocabulary#representingDataElementRepresentation}
	 *            property to be removed.
	 */
	void removeRepresentingDataElementRepresentation(
			DataElementResource representingDataElementRepresentation);

	/**
	 * Set the {@link RepresentationClassResource} of {@link ValueDomainResource}.
	 * 
	 * @param typedByValueDomainRepresentationClass
	 * <br>
	 *            Iff given value null , removes it from {@link Abbreviation}.
	 */
	void setTypedByValueDomainRepresentationClass(
			RepresentationClassResource typedByValueDomainRepresentationClass);

	/**
	 * 
	 * @return the {@link RepresentationClassResource} of a {@link ValueDomainResource}.
	 */
	RepresentationClassResource getTypedByValueDomainRepresentationClass();

	/**
	 * Adds {@link Vocabulary#relatedToValueDomainRelationship} property with
	 * given value
	 * 
	 * @param relatedToValueDomainRelationship
	 */
	void addRelatedToValueDomainRelationship(
			ValueDomainRelationshipAssociationResource relatedToValueDomainRelationship);

	/**
	 * Removes {@link Vocabulary#relatedToValueDomainRelationship} property with
	 * given value
	 * 
	 * @param relatedToValueDomainRelationship
	 */
	void removeRelatedToValueDomainRelationship(
			ValueDomainRelationshipAssociationResource relatedToValueDomainRelationship);

	/**
	 * 
	 * @return {@link List} of all
	 *         {@link Vocabulary#relatedToValueDomainRelationship} property
	 *         values
	 * @throws MDRException
	 */
	List<ValueDomainRelationshipAssociationResource> getRelatedToValueDomainRelationships()
			throws MDRException;

}