package tr.com.srdc.mdr.core.impl.ai;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.api.ai.EnumeratedValueDomain;
import tr.com.srdc.mdr.core.api.composite.Datatype;
import tr.com.srdc.mdr.core.api.composite.PermissibleValue;
import tr.com.srdc.mdr.core.api.composite.ValueMeaning;
import tr.com.srdc.mdr.core.impl.composite.ValueMeaningImpl;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.ConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.EnumeratedConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministeredItemContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.OrganizationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueMeaningResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * A Conceptual Domain sometimes contains a finite allowed inventory of notions
 * that can be enumerated. Such a Conceptual Domain is referred to as an
 * Enumerated Conceptual Domain. An example of an Enumerated Conceptual Domain
 * is the notion of countries that is specified in ISO 3166, Codes for the
 * representation of names of countries. As a sub-type of Conceptual Domain, an
 * Enumerated Conceptual Domain inherits the attributes and relationships of the
 * former.
 * 
 * @author anil
 */
public class EnumeratedConceptualDomainImpl extends ConceptualDomainImpl
		implements EnumeratedConceptualDomainResource {

	private static final Logger logger = LoggerFactory
			.getLogger(EnumeratedConceptualDomainImpl.class);

	/**
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>.
	 * @param g
	 *            The graph which holds all triples.
	 * @param conceptualDomainAdministrationRecord
	 *            The AdministrationRecord for a
	 *            {@link ConceptualDomainResource}.
	 * @param dimensionality
	 *            Optional. An expression of measurement without units.
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
	 * @param administretedBy
	 *            An Administered Item is administered by an
	 *            {@link OrganizationResource} represented by the
	 *            {@link StewardshipRelationshipResource}.
	 * 
	 */
	public EnumeratedConceptualDomainImpl(Node n, EnhGraph g,
			AdministrationRecordResource conceptualDomainAdministrationRecord,
			String dimensionality,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having, MDRDatabase mdrDatabase) {
		super(n, g, conceptualDomainAdministrationRecord, dimensionality,
				administeredBy, submittedBy, registeredBy, having, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().EnumeratedConceptualDomain);
	}

	public EnumeratedConceptualDomainImpl(Resource resource,
			MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void addContainingValueMeaningSet(
			ValueMeaningResource containingValueMeaningSet) {
		if (containingValueMeaningSet == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().containingValueMeaningSet,
				containingValueMeaningSet);
	}

	@Override
	public void removeContainingValueMeaningSet(
			ValueMeaningResource containingValueMeaningSet) {
		if (containingValueMeaningSet == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as a value ıf the property to be removed.");
		}
		removeProperty(mdrDatabase.getVocabulary().containingValueMeaningSet,
				containingValueMeaningSet);
	}

	@Override
	public List<ValueMeaningResource> getContainingValueMeaningSets()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().containingValueMeaningSet);
		return mdrDatabase.getUtil().createList(it, ValueMeaningResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public EnumeratedConceptualDomainResource asMDRResource() {
		return this;
	}

	@Override
	public EnumeratedValueDomain createEnumeratedValueDomain(String name,
			String definition, Datatype dataType,
			List<PermissibleValue> permissibleValues) {
		return getContext().createEnumeratedValueDomain(this, name, definition,
				dataType, permissibleValues);
	}

	@Override
	public List<ValueMeaning> getValueMeanings() {
		List<ValueMeaningResource> vmResourceList = null;
		try {
			vmResourceList = this.getContainingValueMeaningSets();
		}
		catch (MDRException e) {
			logger.error("ValueMeaningList of {} could not be obtained",
					this.getName(), e);
			vmResourceList = new ArrayList<ValueMeaningResource>();
		}
		List<ValueMeaning> vmList = new ArrayList<ValueMeaning>();
		for (ValueMeaningResource vmr : vmResourceList) {
			vmList.add(new ValueMeaningImpl(vmr, mdrDatabase));
		}
		return vmList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ValueMeaning> getValueMeanings(int limit, int offset) {
		List<ValueMeaningResource> vmResourceList = null;
		vmResourceList = (List<ValueMeaningResource>) this.mdrDatabase
				.getQueryFactory().listValueMeningsOfCD(this.getURI(), limit,
						offset);
		List<ValueMeaning> vmList = new ArrayList<ValueMeaning>();
		for (ValueMeaningResource vmr : vmResourceList) {
			vmList.add(new ValueMeaningImpl(vmr, mdrDatabase));
		}
		return vmList;
	}

	@Override
	public ValueMeaning addValueMeaning(String identifier, String description) {
		ValueMeaningResource vm = mdrDatabase.getResourceFactory()
				.createValueMeaning(identifier, description,
						Calendar.getInstance(), Calendar.getInstance(),
						this.asMDRResource());
		this.addContainingValueMeaningSet(vm);
		return vm;
	}

	@Override
	public ValueMeaning getValueMeaning(String identifier) {
		// TODO : this function should obviously be changed, performance is too
		// bad
		List<ValueMeaningResource> vmList = null;
		try {
			vmList = getContainingValueMeaningSets();
		}
		catch (MDRException e) {
			logger.error("ValueMeanings of {} could not be obtained");
			return null;
		}
		for (ValueMeaningResource vm : vmList) {
			if (vm.getValueMeaningIdentifier().equals(identifier)) {
				return vm;
			}
		}
		return null;
	}

	@Override
	public void addValueMeaning(Collection<ValueMeaning> valueMeaningCollection) {
		for (ValueMeaning vm : valueMeaningCollection) {
			this.addContainingValueMeaningSet(vm.asMDRResource());
			vm.asMDRResource().addContainedInValueMeaningSet(this);
		}
	}

	@Override
	public int getNumberOfValueMeanings() {
		return mdrDatabase.getQueryFactory().getNumberOfValueMeanings(
				this.getURI());
	}

	@Override
	public boolean isEnumeratedConceptualDomain() {
		return true;
	}
}
