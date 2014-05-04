package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.util.Map;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.EventLink;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.PageRenderLinkSource;

import de.adv_boeblingen.seegerj.amed.eshop.annotations.RequiresLogin;
import de.adv_boeblingen.seegerj.amed.eshop.api.StockService;
import de.adv_boeblingen.seegerj.amed.eshop.model.Cart;
import de.adv_boeblingen.seegerj.amed.eshop.model.Session;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Customer;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Item;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Purchase;

@RequiresLogin
public class Checkout {

	@Property
	@SessionState
	private Cart shoppingCart;

	private Purchase purchase;

	@Inject
	private StockService stockService;

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
		purchase = createPurchase();

		if (customer.getPaymentInfo().isEmpty()) {
			Link link = this.renderLinkSource.createPageRenderLink(AddPayment.class);
			link.addParameterValue("next", "Checkout");
			return link;
		}

		if (purchase.getPaymentInfo() == null) {
			return CheckoutPayment.class;
		}

		if (customer.getAddress().isEmpty()) {
			Link link = this.renderLinkSource.createPageRenderLink(AddAddress.class);
			link.addParameterValue("next", "Checkout");
			return link;
		}

		if (purchase.getAddress() == null) {
			// return CheckoutAddress.class;
		}

		return null;
	}

	private Purchase createPurchase() {
		boolean existingPurchase = stateManager.exists(Purchase.class);
		Purchase purchase = stateManager.get(Purchase.class);

		if (!existingPurchase) {
			Map<Product, Integer> cartItems = shoppingCart.getItems();
			for (Product product : cartItems.keySet()) {
				int amount = cartItems.get(product);
				Item item = new Item();
				item.setAmount(amount);
				item.setProduct(product);
				item.setPurchase(purchase);
			}
			purchase.setCustomer(customer);
		}
		return purchase;
	}

	@Component(parameters = { "event=send" })
	private EventLink send;

	public Object onSend() {
		boolean allProductInStock = true;

		Map<Product, Integer> cartItems = shoppingCart.getItems();
		for (Product product : cartItems.keySet()) {
			int amount = cartItems.get(product);
			allProductInStock &= stockService.hasEnoughItems(product, amount);
		}

		if (allProductInStock) {
			return Receipt.class;
		}

		// some items are not available!
		return null;
	}
}
