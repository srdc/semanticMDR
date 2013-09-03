package tr.com.srdc.mdr.core.impl.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.iso11179.composite.LanguageIdentificationResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;
import tr.com.srdc.mdr.core.util.Country;
import tr.com.srdc.mdr.core.util.Language;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * The composite data type to represent a Language Identification together with
 * the language code and country code. <br>
 * 
 * @author anil
 * 
 */
public class LanguageIdentificationImpl extends AbstractMDRResource implements
		LanguageIdentificationResource {

	private static final Logger logger = LoggerFactory
			.getLogger(LanguageIdentificationImpl.class);

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createLanguageIdentification(tr.com.srdc.mdr.core.util.Language, tr.com.srdc.mdr.core.util.Country)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param languageIdentifier
	 *            Use the three character alphabetic codes from ISO
	 *            639-2/Terminology, with extensions if required.
	 * @param countryIdentifier
	 *            Optional. Use the three digit numeric codes from ISO 3166-1,
	 *            with extensions if required.
	 * @param mdrDatabase
	 */
	public LanguageIdentificationImpl(Node n, EnhGraph g,
			Language languageIdentifier, Country countryIdentifier,
			MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().LanguageIdentification);
		setLanguageIdentifier(languageIdentifier);
		setCountryIdentifier(countryIdentifier);
	}

	/**
	 * Constructor for {@link LanguageIdentificationImpl} which constructs the
	 * class from a given {@link Resource}.
	 * 
	 * @param resource
	 * @param mdrDatabase
	 */
	public LanguageIdentificationImpl(Resource resource,
			MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setLanguageIdentifier(Language languageIdentifier) {
		if (languageIdentifier == null) {
			throw new IllegalArgumentException(
					"Language must be specified  for LanguageIdentification.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().languageIdentifier,
				mdrDatabase.getUtil().createTypedLiteral(
						languageIdentifier.toString()));
	}

	@Override
	public Language getLanguageIdentifier() {
		return Language.getByValue(getPropertyValue(
				mdrDatabase.getVocabulary().languageIdentifier).asLiteral()
				.getString());
	}

	@Override
	public void setCountryIdentifier(Country countryIdentifier) {
		if (countryIdentifier != null) {
			setPropertyValue(
					mdrDatabase.getVocabulary().countryIdentifier,
					mdrDatabase.getUtil().createTypedLiteral(
							countryIdentifier.toString()));
		} else {
			setPropertyValue(mdrDatabase.getVocabulary().countryIdentifier,
					null);
		}
	}

	@Override
	public Country getCountryIdentifier() {
		RDFNode countryIdentifier = getPropertyValue(mdrDatabase
				.getVocabulary().countryIdentifier);
		if (countryIdentifier == null) {
			logger.debug("LanguageIdentification does not have countryIdentifier");
			return null;
		}
		return Country.getByValue(countryIdentifier.asLiteral().getString());
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public LanguageIdentificationResource asMDRResource() {
		return this;
	}

	@Override
	public Language getLanguage() {
		return this.getLanguageIdentifier();
	}

	@Override
	public Country getCountry() {
		return this.getCountryIdentifier();
	}

}
