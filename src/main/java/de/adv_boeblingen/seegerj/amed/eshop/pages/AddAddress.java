package de.adv_boeblingen.seegerj.amed.eshop.pages;

import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.ComponentSource;

import de.adv_boeblingen.seegerj.amed.eshop.annotations.RequiresLogin;
import de.adv_boeblingen.seegerj.amed.eshop.api.UserDao;
import de.adv_boeblingen.seegerj.amed.eshop.model.Session;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Address;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Customer;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Purchase;

@RequiresLogin
public class AddAddress {
	@Inject
	private ApplicationStateManager stateManager;

	@ActivationRequestParameter("next")
	private String nextPage;

	@Inject
	private ComponentSource componentSource;

	@Inject
	private UserDao userDao;

	@Property
	private String name;

	@Property
	private String street;

	@Property
	private String zip;

	@Property
	private String city;

	public void onValidateFromAddAddressForm() {
		Address address = new Address();
		address.setName(name);
		address.setStreet(street);
		address.setZip(Integer.parseInt(zip));
		address.setCity(city);

		addAddressToUser(address);
	}

	private void addAddressToUser(Address address) {
		Session session = stateManager.get(Session.class);
		Customer customer = session.getCustomer();
		address.setCustomer(customer);
		customer.getAddress().add(address);
		userDao.updateUser(customer);

		if (stateManager.exists(Purchase.class)) {
			stateManager.get(Purchase.class).setAddress(address);
		}
	}

	public Object onSuccess() {
		if (nextPage == null) {
			return Account.class;
		}

		return this.componentSource.getPage(nextPage);
	}
}
