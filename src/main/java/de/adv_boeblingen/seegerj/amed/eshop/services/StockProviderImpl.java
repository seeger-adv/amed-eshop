package de.adv_boeblingen.seegerj.amed.eshop.services;

import org.apache.tapestry5.ioc.annotations.Inject;

import de.adv_boeblingen.seegerj.amed.eshop.api.ProductDao;
import de.adv_boeblingen.seegerj.amed.eshop.api.StockService;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;
import de.adv_boeblingen.seegerj.amed.eshop.model.enums.Availability;

public class StockProviderImpl
		implements StockService {

	@Inject
	ProductDao productDao;

	@Override
	public Availability getAvailability(Product product) {
		int amount = product.getItemsLeft();
		if (amount == 0) {
			return Availability.OUT_OF_STOCK;
		}

		if (amount < 5) {
			return Availability.FEW;
		}

		if (amount >= 5) {
			return Availability.AVAILABLE;
		}

		return Availability.UNKNOWN;
	}

	@Override
	public void decreaseItemCount(Product product, int decreaseBy) {
		if (decreaseBy < 0) {
			throw new IllegalArgumentException();
		}

		int oldAmount = product.getItemsLeft();
		int newAmount = oldAmount - decreaseBy;

		if (newAmount < 0) {
			throw new IllegalArgumentException();
		}

		product.setItemsLeft(newAmount);
		productDao.updateProduct(product);
	}

	@Override
	public void increaseItemCount(Product product, int increaseBy) {
		if (increaseBy < 0) {
			throw new IllegalArgumentException();
		}

		int oldAmount = product.getItemsLeft();
		int newAmount = oldAmount + increaseBy;
		product.setItemsLeft(newAmount);
		productDao.updateProduct(product);
	}

	@Override
	public boolean hasEnoughItems(Product product, int amount) {
		return product.getItemsLeft() >= amount;
	}
}
