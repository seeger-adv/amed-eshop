package de.adv_boeblingen.seegerj.amed.eshop.provider;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DatabaseProvider implements ServletContextListener {
	public static interface DatabaseRunnable<T> {
		public T run(EntityManager manager, EntityTransaction transaction);
	}

	private static EntityManagerFactory emf;

	private static EntityManager createEntityManager() {
		if (emf == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}

		return emf.createEntityManager();
	}

	public static <T> T runQuery(DatabaseRunnable<T> runnable) {
		EntityManager manager = createEntityManager();
		try {
			return runnable.run(manager, null);
		} finally {
			manager.close();
		}
	}

	public static <T> void runTransaction(DatabaseRunnable<T> runnable) {
		EntityManager manager = createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		try {
			runnable.run(manager, transaction);
		} finally {
			transaction.commit();
			manager.close();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		emf.close();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		Logger logger = Logger.getLogger(getClass().getName());
		String databaseConfig = PropertyProvider.getProperty("DATABASECONFIG");
		emf = Persistence.createEntityManagerFactory(databaseConfig);
		logger.log(Level.INFO, "EntityManagerFactory successfully created!");
	}
}