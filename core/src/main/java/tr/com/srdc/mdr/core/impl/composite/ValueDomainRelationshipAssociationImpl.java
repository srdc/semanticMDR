package tr.com.srdc.mdr.core.impl.composite;

import java.util.List;

import tr.com.srdc.mdr.core.model.Abbreviation;
import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.ValueDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueDomainRelationshipAssociationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueDomainRelationshipResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * ValueDomainRelationshipAssociation represents the relation between
 * <ul>
 * <li>ValueDomain Relationship</li>
 * <li>Association Among ValueDomain s</li>
 * </ul>
 * <br>
 * Since {@link Abbreviation} backed by Jena TripleStore, such an holder class is
 * required for n-ary relations
 * 
 * @author anil
 * 
 */
public class ValueDomainRelationshipAssociationImpl extends AbstractMDRResource
		implements ValueDomainRelationshipAssociationResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createValueDomainRelationshipAssociation(ValueDomainRelationshipResource)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param describedByValueDomainRelationship
	 *            ValueDomainRelationship which Describes the assoiaciation
	 * @param mdrDatabase
	 */
	public ValueDomainRelationshipAssociationImpl(Node n, EnhGraph g,
			ValueDomainRelationshipResource describedByValueDomainRelationship,
			MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().ValueDomainRelationshipAssociation);
		setDescribedByValueDomainRelationship(describedByValueDomainRelationship);
	}

	public ValueDomainRelationshipAssociationImpl(Resource resource,
			MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setDescribedByValueDomainRelationship(
			ValueDomainRelationshipResource describedByValueDomainRelationship) {
		if (describedByValueDomainRelationship == null) {
			throw new IllegalArgumentException(
					"Data Element Concept Relationship must be specfied for ValueDomainRelationshipAssociation.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().describedByValueDomainRelationship,
				describedByValueDomainRelationship);
	}

	@Override
	public ValueDomainRelationshipResource getDescribedByValueDomainRelationship() {
		Resource describedByValueDomainRelationship = getPropertyResourceValue(mdrDatabase
				.getVocabulary().describedByValueDomainRelationship);
		return new ValueDomainRelationshipImpl(
				describedByValueDomainRelationship, mdrDatabase);
	}

	@Override
	public void addRelatingValueDomainRelationship(
			ValueDomainResource relatingValueDomainRelationship) {
		if (relatingValueDomainRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be added.");
		}
		addProperty(
				mdrDatabase.getVocabulary().relatingValueDomainRelationship,
				relatingValueDomainRelationship);
	}

	@Override
	public void removeRelatingValueDomainRelationship(
			ValueDomainResource relatingValueDomainRelationship) {
		if (relatingValueDomainRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().relatingValueDomainRelationship,
				relatingValueDomainRelationship);
	}

	@Override
	public List<ValueDomainResource> getRelatingValueDomainRelationships()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().relatingValueDomainRelationship);
		return mdrDatabase.getUtil().createList(it, ValueDomainResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public ValueDomainRelationshipAssociationResource asMDRResource() {
		return this;
	}

}
