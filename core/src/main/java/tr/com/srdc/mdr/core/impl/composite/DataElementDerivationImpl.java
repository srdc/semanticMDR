package tr.com.srdc.mdr.core.impl.composite;

import java.util.Collections;
import java.util.List;

import tr.com.srdc.mdr.core.api.ai.DataElement;
import tr.com.srdc.mdr.core.api.ai.DerivationRule;
import tr.com.srdc.mdr.core.impl.ai.DerivationRuleImpl;
import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.DataElementResource;
import tr.com.srdc.mdr.core.model.iso11179.DerivationRuleResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementDerivationResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * 
 * A Data Element Derivation is the application of a Derivation Rule to one or
 * more input Data Elements, to derive one or more output Data Elements.
 * 
 * @author anil
 * 
 */
public class DataElementDerivationImpl extends AbstractMDRResource implements
		DataElementDerivationResource {

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createDataElementDerivation(DerivationRuleResource, DataElementResource, DataElementResource)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param applyingDerivationRuleApplication
	 * @param inputingDerivationInput
	 * @param derivingDerivationOutput
	 * @param mdrDatabase
	 */
	public DataElementDerivationImpl(Node n, EnhGraph g,
			DerivationRuleResource applyingDerivationRuleApplication,
			DataElementResource inputingDerivationInput,
			DataElementResource derivingDerivationOutput,
			MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().DataElementDerivation);
		setApplyingDerivationRuleApplication(applyingDerivationRuleApplication);
		addInputingDerivationInput(inputingDerivationInput);
		addDerivingDerivationOutput(derivingDerivationOutput);
	}

	public DataElementDerivationImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setApplyingDerivationRuleApplication(
			DerivationRuleResource applyingDerivationRuleApplication) {
		if (applyingDerivationRuleApplication == null) {
			throw new IllegalArgumentException(
					"Derivation Rule must be specified for DataElementDerivation.");
		}

		setPropertyValue(
				mdrDatabase.getVocabulary().applyingDerivationRuleApplication,
				applyingDerivationRuleApplication);
	}

	@Override
	public DerivationRuleResource getApplyingDerivationRuleApplication() {
		Resource applyingDerivationRuleApplication = getPropertyResourceValue(mdrDatabase
				.getVocabulary().applyingDerivationRuleApplication);
		return new DerivationRuleImpl(applyingDerivationRuleApplication,
				mdrDatabase);
	}

	@Override
	public void addInputingDerivationInput(
			DataElementResource inputingDerivationInput) {
		if (inputingDerivationInput == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().inputingDerivationInput,
				inputingDerivationInput);
	}

	@Override
	public void removeInputingDerivationInput(
			DataElementResource inputingDerivationInput) {
		if (inputingDerivationInput == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		if (listPropertyValues(
				mdrDatabase.getVocabulary().inputingDerivationInput).toSet()
				.size() <= 1) {
			throw new IllegalStateException(
					"At least there should 1 value for the property.");
		}
		removeProperty(mdrDatabase.getVocabulary().inputingDerivationInput,
				inputingDerivationInput);
	}

	@Override
	public List<DataElementResource> getInputingDerivationInputs()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().inputingDerivationInput);
		return mdrDatabase.getUtil().createList(it, DataElementResource.class);
	}

	@Override
	public void addDerivingDerivationOutput(
			DataElementResource derivingDerivationOutput) {
		if (derivingDerivationOutput == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().derivingDerivationOutput,
				derivingDerivationOutput);
	}

	@Override
	public void removeDerivingDerivationOutput(
			DataElementResource derivingDerivationOutput) {
		if (derivingDerivationOutput == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		if (listPropertyValues(
				mdrDatabase.getVocabulary().derivingDerivationOutput).toSet()
				.size() <= 1) {
			throw new IllegalStateException(
					"At least there should be 1 value for the property");
		}
		removeProperty(mdrDatabase.getVocabulary().derivingDerivationOutput,
				derivingDerivationOutput);
	}

	@Override
	public List<DataElementResource> getDerivingDerivationOutputs()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().derivingDerivationOutput);
		return mdrDatabase.getUtil().createList(it, DataElementResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public DataElementDerivationResource asMDRResource() {
		return this;
	}

	@Override
	public DerivationRule getDerivationRule() {
		return this.getApplyingDerivationRuleApplication();
	}

	@Override
	public List<DataElement> getInputDataElements() throws MDRException {
		return Collections.<DataElement> unmodifiableList(this
				.getInputingDerivationInputs());
	}

	@Override
	public List<DataElement> getOutputDataElements() throws MDRException {
		return Collections.<DataElement> unmodifiableList(this
				.getDerivingDerivationOutputs());
	}

}
