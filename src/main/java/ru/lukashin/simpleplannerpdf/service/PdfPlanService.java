package ru.lukashin.simpleplannerpdf.service;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import ru.lukashin.simpleplannerpdf.dto.PlanDTO;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

@Service
public class PdfPlanService {

    private final PlanService planService;

    public PdfPlanService(PlanService planService) {
        this.planService = planService;
    }

    public void exportPdfFile(HttpServletResponse response) throws IOException {

        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Plan List", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        PdfPTable table = new PdfPTable(12);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1f, 10f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTablePlan(table);

        document.add(table);
        document.close();

    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(2);

        Font font = FontFactory.getFont(FontFactory.COURIER);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("№", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("To Do", font));
        table.addCell(cell);

        int k = 10;
        for (int i = 0; i < 10; i++) {
            cell.setPhrase(new Phrase(k + "%", font));
            table.addCell(cell);
            k += 10;
        }
    }

    private void writeTablePlan(PdfPTable table) {
        for (PlanDTO plan : planService.findAll()) {
            table.addCell(String.valueOf(plan.getId()));
            table.addCell(plan.getName());
            for (int i = 0; i < 10; i++)
                table.addCell("");
        }
    }
}
