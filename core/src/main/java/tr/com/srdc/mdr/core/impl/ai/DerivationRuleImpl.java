package tr.com.srdc.mdr.core.impl.ai;

import java.util.List;

import tr.com.srdc.mdr.core.impl.composite.AdministrationRecordImpl;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.DerivationRuleResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministeredItemContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementDerivationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionRelationshipResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * A Data Element may have a Derivation Rule that is a specification of
 * derivation for the data element. The derivation rule may range from a simple
 * operation such as subtraction to a very complex set of derivations
 * (derivation being defined as a relationship between a derivation rule and an
 * input set upon which it acts). Derivation rules are not limited to arithmetic
 * and logical operations.
 * 
 * @author anil
 */
public class DerivationRuleImpl extends AdministeredItemImpl implements
		DerivationRuleResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createDerivationRule(String, AdministrationRecordResource, StewardshipRelationshipResource, SubmissionRelationshipResource, RegistrationAuthorityResource,AdministeredItemContextResource)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param derivationRuleSpecification
	 * @param derivationRuleAdministrationRecord
	 * @param administeredBy
	 * @param submittedBy
	 * @param registeredBy
	 * @param having
	 * @param mdrDatabase
	 */
	public DerivationRuleImpl(Node n, EnhGraph g,
			String derivationRuleSpecification,
			AdministrationRecordResource derivationRuleAdministrationRecord,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having, MDRDatabase mdrDatabase) {
		super(n, g, administeredBy, submittedBy, registeredBy, having,
				mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().DerivationRule);
		setAdministrationRecord(derivationRuleAdministrationRecord);
		setDerivationRuleSpecification(derivationRuleSpecification);
	}

	public DerivationRuleImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setAdministrationRecord(
			AdministrationRecordResource derivationRuleAdministrationRecord) {
		if (derivationRuleAdministrationRecord == null) {
			throw new IllegalArgumentException(
					"AdministrationRecord must be specified for DerivationRule.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().derivationRuleAdministrationRecord,
				derivationRuleAdministrationRecord);
	}

	@Override
	public AdministrationRecordResource getAdministrationRecord() {
		Resource derivationRuleAdministrationRecord = getPropertyResourceValue(mdrDatabase
				.getVocabulary().derivationRuleAdministrationRecord);
		return new AdministrationRecordImpl(derivationRuleAdministrationRecord,
				mdrDatabase);
	}

	@Override
	public void setDerivationRuleSpecification(
			String derivationRuleSpecification) {
		if (Util.isNull(derivationRuleSpecification)) {
			throw new IllegalArgumentException(
					"Specification should be specified for DerivationRule");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().derivationRuleSpecification,
				mdrDatabase.getUtil().createTypedLiteral(
						derivationRuleSpecification));
	}

	@Override
	public String getDerivationRuleSpecification() {
		RDFNode derivationRuleSpecification = getPropertyValue(mdrDatabase
				.getVocabulary().derivationRuleSpecification);
		return derivationRuleSpecification.asLiteral().getString();
	}

	@Override
	public void addAppliedToDerivationRuleApplication(
			DataElementDerivationResource appliedToDerivationRuleApplication) {
		if (appliedToDerivationRuleApplication == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(
				mdrDatabase.getVocabulary().appliedToDerivationRuleApplication,
				appliedToDerivationRuleApplication);
	}

	@Override
	public void removeAppliedToDerivationRuleApplication(
			DataElementDerivationResource appliedToDerivationRuleApplication) {
		if (appliedToDerivationRuleApplication == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		removeProperty(
				mdrDatabase.getVocabulary().appliedToDerivationRuleApplication,
				appliedToDerivationRuleApplication);
	}

	@Override
	public List<DataElementDerivationResource> getAppliedToDerivationRuleApplications()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().appliedToDerivationRuleApplication);
		return mdrDatabase.getUtil().createList(it,
				DataElementDerivationResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public DerivationRuleResource asMDRResource() {
		return this;
	}

//	@Override
//	public DataElementDerivation createDerivation(DataElement input,
//			DataElement output) {
//		DataElementDerivationResource dataElementDerivation = mdrDatabase
//				.getResourceFactory().createDataElementDerivation(this,
//						input.asMDRResource(), output.asMDRResource());
//		logger.debug(
//				"DataElementDerivation created with {} and will be added to DerivationRule",
//				this);
//		this.addAppliedToDerivationRuleApplication(dataElementDerivation);
//		return dataElementDerivation;
//	}
//
//	@Override
//	public List<DataElementDerivation> getDataElementDerivations()
//			throws MDRException {
//		List<DataElementDerivation> derList;
//		try {
//			derList = Collections.<DataElementDerivation> unmodifiableList(this
//					.getAppliedToDerivationRuleApplications());
//		} catch (MDRException e) {
//			logger.error(
//					"Error at obtaining DataElementDerivations which {} is applieed",
//					this.getDerivationRuleSpecification());
//			throw e;
//		}
//		return derList;
//	}
//
//	@Override
//	public String getSpecification() {
//		return this.getDerivationRuleSpecification();
//	}

}
