package ru.voronkov.WebClient.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Produce {
    private Long id;
    private LocalDate produceDate;
    private Long productId;
    private Float quantity;
}
