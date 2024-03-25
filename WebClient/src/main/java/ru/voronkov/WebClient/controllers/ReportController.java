package ru.voronkov.WebClient.controllers;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.voronkov.WebClient.models.DateForm;
import ru.voronkov.WebClient.services.ProductService;
import ru.voronkov.WebClient.services.ReportService;

import java.time.LocalDate;


@Controller
@AllArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;
    private final ProductService productService;

    @GetMapping("/{targetDate}")
    public String getReportByDay(@PathVariable LocalDate targetDate, Model model){
        model.addAttribute("text", targetDate);
        model.addAttribute("dateForm", new DateForm(targetDate));
        model.addAttribute("reports",  reportService.getReportsByDate(targetDate));
        model.addAttribute("products", productService.getAllProducts());
        return "report";
    }

    @GetMapping
    public String getReportToday(){
        return "redirect:/report/" + LocalDate.now();
    }

    @PostMapping
    public String redirectByDate(@RequestParam LocalDate date){
        return "redirect:/report/" + date;
    }
}
