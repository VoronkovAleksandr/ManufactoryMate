package ru.voronkov.produce.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.voronkov.produce.models.Produce;
import ru.voronkov.produce.services.ProduceService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProduceController {

    private final ProduceService service;

    @GetMapping
    public ResponseEntity<List<Produce>> getAllProduce() {
        return ResponseEntity.ok(service.getAllProduce());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produce> getProduceById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProduceById(id));
    }

    @PostMapping
    public ResponseEntity<Void> addProduce(@RequestBody Produce produce) {
        service.addProduce(produce);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduce(@PathVariable Long id, @RequestBody Produce changedProduce) {
        Produce produce = getProduceById(id).getBody();
        if (produce != null){
            produce.setProduceDate(changedProduce.getProduceDate());
            produce.setProductId(changedProduce.getProductId());
            produce.setQuantity(changedProduce.getQuantity());
            service.updateProduce(produce);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduce(@PathVariable Long id) {
        service.deleteProduce(id);
        return ResponseEntity.ok().build();
    }

}
