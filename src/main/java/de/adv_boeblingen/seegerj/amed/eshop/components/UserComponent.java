package de.adv_boeblingen.seegerj.amed.eshop.components;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;

import de.adv_boeblingen.seegerj.amed.eshop.model.Customer;
import de.adv_boeblingen.seegerj.amed.eshop.model.Session;

public class UserComponent {

	@Inject
	private ApplicationStateManager stateManager;

	private Session session;

	public String getUserInfo() {
		Customer user = session.getCustomer();
		return user.getMail();
	}

	public boolean getHasUserInfo() {
		session = stateManager.get(Session.class);
		return session.isValid();
	}
}