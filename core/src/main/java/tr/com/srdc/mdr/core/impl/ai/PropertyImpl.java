package tr.com.srdc.mdr.core.impl.ai;

import tr.com.srdc.mdr.core.impl.composite.AdministrationRecordImpl;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.PropertyResource;
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
 * 
 * A Property is a characteristic common to all members of an Object Class. It
 * may be any feature that humans naturally use to distinguish one individual
 * object from another. It is the human perception of a single characteristic of
 * an Object Class in the real world. It is conceptual and thus has no
 * particular associated means of representation by which the Property can be
 * communicated.
 * 
 * @author mert
 * 
 */
public class PropertyImpl extends AdministeredItemImpl implements
		PropertyResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createProperty(AdministrationRecordResource)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>.
	 * @param g
	 *            The graph which holds all triples.
	 * @param propertyAdministrationRecord
	 *            The {@link AdministrationRecordResource} for a
	 *            {@link PropertyResource}.
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
	public PropertyImpl(Node n, EnhGraph g,
			AdministrationRecordResource propertyAdministrationRecord,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having, MDRDatabase mdrDatabase) {
		super(n, g, administeredBy, submittedBy, registeredBy, having,
				mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().Property);
		setAdministrationRecord(propertyAdministrationRecord);
	}

	public PropertyImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public AdministrationRecordResource getAdministrationRecord() {
		return new AdministrationRecordImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().propertyAdministrationRecord),
				mdrDatabase);
	}

	@Override
	public void setAdministrationRecord(
			AdministrationRecordResource propertyAdministrationRecord) {
		if (propertyAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"Administration Record must be specified for Property.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().propertyAdministrationRecord,
				propertyAdministrationRecord);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public PropertyResource asMDRResource() {
		return this;
	}

}
