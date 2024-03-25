package ru.voronkov.WebClient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullProduce {
    private Long id;
    private LocalDate date;
    private Long productId;
    private String title;
    private Float quantity;
}
