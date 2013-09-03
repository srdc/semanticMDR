package tr.com.srdc.mdr.core.model.iso11179;

import java.util.List;

import tr.com.srdc.mdr.core.api.ai.DerivationRule;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementDerivationResource;



/**
 * 
 * A Data Element may have a Derivation Rule that is a specification of
 * derivation for the data element. The derivation rule may range from a sINDe
 * operation such as subtraction to a very complex set of derivations
 * (derivation being defined as a relationship between a derivation rule and an
 * input set upon which it acts). Derivation rules are not limited to arithmetic
 * and logical operations.
 * 
 * @author anil
 */
public interface DerivationRuleResource extends AdministeredItemResource, DerivationRule {

	void setAdministrationRecord(
			AdministrationRecordResource derivationRuleAdministrationRecord);

	AdministrationRecordResource getAdministrationRecord();

	/**
	 * Sets {@link Vocabulary#derivationRuleSpecification} property with given
	 * value
	 * 
	 * @param derivationRuleSpecification
	 */
	void setDerivationRuleSpecification(String derivationRuleSpecification);

	/**
	 * 
	 * @return value of {@link Vocabulary#derivationRuleSpecification} property
	 */
	String getDerivationRuleSpecification();

	/**
	 * Adds {@link Vocabulary#appliedToDerivationRuleApplication} property with
	 * given value
	 * 
	 * @param appliedToDerivationRuleApplication
	 */
	void addAppliedToDerivationRuleApplication(
			DataElementDerivationResource appliedToDerivationRuleApplication);

	/**
	 * Removes {@link Vocabulary#appliedToDerivationRuleApplication} with given
	 * value
	 * 
	 * @param appliedToDerivationRuleApplication
	 *            if given <code>null</code>, throws
	 *            {@link IllegalArgumentException}
	 */
	void removeAppliedToDerivationRuleApplication(
			DataElementDerivationResource appliedToDerivationRuleApplication);

	/**
	 * 
	 * @return {@link List} of all
	 *         {@link Vocabulary#appliedToDerivationRuleApplication} property
	 *         values
	 * @throws MDRException
	 */
	List<DataElementDerivationResource> getAppliedToDerivationRuleApplications()
			throws MDRException;

}