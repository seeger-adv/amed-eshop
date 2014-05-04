package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.util.Set;

import de.adv_boeblingen.seegerj.amed.eshop.annotations.RequiresLogin;
import de.adv_boeblingen.seegerj.amed.eshop.model.payment.PaymentInfo;

@RequiresLogin
public class CheckoutPayment extends AbstractCheckoutSelection<PaymentInfo> {
	@Override
	protected void add(PaymentInfo a) {
		purchase.setPaymentInfo(a);
	}

	@Override
	public Set<PaymentInfo> getItems() {
		return session.getCustomer().getPaymentInfo();
	}
}