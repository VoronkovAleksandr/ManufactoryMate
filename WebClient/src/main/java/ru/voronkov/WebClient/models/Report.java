package ru.voronkov.WebClient.models;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Report {
    private Long id;
    private LocalDate date;
    private Long productId;
    private Float schedulingQuantity;
    private Float produceQuantity;
}
