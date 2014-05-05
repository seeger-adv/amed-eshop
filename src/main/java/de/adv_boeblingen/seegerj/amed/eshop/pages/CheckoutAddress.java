package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.util.Set;

import de.adv_boeblingen.seegerj.amed.eshop.api.RequiresLogin;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Address;

@RequiresLogin
public class CheckoutAddress extends AbstractCheckoutSelection<Address> {
	@Override
	protected void add(Address a) {
		purchase.setAddress(a);
	}

	@Override
	public Set<Address> getItems() {
		return session.getCustomer().getAddress();
	}
}
