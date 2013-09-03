package tr.com.srdc.mdr.core.impl.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.Abbreviation;
import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.DataElementConceptResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementConceptRelationshipAssociationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementConceptRelationshipResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * DataElementConceptRelationshipAssociation represents the relation between
 * <ul>
 * <li>Data Element Concept Relationship</li>
 * <li>Association Among Data Element Concepts</li>
 * </ul>
 * <br>
 * Since {@link Abbreviation} backed by Jena TripleStore, such an holder class is
 * required for n-ary relations
 * 
 * @author anil
 * 
 */
public class DataElementConceptRelationshipAssociationImpl extends
		AbstractMDRResource implements
		DataElementConceptRelationshipAssociationResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createDataElementConceptRelationshipAssociation(DataElementConceptRelationshipResource)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param describedByDataElementConceptRelationship
	 *            DataElementConceptRelationship which Describes the
	 *            assoiaciation
	 * @param mdrDatabase
	 */
	public DataElementConceptRelationshipAssociationImpl(
			Node n,
			EnhGraph g,
			DataElementConceptRelationshipResource describedByDataElementConceptRelationship,
			MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().DataElementConceptRelationshipAssociation);
		setDescribedByDataElementConceptRelationship(describedByDataElementConceptRelationship);
	}

	public DataElementConceptRelationshipAssociationImpl(Resource resource,
			MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setDescribedByDataElementConceptRelationship(
			DataElementConceptRelationshipResource describedByDataElementConceptRelationship) {
		if (describedByDataElementConceptRelationship == null) {
			throw new IllegalArgumentException(
					"Data Element Concept Relationship must be specfied for DataElementConceptRelationshipAssociation.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().describedByDataElementConceptRelationship,
				describedByDataElementConceptRelationship);
	}

	@Override
	public DataElementConceptRelationshipResource getDescribedByDataElementConceptRelationship() {
		Resource describedByDataElementConceptRelationship = getPropertyResourceValue(mdrDatabase
				.getVocabulary().describedByDataElementConceptRelationship);
		return new DataElementConceptRelationshipImpl(
				describedByDataElementConceptRelationship, mdrDatabase);
	}

	@Override
	public void addRelatingDataElementConceptRelationship(
			DataElementConceptResource relatingDataElementConceptRelationship) {
		if (relatingDataElementConceptRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be added.");
		}
		addProperty(
				mdrDatabase.getVocabulary().relatingDataElementConceptRelationship,
				relatingDataElementConceptRelationship);
	}

	@Override
	public void removeRelatingDataElementConceptRelationship(
			DataElementConceptResource relatingDataElementConceptRelationship) {
		if (relatingDataElementConceptRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().relatingDataElementConceptRelationship,
				relatingDataElementConceptRelationship);
	}

	@Override
	public List<DataElementConceptResource> getRelatingDataElementConceptRelationships()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().relatingDataElementConceptRelationship);
		return mdrDatabase.getUtil().createList(it,
				DataElementConceptResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public DataElementConceptRelationshipAssociationResource asMDRResource() {
		return this;
	}

}
