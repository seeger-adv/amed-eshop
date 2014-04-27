package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.util.Set;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.PageRenderLinkSource;

import de.adv_boeblingen.seegerj.amed.eshop.annotations.RequiresLogin;
import de.adv_boeblingen.seegerj.amed.eshop.model.Session;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Address;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Customer;
import de.adv_boeblingen.seegerj.amed.eshop.model.payment.PaymentInfo;

@RequiresLogin
public class Checkout {

	@Inject
	private ApplicationStateManager stateManager;

	@Inject
	private PageRenderLinkSource renderLinkSource;

	private Session session;
	private Set<PaymentInfo> paymentInfo;
	private Set<Address> addresses;

	public Object onActivate() {
		session = stateManager.get(Session.class);

		Customer customer = session.getCustomer();
		if (customer == null) {
			return Login.class;
		}

		Link link = null;

		addresses = customer.getAddress();
		if (addresses.isEmpty()) {
			link = this.renderLinkSource.createPageRenderLink(AddAddress.class);
			link.addParameterValue("next", "Checkout");
		}

		paymentInfo = customer.getPaymentInfo();
		if (paymentInfo.isEmpty()) {
			link = this.renderLinkSource.createPageRenderLink(AddPayment.class);
			link.addParameterValue("next", "Checkout");
		}

		return link;
	}

}
