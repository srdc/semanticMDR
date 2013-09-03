package tr.com.srdc.mdr.core.impl.ai;

import java.util.List;

import tr.com.srdc.mdr.core.api.ai.ObjectClass;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.ConceptRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.ConceptResource;
import tr.com.srdc.mdr.core.model.iso11179.ObjectClassResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministeredItemContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.OrganizationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionRelationshipResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * An Object Class is a set of ideas, abstractions, or things in the real world
 * that can be identified with explicit boundaries and meaning and whose
 * properties and behavior follow the same rules. It may be either a single or a
 * group of associated concepts, abstractions, or things. An Object Class may be
 * a single unit of thought (i.e., Concept) or a set of Concepts in a
 * relationship with each other to form a more complex concept (i.e., Concept
 * Relationship). A Concept and a Concept Relationship are subtypes of an Object
 * Class.
 * 
 * @author anil
 * 
 */
public class ConceptRelationshipImpl extends ObjectClassImpl implements
		ConceptRelationshipResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createConceptRelationship(AdministrationRecordResource, String)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>.
	 * @param g
	 *            The graph which holds all triples.
	 * @param objectClassAdministrationRecord
	 *            The Administration Record for an {@link ObjectClassResource}.
	 * @param conceptRelationshipTypeDescription
	 *            Type Description for the relationship between Concepts
	 * @param administeredBy
	 *            An Administered Item is administered by an
	 *            {@link OrganizationResource} represented by the
	 *            {@link StewardshipRelationshipResource}.
	 * @param submittedBy
	 *            An Administered Item is submitted by an
	 *            {@link OrganizationResource} represented by the
	 *            {@link SubmissionRelationshipResource}.
	 * @param registeredBy
	 *            An {@link AdministeredItemResource} is registered by a
	 *            {@link RegistrationAuthorityResource}.
	 * @param having
	 *            An {@link AdministeredItemResource} has to have at least one
	 *            {@link AdministeredItemContextResource}.
	 * @param mdrDatabase
	 */
	public ConceptRelationshipImpl(Node n, EnhGraph g,
			AdministrationRecordResource objectClassAdministrationRecord,
			String conceptRelationshipTypeDescription,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having, MDRDatabase mdrDatabase) {
		super(n, g, objectClassAdministrationRecord, administeredBy,
				submittedBy, registeredBy, having, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().ConceptRelationship);
		setConceptRelationshipTypeDescription(conceptRelationshipTypeDescription);
	}

	public ConceptRelationshipImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setConceptRelationshipTypeDescription(
			String conceptRelationshipTypeDescription) {
		if (Util.isNull(conceptRelationshipTypeDescription)) {
			throw new IllegalArgumentException(
					"Concept Relationship Type Description must be specified  for ObjectClass.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().conceptRelationshipTypeDescription,
				mdrDatabase.getUtil().createTypedLiteral(
						conceptRelationshipTypeDescription));
	}

	@Override
	public String getConceptRelationshipTypeDescription() {
		return getPropertyValue(
				mdrDatabase.getVocabulary().conceptRelationshipTypeDescription)
				.asLiteral().getString();
	}

	@Override
	public void addUsingConceptRelationship(
			ConceptResource usingConceptRelationship) {
		if (usingConceptRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().usingConceptRelationship,
				usingConceptRelationship);
	}

	@Override
	public void removeUsingConceptRelationship(
			ConceptResource usingConceptRelationship) {
		if (usingConceptRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be removed.");
		}
		removeProperty(mdrDatabase.getVocabulary().usingConceptRelationship,
				usingConceptRelationship);
	}

	@Override
	public List<ConceptResource> getUsingConceptRelationships()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().usingConceptRelationship);
		return mdrDatabase.getUtil().createList(it, ConceptResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public ConceptRelationshipResource asMDRResource() {
		return this;
	}

	@Override
	public void setParentConcept(ObjectClass parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ObjectClass getParentConcept() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSubConcept(ObjectClass oc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ObjectClass> getSubConcepts() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public String getTypeDescription() {
//		return this.getConceptRelationshipTypeDescription();
//	}
//
//	@Override
//	public void addRelatedConcept(Concept concept) {
//		this.addUsingConceptRelationship(concept.asMDRResource());
//		concept.asMDRResource().addUsedInConceptRelationship(this);
//	}
//
//	@Override
//	public List<Concept> getRelatedConcepts() throws MDRException {
//		List<Concept> conceptList;
//		try {
//			conceptList = Collections.<Concept> unmodifiableList(this
//					.getUsingConceptRelationships());
//		} catch (MDRException e) {
//			logger.error("List of used Concepts could not be removed");
//			throw e;
//		}
//		return conceptList;
//	}

}
