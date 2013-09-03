package tr.com.srdc.mdr.core.api.ai;

import java.util.Collection;
import java.util.List;

import tr.com.srdc.mdr.core.api.composite.Datatype;
import tr.com.srdc.mdr.core.api.composite.PermissibleValue;
import tr.com.srdc.mdr.core.api.composite.ValueMeaning;
import tr.com.srdc.mdr.core.model.iso11179.EnumeratedConceptualDomainResource;


/**
 * A Conceptual Domain sometimes contains a finite allowed inventory of notions
 * that can be enumerated. Such a Conceptual Domain is referred to as an
 * Enumerated Conceptual Domain. <br>
 * An example of an Enumerated Conceptual Domain is the notion of countries that
 * is specified in ISO 3166, Codes for the representation of names of countries.<br>
 * As a sub-type of Conceptual Domain, an Enumerated Conceptual Domain inherits
 * the attributes and relationships of the former.
 * 
 * @author anil
 * 
 */
public interface EnumeratedConceptualDomain extends ConceptualDomain {

	@Override
	/**
	 * @return the {@link EnumeratedConceptualDomainResource} version this instance.
	 */
	EnumeratedConceptualDomainResource asMDRResource();

	/**
	 * Create an {@link EnumeratedValueDomain} out of this
	 * {@link EnumeratedConceptualDomain} with the given name, definition,
	 * dataType and permissibleValues. The list of permissibleValues is the
	 * enumerations which belong to the created {@link EnumeratedValueDomain}.
	 * 
	 * @param name
	 * @param definition
	 * @param dataType
	 * @param permissibleValues
	 * @return
	 */
	EnumeratedValueDomain createEnumeratedValueDomain(String name,
			String definition, Datatype dataType,
			List<PermissibleValue> permissibleValues);

	/**
	 * @return {@link ValueMeaning} belonging to this ConceptualDomain and
	 *         identified by given ID
	 */
	ValueMeaning getValueMeaning(String identifier);

	/**
	 * Enumerated Conceptual Domain's are used for grouping ValueMeaning which
	 * will later be represented with {@link ValueDomain}s
	 * 
	 * @return List of {@link ValueMeaning}s which are grouped under this
	 *         EnumeratedConceptualDomain
	 */
	List<ValueMeaning> getValueMeanings();

	/**
	 * Enumerated Conceptual Domain's are used for grouping ValueMeaning which
	 * will later be represented with {@link ValueDomain}s
	 * 
	 * @param limit
	 *            Total number of results to be returned
	 * 
	 * @param offset
	 *            The initial point of the results
	 * 
	 * @return List of {@link ValueMeaning}s which are grouped under this
	 *         EnumeratedConceptualDomain
	 */
	List<ValueMeaning> getValueMeanings(int limit, int offset);

	/**
	 * 
	 * @return executes sparql query on mdr database and returns total number of
	 *         value meanings of this conceptual domain.
	 */
	int getNumberOfValueMeanings();

	/**
	 * Enumerated Conceptual Domain's are used for grouping ValueMeaning which
	 * will later be represented with {@link ValueDomain}s
	 * 
	 * @param identifier
	 *            String which will uniquely identify the ValueMeaning under
	 *            this domain
	 * @param description
	 *            Descriptive note about the Meaning
	 */
	ValueMeaning addValueMeaning(String identifier, String description);

	/**
	 * Enumerated Conceptual Domain's are used for grouping ValueMeaning which
	 * will later be represented with {@link ValueDomain}s
	 * 
	 * @param valueMeaningCollection
	 *            Collection of {@link ValueMeaning}'s which will be contained
	 *            in this ConceptualDomain
	 */
	void addValueMeaning(Collection<ValueMeaning> valueMeaningCollection);

}
