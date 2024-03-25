package ru.voronkov.dataservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.voronkov.dataservice.dto.DataResult;
import ru.voronkov.dataservice.models.Product;
import ru.voronkov.dataservice.services.DataService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class DataController {

    private final DataService service;

    @GetMapping("/{date}")
    public ResponseEntity<List<DataResult>> getDataForDate(@PathVariable LocalDate date){
        return ResponseEntity.ok(service.dataResultList(date));
    }


}
