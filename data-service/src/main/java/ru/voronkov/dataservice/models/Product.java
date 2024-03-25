package ru.voronkov.dataservice.models;

import lombok.*;

@Data
@AllArgsConstructor
public class Product {
    private final Long id;
    private String title;
}