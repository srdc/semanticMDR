package tr.com.srdc.mdr.core.impl.composite;

import java.util.List;

import tr.com.srdc.mdr.core.impl.ai.DataElementConceptImpl;
import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementConceptRelationshipAssociationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementConceptRelationshipResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * A Data Element Concept may be associated with other Data Element Concepts,
 * via the Data Element Concept Relationship. The nature of the relationship is
 * described using the data element concept relationship type description.
 * 
 * @author anil
 * 
 */
public class DataElementConceptRelationshipImpl extends AbstractMDRResource
		implements DataElementConceptRelationshipResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createDataElementConceptrelationship(String)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param dataElementConceptRelationshipTypeDescription
	 *            Type Description of the Relationship Between
	 *            {@link DataElementConceptImpl}s
	 * @param dataElementConceptRelationshipTypeDescription
	 * @param mdrDatabase
	 */
	public DataElementConceptRelationshipImpl(Node n, EnhGraph g,
			String dataElementConceptRelationshipTypeDescription,
			MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().DataElementConceptRelationship);
		setDataElementConceptRelationshipTypeDescription(dataElementConceptRelationshipTypeDescription);
	}

	public DataElementConceptRelationshipImpl(Resource resource,
			MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setDataElementConceptRelationshipTypeDescription(
			String dataElementConceptRelationshipTypeDescription) {
		if (Util.isNull(dataElementConceptRelationshipTypeDescription)) {
			throw new IllegalArgumentException(
					"Type Description must be specified for DataElementConceptRelationship.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().dataElementConceptRelationshipTypeDescription,
				mdrDatabase.getUtil().createTypedLiteral(
						dataElementConceptRelationshipTypeDescription));
	}

	@Override
	public String getDataElementConceptRelationshipTypeDescription() {
		RDFNode dataElementConceptRelationshipTypeDescription = getPropertyValue(mdrDatabase
				.getVocabulary().dataElementConceptRelationshipTypeDescription);
		return dataElementConceptRelationshipTypeDescription.asLiteral()
				.getString();
	}

	@Override
	public void addDescribingDataElementConceptRelationship(
			DataElementConceptRelationshipAssociationResource describingDataElementConceptRelationship) {
		if (describingDataElementConceptRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as value of the property to be added.");
		}
		addProperty(
				mdrDatabase.getVocabulary().describingDataElementConceptRelationship,
				describingDataElementConceptRelationship);
	}

	@Override
	public void removeDescribingDataElementConceptRelationship(
			DataElementConceptRelationshipAssociationResource describingDataElementConceptRelationship) {
		if (describingDataElementConceptRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().describingDataElementConceptRelationship,
				describingDataElementConceptRelationship);
	}

	@Override
	public List<DataElementConceptRelationshipAssociationResource> getDescribingDataElementConceptRelationships()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().describingDataElementConceptRelationship);
		return mdrDatabase.getUtil().createList(it,
				DataElementConceptRelationshipAssociationResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public DataElementConceptRelationshipResource asMDRResource() {
		return this;
	}
}
