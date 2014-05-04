package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.util.Map;
import java.util.Set;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.PageRenderLinkSource;

import de.adv_boeblingen.seegerj.amed.eshop.api.PurchaseDao;
import de.adv_boeblingen.seegerj.amed.eshop.api.RequiresLogin;
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

	@Property
	private Purchase purchase;

	private float sum;

	@Property
	private Item item;

	@Inject
	private StockService stockService;

	@Inject
	private PurchaseDao purchaseDao;

	@Inject
	private ApplicationStateManager stateManager;

	@Inject
	private PageRenderLinkSource renderLinkSource;

	private Session session;

	@Property
	private Customer customer;

	private boolean checkAvailability() {
		boolean allProductInStock = true;

		for (Item item : purchase.getItems()) {
			allProductInStock &= stockService.hasEnoughItems(item.getProduct(), item.getAmount());
		}
		return allProductInStock;
	}

	private Purchase createPurchase() {
		Purchase purchase = stateManager.get(Purchase.class);
		purchase.setCustomer(customer);

		Set<Item> items = purchase.getItems();
		items.clear();

		Map<Product, Integer> cartItems = shoppingCart.getItems();
		for (Product product : cartItems.keySet()) {
			int amount = cartItems.get(product);
			Item item = new Item();
			item.setAmount(amount);
			item.setProduct(product);
			item.setPurchase(purchase);
			items.add(item);
		}
		return purchase;
	}

	public float getSum() {
		return sum;
	}

	public double getTax() {
		return getSum() - getWoTax();
	}

	public double getTotal() {
		float total = item.getAmount() * item.getProduct().getPrice();
		sum += total;
		return total;
	}

	public double getWoTax() {
		return getSum() / 1.16;
	}

	private boolean isValidAddress() {
		return purchase.getAddress() != null;
	}

	private boolean isValidPayment() {
		return purchase.getPaymentInfo() != null;
	}

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
			return CheckoutAddress.class;
		}

		return null;
	}

	public Object onSend() {
		if (checkAvailability() && isValidAddress() && isValidPayment()) {
			purchaseDao.addPurchase(purchase);
			purchase = null;
			shoppingCart.clearAll();
			return Receipt.class;
		}

		return null;
	}
}
