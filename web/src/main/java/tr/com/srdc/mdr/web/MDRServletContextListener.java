package tr.com.srdc.mdr.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.salusproject.configuration.SALUSConfiguration;
import eu.salusproject.securityprivacy.authentication.db.Database;

import tr.com.srdc.mdr.core.impl.Repository;
import tr.com.srdc.mdr.core.impl.RepositoryManager;
import tr.com.srdc.mdr.core.model.MDRException;

public class MDRServletContextListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory
			.getLogger(MDRServletContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String configFilePath = sce.getServletContext()
				.getInitParameter(SALUSConfiguration.CONTEXT_ATTRIBUTE_SALUS_CONFIG);
		SALUSConfiguration.init(configFilePath);
		
		Database.getInstance();
		Repository repository = RepositoryManager.getInstance().getRepository();
		try {
			repository.getMDRDatabase().setSyncMode(false);
			repository.getDefaults().initDefaultResources();
		} catch (MDRException e) {
			logger.error("Default Resource from Repository Defaults could not be created");
		} finally {
			repository.getMDRDatabase().setSyncMode(true);
		}
		logger.info("MDR servlet context initialized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("Closing down...");
		RepositoryManager.getInstance().getRepository().getMDRDatabase()
				.close();
		logger.info("MDRDatabase closed successfully while exiting the MDRServlet.");
	}
}
