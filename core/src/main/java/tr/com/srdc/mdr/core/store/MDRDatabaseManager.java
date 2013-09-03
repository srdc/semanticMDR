package tr.com.srdc.mdr.core.store;

import tr.com.srdc.mdr.core.model.MDRException;
import tr.com.srdc.triplestore.JenaStore;
import tr.com.srdc.triplestore.TripleStoreProvider.TripleStoreType;
import tr.com.srdc.triplestore.tdb.TDBStore;

public class MDRDatabaseManager {

	private static MDRDatabaseManager instance;

	private static final String MDRJenaStoreName = "mdr";

	private MDRDatabase mdrDatabase;

	private MDRDatabaseManager() {
	}

	public static MDRDatabaseManager getInstance() {
		if (instance == null) {
			instance = new MDRDatabaseManager();
		}
		return instance;
	}

	/**
	 * Create a {@link MDRDatabase} which is backed by a {@link TDBStore} by
	 * default whose name is "mdr".
	 * 
	 * @return
	 * @throws MDRException
	 */
	public MDRDatabase createMDRDatabase() throws MDRException {
		return createMDRDatabase(TripleStoreType.JenaTDB, MDRJenaStoreName);
	}

	/**
	 * Create a {@link MDRDatabase} which is backed by a {@link JenaStore} with
	 * given storeType and storeName.
	 * 
	 * @param storeType
	 * @param storeName
	 * @return
	 * @throws MDRException
	 */
	public MDRDatabase createMDRDatabase(TripleStoreType storeType,
			String storeName) throws MDRException {
		if (mdrDatabase == null) {
			mdrDatabase = new MDRDatabase(storeType, storeName);
		}
		return mdrDatabase;
	}

	/**
	 * Get the default {@link MDRDatabase} which is backed by a {@link TDBStore}
	 * whose name is "mdr".
	 * 
	 * @return
	 * @throws MDRException
	 */
	public MDRDatabase getMDRDatabase() throws MDRException {
		if (mdrDatabase == null) {
			throw new MDRException(
					"Default MDRDatabase has not been created yet.");
		}
		return mdrDatabase;
	}

	public MDRDatabase getMDRDatabase(String name) {
		return mdrDatabase;
	}

	public void removeMDRDatabase() throws MDRException {
		mdrDatabase.remove();
		mdrDatabase = null;
	}

}
