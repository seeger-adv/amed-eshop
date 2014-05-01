package de.adv_boeblingen.seegerj.amed.eshop.pages;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Response;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import de.adv_boeblingen.seegerj.amed.eshop.annotations.RequiresLogin;
import de.adv_boeblingen.seegerj.amed.eshop.api.PurchaseDao;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Purchase;

@RequiresLogin
public class Invoice {

	@Inject
	private PurchaseDao purchaseDao;

	private Purchase purchase;

	public StreamResponse onAction(int purchaseId) {
		purchase = purchaseDao.getPurchase(purchaseId);

		return new StreamResponse() {
			@Override
			public String getContentType() {
				return "application/pdf";
			}

			@Override
			public InputStream getStream() throws IOException {
				return createInvoice();
			}

			@Override
			public void prepareResponse(Response response) {
				response.setHeader("Content-disposition", "attachment;filename=Invoice.pdf");
			}
		};
	}

	public InputStream createInvoice() {
		Document document = new Document(PageSize.A4);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, baos);
			document.open();


			return new ByteArrayInputStream(baos.toByteArray());
		} catch (Exception e) {
		} finally {
			IOUtils.closeQuietly(baos);
		}
		return null;
	}
}
