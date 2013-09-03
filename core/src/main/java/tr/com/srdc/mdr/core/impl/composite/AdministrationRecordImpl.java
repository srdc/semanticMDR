package tr.com.srdc.mdr.core.impl.composite;

import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.model.AbstractMDRResource;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ItemIdentifierResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * The Administration Record instance provides a basis for identifying, naming,
 * defining, classifying and recording administrative information about the
 * Administered Item in the registry.
 * 
 * @author anil
 * 
 */
public class AdministrationRecordImpl extends AbstractMDRResource implements
		AdministrationRecordResource {

	private static final Logger logger = LoggerFactory
			.getLogger(AdministrationRecordImpl.class);

	/**
	 * Instead of calling the constructor of this class, use
	 * {@link MDRResourceFactory#createAdministrationRecord(ItemIdentifierResource, String, String, java.util.Date, java.util.Date, java.util.Date, java.util.Date, String, String, String, String, String)}
	 * to avoid entering illegal states.
	 * 
	 * @param n
	 *            Mostly created by <code>Node.createURI()</code>
	 * @param g
	 *            The graph which holds all triples.
	 * @param administeredItemIdentifier
	 *            An identifier for an administered item.
	 * @param registrationStatus
	 *            A designation of the status in the registration life-cycle of
	 *            an Administered Item.
	 * @param administrativeStatus
	 *            A designation of the status in the administrative process of a
	 *            Registration Authority for handling registration requests.
	 * @param creationDate
	 *            The date the Administered Item was created.
	 * @param lastChangeDate
	 *            Optional. The date the Administered Item was last changed.
	 * @param effectiveDate
	 *            Optional. The date an administered item became/becomes
	 *            available to registry users.
	 * @param untilDate
	 *            Optional. The date an Administered Item is no longer effective
	 *            in the registry.
	 * @param changeDescription
	 *            Optional. However, must exist if the
	 *            <code>lastChangeDate</code> is specified.The description of
	 *            what has changed in the Administered Item since the prior
	 *            version of the Administered Item.
	 * @param administrativeNote
	 *            Optional. Any general note about the Administered Item.
	 * @param explanatoryComment
	 *            Optional. Descriptive comments about the Administered Item.
	 * @param unresolvedIssue
	 *            Optional. Any problem that remains unresolved regarding proper
	 *            documentation of the Administered Item.
	 * @param origin
	 *            Optional. (Administered item) the source (document, project,
	 *            discipline or model) for the Administered Item
	 * @param mdrDatabase
	 */
	public AdministrationRecordImpl(Node n, EnhGraph g,
			ItemIdentifierResource administeredItemIdentifier,
			String registrationStatus, String administrativeStatus,
			Calendar creationDate, Calendar lastChangeDate,
			Calendar effectiveDate, Calendar untilDate,
			String changeDescription, String administrativeNote,
			String explanatoryComment, String unresolvedIssue, String origin,
			MDRDatabase mdrDatabase) {
		super(n, g, mdrDatabase);
		setSuperClass(mdrDatabase.getVocabulary().AdministrationRecord);
		setAdministeredItemIdentifier(administeredItemIdentifier);
		setRegistrationStatus(registrationStatus);
		setAdministrativeStatus(administrativeStatus);
		setCreationDate(creationDate);
		setLastChangeDate(lastChangeDate, changeDescription);
		setEffectiveDate(effectiveDate);
		setUntilDate(untilDate);
		setAdministrativeNote(administrativeNote);
		setExplanatoryComment(explanatoryComment);
		setUnresolvedIssue(unresolvedIssue);
		setOrigin(origin);
	}

	/**
	 * Constructor for {@link AdministrationRecordImpl} which constructs the
	 * class from a given {@link Resource}.
	 * 
	 * @param resource
	 * @param mdrDatabase
	 */
	public AdministrationRecordImpl(Resource resource, MDRDatabase mdrDatabase) {
		super(resource, mdrDatabase);
	}

	@Override
	public void setAdministeredItemIdentifier(
			ItemIdentifierResource administeredItemIdentifier) {
		if (administeredItemIdentifier == null) {
			throw new IllegalArgumentException(
					"Administered Item Identifier must be specified for Administration Record.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().administeredItemIdentifier,
				administeredItemIdentifier);
	}

	@Override
	public ItemIdentifierResource getAdministeredItemIdentifier() {
		return new ItemIdentifierImpl(
				getPropertyResourceValue(mdrDatabase.getVocabulary().administeredItemIdentifier),
				mdrDatabase);
	}

	@Override
	public void setRegistrationStatus(String registrationStatus) {
		if (Util.isNull(registrationStatus)) {
			throw new IllegalArgumentException(
					"Registration Status must be specified for Administration Record.");
		}
		setPropertyValue(mdrDatabase.getVocabulary().registrationStatus,
				mdrDatabase.getUtil().createTypedLiteral(registrationStatus));
	}

	@Override
	public String getRegistrationStatus() {
		return getPropertyValue(mdrDatabase.getVocabulary().registrationStatus)
				.asLiteral().getString();
	}

	@Override
	public void setAdministrativeStatus(String administrativeStatus) {
		if (Util.isNull(administrativeStatus)) {
			throw new IllegalArgumentException(
					"Administrative Status must be specified for Administration Record.");
		}
		setPropertyValue(mdrDatabase.getVocabulary().administrativeStatus,
				mdrDatabase.getUtil().createTypedLiteral(administrativeStatus));
	}

	@Override
	public String getAdministrativeStatus() {
		return getPropertyValue(
				mdrDatabase.getVocabulary().administrativeStatus).asLiteral()
				.getString();
	}

	@Override
	public void setCreationDate(Calendar creationDate) {
		if (creationDate == null) {
			throw new IllegalArgumentException(
					"Creation Date must be specified for Administration Record.");
		}
		setPropertyValue(
				mdrDatabase.getVocabulary().creationDate,
				mdrDatabase.getOntModel().createTypedLiteral(
						DatatypeConverter.printDateTime(creationDate)));
	}

	@Override
	public Calendar getCreationDate() {
		return DatatypeConverter.parseDateTime(getPropertyValue(
				mdrDatabase.getVocabulary().creationDate).asLiteral()
				.getLexicalForm());
	}

	@Override
	public void setLastChangeDate(Calendar lastChangeDate,
			String changeDescription) {
		if (lastChangeDate != null && Util.isNull(changeDescription)) {
			throw new IllegalArgumentException(
					"Change Description must be specified if a Last Change Date is set for Administration Record.");
		}
		if (lastChangeDate == null && !Util.isNull(changeDescription)) {
			throw new IllegalArgumentException(
					"Change Description cannot be set if a Last Change Date is not specified for Administration Record.");
		}
		if (lastChangeDate == null) {
			setPropertyValue(mdrDatabase.getVocabulary().lastChangeDate,
					mdrDatabase.getUtil().createTypedLiteral(null));
		} else {
			setPropertyValue(
					mdrDatabase.getVocabulary().lastChangeDate,
					mdrDatabase.getOntModel().createTypedLiteral(
							DatatypeConverter.printDateTime(lastChangeDate)));
		}
		setPropertyValue(mdrDatabase.getVocabulary().changeDescription,
				mdrDatabase.getUtil().createTypedLiteral(changeDescription));
	}

	@Override
	public Calendar getLastChangeDate() {
		RDFNode lastChangeDate = getPropertyValue(mdrDatabase.getVocabulary().lastChangeDate);
		if (lastChangeDate == null) {
			logger.debug("AdministrationRecord does not have lastChangeDate");
			return null;
		}
		return DatatypeConverter.parseDateTime(lastChangeDate.asLiteral()
				.getLexicalForm());
	}

	@Override
	public String getChangeDescription() {
		RDFNode changeDescription = getPropertyValue(mdrDatabase
				.getVocabulary().changeDescription);
		if (changeDescription == null) {
			logger.debug("AdministrationRecord does not have changeDescription");
			return null;
		}
		return changeDescription.asLiteral().getString();
	}

	@Override
	public void setEffectiveDate(Calendar effectiveDate) {
		if (effectiveDate == null) {
			setPropertyValue(mdrDatabase.getVocabulary().effectiveDate,
					mdrDatabase.getUtil().createTypedLiteral(null));
		} else {
			setPropertyValue(
					mdrDatabase.getVocabulary().effectiveDate,
					mdrDatabase.getOntModel().createTypedLiteral(
							DatatypeConverter.printDateTime(effectiveDate)));
		}
	}

	@Override
	public Calendar getEffectiveDate() {
		RDFNode effectiveDate = getPropertyValue(mdrDatabase.getVocabulary().effectiveDate);
		if (effectiveDate == null) {
			logger.debug("AdministrationRecord does not have effectiveDate");
			return null;
		}
		return DatatypeConverter.parseDateTime(effectiveDate.asLiteral()
				.getLexicalForm());
	}

	@Override
	public void setUntilDate(Calendar untilDate) {
		if (untilDate == null) {
			setPropertyValue(mdrDatabase.getVocabulary().untilDate, mdrDatabase
					.getUtil().createTypedLiteral(null));
		} else {
			setPropertyValue(
					mdrDatabase.getVocabulary().untilDate,
					mdrDatabase.getOntModel().createTypedLiteral(
							DatatypeConverter.printDateTime(untilDate)));
		}
	}

	@Override
	public Calendar getUntilDate() {
		RDFNode untilDate = getPropertyValue(mdrDatabase.getVocabulary().untilDate);
		if (untilDate == null) {
			logger.debug("AdministrationRecord does not have untilDate");
			return null;
		}
		return DatatypeConverter.parseDateTime(untilDate.asLiteral()
				.getLexicalForm());
	}

	@Override
	public void setAdministrativeNote(String administrativeNote) {
		setPropertyValue(mdrDatabase.getVocabulary().administrativeNote,
				mdrDatabase.getUtil().createTypedLiteral(administrativeNote));
	}

	@Override
	public String getAdministrativeNote() {
		RDFNode administrativeNote = getPropertyValue(mdrDatabase
				.getVocabulary().administrativeNote);
		if (administrativeNote == null) {
			logger.debug("AdministrationRecord does not have administrativeNote");
			return null;
		}
		return administrativeNote.asLiteral().getString();
	}

	@Override
	public void setExplanatoryComment(String explanatoryComment) {
		setPropertyValue(mdrDatabase.getVocabulary().explanatoryComment,
				mdrDatabase.getUtil().createTypedLiteral(explanatoryComment));
	}

	@Override
	public String getExplanatoryComment() {
		RDFNode explanatoryComment = getPropertyValue(mdrDatabase
				.getVocabulary().explanatoryComment);
		if (explanatoryComment == null) {
			logger.debug("AdministrationRecord does not have explanatoryComment");
			return null;
		}
		return explanatoryComment.asLiteral().getString();
	}

	@Override
	public void setUnresolvedIssue(String unresolvedIssue) {
		setPropertyValue(mdrDatabase.getVocabulary().unresolvedIssue,
				mdrDatabase.getUtil().createTypedLiteral(unresolvedIssue));
	}

	@Override
	public String getUnresolvedIssue() {
		RDFNode unresolvedIssue = getPropertyValue(mdrDatabase.getVocabulary().unresolvedIssue);
		if (unresolvedIssue == null) {
			logger.debug("AdministrationRecord does not have unresolvedIssue");
			return null;
		}
		return unresolvedIssue.asLiteral().getString();
	}

	@Override
	public void setOrigin(String origin) {
		setPropertyValue(mdrDatabase.getVocabulary().origin, mdrDatabase
				.getUtil().createTypedLiteral(origin));
	}

	@Override
	public String getOrigin() {
		RDFNode origin = getPropertyValue(mdrDatabase.getVocabulary().origin);
		if (origin == null) {
			logger.debug("AdministrationRecord does not have origin");
			return null;
		}
		return origin.asLiteral().getString();
	}

	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// ///// High-Level Interface Methods////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////
	// //////////////////////////////////////////////

	@Override
	public AdministrationRecordResource asMDRResource() {
		return this;
	}

}
