package ru.voronkov.dataservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DataResult {
    private LocalDate date;
    private Long productId;
    private Float schedulingQuantity;
    private Float produceQuantity;
}
