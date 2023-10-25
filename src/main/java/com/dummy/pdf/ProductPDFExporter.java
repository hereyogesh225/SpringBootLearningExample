package com.dummy.pdf;

import com.dummy.model.Product;
import com.dummy.utils.Constants;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;

@AllArgsConstructor
public class ProductPDFExporter {

    private List<Product> products;

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Sr.No", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Title", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Category", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Product Brand", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Product user : products) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getTitle());
            table.addCell(user.getDescription());
            table.addCell(String.valueOf(user.getPrice()));
            table.addCell(user.getCategory());
            table.addCell(user.getBrand());
        }
    }

    private void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Products", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.5f, 1.5f, 3.0f, 3.0f});
        table.setSpacingBefore(12);

        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();
    }

    public void downloadPDF(HttpServletResponse response) throws Exception {
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        DateFormat dateFormatter = new SimpleDateFormat(Constants.DATE_PATTERN);
        String currentDateTime = dateFormatter.format(new Date());
        String headerValue = "attachment; filename=products_" + currentDateTime + ".pdf";
        response.setHeader(Constants.CONTENT_DISPOSITION, headerValue);

        ProductPDFExporter exporter = new ProductPDFExporter(this.products);
        exporter.export(response);
    }
}