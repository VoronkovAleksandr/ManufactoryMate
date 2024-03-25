package ru.voronkov.dataservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class Produce {
    private final Long id;
    private LocalDate produceDate;
    private Long productId;
    private Float quantity;
}

