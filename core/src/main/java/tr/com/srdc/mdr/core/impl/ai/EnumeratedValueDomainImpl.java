package tr.com.srdc.mdr.core.impl.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.api.composite.PermissibleValue;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.iso11179.ConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.EnumeratedValueDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministeredItemContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DatatypeResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.PermissibleValueResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.UnitOfMeasureResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * An Enumerated Value Domain is one where the Value Domain is expressed as an
 * explicit set of two or more Permissible Values. As a sub-type of Value
 * Domain, an Enumerated Value Domain inherits the attributes and relationships
 * of the former.
 * 
 * @author anil
 * 
 */
public class EnumeratedValueDomainImpl extends ValueDomainImpl implements
		EnumeratedValueDomainResource {

	Logger logger = LoggerFactory.getLogger(EnumeratedValueDomainImpl.class);

	/**
	 * Instead of calling the constructor of this class, use {@link
	 * IndividualFactory#createValueDomain(AdministrationRecord, Datatype,
	 * UnitOfMeasure, Integer, String, StewardshipRelationship,
	 * SubmissionRelationship, RegistrationAuthority, AdministeredItemContext,
	 * ConceptualDomain,List<PermissibleValue>)} to avoid entering illegal
	 * states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>.
	 * @param g
	 *            The graph which holds all triples.
	 * @param valueDomainAdministrationRecord
	 * @param valueDomainDatatype
	 * @param valueDomainUnitOfMeasure
	 * @param valueDomainMaximumCharacterQuantity
	 * @param valueDomainFormat
	 * @param administeredBy
	 * @param submittedBy
	 * @param registeredBy
	 * @param having
	 * @param representingConceptualDomainRepresentation
	 * @param containingPermissibleValueSet
	 * @param mdrDatabase
	 */
	public EnumeratedValueDomainImpl(
			Node n,
			EnhGraph g,
			AdministrationRecordResource valueDomainAdministrationRecord,
			DatatypeResource valueDomainDatatype,
			UnitOfMeasureResource valueDomainUnitOfMeasure,
			Integer valueDomainMaximumCharacterQuantity,
			String valueDomainFormat,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having,
			ConceptualDomainResource representingConceptualDomainRepresentation,
			List<PermissibleValueResource> containingPermissibleValueSet,
			MDRDatabase mdrDatabase) {
		super(n, g, valueDomainAdministrationRecord, valueDomainDatatype,
				valueDomainUnitOfMeasure, valueDomainMaximumCharacterQuantity,
				valueDomainFormat, administeredBy, submittedBy, registeredBy,
				having, representingConceptualDomainRepresentation, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().EnumeratedValueDomain);
		for (PermissibleValueResource i : containingPermissibleValueSet) {
			addContainingPermissibleValueSet(i);
		}
	}

	public EnumeratedValueDomainImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void addContainingPermissibleValueSet(
			PermissibleValueResource containingPermissibleValueSet) {
		if (containingPermissibleValueSet == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value for the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().containingPermissibleValueSet,
				containingPermissibleValueSet);
	}

	@Override
	public void removeContaningPermissibleValueSet(
			PermissibleValueResource containingPermissibleValueSet) {
		if (containingPermissibleValueSet == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().containingPermissibleValueSet,
				containingPermissibleValueSet);
	}

	@Override
	public List<PermissibleValueResource> getContainingPermissibleValueSets()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().containingPermissibleValueSet);
		return mdrDatabase.getUtil().createList(it,
				PermissibleValueResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public EnumeratedValueDomainResource asMDRResource() {
		return this;
	}

	@Override
	public List<PermissibleValue> getPermissibleValues() {
		List<PermissibleValue> permissibleValues = new ArrayList<PermissibleValue>();
		try {
			permissibleValues = Collections
					.<PermissibleValue> unmodifiableList(this
							.getContainingPermissibleValueSets());
		} catch (MDRException e) {
			logger.error("PermissibleValue list could not be obtained");
		}
		return permissibleValues;
	}

	@Override
	public void addPermissibleValue(PermissibleValue... pv) {
		for (PermissibleValue p : pv) {
			this.addContainingPermissibleValueSet(p.asMDRResource());
		}
	}
}
