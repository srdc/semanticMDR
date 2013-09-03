package tr.com.srdc.mdr.core.impl.composite;

import java.util.List;

import tr.com.srdc.mdr.core.impl.ai.ConceptualDomainImpl;
import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.composite.ConceptualDomainRelationshipAssociationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ConceptualDomainRelationshipResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * A Conceptual Domain may be associated with other ConceptualDomains , via the
 * Conceptual Domaint Relationship. The nature of the relationship is described
 * using the Conceptual Domain relationship type description.
 * 
 * @author anil
 * 
 */
public class ConceptualDomainRelationshipImpl extends AbstractMDRResource
		implements ConceptualDomainRelationshipResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createConceptualDomainRelationship(String)} to
	 * avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param conceptualDomainRelationshipTypeDescription
	 *            Type Description of the Relationship Between
	 *            {@link ConceptualDomainImpl}s
	 * @param conceptualDomainRelationshipTypeDescription
	 * @param mdrDatabase
	 */
	public ConceptualDomainRelationshipImpl(Node n, EnhGraph g,
			String conceptualDomainRelationshipTypeDescription,
			MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().ConceptualDomainRelationship);
		setConceptualDomainRelationshipTypeDescription(conceptualDomainRelationshipTypeDescription);
	}

	public ConceptualDomainRelationshipImpl(Resource resource,
			MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setConceptualDomainRelationshipTypeDescription(
			String conceptualDomainRelationshipTypeDescription) {
		if (Util.isNull(conceptualDomainRelationshipTypeDescription)) {
			throw new IllegalArgumentException(
					"Type Description must be specified for ConceptualDomainRelationship.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().conceptualDomainRelationshipTypeDescription,
				mdrDatabase.getUtil().createTypedLiteral(
						conceptualDomainRelationshipTypeDescription));
	}

	@Override
	public String getConceptualDomainRelationshipTypeDescription() {
		RDFNode conceptualDomainRelationshipTypeDescription = getPropertyValue(mdrDatabase
				.getVocabulary().conceptualDomainRelationshipTypeDescription);
		return conceptualDomainRelationshipTypeDescription.asLiteral()
				.getString();
	}

	@Override
	public void addDescribingConceptualDomainRelationship(
			ConceptualDomainRelationshipAssociationResource describingConceptualDomainRelationship) {
		if (describingConceptualDomainRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as value of the property to be added.");
		}
		addProperty(
				mdrDatabase.getVocabulary().describingConceptualDomainRelationship,
				describingConceptualDomainRelationship);
	}

	@Override
	public void removeDescribingConceptualDomainRelationship(
			ConceptualDomainRelationshipAssociationResource describingConceptualDomainRelationship) {
		if (describingConceptualDomainRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().describingConceptualDomainRelationship,
				describingConceptualDomainRelationship);
	}

	@Override
	public List<ConceptualDomainRelationshipAssociationResource> getDescribingConceptualDomainRelationships()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().describingConceptualDomainRelationship);
		return mdrDatabase.getUtil().createList(it,
				ConceptualDomainRelationshipAssociationResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public ConceptualDomainRelationshipResource asMDRResource() {
		return this;
	}
}
