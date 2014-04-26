package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.util.Set;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;

import de.adv_boeblingen.seegerj.amed.eshop.annotations.RequiresLogin;
import de.adv_boeblingen.seegerj.amed.eshop.model.Session;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Address;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Customer;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Purchase;
import de.adv_boeblingen.seegerj.amed.eshop.model.payment.PaymentInfo;

@RequiresLogin
public class Account {
	@Inject
	private ApplicationStateManager stateManager;

	@Property
	private Purchase purchase;

	@Property
	private Address address;

	@Property
	private PaymentInfo payment;

	public Set<Purchase> getPurchases() {
		return getCustomer().getPurchase();
	}

	public Customer getCustomer() {
		Session session = stateManager.get(Session.class);
		return session.getCustomer();
	}
}
