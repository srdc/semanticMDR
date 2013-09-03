package tr.com.srdc.mdr.core.api.ai;

import tr.com.srdc.mdr.core.model.iso11179.DerivationRuleResource;


/**
 * A Data Element may have a Derivation Rule that is a specification of
 * derivation for the data element. The derivation rule may range from a simple
 * operation such as subtraction to a very complex set of derivations
 * (derivation being defined as a relationship between a derivation rule and an
 * input set upon which it acts). Derivation rules are not limited to arithmetic
 * and logical operations. As an Administered Item, a Derivation Rule carries
 * its own Administration Record information, allowing it to be identified,
 * named, defined and optionally classified in a Classification Scheme. A
 * Derivation Rule may be registered as an Administered Item without necessarily
 * being associated with any Data Element Derivation.
 * 
 * @author anil
 * 
 */
public interface DerivationRule extends AdministeredItem {

	@Override
	/**
	 * @return the {@link DerivationRuleResource} version this instance.
	 */
	DerivationRuleResource asMDRResource();

//	/**
//	 * Method to create a {@link DataElementDerivation} with given input/output
//	 * {@link DataElement}'s and applying this DerivationRule as a rule
//	 * specification
//	 * 
//	 * @param input
//	 *            DataElements which will be the input of DataElementDerivation
//	 * @param output
//	 *            DataElements which are the derivations of input DataElements
//	 *            as a result of applying this DerivationRule
//	 * @return
//	 */
//	DataElementDerivation createDerivation(DataElement input, DataElement output);
//
//	/**
//	 * @return List of all {@link DataElementDerivation}'s which are applying
//	 *         this DerivationRule
//	 * @throws MDRException 
//	 */
//	List<DataElementDerivation> getDataElementDerivations() throws MDRException;
//
//	/**
//	 * @return Specification of the DerivationRule which will be applied on
//	 *         {@link DataElement}s to derive new ones from existings.
//	 */
//	String getSpecification();
}
