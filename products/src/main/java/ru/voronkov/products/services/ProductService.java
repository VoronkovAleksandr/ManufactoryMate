package ru.voronkov.products.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.voronkov.products.models.Product;
import ru.voronkov.products.repositories.ProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    public void addProduct(Product product){
        repository.save(product);
    }

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public Product getProductById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void updateProduct(Product product) {
            repository.save(product);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}
