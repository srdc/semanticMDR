package tr.com.srdc.mdr.core.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.mdr.core.store.MDRDatabase;
import tr.com.srdc.mdr.core.store.MDRDatabaseManager;


public class RepositoryManager {

	private static final Logger logger = LoggerFactory
			.getLogger(RepositoryManager.class);

	private static RepositoryManager repositoryManager;

	private MDRDatabase mdrDatabase;

	// Currently, only one repository exists.
	// TODO Lateron, user management facilities may require one repository
	// for each user which all process on the same MDRDatabase
	private Repository repository;

	// Each Repository works on the same MDRDatabase.
	private RepositoryManager() {
		try {
			mdrDatabase = MDRDatabaseManager.getInstance().createMDRDatabase();
			repository = new Repository(mdrDatabase);
			mdrDatabase.setSyncMode(true);
			
		} catch (MDRException e) {
			logger.error("Repository could not be initialized for the user", e);
			throw new IllegalStateException(e);
		}
	}

	public static RepositoryManager getInstance() {
		if (repositoryManager == null) {
			repositoryManager = new RepositoryManager();
		}
		return repositoryManager;
	}

	public Repository getRepository() {
		return repository;
	}

}
