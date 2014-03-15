package de.adv_boeblingen.seegerj.amed.eshop.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;

import de.adv_boeblingen.seegerj.amed.eshop.api.AuthenticatorService;
import de.adv_boeblingen.seegerj.amed.eshop.api.CryptService;
import de.adv_boeblingen.seegerj.amed.eshop.api.UserDao;
import de.adv_boeblingen.seegerj.amed.eshop.model.Customer;
import de.adv_boeblingen.seegerj.amed.eshop.provider.DatabaseProvider;
import de.adv_boeblingen.seegerj.amed.eshop.provider.DatabaseProvider.DatabaseRunnable;

public class UserDaoImpl
		implements UserDao {

	@InjectService("sha512")
	private CryptService cryptService;

	@Inject
	private AuthenticatorService authenticator;

	@Override
	public boolean register(final String mail, final String password) {
		DatabaseProvider.runTransaction(new DatabaseRunnable<Void>() {
			@Override
			public Void run(EntityManager manager, EntityTransaction transaction) {
				Customer customer = createUser(mail, password);
				manager.persist(customer);
				return null;
			}
		});

		return authenticator.login(mail, password);
	}

	private Customer createUser(String mail, String password) {
		Customer customer = new Customer();
		customer.setMail(mail);
		String cryptPassword = cryptService.crypt(password);
		customer.setPassword(cryptPassword);
		return customer;
	}
}
