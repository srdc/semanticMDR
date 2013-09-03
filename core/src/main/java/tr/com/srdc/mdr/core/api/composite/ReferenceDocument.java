package tr.com.srdc.mdr.core.api.composite;

import java.util.List;

import tr.com.srdc.mdr.core.api.MDRNode;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.iso11179.composite.ReferenceDocumentResource;



/**
 * An Administered Item may be described by one or more Reference Documents. For
 * each Reference Document, the Organization that originated the Reference
 * Document must be identified.
 * 
 * @author anil
 * 
 */
public interface ReferenceDocument extends MDRNode {

	@Override
	/**
	 * @return the {@link ReferenceDocumentResource} version this instance.
	 */
	ReferenceDocumentResource asMDRResource();

	String getIdentifier();

	/**
	 * 
	 * @return the type description of {@link ReferenceDocument}. If the
	 *         attribute does not exist return <code>null</code>
	 */
	String getTypeDescription();

	/**
	 * 
	 * @return the unmodifiable list of {@link LanguageIdentification} of
	 *         {@link ReferenceDocument}.
	 * @throws MDRException
	 */
	List<LanguageIdentification> getLanguageIdentifications()
			throws MDRException;

	/**
	 * 
	 * @return the title of {@link ReferenceDocument}. If attribute does not
	 *         exist return <code>null</code>
	 */
	String getTitle();

	/**
	 * 
	 * @return the unmodifiable list of {@link Organization} providing this
	 *         {@link ReferenceDocument}
	 * @throws MDRException
	 */
	List<Organization> getProviderOrganizations() throws MDRException;

}
