package de.adv_boeblingen.seegerj.amed.eshop.pages;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.EventLink;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.PageRenderLinkSource;

import de.adv_boeblingen.seegerj.amed.eshop.annotations.RequiresLogin;
import de.adv_boeblingen.seegerj.amed.eshop.model.Session;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Customer;

@RequiresLogin
public class Checkout {

	@Inject
	private ApplicationStateManager stateManager;

	@Inject
	private PageRenderLinkSource renderLinkSource;

	private Session session;

	@Property
	private Customer customer;

	public Object onActivate() {
		session = stateManager.get(Session.class);

		customer = session.getCustomer();
		if (customer == null) {
			return Login.class;
		}

		Link link = null;
		if (customer.getAddress().isEmpty()) {
			link = this.renderLinkSource.createPageRenderLink(AddAddress.class);
			link.addParameterValue("next", "Checkout");
		}

		if (customer.getPaymentInfo().isEmpty()) {
			link = this.renderLinkSource.createPageRenderLink(AddPayment.class);
			link.addParameterValue("next", "Checkout");
		}

		return link;
	}

}
