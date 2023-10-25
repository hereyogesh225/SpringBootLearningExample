package com.dummy.pdf;

import com.dummy.model.Quote;
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
public class QuotePDFExporter {
    private List<Quote> quotes;
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Sr.No", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Quote", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Author", font));
        table.addCell(cell);
    }
     
    private void writeTableData(PdfPTable table) {
        for (Quote user : quotes) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getQuote());
            table.addCell(user.getAuthor());
        }
    }
     
    private void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Quotes", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.0f, 5.5f, 2.0f});
        table.setSpacingBefore(12);
         
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();
    }

    public void downLoadPdf(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        DateFormat dateFormatter = new SimpleDateFormat(Constants.DATE_PATTERN);
        String currentDateTime = dateFormatter.format(new Date());

        String headerValue = "attachment; filename=quotes_" + currentDateTime + ".pdf";
        response.setHeader(Constants.CONTENT_DISPOSITION, headerValue);

        QuotePDFExporter exporter = new QuotePDFExporter(quotes);
        exporter.export(response);
    }
}