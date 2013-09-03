package tr.com.srdc.mdr.core.api.composite;

import tr.com.srdc.mdr.core.api.MDRNode;
import tr.com.srdc.mdr.core.model.iso11179.composite.OrganizationResource;

public interface Organization extends MDRNode {

	@Override
	/**
	 * @return the {@link OrganizationResource} version this instance.
	 */
	OrganizationResource asMDRResource();

	String getName();

	/**
	 * 
	 * @return the mail address of {@link Organization}. If the attribute does
	 *         not exist return <code>null</code>
	 */
	String getMailAddress();

	/**
	 * 
	 * @param identifier
	 * @return the {@link ReferenceDocument} provided by this
	 *         {@link Organization}
	 */
	ReferenceDocument createReferenceDocument(String identifier);

	/**
	 * 
	 * @param identifier
	 * @param typeDescription
	 *            Optional.
	 * @param languageIdentification
	 *            Optional.
	 * @param title
	 *            Optional.
	 * @return the {@link ReferenceDocument} provided by this
	 *         {@link Organization}
	 */
	ReferenceDocument createReferenceDocument(String identifier,
			String typeDescription,
			LanguageIdentification languageIdentification, String title);

}
