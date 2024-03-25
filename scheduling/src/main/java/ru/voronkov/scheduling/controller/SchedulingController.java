package ru.voronkov.scheduling.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.voronkov.scheduling.models.Scheduling;
import ru.voronkov.scheduling.services.SchedulingService;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SchedulingController {

    private final SchedulingService service;

    @GetMapping
    public ResponseEntity<List<Scheduling>> getAllProduct() {
        return ResponseEntity.ok(service.getAllScheduling());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Scheduling> getSchedulingById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSchedulingById(id));
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody Scheduling scheduling) {
        service.addScheduling(scheduling);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody Scheduling changedScheduling) {
        Scheduling scheduling = getSchedulingById(id).getBody();
        if (scheduling != null) {


            scheduling.setSchedulingDate(changedScheduling.getSchedulingDate());
            scheduling.setProductId(changedScheduling.getProductId());
            scheduling.setQuantity(changedScheduling.getQuantity());
            service.updateScheduling(scheduling);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduling(@PathVariable Long id) {
        service.deleteScheduling(id);
        return ResponseEntity.ok().build();
    }


}
