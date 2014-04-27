package de.adv_boeblingen.seegerj.amed.eshop.pages;

import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.ComponentSource;

import de.adv_boeblingen.seegerj.amed.eshop.annotations.RequiresLogin;
import de.adv_boeblingen.seegerj.amed.eshop.api.UserDao;
import de.adv_boeblingen.seegerj.amed.eshop.model.Session;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Customer;
import de.adv_boeblingen.seegerj.amed.eshop.model.payment.PaymentInfo;

@RequiresLogin
public class AddPayment {

	@Inject
	private ApplicationStateManager stateManager;

	@ActivationRequestParameter("next")
	private String nextPage;

	@Inject
	private ComponentSource componentSource;

	@Inject
	private UserDao userDao;

	@Property
	private String cardholdername;

	@Property
	private String cardnumber;

	@Property
	private String cvv;

	@Component
	private Form addCardForm;

	public void onValidateFromAddCardForm() {
		PaymentInfo payment = new PaymentInfo();
		payment.setOwner(cardholdername);
		payment.setNumber(cardnumber);
		payment.setCvv(cvv);

		addPaymentToUser(payment);
	}

	private void addPaymentToUser(PaymentInfo payment) {
		Session session = stateManager.get(Session.class);
		Customer customer = session.getCustomer();
		customer.getPaymentInfo().add(payment);
		userDao.updateUser(customer);
	}

	public Object onSuccess() {
		if (nextPage == null) {
			return Account.class;
		}

		return this.componentSource.getPage(nextPage);
	}
}
