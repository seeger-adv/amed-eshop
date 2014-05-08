package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.text.DecimalFormat;
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
import de.adv_boeblingen.seegerj.amed.eshop.model.enums.PaymentState;
import de.adv_boeblingen.seegerj.amed.eshop.model.enums.ShippingState;

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

	public DecimalFormat getNumberFormat() {
		return new DecimalFormat("0.00");
	}

	private boolean checkAvailability() {
		boolean allProductInStock = true;

		for (Item item : this.purchase.getItems()) {
			allProductInStock &= this.stockService.hasEnoughItems(item.getProduct(), item.getAmount());
		}
		return allProductInStock;
	}

	private Purchase createPurchase() {
		Purchase purchase = this.stateManager.get(Purchase.class);
		purchase.setCustomer(this.customer);
		purchase.setPaymentState(PaymentState.UNPAID);
		purchase.setShippingState(ShippingState.RECEIVED);

		Set<Item> items = purchase.getItems();
		items.clear();

		Map<Product, Integer> cartItems = this.shoppingCart.getItems();
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
		return this.sum;
	}

	public double getTax() {
		return getSum() - getSubtotal();
	}

	public double getTotal() {
		float total = this.item.getAmount() * this.item.getProduct().getPrice();
		this.sum += total;
		return total;
	}

	public double getSubtotal() {
		return getSum() / 1.16;
	}

	private boolean isValidAddress() {
		return this.purchase.getAddress() != null;
	}

	private boolean isValidPayment() {
		return this.purchase.getPaymentInfo() != null;
	}

	public Object onActivate() {
		this.session = this.stateManager.get(Session.class);
		this.customer = this.session.getCustomer();
		this.purchase = createPurchase();

		if (this.customer.getPaymentInfo().isEmpty()) {
			Link link = this.renderLinkSource.createPageRenderLink(AddPayment.class);
			link.addParameterValue("next", "Checkout");
			return link;
		}

		if (this.purchase.getPaymentInfo() == null) {
			return CheckoutPayment.class;
		}

		if (this.customer.getAddress().isEmpty()) {
			Link link = this.renderLinkSource.createPageRenderLink(AddAddress.class);
			link.addParameterValue("next", "Checkout");
			return link;
		}

		if (this.purchase.getAddress() == null) {
			return CheckoutAddress.class;
		}

		return null;
	}

	public Object onSend() {
		if (checkAvailability() && isValidAddress() && isValidPayment()) {
			reduceStock();
			resetState();
			return this.renderLinkSource.createPageRenderLinkWithContext(Invoice.class, this.purchase.getId());
		}

		return null;
	}

	private void reduceStock() {
		for (Item item : this.purchase.getItems()) {
			this.stockService.decreaseItemCount(item.getProduct(), item.getAmount());
		}
	}

	private void resetState() {
		this.purchaseDao.addPurchase(this.purchase);
		this.stateManager.set(Purchase.class, null);
		this.shoppingCart.clearAll();
	}
}
