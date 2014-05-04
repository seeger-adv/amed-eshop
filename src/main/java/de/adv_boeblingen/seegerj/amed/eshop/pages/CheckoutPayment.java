package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.util.Set;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;

import de.adv_boeblingen.seegerj.amed.eshop.annotations.RequiresLogin;
import de.adv_boeblingen.seegerj.amed.eshop.model.Session;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Purchase;
import de.adv_boeblingen.seegerj.amed.eshop.model.payment.PaymentInfo;

@RequiresLogin
public class CheckoutPayment {
	@Inject
	private ApplicationStateManager stateManager;

	private Session session;

	private Purchase purchase;

	@Property
	private PaymentInfo payment;

	public Set<PaymentInfo> getPaymentInfos() {
		return session.getCustomer().getPaymentInfo();
	}

	public Object onActionFromSelect(long paymentid) {
		for (PaymentInfo p : getPaymentInfos()) {
			if (p.getId() == paymentid) {
				purchase.setPaymentInfo(p);
			}
		}
		return Checkout.class;
	}

	public void onActivate() {
		session = stateManager.get(Session.class);
		purchase = stateManager.get(Purchase.class);
	}
}
