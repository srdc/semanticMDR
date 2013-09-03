package tr.com.srdc.mdr.core.model.iso11179.composite;

import tr.com.srdc.mdr.core.api.composite.LanguageIdentification;
import tr.com.srdc.mdr.core.impl.composite.LanguageIdentificationImpl;
import tr.com.srdc.mdr.core.model.MDRResource;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.util.Country;
import tr.com.srdc.mdr.core.util.Language;

/**
 * The composite data type to represent a Language Identification together with
 * the language code and country code. <br>
 * 
 * @author anil
 * 
 */
public interface LanguageIdentificationResource extends MDRResource,
		LanguageIdentification {

	/**
	 * Method for setting the {@link Vocabulary#languageIdentifier} for
	 * {@link LanguageIdentificationImpl}.
	 * 
	 * @param languageIdentifier
	 */
	void setLanguageIdentifier(Language languageIdentifier);

	/**
	 * Method for getting the {@link Vocabulary#languageIdentifier} of
	 * {@link LanguageIdentificationImpl}.
	 * 
	 * @return {@link Language}
	 */
	Language getLanguageIdentifier();

	/**
	 * Method for setting the {@link Vocabulary#countryIdentifier} for
	 * {@link LanguageIdentificationImpl}.
	 * 
	 * @param countryIdentifier
	 */
	void setCountryIdentifier(Country countryIdentifier);

	/**
	 * Method for getting the {@link Vocabulary#countryIdentifier} of
	 * {@link LanguageIdentificationImpl}.
	 * 
	 * @return {@link Country}
	 */
	Country getCountryIdentifier();

}