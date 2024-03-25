package ru.voronkov.dataservice.services;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.voronkov.dataservice.models.Product;
import ru.voronkov.dataservice.models.api.ProductApi;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductApi api;

    public List<Product> getAllProducts() {
        RestTemplate template = new RestTemplate();
        String path = api.getBasicUri();
        ResponseEntity<List<Product>> response = template.exchange(
                path,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {

                });
        System.out.println(response.getBody());
        return response.getBody();
    }

    public Product getProductById(Long id) {
        RestTemplate template = new RestTemplate();
        String path = api.getBasicUri() + "/" + id;
        return template.getForObject(path, Product.class, id);
    }

    public void addProduct(Product product) {
        String path = api.getBasicUri();
        RestTemplate template = new RestTemplate();
        template.postForEntity(path, product, Product.class);
    }

    public void updateProduct(Long id, Product changedProduct) {
        Product productForUpdate = getProductById(id);
        if (productForUpdate != null) {
            productForUpdate.setTitle(changedProduct.getTitle());
            RestTemplate template = new RestTemplate();
            String path = api.getBasicUri() + "/" + id;
            template.put(path, productForUpdate);
        }
    }

    public void deleteProduct(Long id) {
        RestTemplate template = new RestTemplate();
        String path = api.getBasicUri() + "/" + id;
        template.delete(path);
    }

}

