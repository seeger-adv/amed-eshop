package de.adv_boeblingen.seegerj.amed.eshop.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import de.adv_boeblingen.seegerj.amed.eshop.api.PurchaseDao;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Customer;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Item;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Purchase;
import de.adv_boeblingen.seegerj.amed.eshop.provider.DatabaseProvider;
import de.adv_boeblingen.seegerj.amed.eshop.provider.DatabaseProvider.DatabaseRunnable;

public class PurchaseDaoImpl
		implements PurchaseDao {

	@Override
	public Purchase getPurchase(final long purchaseId) {
		return DatabaseProvider.runQuery(new DatabaseRunnable<Purchase>() {
			@Override
			public Purchase run(EntityManager manager, EntityTransaction transaction) {
				return manager.find(Purchase.class, purchaseId);
			}
		});
	}

	@Override
	public void addPurchase(final Purchase purchase) {
		DatabaseProvider.runTransaction(new DatabaseRunnable<Void>() {
			@Override
			public Void run(EntityManager manager, EntityTransaction transaction) {
				for (Item item : purchase.getItems()) {
					manager.persist(item);
				}
				manager.persist(purchase);
				Customer customer = purchase.getCustomer();
				customer.getPurchase().add(purchase);
				manager.merge(customer);
				return null;
			}
		});
	}
}
