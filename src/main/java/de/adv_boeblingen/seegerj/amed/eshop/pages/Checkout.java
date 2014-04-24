package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.util.Set;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;

import de.adv_boeblingen.seegerj.amed.eshop.annotations.RequiresLogin;
import de.adv_boeblingen.seegerj.amed.eshop.model.Session;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Address;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Customer;
import de.adv_boeblingen.seegerj.amed.eshop.model.payment.PaymentInfo;

@RequiresLogin
public class Checkout {

	@Inject
	private ApplicationStateManager stateManager;

	private Session session;
	private Set<PaymentInfo> paymentInfo;
	private Set<Address> addresses;

	public Object onActivate() {
		session = stateManager.get(Session.class);

		Customer customer = session.getCustomer();
		if (customer == null) {
			return Login.class;
		}

		paymentInfo = customer.getPaymentInfo();
		if (paymentInfo.isEmpty()) {
			return AddPayment.class;
		}

		addresses = customer.getAddress();
		if (addresses.isEmpty()) {
			return AddAddress.class;
		}

		return null;
	}

}
