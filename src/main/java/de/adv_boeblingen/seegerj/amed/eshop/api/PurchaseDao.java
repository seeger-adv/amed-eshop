package de.adv_boeblingen.seegerj.amed.eshop.api;

import de.adv_boeblingen.seegerj.amed.eshop.model.database.Purchase;

public interface PurchaseDao {
	public Purchase getPurchase(long purchaseId);
}
