package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.util.Set;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;

import de.adv_boeblingen.seegerj.amed.eshop.api.Identifiable;
import de.adv_boeblingen.seegerj.amed.eshop.api.RequiresLogin;
import de.adv_boeblingen.seegerj.amed.eshop.api.UserDao;
import de.adv_boeblingen.seegerj.amed.eshop.model.Session;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Address;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Customer;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Purchase;
import de.adv_boeblingen.seegerj.amed.eshop.model.payment.PaymentInfo;

@RequiresLogin
public class Account {
	@Inject
	private ApplicationStateManager stateManager;

	@Inject
	private UserDao userDao;

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
		Session session = this.stateManager.get(Session.class);
		return session.getCustomer();
	}

	public void onRemovepayment(String columnId) {
		Customer customer = getCustomer();
		removeById(customer.getPaymentInfo(), columnId);
		this.userDao.updateUser(customer);
	}

	public void onRemoveaddress(String columnId) {
		Customer customer = getCustomer();
		removeById(customer.getAddress(), columnId);
		this.userDao.updateUser(customer);
	}

	<T extends Identifiable> void removeById(Set<T> list, String idString) {
		long id = Long.parseLong(idString);

		for (T item : list) {
			if (item.getId() == id) {
				list.remove(item);
			}
		}
	}
}
