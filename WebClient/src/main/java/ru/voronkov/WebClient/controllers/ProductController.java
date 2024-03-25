package ru.voronkov.WebClient.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.voronkov.WebClient.models.Product;
import ru.voronkov.WebClient.services.ProductService;

@Controller
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("text", "Изделия");
        model.addAttribute("products", productService.getAllProducts());
        return "product-list";
    }

    @PostMapping
    public String addProduct(Product product){
        productService.addProduct(product);
        return "redirect:/product";
    }

    @GetMapping("/add")
    public String addProduct(Product product ,Model model){
        model.addAttribute("text", "Добавить новое изделие");
        return "product-add";
    }

    @GetMapping("/{id}")
    public String updateProduct(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "product-update";
    }

    @PostMapping("/update")
    public String updateProduct(@RequestParam Long id, Product product){
        productService.updateProduct(id, product);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/product";
    }
}
