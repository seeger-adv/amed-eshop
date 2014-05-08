package de.adv_boeblingen.seegerj.amed.eshop.api;

import java.io.InputStream;

import de.adv_boeblingen.seegerj.amed.eshop.model.database.Purchase;

public interface InvoiceService {
	InputStream createInvoice(Purchase purchase);
}