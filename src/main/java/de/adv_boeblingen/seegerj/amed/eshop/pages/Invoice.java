package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.io.IOException;
import java.io.InputStream;

import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Response;

import de.adv_boeblingen.seegerj.amed.eshop.api.InvoiceService;
import de.adv_boeblingen.seegerj.amed.eshop.api.PurchaseDao;
import de.adv_boeblingen.seegerj.amed.eshop.api.RequiresLogin;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Purchase;

@RequiresLogin
public class Invoice {

	@Inject
	private InvoiceService invoiceService;

	@Inject
	private PurchaseDao purchaseDao;

	public StreamResponse onActivate(final int purchaseId) {
		final Purchase purchase = this.purchaseDao.getPurchase(purchaseId);
		return new StreamResponse() {
			@Override
			public String getContentType() {
				return "application/pdf";
			}

			@Override
			public InputStream getStream() throws IOException {
				return Invoice.this.invoiceService.createInvoice(purchase);
			}

			@Override
			public void prepareResponse(Response response) {
				response.setHeader("Content-disposition", "attachment;filename=Invoice.pdf");
			}
		};
	}
}
