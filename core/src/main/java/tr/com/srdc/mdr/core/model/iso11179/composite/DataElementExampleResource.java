package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.api.composite.DataElementExample;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.DataElementResource;



/**
 * 
 * A Data Element may have Data Element Examples that are used to provide
 * representative samples of the Data Element.
 * 
 * @author anil
 * 
 */
public interface DataElementExampleResource extends MDRResource,
		DataElementExample {

	/**
	 * sets {@link Vocabulary#dataElementExampleItem} property with given value
	 * 
	 * @param dataElementExampleItem
	 */
	void setDataElementExampleItem(String dataElementExampleItem);

	/**
	 * 
	 * @return value of {@link Vocabulary#dataElementExampleItem} property
	 */
	String getDataElementExampleItem();

	/**
	 * Adds {@link Vocabulary#exemplifyingExemplification} property with given
	 * value
	 * 
	 * @param exemplifyingExemplification
	 *            if given <code>null</code> throws
	 *            {@link IllegalArgumentException}
	 */
	void addExemplifyingExemplification(
			DataElementResource exemplifyingExemplification);

	/**
	 * removes {@link Vocabulary#exemplifyingExemplification} with given value
	 * 
	 * @param exemplifyingExemplification
	 *            there should be at least 1 value of the property, if try to
	 *            remove all, throws {@link IllegalArgumentException}
	 */
	void removeExemplifyingExemplification(
			DataElementResource exemplifyingExemplification);

	/**
	 * 
	 * @return List of all {@link Vocabulary#exemplifyingExemplification}
	 *         property values
	 * @throws MDRException
	 */
	List<DataElementResource> getExemplifyingExemplifications() throws MDRException;

}