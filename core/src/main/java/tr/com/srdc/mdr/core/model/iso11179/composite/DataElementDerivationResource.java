package tr.com.srdc.mdr.core.model.iso11179.composite;

import java.util.List;

import tr.com.srdc.mdr.core.api.composite.DataElementDerivation;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.DataElementResource;
import tr.com.srdc.mdr.core.model.iso11179.DerivationRuleResource;



/**
 * 
 * A Data Element Derivation is the application of a Derivation Rule to one or
 * more input Data Elements, to derive one or more output Data Elements.
 * 
 * @author anil
 * 
 */
public interface DataElementDerivationResource extends MDRResource,
		DataElementDerivation {

	/**
	 * Sets {@link Vocabulary#applyingDerivationRuleApplication} property with
	 * given value
	 * 
	 * @param applyingDerivationRuleApplication
	 */
	void setApplyingDerivationRuleApplication(
			DerivationRuleResource applyingDerivationRuleApplication);

	/**
	 * 
	 * @return value of {@link Vocabulary#applyingDerivationRuleApplication}
	 *         property
	 */
	DerivationRuleResource getApplyingDerivationRuleApplication();

	/**
	 * Adds {@link Vocabulary#inputingDerivationInput} property with given value
	 * 
	 * @param inputingDerivationInput
	 *            if given <code>null</code>, throws
	 *            {@link IllegalArgumentException}
	 */
	void addInputingDerivationInput(DataElementResource inputingDerivationInput);

	/**
	 * removes {@link Vocabulary#inputingDerivationInput} property with given
	 * value
	 * 
	 * @param inputingDerivationInput
	 *            throws {@link IllegalStateException} when try to remove all
	 *            values of the property
	 */
	void removeInputingDerivationInput(DataElementResource inputingDerivationInput);

	/**
	 * {@link List} of all {@link Vocabulary#inputingDerivationInput} property
	 * values
	 * 
	 * @return
	 * @throws MDRException
	 */
	List<DataElementResource> getInputingDerivationInputs() throws MDRException;

	/**
	 * Adds {@link Vocabulary#derivingDerivationOutput} property with given
	 * value
	 * 
	 * @param derivingDerivationOutput
	 */
	void addDerivingDerivationOutput(DataElementResource derivingDerivationOutput);

	/**
	 * removes {@link Vocabulary#derivingDerivationOutput} with given value
	 * 
	 * @param derivingDerivationOutput
	 *            throws {@link IllegalStateException} when try to remove all
	 *            values of the property
	 */
	void removeDerivingDerivationOutput(DataElementResource derivingDerivationOutput);

	/**
	 * 
	 * @return {@link List} of all {@link Vocabulary#derivingDerivationOutput}
	 *         property values
	 * @throws MDRException
	 */
	List<DataElementResource> getDerivingDerivationOutputs() throws MDRException;

}