package ru.voronkov.WebClient.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.voronkov.WebClient.dto.FullProduce;
import ru.voronkov.WebClient.services.ProduceService;
import ru.voronkov.WebClient.services.ProductService;

@Controller
@AllArgsConstructor
@RequestMapping("/produce")
public class ProduceController {

    private final ProduceService produceService;
    private final ProductService productService;

    @GetMapping
    public String getAllProduces(Model model) {
        model.addAttribute("text", "Выпуск изделий");
        model.addAttribute("items", produceService.getFullProduceList(produceService.getAllProduces()));
        model.addAttribute("href", "produce");
        return "prod-sched-list";
    }

    @GetMapping("/add")
    public String addProduce(Model model){
        model.addAttribute("text", "Добавить выпуск изделия");
        model.addAttribute("item", new FullProduce());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("href", "produce");
        return "prod-sched-add";
    }

    @PostMapping
    public String addProduct(FullProduce fullProduce){
        produceService.addProduce(produceService.getProduceDto(fullProduce));
        return "redirect:/produce";
    }


    @GetMapping("/{id}")
    public String updateProduce(@PathVariable Long id, Model model){
        model.addAttribute("item", produceService.getFullProduceDto(produceService.getProduceById(id)));
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("href", "produce");
        return "prod-sched-update";
    }

    @PostMapping("/update")
    public String updateProduct(@RequestParam Long id, FullProduce fullProduce){
        produceService.updateProduce(id, produceService.getProduceDto(fullProduce));
        return "redirect:/produce";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduce(@PathVariable Long id){
        produceService.deleteProduce(id);
        return "redirect:/produce";
    }
}
