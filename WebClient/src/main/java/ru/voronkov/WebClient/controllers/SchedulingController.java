package ru.voronkov.WebClient.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.voronkov.WebClient.dto.FullScheduling;
import ru.voronkov.WebClient.services.ProductService;
import ru.voronkov.WebClient.services.SchedulingService;

@Controller
@AllArgsConstructor
@RequestMapping("/scheduling")
public class SchedulingController {

    private final SchedulingService schedulingService;
    private final ProductService productService;

    @GetMapping
    public String getAllScheduling(Model model) {
        model.addAttribute("text", "Планирование выпуска изделий");
        model.addAttribute("items", schedulingService.getFullSchedulingList(schedulingService.getAllShedulings()));
        model.addAttribute("href", "scheduling");
        return "prod-sched-list";
    }

    @GetMapping("/add")
    public String addAScheduling(Model model) {
        model.addAttribute("text", "Добавить выпуск изделия в план");
        model.addAttribute("item", new FullScheduling());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("href", "scheduling");
        return "prod-sched-add";
    }

    @PostMapping
    public String addScheduling(FullScheduling fullScheduling){
        schedulingService.addScheduling(schedulingService.getSchedulingDto(fullScheduling));
        return "redirect:/scheduling";
    }

    @GetMapping("/{id}")
    public String updateScheduling(@PathVariable Long id, Model model){
        model.addAttribute("item", schedulingService.getFullSchedulingDto(schedulingService.getSchedulingById(id)));
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("href", "scheduling");
        return "prod-sched-update";
    }
    @PostMapping("/update")
    public String updateScheduling(@RequestParam Long id, FullScheduling fullScheduling){
        schedulingService.updateScheduling(id, schedulingService.getSchedulingDto(fullScheduling));
        return "redirect:/scheduling";
    }

    @GetMapping("/delete/{id}")
    public String deleteScheduling(@PathVariable Long id){
        schedulingService.deleteScheduling(id);
        return "redirect:/scheduling";
    }




}
