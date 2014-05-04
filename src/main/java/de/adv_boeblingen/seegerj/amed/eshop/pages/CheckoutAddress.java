package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.util.Set;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;

import de.adv_boeblingen.seegerj.amed.eshop.annotations.RequiresLogin;
import de.adv_boeblingen.seegerj.amed.eshop.model.Session;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Address;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Purchase;

@RequiresLogin
public class CheckoutAddress {
	@Inject
	private ApplicationStateManager stateManager;

	private Session session;

	private Purchase purchase;

	@Property
	private Address address;

	public Set<Address> getAddresses() {
		return session.getCustomer().getAddress();
	}

	public Object onActionFromSelect(long addressId) {
		for (Address a : getAddresses()) {
			if (a.getId() == addressId) {
				purchase.setAddress(a);
			}
		}
		return Checkout.class;
	}

	public void onActivate() {
		session = stateManager.get(Session.class);
		purchase = stateManager.get(Purchase.class);
	}
}
