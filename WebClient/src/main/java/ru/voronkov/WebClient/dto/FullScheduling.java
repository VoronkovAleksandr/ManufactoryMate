package ru.voronkov.WebClient.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullScheduling {
    private Long id;
    private LocalDate date;
    private Long productId;
    private String title;
    private Float quantity;
}
