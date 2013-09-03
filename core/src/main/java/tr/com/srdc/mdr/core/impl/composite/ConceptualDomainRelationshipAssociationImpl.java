package tr.com.srdc.mdr.core.impl.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.Abbreviation;
import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.ConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ConceptualDomainRelationshipAssociationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ConceptualDomainRelationshipResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * ConceptualDomainRelationshipAssociation represents the relation between
 * <ul>
 * <li>ConceptualDomain Relationship</li>
 * <li>Association Among ConceptualDomain s</li>
 * </ul>
 * <br>
 * Since {@link Abbreviation} backed by Jena TripleStore, such an holder class is
 * required for n-ary relations
 * 
 * @author anil
 * 
 */
public class ConceptualDomainRelationshipAssociationImpl extends
		AbstractMDRResource implements
		ConceptualDomainRelationshipAssociationResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createConceptualDomainRelationshipAssociation(ConceptualDomainRelationshipResource)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param describedByConceptualDomainRelationship
	 *            ConceptualDomainRelationship which Describes the assoiaciation
	 * @param mdrDatabase
	 */
	public ConceptualDomainRelationshipAssociationImpl(
			Node n,
			EnhGraph g,
			ConceptualDomainRelationshipResource describedByConceptualDomainRelationship,
			MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().ConceptualDomainRelationshipAssociation);
		setDescribedByConceptualDomainRelationship(describedByConceptualDomainRelationship);
	}

	public ConceptualDomainRelationshipAssociationImpl(Resource resource,
			MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setDescribedByConceptualDomainRelationship(
			ConceptualDomainRelationshipResource describedByConceptualDomainRelationship) {
		if (describedByConceptualDomainRelationship == null) {
			throw new IllegalArgumentException(
					"Data Element Concept Relationship must be specfied for ConceptualDomainRelationshipAssociation.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().describedByConceptualDomainRelationship,
				describedByConceptualDomainRelationship);
	}

	@Override
	public ConceptualDomainRelationshipResource getDescribedByConceptualDomainRelationship() {
		Resource describedByConceptualDomainRelationship = getPropertyResourceValue(mdrDatabase
				.getVocabulary().describedByConceptualDomainRelationship);
		return new ConceptualDomainRelationshipImpl(
				describedByConceptualDomainRelationship, mdrDatabase);
	}

	@Override
	public void addRelatingConceptualDomainRelationship(
			ConceptualDomainResource relatingConceptualDomainRelationship) {
		if (relatingConceptualDomainRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be added.");
		}
		addProperty(
				mdrDatabase.getVocabulary().relatingConceptualDomainRelationship,
				relatingConceptualDomainRelationship);
	}

	@Override
	public void removeRelatingConceptualDomainRelationship(
			ConceptualDomainResource relatingConceptualDomainRelationship) {
		if (relatingConceptualDomainRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().relatingConceptualDomainRelationship,
				relatingConceptualDomainRelationship);
	}

	@Override
	public List<ConceptualDomainResource> getRelatingConceptualDomainRelationships()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().relatingConceptualDomainRelationship);
		return mdrDatabase.getUtil().createList(it,
				ConceptualDomainResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public ConceptualDomainRelationshipAssociationResource asMDRResource() {
		return this;
	}

}
