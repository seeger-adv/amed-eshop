package de.adv_boeblingen.seegerj.amed.eshop.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.io.IOUtils;

public class PropertyProvider implements ServletContextListener {
	private static Properties properties = new Properties();

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		PropertyProvider.properties = new Properties();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		Logger logger = Logger.getLogger(getClass().getName());
		InputStream stream = null;
		try {
			ServletContext context = event.getServletContext();
			stream = context.getResourceAsStream("/WEB-INF/config.properties");
			PropertyProvider.properties.load(stream);
			logger.log(Level.INFO, String.format("loaded %d properties from config.", properties.size()));
		} catch (IOException e) {
			logger.logp(Level.SEVERE, "ContextProperties", "contextInitialized", "Exception thrown", e);
		} finally {
			IOUtils.closeQuietly(stream);
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
