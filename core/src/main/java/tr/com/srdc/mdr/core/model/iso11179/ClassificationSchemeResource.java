package tr.com.srdc.mdr.core.model.iso11179;

import java.util.List;

import tr.com.srdc.mdr.core.api.ai.ClassificationScheme;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ClassificationSchemeItemResource;



/**
 * 
 * A Classification Scheme may be a taxonomy, a network, an ontology, or any
 * other terminological system. The classification may also be just a list of
 * controlled jenaDatabase of property words (or terms). The list might be taken
 * from the "leaf level" of a taxonomy.<br>
 * <br>
 * 
 * @author anil
 * 
 */
public interface ClassificationSchemeResource extends AdministeredItemResource,
		ClassificationScheme {

	/**
	 * Method for setting the
	 * {@link Vocabulary#classificationSchemeAdministrationRecord} for
	 * {@link ClassificationSchemeResource}.
	 * 
	 * @param classificationSchemeAdministrationRecord
	 */
	void setAdministrationRecord(
			AdministrationRecordResource classificationSchemeAdministrationRecord);

	/**
	 * Method for getting the
	 * {@link Vocabulary#classificationSchemeAdministrationRecord} of
	 * {@link ClassificationSchemeResource}.
	 * 
	 * @return {@link AdministrationRecordResource}
	 */
	AdministrationRecordResource getAdministrationRecord();

	/**
	 * Method for setting the {@link Vocabulary#classificationSchemeTypeName}
	 * for {@link ClassificationSchemeResource}.
	 * 
	 * @param classificationSchemeTypeName
	 */
	void setClassificationSchemeTypeName(String classificationSchemeTypeName);

	/**
	 * Method for getting the {@link Vocabulary#classificationSchemeTypeName} of
	 * {@link ClassificationSchemeResource}.
	 * 
	 * @return classificationSchemeTypeName
	 */
	String getClassificationSchemeTypeName();

	/**
	 * Method for adding the {@link Vocabulary#containing} property to the
	 * {@link ClassificationSchemeResource} with the value of
	 * {@link ClassificationSchemeItemResource}.
	 * 
	 * @param classificationSchemeItem
	 */
	void addContaining(ClassificationSchemeItemResource classificationSchemeItem);

	/**
	 * Method for removeing the {@link Vocabulary#containing} property to the
	 * {@link ClassificationSchemeResource} with the value of
	 * {@link ClassificationSchemeItemResource}.
	 * 
	 * @param classificationSchemeItem
	 */
	void removeContaining(ClassificationSchemeItemResource classificationSchemeItem);

	/**
	 * Method for getting the list of {@link ClassificationSchemeItemResource}
	 * property values for the {@link Vocabulary#containing} property.
	 * 
	 * @return List<{@link ClassificationSchemeItemResource}>
	 * @throws MDRException
	 */
	List<ClassificationSchemeItemResource> getContanining() throws MDRException;

}