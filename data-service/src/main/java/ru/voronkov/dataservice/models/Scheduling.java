package ru.voronkov.dataservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
public class Scheduling {

    private final Long id;
    private LocalDate schedulingDate;
    private Long productId;
    private Float quantity;
}
