package de.adv_boeblingen.seegerj.amed.eshop.services;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.InjectService;

import de.adv_boeblingen.seegerj.amed.eshop.model.Customer;
import de.adv_boeblingen.seegerj.amed.eshop.model.CustomerSession;
import de.adv_boeblingen.seegerj.amed.eshop.provider.DatabaseProvider;
import de.adv_boeblingen.seegerj.amed.eshop.provider.DatabaseProvider.DatabaseRunnable;

public class AuthenticatorServiceImpl
		implements AuthenticatorService {

	@SessionState
	private CustomerSession session;

	private boolean sessionExists;

	@InjectService("sha512")
	private CryptService cryptService;

	@Override
	public boolean login(String username, String password) {
		if (username == null || password == null) {
			return false;
		}

		final Customer foundUser = findUser(username);
		if (foundUser == null) {
			return false;
		}

		String passwordHash = cryptService.crypt(password);
		if (foundUser.getPassword().equals(passwordHash)) {
			writeLoginTime(foundUser);
			session = new CustomerSession(foundUser);
			return true;
		}

		return false;
	}

	@Override
	public boolean isValidSession() {
		return sessionExists && session.isValid();
	}

	private static Customer findUser(final String username) {
		return DatabaseProvider.runQuery(new DatabaseRunnable<Customer>() {
			@Override
			public Customer run(EntityManager manager, EntityTransaction transaction) {
				return manager.find(Customer.class, username);
			}
		});
	}

	private static void writeLoginTime(final Customer foundUser) {
		final Date now = new Date();

		DatabaseProvider.runTransaction(new DatabaseRunnable<Void>() {
			@Override
			public Void run(EntityManager manager, EntityTransaction transaction) {
				foundUser.setLastLogin(now);
				manager.merge(foundUser);
				return null;
			}
		});
	}
}
