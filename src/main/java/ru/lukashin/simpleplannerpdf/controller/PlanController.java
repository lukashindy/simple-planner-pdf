package ru.lukashin.simpleplannerpdf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lukashin.simpleplannerpdf.dto.PlanDTO;
import ru.lukashin.simpleplannerpdf.service.PdfPlanService;
import ru.lukashin.simpleplannerpdf.service.PlanService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/plan")
public class PlanController {

    private final PlanService planService;
    private final PdfPlanService pdfPlanService;

    public PlanController(PlanService planService, PdfPlanService pdfPlanService) {
        this.planService = planService;
        this.pdfPlanService = pdfPlanService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("plans", planService.findAll());
        return "plans";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id,  Model model) {
        model.addAttribute("plan", planService.findById(id));
        return "plan";
    }

    @GetMapping("/new")
    public String formNewPlan(Model model) {
        model.addAttribute("plan", new PlanDTO());
        return "form";
    }

    @GetMapping("/{id}/update")
    public String formUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("plan", planService.findById(id));
        return "form";
    }

    @PostMapping
    public String savePlan(@ModelAttribute PlanDTO source) {
        PlanDTO savedPlan = planService.save(source);
        return "redirect:/plan/" + savedPlan.getId();
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        planService.deleteById(id);
        return "redirect:/plan";
    }

    @GetMapping("/pdf")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=plans.pdf";
        response.setHeader(headerKey, headerValue);
        pdfPlanService.exportPdfFile(response);
    }
}
