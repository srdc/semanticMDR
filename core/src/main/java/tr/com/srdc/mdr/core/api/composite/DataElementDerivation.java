package tr.com.srdc.mdr.core.api.composite;

import java.util.List;

import tr.com.srdc.mdr.core.api.MDRNode;
import tr.com.srdc.mdr.core.api.ai.DataElement;
import tr.com.srdc.mdr.core.api.ai.DerivationRule;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementDerivationResource;



/**
 * A Data Element Derivation is the application of a Derivation Rule to one or
 * more input Data Elements, to derive one or more output Data Elements.
 * 
 * @author anil
 * 
 */
public interface DataElementDerivation extends MDRNode {

	@Override
	/**
	 * @return the {@link DataElementDerivationResource} version this instance.
	 */
	DataElementDerivationResource asMDRResource();

	/**
	 * 
	 * @return {@link DerivationRule} applied to this
	 *         {@link DataElementDerivation}
	 */
	DerivationRule getDerivationRule();

	/**
	 * 
	 * @return the unmodifiable list of {@link DataElement} which is input to
	 *         this {@link DataElementDerivation}
	 * @throws MDRException
	 */
	List<DataElement> getInputDataElements() throws MDRException;

	/**
	 * 
	 * @return the unmodifiable list of {@link DataElement} derived from this
	 *         {@link DataElementDerivation}
	 * @throws MDRException
	 */
	List<DataElement> getOutputDataElements() throws MDRException;

}
