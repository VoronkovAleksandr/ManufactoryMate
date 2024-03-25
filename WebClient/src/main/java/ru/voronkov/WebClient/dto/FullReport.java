package ru.voronkov.WebClient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FullReport {
    private LocalDate date;
    private String productTitle;
    private String schedulingQuantity;
    private String produceQuantity;
    private String percent;
}
