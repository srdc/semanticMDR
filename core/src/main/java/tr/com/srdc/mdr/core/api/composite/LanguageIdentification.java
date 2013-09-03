package tr.com.srdc.mdr.core.api.composite;

import tr.com.srdc.mdr.core.api.MDRNode;
import tr.com.srdc.mdr.core.model.iso11179.composite.LanguageIdentificationResource;
import tr.com.srdc.mdr.core.util.Country;
import tr.com.srdc.mdr.core.util.Language;

/**
 * The composite datatype Language Identification serves as an identifier for a
 * language. It is used in:
 * <ul>
 * <li>the {@link RegistrationAuthority} class to identify the default
 * language(s) of the registration authority</li>
 * <li>the {@link ReferenceDocument} class to identify the language(s) used
 * within the document</li>
 * <li>the {@link LanguageSection} class of the Naming and Definition region to
 * identify the language used for names and definitions within that section.</li>
 * </ul>
 * <br>
 * The identifier comprises a mandatory language identifier and an optional
 * country identifier, the latter being used to distinguish variations in
 * language use in different countries.
 * 
 * @author anil
 * 
 */
public interface LanguageIdentification extends MDRNode {

	@Override
	/**
	 * @return the {@link LanguageIdentificationResource} version this instance.
	 */
	LanguageIdentificationResource asMDRResource();

	Language getLanguage();

	/**
	 * 
	 * @return the {@link Country} of a {@link LanguageIdentification}. If the
	 *         attribute does not exist return <code>null</code>
	 */
	Country getCountry();

}
