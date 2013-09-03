package tr.com.srdc.mdr.core.api.composite;

import java.util.List;

import tr.com.srdc.mdr.core.api.MDRNode;
import tr.com.srdc.mdr.core.api.ai.DataElement;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementExampleResource;



/**
 * A Data Element may have Data Element Examples that are used to provide
 * representative samples of the Data Element.
 * 
 * @author anil
 * 
 */
public interface DataElementExample extends MDRNode {

	@Override
	/**
	 * @return the {@link DataElementExampleResource} version this instance.
	 */
	DataElementExampleResource asMDRResource();

	String getExampleItem();

	/**
	 * 
	 * @return the unmodifiable list of {@link DataElement} exemplified by this
	 *         {@link DataElementExample}
	 * @throws MDRException
	 */
	List<DataElement> getDataElements() throws MDRException;
}
