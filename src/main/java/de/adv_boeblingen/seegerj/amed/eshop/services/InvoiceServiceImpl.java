package de.adv_boeblingen.seegerj.amed.eshop.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import org.apache.commons.io.IOUtils;
import org.apache.tapestry5.Asset;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import de.adv_boeblingen.seegerj.amed.eshop.api.InvoiceService;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Address;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Item;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Product;
import de.adv_boeblingen.seegerj.amed.eshop.model.database.Purchase;

public class InvoiceServiceImpl implements InvoiceService {
	@Inject
	@Path("context:img/icon.jpg")
	private Asset logo;

	@Override
	public InputStream createInvoice(Purchase purchase) {
		Document document = new Document(PageSize.A4, 50, 50, 100, 50);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			writer.setLinearPageMode();
			writer.setPageEvent(new PageEvents());
			document.addTitle("Invoice #" + purchase.getId());
			document.addCreator("Coffe Time Invoice Generator v1.337");
			document.open();

			assembleInvoice(document, purchase);

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				baos.flush();
			} catch (IOException e) {
			}
		}
		return new ByteArrayInputStream(baos.toByteArray());
	}

	private void assembleInvoice(Document document, Purchase purchase) {
		try {
			Address a = purchase.getAddress();
			Paragraph address = new Paragraph();
			address.setSpacingBefore(15);
			address.add(new Phrase(a.getName()));
			address.add(Chunk.NEWLINE);
			address.add(new Phrase(a.getStreet()));
			address.add(Chunk.NEWLINE);
			address.add(new Phrase(a.getZip() + a.getCity()));
			document.add(address);

			// Head
			createHeadline(document, "Invoice #" + purchase.getId());

			// Table
			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);
			table.setWidths(new int[] { 2, 1, 1, 1 });
			table.addCell("Item");
			table.addCell("Quantity");
			table.addCell("Price");
			table.addCell("Amount");

			float total = 0;
			for (Item i : purchase.getItems()) {
				Product product = i.getProduct();
				table.addCell(product.getSlug());
				table.addCell(product.getPrice() + " €");
				table.addCell(Integer.toString(i.getAmount()));
				float sum = i.getAmount() * product.getPrice();
				total += sum;
				table.addCell(sum + " €");
			}

			float tax = total / 1.16f;
			float sum = total - tax;

			PdfPCell cell = new PdfPCell();
			cell.setColspan(2);
			cell.setBorder(PdfPCell.RIGHT);
			table.addCell(cell);
			table.addCell("Sub Total");
			table.addCell(sum + " €");

			table.addCell(cell);
			table.addCell("Sales Tax");
			table.addCell(tax + " €");

			table.addCell(cell);
			table.addCell("Total");
			table.addCell(total + " €");

			document.add(table);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private void createHeadline(Document document, String headline) throws DocumentException {
		Paragraph h = new Paragraph(headline);
		h.setSpacingBefore(5);
		h.setSpacingAfter(5);
		h.setFont(new Font(FontFamily.HELVETICA, 24.0f, Font.BOLD));
		document.add(h);
	}

	private class PageEvents extends PdfPageEventHelper {
		@Override
		public void onStartPage(PdfWriter writer, Document document) {
			try {
				InputStream stream = InvoiceServiceImpl.this.logo.getResource().openStream();
				Image img = Image.getInstance(IOUtils.toByteArray(stream));
				img.setAbsolutePosition(document.left(), document.top());
				document.add(img);
				document.add(new Paragraph("Coffee Time"));
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onEndPage(PdfWriter writer, Document document) {
			try {
				Paragraph footer = new Paragraph("Steuerid: 123456789");
				footer.add(Chunk.NEWLINE);
				footer.add("Handelskammer Stuttgart");
				footer.setAlignment(Paragraph.ALIGN_BOTTOM);
				document.add(footer);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
	}
}
