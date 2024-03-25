package ru.voronkov.WebClient.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Scheduling {
    private Long id;
    private LocalDate schedulingDate;
    private Long productId;
    private Float quantity;

}
