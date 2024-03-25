package ru.voronkov.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.voronkov.products.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
