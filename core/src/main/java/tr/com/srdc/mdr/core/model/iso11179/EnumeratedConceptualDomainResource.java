package tr.com.srdc.mdr.core.model.iso11179;

import java.util.List;

import tr.com.srdc.mdr.core.api.ai.EnumeratedConceptualDomain;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueMeaningResource;



/**
 * @author anil
 * 
 */
public interface EnumeratedConceptualDomainResource extends ConceptualDomainResource,
		EnumeratedConceptualDomain {

	/**
	 * Adds {@link Vocabulary#containingValueMeaningSet} property with given
	 * value
	 * 
	 * @param containingValueMeaningSet
	 */
	void addContainingValueMeaningSet(ValueMeaningResource containingValueMeaningSet);

	/**
	 * removes {@link Vocabulary#containingValueMeaningSet} property with given
	 * value
	 * 
	 * @param containingValueMeaningSet
	 */
	void removeContainingValueMeaningSet(
			ValueMeaningResource containingValueMeaningSet);

	/**
	 * {@link List} of all {@link Vocabulary#containingValueMeaningSet} property
	 * values
	 * 
	 * @return
	 * @throws MDRException
	 */
	List<ValueMeaningResource> getContainingValueMeaningSets() throws MDRException;

}