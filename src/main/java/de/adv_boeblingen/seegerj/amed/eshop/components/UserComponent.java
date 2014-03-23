package de.adv_boeblingen.seegerj.amed.eshop.components;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;

import de.adv_boeblingen.seegerj.amed.eshop.model.Session;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Customer;

public class UserComponent {

	@Inject
	private ApplicationStateManager stateManager;

	@Parameter
	@Property
	private String layout;

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