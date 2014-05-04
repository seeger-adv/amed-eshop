package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.util.Set;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import de.adv_boeblingen.seegerj.amed.eshop.api.StockService;
import de.adv_boeblingen.seegerj.amed.eshop.model.Cart;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;
import de.adv_boeblingen.seegerj.amed.eshop.model.enums.Availability;

public class ShoppingCart {
	@Property
	@SessionState
	private Cart shoppingCart;

	@Inject
	private StockService stockService;

	@Property
	private Product item;

	public int getAmount() {
		return getAmount(this.item);
	}

	private int getAmount(Product product) {
		return this.shoppingCart.getItems().get(product);
	}

	public Availability getAvailability() {
		int amount = getAmount();
		if (!stockService.hasEnoughItems(item, amount)) {
			return Availability.OUT_OF_STOCK;

		}
		return null;
	}

	public String getAvailabilityBadge() {
		Availability availability = getAvailability();
		if (availability != null) {
			return availability.getBadge();
		}

		return "";
	}

	private Product getItem(String columnId) {
		int itemId = Integer.parseInt(columnId);
		for (Product item : getItems()) {
			if (itemId == item.getId()) {
				return item;
			}
		}
		return null;
	}

	public Set<Product> getItems() {
		return this.shoppingCart.getItems().keySet();
	}

	public float getTotal() {
		return getAmount() * this.item.getPrice();
	}

	public void onAdd(String columnId) {
		Product item = getItem(columnId);
		shoppingCart.add(item);
	}

	public Object onCheckout() {
		Object redirectTo = Checkout.class;
		for (Product product : getItems()) {
			if (product.getItemsLeft() < getAmount(product)) {
				redirectTo = null;
			}
		}
		return redirectTo;
	}

	public void onDel(String columnId) {
		Product item = getItem(columnId);
		shoppingCart.clear(item);
	}

	public void onSub(String columnId) {
		Product item = getItem(columnId);
		shoppingCart.remove(item);
	}
}
