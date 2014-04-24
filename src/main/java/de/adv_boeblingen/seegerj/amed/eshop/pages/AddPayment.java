package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.util.Set;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;

import de.adv_boeblingen.seegerj.amed.eshop.annotations.RequiresLogin;
import de.adv_boeblingen.seegerj.amed.eshop.model.Session;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Customer;
import de.adv_boeblingen.seegerj.amed.eshop.model.payment.CreditCard;
import de.adv_boeblingen.seegerj.amed.eshop.model.payment.PaymentInfo;

@RequiresLogin
public class AddPayment {

	@Inject
	private ApplicationStateManager stateManager;

	@Property
	private String cardholdername;

	@Property
	private String cardnumber;

	@Property
	private String cvv;

	@Component
	private Form addCardForm;

	public void onValidateFromAddCardForm() {
		CreditCard payment = new CreditCard();
		payment.setOwner(cardholdername);
		payment.setNumber(cardnumber);
		payment.setCvv(cvv);

		getUserPaymentInfos().add(payment);
	}

	private Set<PaymentInfo> getUserPaymentInfos() {
		Session session = stateManager.get(Session.class);
		Customer customer = session.getCustomer();
		return customer.getPaymentInfo();
	}

	public Object onSuccess() {
		return Checkout.class;
	}
}
