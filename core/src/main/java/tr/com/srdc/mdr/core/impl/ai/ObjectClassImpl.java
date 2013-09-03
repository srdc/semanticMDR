package tr.com.srdc.mdr.core.impl.ai;

import java.util.List;

import tr.com.srdc.mdr.core.api.ai.ConceptualDomain;
import tr.com.srdc.mdr.core.api.ai.DataElementConcept;
import tr.com.srdc.mdr.core.api.ai.Property;
import tr.com.srdc.mdr.core.impl.composite.AdministrationRecordImpl;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
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
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * An Object Class is a set of ideas, abstractions, or things in the real world
 * that can be identified with explicit boundaries and meaning and whose
 * properties and behavior follow the same rules. It may be either a single or a
 * group of associated concepts, abstractions, or things. An Object Class may be
 * a single unit of thought (i.e., Concept) or a set of Concepts in a
 * relationship with each other to form a more complex concept (i.e., Concept
 * Relationship). A Concept and a Concept Relationship are subtypes of an Object
 * Class. Each Concept Relationship carries a concept relationship type
 * description that describes the nature of the relationship.
 * 
 * @author anil
 * 
 */
public abstract class ObjectClassImpl extends AdministeredItemImpl implements
		ObjectClassResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createObjectClass(AdministrationRecordResource)}
	 * to avoid entering illegal states.
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
	public ObjectClassImpl(Node n, EnhGraph g,
			AdministrationRecordResource objectClassAdministrationRecord,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having, MDRDatabase mdrDatabase) {
		super(n, g, administeredBy, submittedBy, registeredBy, having,
				mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().ObjectClass);
		setAdministrationRecord(objectClassAdministrationRecord);
	}

	public ObjectClassImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setAdministrationRecord(
			AdministrationRecordResource objectClassAdministrationRecord) {
		if (objectClassAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Administration Record must be specified for Object Class.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().objectClassAdministrationRecord,
				objectClassAdministrationRecord);
	}

	@Override
	public AdministrationRecordResource getAdministrationRecord() {
		return new AdministrationRecordImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().objectClassAdministrationRecord),
				mdrDatabase);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public ObjectClassResource asMDRResource() {
		return this;
	}

	@Override
	public DataElementConcept createDataElementConcept(Property property,
			ConceptualDomain conceptualDomain, String definition) {
		return getContext().createDataElementConcept(this, property,
				conceptualDomain);
	}

	@Override
	public DataElementConcept createDataElementConcept(String propertyName,
			String propertyDefinition, ConceptualDomain conceptualDomain,
			String definition) {
		return getContext().createDataElementConcept(this, propertyName,
				propertyDefinition, conceptualDomain);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DataElementConcept> getDataElementConcepts() {
		return (List<DataElementConcept>) mdrDatabase.getQueryFactory()
				.getDECSofOC(this.getURI(), null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DataElementConcept> getDataElementConcepts(Integer limit,
			Integer offset) {
		return (List<DataElementConcept>) mdrDatabase.getQueryFactory()
				.getDECSofOC(this.getURI(), limit, offset);
	}

	@Override
	public int getNumberOfDataElementConcepts() {
		return mdrDatabase.getQueryFactory().getNumberOfDEC(this.getURI());
	}

	@Override
	public DataElementConcept getDataElementConcept(String id) {
		return new DataElementConceptImpl(mdrDatabase.getQueryFactory()
				.getAdministeredItem(id), mdrDatabase);
	}

}
