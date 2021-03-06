package de.adv_boeblingen.seegerj.amed.eshop.services;

import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.JavaScriptStackSource;

import de.adv_boeblingen.seegerj.amed.eshop.api.AuthenticatorService;
import de.adv_boeblingen.seegerj.amed.eshop.api.CategoryDao;
import de.adv_boeblingen.seegerj.amed.eshop.api.CryptService;
import de.adv_boeblingen.seegerj.amed.eshop.api.FilterFactory;
import de.adv_boeblingen.seegerj.amed.eshop.api.InvoiceService;
import de.adv_boeblingen.seegerj.amed.eshop.api.ProductDao;
import de.adv_boeblingen.seegerj.amed.eshop.api.PurchaseDao;
import de.adv_boeblingen.seegerj.amed.eshop.api.StockService;
import de.adv_boeblingen.seegerj.amed.eshop.api.UserDao;
import de.adv_boeblingen.seegerj.amed.eshop.dao.CategoryDaoImpl;
import de.adv_boeblingen.seegerj.amed.eshop.dao.ProductDaoImpl;
import de.adv_boeblingen.seegerj.amed.eshop.dao.PurchaseDaoImpl;
import de.adv_boeblingen.seegerj.amed.eshop.dao.UserDaoImpl;
import de.adv_boeblingen.seegerj.amed.eshop.filters.RequiresLoginFilter;

public class AppModule {
	public static void bind(ServiceBinder binder) {
		binder.bind(AuthenticatorService.class, AuthenticatorServiceImpl.class);
		binder.bind(CryptService.class, SHA512Service.class);
		binder.bind(CryptService.class, MD5Service.class);

		binder.bind(UserDao.class, UserDaoImpl.class);
		binder.bind(ProductDao.class, ProductDaoImpl.class);
		binder.bind(CategoryDao.class, CategoryDaoImpl.class);
		binder.bind(FilterFactory.class, FilterFactoryImpl.class);
		binder.bind(StockService.class, StockProviderImpl.class);
		binder.bind(PurchaseDao.class, PurchaseDaoImpl.class);
		binder.bind(InvoiceService.class, InvoiceServiceImpl.class);
	}

	public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {
		configuration.add("tapestry.hmac-passphrase", "machete schreibt keine sms!");
	}

	public static void contributeComponentRequestHandler(OrderedConfiguration<Object> configuration) {
		configuration.addInstance("RequiresLogin", RequiresLoginFilter.class);
	}

	@Contribute(JavaScriptStackSource.class)
	public static void addMyStack(MappedConfiguration<String, JavaScriptStack> configuration) {
		configuration.addInstance("JqueryZoom", JqueryZoom.class);
	}
}
