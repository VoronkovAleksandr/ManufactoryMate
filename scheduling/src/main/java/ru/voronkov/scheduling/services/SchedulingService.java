package ru.voronkov.scheduling.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.voronkov.scheduling.models.Scheduling;
import ru.voronkov.scheduling.repositories.SchedulingRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SchedulingService {

    private final SchedulingRepository repository;

    public void addScheduling(Scheduling scheduling){
        repository.save(scheduling);
    }

    public List<Scheduling> getAllScheduling(){
        return repository.findAll();
    }

    public Scheduling getSchedulingById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void updateScheduling(Scheduling scheduling) {
        repository.save(scheduling);
    }

    public void deleteScheduling(Long id) {
        repository.deleteById(id);
    }


}
