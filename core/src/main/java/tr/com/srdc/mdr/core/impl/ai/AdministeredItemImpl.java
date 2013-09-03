package tr.com.srdc.mdr.core.impl.ai;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.api.ai.Context;
import tr.com.srdc.mdr.core.api.composite.Contact;
import tr.com.srdc.mdr.core.api.composite.Organization;
import tr.com.srdc.mdr.core.impl.composite.RegistrationAuthorityImpl;
import tr.com.srdc.mdr.core.impl.composite.StewardshipRelationshipImpl;
import tr.com.srdc.mdr.core.impl.composite.SubmissionRelationshipImpl;
import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministeredItemContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ClassificationSchemeItemResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DefinitionResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DesignationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ItemIdentifierResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.LanguageSectionResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.OrganizationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ReferenceDocumentResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.TerminologicalEntryResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * The abstract class of Administered Item. An Administered Item may be any one
 * of those types listed in Figure 2 which is specified in ISO/IEC 11179-3. Each
 * instance of an Administered Item encapsulates its own Administration Record.
 * 
 * @author anil
 * 
 */
public abstract class AdministeredItemImpl extends AbstractMDRResource
		implements AdministeredItemResource {

	private static final Logger logger = LoggerFactory
			.getLogger(AdministeredItemImpl.class);

	@Override
	public abstract void setAdministrationRecord(
			AdministrationRecordResource administrationRecord);

	@Override
	public abstract AdministrationRecordResource getAdministrationRecord();

	/**
	 * Constructor for an {@link AdministeredItemResource} which extends
	 * {@link AbstractMDRResource}.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param administeredBy
	 *            An {@link AdministeredItemResource} is administered by an
	 *            {@link OrganizationResource} represented by the
	 *            {@link StewardshipRelationshipResource}.
	 * @param submittedBy
	 *            An {@link AdministeredItemResource} is submitted by an
	 *            {@link OrganizationResource} represented by the
	 *            {@link SubmissionRelationshipResource}.
	 * @param registeredBy
	 *            An {@link AdministeredItemResource} is registered by a
	 *            {@link RegistrationAuthorityResource}.
	 * @param having
	 *            An {@link AdministeredItemResource} has to have at least one
	 *            {@link AdministeredItemContextResource}.
	 * @param mdrDatabase
	 */
	public AdministeredItemImpl(Node n, EnhGraph g,
			StewardshipRelationshipResource administeredBy,
			SubmissionRelationshipResource submittedBy,
			RegistrationAuthorityResource registeredBy,
			AdministeredItemContextResource having, MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setAdministeredBy(administeredBy);
		setSubmittedBy(submittedBy);
		setRegisteredBy(registeredBy);
		addHaving(having);
	}

	/**
	 * Constructor for an {@link AdministeredItemImpl} which extends
	 * {@link AbstractMDRResource}.
	 * 
	 * @param resource
	 *            An RDF Resource.
	 * @param mdrDatabase
	 */
	public AdministeredItemImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setSubmittedBy(SubmissionRelationshipResource submittedBy) {
		if (submittedBy == null) {
			throw new IllegalArgumentException(
					"Submitted By must be specified for AdministratedItem");
		}
		setPropertyValue(mdrDatabase.getVocabulary().submittedBy, submittedBy);
	}

	@Override
	public SubmissionRelationshipResource getSubmittedBy() {
		return new SubmissionRelationshipImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().submittedBy),
				mdrDatabase);
	}

	@Override
	public void addClassifiedBy(
			ClassificationSchemeItemResource classificationSchemeItem) {
		if (classificationSchemeItem == null) {
			throw new IllegalArgumentException(
					"Classification Scheme Item must be specified for AdministratedITem");
		}
		addProperty(mdrDatabase.getVocabulary().classifiedBy,
				classificationSchemeItem);
	}

	@Override
	public List<ClassificationSchemeItemResource> getClassifiedBy()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().classifiedBy);
		return mdrDatabase.getUtil().createList(it,
				ClassificationSchemeItemResource.class);
	}

	@Override
	public void removeClassifiedBy(
			ClassificationSchemeItemResource classificationSchemeItem) {
		if (classificationSchemeItem == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		removeProperty(mdrDatabase.getVocabulary().classifiedBy,
				classificationSchemeItem);
	}

	@Override
	public void setAdministeredBy(StewardshipRelationshipResource administeredBy) {
		if (administeredBy == null) {
			throw new IllegalArgumentException(
					"Administrated By must be specified for AdministratedItem");
		}
		setPropertyValue(mdrDatabase.getVocabulary().administeredBy,
				administeredBy);
	}

	@Override
	public StewardshipRelationshipResource getAdministeredBy() {
		return new StewardshipRelationshipImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().administeredBy),
				mdrDatabase);
	}

	@Override
	public void setRegisteredBy(RegistrationAuthorityResource registeredBy) {
		if (registeredBy == null) {
			throw new IllegalArgumentException(
					"Registration Authority must be specified for Administrated Item");
		}
		setPropertyValue(mdrDatabase.getVocabulary().registeredBy, registeredBy);
	}

	@Override
	public RegistrationAuthorityResource getRegisteredBy() {
		return new RegistrationAuthorityImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().registeredBy),
				mdrDatabase);
	}

	@Override
	public void addHaving(AdministeredItemContextResource having) {
		if (having == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		addProperty(mdrDatabase.getVocabulary().having, having);
	}

	@Override
	public List<AdministeredItemContextResource> getHavings()
			throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().having);
		return mdrDatabase.getUtil().createList(it,
				AdministeredItemContextResource.class);
	}

	@Override
	public void removeHaving(AdministeredItemContextResource having) {
		if (having == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}
		removeProperty(mdrDatabase.getVocabulary().having, having);
	}

	@Override
	public void addDescribedBy(ReferenceDocumentResource describedBy) {
		if (describedBy == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be added.");
		}
		addProperty(mdrDatabase.getVocabulary().describedBy, describedBy);
	}

	@Override
	public void removeDescribedBY(ReferenceDocumentResource describedBy) {
		if (describedBy == null) {
			throw new IllegalArgumentException(
					"Null is not allowed as the value of the property to be removed.");
		}

		removeProperty(mdrDatabase.getVocabulary().describedBy, describedBy);
	}

	@Override
	public List<ReferenceDocumentResource> getDescribedBy() throws MDRException {
		NodeIterator it = listPropertyValues(mdrDatabase.getVocabulary().describedBy);
		return mdrDatabase.getUtil().createList(it,
				ReferenceDocumentResource.class);
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public AdministeredItemResource asMDRResource() {
		return this;
	}

	@Override
	public void delete() {
		AdministrationRecordResource ar = this.getAdministrationRecord();
		ItemIdentifierResource ii = ar.getAdministeredItemIdentifier();

		ii.delete();
		ar.delete();

		try {
			AdministeredItemContextResource aic = this.getHavings().get(0);
			TerminologicalEntryResource te = aic.getTerminologicalEntry();
			LanguageSectionResource ls = te
					.getContaninigTerminologicalEntryLanguage().get(0);
			DefinitionResource def = ls.getContainigDefinitionEntry().get(0);
			DesignationResource name = ls.getContainingNameEntry().get(0);

			name.delete();
			def.delete();
			ls.delete();
			te.delete();
			aic.delete();
		} catch (MDRException e) {
			logger.error("{}'s resources about Context could not be deleted");
		}
		logger.debug("Only the first entries in name, definition, language section lists deleted form ");
		try {
			List<ClassificationSchemeItemResource> classifications = this
					.getClassifiedBy();
			for (ClassificationSchemeItemResource csi : classifications) {
				csi.delete();
			}
		} catch (MDRException e) {
			logger.error("{}'s resources about Classification Scheme could not be deleted");
		}
		logger.debug("All the entries about classification scheme deleted form {}");
	}

	@Override
	public String getUniqueID() {
		return this.getAdministrationRecord().getAdministeredItemIdentifier()
				.getDataIdentifier();
	}
	

	private DesignationResource getDesignationResource() {
		TerminologicalEntryResource terminologicalEntry;
		try {
			// Since only one context per AdministeredItem is assumed,
			// first AIC selected
			terminologicalEntry = this.getHavings().get(0)
					.getTerminologicalEntry();
		} catch (MDRException e) {
			String msg = "AIC List of AdministeredItem could not be obtained";
			logger.error(msg);
			throw new IllegalStateException(msg);
		}

		DesignationResource designationResource;
		try {
			// Since one definition per context is assumed, first items
			// selected
			LanguageSectionResource languageSectionResource = terminologicalEntry
					.getContaninigTerminologicalEntryLanguage().get(0);
			List<DesignationResource> designationList = languageSectionResource
					.getContainingNameEntry();
			if (designationList == null || designationList.isEmpty()) {
				// There is no Name Entry for this AdministeredItem
				return null;
			}
			designationResource = designationList.get(0);
		} catch (MDRException e) {
			String msg = "Designation List / LanguageSection List could not be obtained from AdministeredItem";
			logger.error(msg);
			throw new IllegalStateException(msg);
		}
		return designationResource;
	}

	/**
	 * Find a name in a depth-first manner. We know that this {@link Context}
	 * has only one name.
	 */
	@Override
	public String getName() {
		DesignationResource designationResource = getDesignationResource();
		if (designationResource == null)
			return null;
		return getDesignationResource().getName();
	}

	@Override
	public void setName(String name) {
		DesignationResource designationResource = getDesignationResource();
		designationResource.setName(name);
	}

	private DefinitionResource getDefinitionResource() {
		TerminologicalEntryResource terminologicalEntry;
		try {
			// Since only one context per AdministeredItem is assumed,
			// first AIC selected
			terminologicalEntry = this.getHavings().get(0)
					.getTerminologicalEntry();
		} catch (MDRException e) {
			String msg = "AIC List of AdministeredItem could not be obtained";
			logger.error(msg);
			throw new IllegalStateException(msg);
		}

		DefinitionResource definitionResource;
		try {
			// Since one definition per context is assumed, first items
			// selected
			LanguageSectionResource languageSectionResource = terminologicalEntry
					.getContaninigTerminologicalEntryLanguage().get(0);
			List<DefinitionResource> definitionList = languageSectionResource
					.getContainigDefinitionEntry();
			if (definitionList == null || definitionList.isEmpty()) {
				// There is no Definition Entry for this AdministeredItem
				return null;
			}
			definitionResource = definitionList.get(0);
		} catch (MDRException e) {
			String msg = "Definition List / LanguageSection List could bot be obtained from AdministeredItem";
			logger.error(msg);
			throw new IllegalStateException(msg);
		}
		return definitionResource;
	}

	@Override
	public String getDefinition() {
		DefinitionResource definitionResource = getDefinitionResource();
		if (definitionResource == null)
			return null;
		return definitionResource.getDefinitionText();
	}

	@Override
	public void setDefinition(String definition) {
		DefinitionResource definitionResource = getDefinitionResource();
		if (definitionResource == null) {
			TerminologicalEntryResource terminologicalEntry;
			try {
				// Since only one context per AdministeredItem is assumed,
				// first AIC selected
				terminologicalEntry = this.getHavings().get(0)
						.getTerminologicalEntry();
			} catch (MDRException e) {
				String msg = "AIC List of AdministeredItem could not be obtained";
				logger.error(msg);
				throw new IllegalStateException(msg);
			}

			try {
				// Since one definition per context is assumed, first items
				// selected
				LanguageSectionResource languageSectionResource = terminologicalEntry
						.getContaninigTerminologicalEntryLanguage().get(0);
				definitionResource = mdrDatabase.getResourceFactory()
						.createDefinition(languageSectionResource, definition,
								null);
				languageSectionResource
						.addContainigDefinitionEntry(definitionResource);

			} catch (MDRException e) {
				String msg = "LanguageSection of AdministeredItem could not be obtained";
				logger.error(msg);
				throw new IllegalStateException(msg);
			}
		}
		definitionResource.setDefinitionText(definition);
	}

	@Override
	public Context getContext() {
		List<AdministeredItemContextResource> aicList;
		try {
			// Assumed that AI registered in only one context
			aicList = this.getHavings();
		} catch (MDRException e) {
			String msg = "Error at obtaining Context relationships from AdministeredItem, null returned";
			logger.error(msg);
			throw new IllegalStateException(msg);
		}

		AdministeredItemContextResource aicr;
		try {
			aicr = aicList.get(0);
		} catch (Exception e) {
			String msg = "havings of the AI is null or empty";
			logger.error(msg);
			throw new IllegalStateException(msg,e);
		}

		return aicr.getContext();
	}

	@Override
	public Organization getAdministerOrganization() {
		StewardshipRelationshipResource stewardshipRelationship = this
				.getAdministeredBy();
		logger.debug("StewardshipRelationship obtained from AdministeredItem");
		return stewardshipRelationship.getStewardshipOrganization();
	}

	@Override
	public Contact getAdministerPerson() {
		StewardshipRelationshipResource stewardshipRelationship = this
				.getAdministeredBy();
		logger.debug("StewardshipRelationship obtained from AdministeredItem");
		return stewardshipRelationship.getAdminister().getStewardshipContact();
	}

}
