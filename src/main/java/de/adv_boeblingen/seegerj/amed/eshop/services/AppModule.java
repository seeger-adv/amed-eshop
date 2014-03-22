package de.adv_boeblingen.seegerj.amed.eshop.services;

import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;

import de.adv_boeblingen.seegerj.amed.eshop.api.AuthenticatorService;
import de.adv_boeblingen.seegerj.amed.eshop.api.Catalog;
import de.adv_boeblingen.seegerj.amed.eshop.api.CryptService;
import de.adv_boeblingen.seegerj.amed.eshop.api.UserDao;
import de.adv_boeblingen.seegerj.amed.eshop.dao.CatalogDao;
import de.adv_boeblingen.seegerj.amed.eshop.dao.UserDaoImpl;
import de.adv_boeblingen.seegerj.amed.eshop.filters.RequiresLoginFilter;

public class AppModule {
	public static void contributeComponentRequestHandler(OrderedConfiguration<Object> configuration) {
		configuration.addInstance("RequiresLogin", RequiresLoginFilter.class);
	}

	public static void bind(ServiceBinder binder) {
		binder.bind(AuthenticatorService.class, AuthenticatorServiceImpl.class);
		binder.bind(CryptService.class, SHA512Service.class);
		binder.bind(CryptService.class, MD5Service.class);

		binder.bind(UserDao.class, UserDaoImpl.class);
		binder.bind(Catalog.class, CatalogDao.class);
	}
}