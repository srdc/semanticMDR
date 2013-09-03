package tr.com.srdc.mdr.core.impl.composite;

import java.util.Collections;
import java.util.List;

import tr.com.srdc.mdr.core.api.ai.DataElement;
import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.DataElementResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementExampleResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * A Data Element may have Data Element Examples that are used to provide
 * representative samples of the Data Element.
 * 
 * @author anil
 * 
 */
public class DataElementExampleImpl extends AbstractMDRResource implements
		DataElementExampleResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createDataElementExample(String, DataElementResource)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param dataElementExampleItem
	 * @param exemplifyingExemplification
	 * @param mdrDatabase
	 */

	// TODO dataElementExample might be contain more than one
	// dataElementExampleItem
	public DataElementExampleImpl(Node n, EnhGraph g,
			String dataElementExampleItem,
			DataElementResource exemplifyingExemplification,
			MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().DataElementExample);
		setDataElementExampleItem(dataElementExampleItem);
		addExemplifyingExemplification(exemplifyingExemplification);
	}

	public DataElementExampleImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setDataElementExampleItem(String dataElementExampleItem) {
		if (Util.isNull(dataElementExampleItem)) {
			throw new IllegalArgumentException(
					"An Item must be specified for DataElementExample.");
		}
		setPropertyValue(mdrDatabase.getVocabulary().dataElementExampleItem,
				mdrDatabase.getUtil()
						.createTypedLiteral(dataElementExampleItem));
	}

	@Override
	public String getDataElementExampleItem() {
		RDFNode dataElementExampleItem = getPropertyValue(mdrDatabase
				.getVocabulary().dataElementExampleItem);
		return dataElementExampleItem.asLiteral().getString();
	}

	@Override
	public void addExemplifyingExemplification(
			DataElementResource exemplifyingExemplification) {
		if (exemplifyingExemplification == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added");
		}
		addProperty(mdrDatabase.getVocabulary().exemplifyingExemplification,
				exemplifyingExemplification);
	}

	@Override
	public void removeExemplifyingExemplification(
			DataElementResource exemplifyingExemplification) {
		if (exemplifyingExemplification == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed");
		}
		if (listPropertyValues(
				mdrDatabase.getVocabulary().exemplifyingExemplification)
				.toList().size() <= 1) {
			throw new IllegalStateException(
					"At least there should 1 value for the property");
		}
		removeProperty(mdrDatabase.getVocabulary().exemplifyingExemplification,
				exemplifyingExemplification);
	}

	@Override
	public List<DataElementResource> getExemplifyingExemplifications()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().exemplifyingExemplification);
		return mdrDatabase.getUtil().createList(it, DataElementResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public DataElementExampleResource asMDRResource() {
		return this;
	}

	@Override
	public String getExampleItem() {
		return this.getDataElementExampleItem();
	}

	@Override
	public List<DataElement> getDataElements() throws MDRException {
		return Collections.<DataElement> unmodifiableList(this
				.getExemplifyingExemplifications());
	}
}
