package ru.voronkov.WebClient.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DateForm {
    private LocalDate date;
}
