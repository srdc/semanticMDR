package tr.com.srdc.mdr.core.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.model.MDRResourceFactory;
import tr.com.srdc.mdr.core.model.Util;
import tr.com.srdc.mdr.core.model.Vocabulary;
import tr.com.srdc.mdr.core.store.query.ResourceQueryFactory;
import tr.com.srdc.mdr.core.store.query.TDBQueryFactory;
import tr.com.srdc.mdr.core.store.query.VirtuosoQueryFactory;
import tr.com.srdc.triplestore.JenaStore;
import tr.com.srdc.triplestore.JenaStoreException;
import tr.com.srdc.triplestore.TripleStoreProvider;
import tr.com.srdc.triplestore.TripleStoreProvider.TripleStoreType;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;


public class MDRDatabase {

	private static final Logger logger = LoggerFactory
			.getLogger(MDRDatabase.class);

	private static final String ISO11179OntologyFilePath = "model/salus.mdr.owl";
	public static final String BASE_URI = "http://www.salusproject.eu/iso11179-3/mdr#";

	private JenaStore jenaStore;
	private TripleStoreType storeType;
	private String storeName;
	private OntModel ontModel;
	private MDRResourceFactory resourceFactory;
	private ResourceQueryFactory queryFactory;
	private Vocabulary vocabulary;
	private Util util;

	// used to indicate whether the store will be synced or not.
	// for serial updates such as importing models
	// one can change the sync mode for performance improvement
	private boolean syncMode = true;

	public MDRDatabase(TripleStoreType storeType, String storeName)
			throws MDRException {
		this.storeType = storeType;
		this.storeName = storeName;
		init();
	}

	private void init() throws MDRException {
		try {
			jenaStore = TripleStoreProvider.getInstance().createStore(
					this.storeType, this.storeName);
			logger.info("--Existing models in the JenaStore--");
			for (String m : jenaStore.listModels()) {
				logger.info(m);
			}
			logger.info("---------------");
			this.ontModel = jenaStore.createOntModel(BASE_URI, null,
					ISO11179OntologyFilePath);
			if (!BASE_URI.equals(this.ontModel.getNsPrefixURI(""))) {
				logger.info("BASE_URI and the Base URI of the loaded ontology do not match!!!");
				logger.info("BASE_URI: {}", BASE_URI);
				logger.info("Coming from the ontology: {}",
						this.ontModel.getNsPrefixURI(""));
			}
		} catch (JenaStoreException e) {
			String msg = "Cannot create the JenaStore for MDRDatabase, storeName: "
					+ storeName;
			logger.error(msg);
			throw new MDRException(msg, e);
		}
		this.vocabulary = new Vocabulary(this);
		this.util = new Util(this);
		this.resourceFactory = new MDRResourceFactory(this);

		switch (this.storeType) {
		case JenaTDB:
			this.queryFactory = new TDBQueryFactory(this);
			break;
		case Virtuoso:
			this.queryFactory = new VirtuosoQueryFactory(this);
			break;
		}
	}

	public JenaStore getJenaStore() {
		if (this.jenaStore == null) {
			logger.error("JenaStore of the MDRDatabase is null.");
			throw new IllegalStateException(
					"JenaStore of the MDRDatabase cannot be null.");
		}
		return this.jenaStore;
	}

	/**
	 * MDRDatabase is based upon an ontology which keeps definitions and
	 * Elements of the MetaDataRepository. Apart form backend, this ontology is
	 * managed as on Jena {@link OntModel}
	 * 
	 * @return {@link OntModel} representation of MDR Ontology.
	 */
	public OntModel getOntModel() {
		if (this.ontModel == null) {
			logger.error("OntModel of the MDRDatabase is null.");
			throw new IllegalStateException(
					"OntModel of the MDRDatabase cannot be null.");
		}
		return this.ontModel;
	}

	/**
	 * Elements in MetaDataRepository are based on ISO 11179-3 specification.
	 * Classes, attributes and properties at the speciication is kept in
	 * Vocabulary.
	 * 
	 * @return {@link Vocabulary} based on ISO 11179-3 specification
	 */
	public Vocabulary getVocabulary() {
		if (this.vocabulary == null) {
			logger.error("Vocabulary of the MDRDatabase is null.");
			throw new IllegalStateException(
					"Vocabulary of the MDRDatabase cannot be null.");
		}
		return this.vocabulary;
	}

	/**
	 * Utility which have various methods to ease development like
	 * {@link Util#isNull(String)} or
	 * {@link Util#createList(com.hp.hpl.jena.rdf.model.NodeIterator, Class)}
	 * 
	 * @return {@link Util} which have been initalized with this MDRDatabase
	 */
	public Util getUtil() {
		if (this.util == null) {
			logger.error("Util of the MDRDatabase is null.");
			throw new IllegalStateException(
					"Util of the MDRDatabase cannot be null.");
		}
		return this.util;
	}

