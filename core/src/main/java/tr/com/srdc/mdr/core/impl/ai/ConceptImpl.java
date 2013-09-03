package tr.com.srdc.mdr.core.impl.ai;

import java.util.ArrayList;
import java.util.List;

import tr.com.srdc.mdr.core.api.ai.ObjectClass;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Vocabulary;
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
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.RDFS;


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
public class ConceptImpl extends ObjectClassImpl implements ConceptResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createConcept(AdministrationRecordResource)} to
	 * avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>.
	 * @param g
	 *            The graph which holds all triples.
	 * @param objectClassAdministrationRecord
	 *            The Administration Record for an {@link ObjectClassResource}.
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
	public ConceptImpl(Node n, EnhGraph g,
			AdministrationRecordResource objectClassAdministrationRecord,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having, MDRDatabase mdrDatabase) {
		super(n, g, objectClassAdministrationRecord, administeredBy,
				submittedBy, registeredBy, having, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().Concept);
	}

	public ConceptImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	/**
	 * Adds {@link Vocabulary#usedInConceptRelationship} property with given
	 * value
	 * 
	 * @param usedInConceptRelationship
	 */
	@Override
	public void addUsedInConceptRelationship(
			ConceptRelationshipResource usedInConceptRelationship) {
		if (usedInConceptRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().usedInConceptRelationship,
				usedInConceptRelationship);
	}

	/**
	 * Removes {@link Vocabulary#usedInConceptRelationship} property with given
	 * value
	 * 
	 * @param usedInConceptRelationship
	 */
	@Override
	public void removeUsedInConceptRelationship(
			ConceptRelationshipResource usedInConceptRelationship) {
		if (usedInConceptRelationship == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be removed.");
		}
		removeProperty(mdrDatabase.getVocabulary().usedInConceptRelationship,
				usedInConceptRelationship);
	}

	/**
	 * 
	 * @return {@link List} of all {@link Vocabulary#usedInConceptRelationship}
	 *         property values
	 * @throws MDRException
	 */
	@Override
	public List<ConceptRelationshipResource> getUsedInConceptRelationships()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().usedInConceptRelationship);
		return mdrDatabase.getUtil().createList(it,
				ConceptRelationshipResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public ConceptResource asMDRResource() {
		return this;
	}

	@Override
	public void setParentConcept(ObjectClass parent) {
		if (parent != null) {
			this.addProperty(RDFS.subClassOf, parent.asMDRResource());
		} else {
			ExtendedIterator<OntClass> l = this.listSuperClasses();
			OntClass found = null;
			while (l.hasNext()) {
				OntClass ontClass = l.next();
				if (ontClass.hasSuperClass(mdrDatabase.getVocabulary().Concept)) {
					found = ontClass;
				}
			}
			if (found != null) {
				this.removeProperty(RDFS.subClassOf, found);
			}
		}
	}

	@Override
	public ObjectClass getParentConcept() {
		NodeIterator l = this.listPropertyValues(RDFS.subClassOf);

		while (l.hasNext()) {
			RDFNode res = l.nextNode();
			if (res.canAs(OntClass.class)) {
				OntClass ontClass = res.as(OntClass.class);
				if (ontClass.hasSuperClass(mdrDatabase.getVocabulary().Concept)) {
					return new ConceptImpl(res.asResource(), mdrDatabase);
				}
			}
		}
		return null;
	}

	@Override
	public void addSubConcept(ObjectClass oc) {
		oc.setParentConcept(this);
	}

	@Override
	public List<ObjectClass> getSubConcepts() {
		ExtendedIterator<OntClass> l = this.listSubClasses();
		List<ObjectClass> ocList = new ArrayList<ObjectClass>();

		while (l.hasNext()) {
			OntClass res = l.next();
			ocList.add(new ConceptImpl(res, mdrDatabase));
		}

		return ocList;
	}

}
