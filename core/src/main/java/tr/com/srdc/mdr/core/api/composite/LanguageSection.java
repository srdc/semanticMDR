package tr.com.srdc.mdr.core.api.composite;

import tr.com.srdc.mdr.core.api.MDRNode;
import tr.com.srdc.mdr.core.model.iso11179.composite.LanguageSectionResource;

/**
 * 
 * If a registry supports multiple languages, the language(s) associated with
 * particular names and definitions need to be identified. A Language Section
 * partitions a Terminological Entry by Language. A language section language
 * identifier identifies the Language associated with a particular Language
 * Section. A Language Section contains zero or more Designations. A Language
 * Section contains zero or more Definitions.
 * 
 * @author anil
 * 
 */
public interface LanguageSection extends MDRNode {

	@Override
	/**
	 * @return the {@link LanguageSectionResource} version this instance.
	 */
	LanguageSectionResource asMDRResource();

}