	/**
	 * Given the name of the resource, retrieves the {@link OntClass} from the
	 * associated {@link OntModel}. Default and Test {@link OntModel}s must load
	 * the ISO11179 ontology upon the start of the application.
	 * 
	 * @param name
	 * @return
	 */
	public OntClass getClass(String name) {
		OntClass ontClass = ontModel.getOntClass(BASE_URI + name);
		if (ontClass == null) {
			logger.error("Class: {} does not exist in the OntModel", name);
			throw new IllegalStateException(
					"The classes of the ontology should already be loaded into the Model");
		}
		return ontClass;
	}

	/**
	 * Given the name of the resource, retrieves the {@link OntProperty} from
	 * the associated {@link OntModel}. Default and Test {@link OntModel}s must
	 * load the ISO11179 ontology upon the start of the application.
	 * 
	 * @param name
	 * @return
	 */
	public OntProperty getProperty(String name) {
		OntProperty ontProperty = ontModel.getOntProperty(BASE_URI + name);
		if (ontProperty == null) {
			throw new IllegalStateException(
					"The properties of the ontology should already be loaded into the Model");
		}
		return ontProperty;
	}

	/**
	 * Static Factory has been adopted to prevent entering illegal states while
	 * creating elements in the Repository. So {@link MDRResourceFactory} which
	 * will create the Resources in this MDRDatabase is initialized while
	 * creating MDRDatabase.
	 * 
	 * @return {@link MDRResourceFactory} which have been initialized with this
	 *         MDRDatabase
	 */
	public MDRResourceFactory getResourceFactory() {
		if (this.resourceFactory == null) {
			logger.error("MDRResourceFactory of the MDRDatabase is null.");
			throw new IllegalStateException(
					"MDRResourceFactory of the MDRDatabase cannot be null.");
		}
		return this.resourceFactory;
	}

	// /**
	// * Base URI of the Ontology holding definitions and elements of Metadata
	// * Repository. It is known before loading the ontology file, however
	// * obtained by using {@link OntModel#getNsPrefixURI("")} so that it can be
	// * cross-checked in order to avoid inconsistencies.}
	// *
	// * @return the Base URI of the ontology holded by MDRDatabase
	// */
	// public String getBaseURI() {
	// return BASE_URI;
	// }

	/**
	 * Type of the Instance the {@link JenaStore} which persists this
	 * MDRDatabase, is specified while creating database through the
	 * constructors.
	 * 
	 * @return Type of {@link TripleStoreType}, {@link TripleStoreType#JenaTDB}
	 *         or {@link TripleStoreType#Virtuoso}
	 */
	public TripleStoreType getStoreType() {
		return this.storeType;
	}

	/**
	 * MDRQueryFactory is used to execute pre-defined SPARQL Queries over
	 * MDRDatabase's.
	 * <p>
	 * MDRQueryFactory is an abstract class including common query executions
	 * for both {@link JenaDatabase} and {@link VirtuosoDatabase}.
	 * {@link TDBQueryFactory} and {@link VirtuosoQueryFactory} are extensions
	 * to the {@link ResourceQueryFactory} and includes SPARQL with specific
	 * extensions such as pf:textMatch for Jena or bif:contains for Virtuoso.
	 * 
	 * @return Appropriate {@link ResourceQueryFactory} implementation according
	 *         to the type of {@link MDRDatabase#}
	 */
	public ResourceQueryFactory getQueryFactory() {
		if (this.queryFactory == null) {
			logger.error("ResourceQueryFactory of the MDRDatabase is null.");
			throw new IllegalStateException(
					"ResourceQueryFactory of the MDRDatabase cannot be null.");
		}
		return this.queryFactory;
	}

	/**
	 * Removes this {@link MDRDatabase} instance by removing the associated
	 * {@link JenaStore}.
	 * 
	 * @throws MDRException
	 */
	public void remove() throws MDRException {
		try {
			this.jenaStore.remove();
		} catch (JenaStoreException e) {
			String msg = "Cannot remove the store: " + this.storeName;
			logger.error(msg, e);
			throw new MDRException(msg, e);
		}
	}

	public void close() {
		this.jenaStore.close();
	}

	/**
	 * Used to control whether mdrDatabase.sync should be called automatically
	 * or by user
	 * 
	 * @param autoSync
	 *            if given <code>true</code>, mdrDatabase.sync will be called
	 *            automatically after each modification on underlying JenaStore.
	 *            If given <code>false</code>, no sync will be done, it is the
	 *            users responsibility to call sync to keep underlying JenaStore
	 *            consistent.
	 */
	public void setSyncMode(boolean activated) {
		this.syncMode = activated;
		if (activated) {
			this.jenaStore.setAutoSync(true);
		} else {
			this.jenaStore.setAutoSync(false);
		}
	}

	public void sync() {
		if (syncMode) {
			this.jenaStore.sync();
		}
	}

}
