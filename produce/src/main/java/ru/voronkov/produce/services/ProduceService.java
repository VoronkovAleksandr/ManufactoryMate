package ru.voronkov.produce.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.voronkov.produce.models.Produce;
import ru.voronkov.produce.repositories.ProduceRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProduceService {

    private final ProduceRepository repository;

    public void addProduce(Produce produce) {
        repository.save(produce);
    }

    public List<Produce> produceList() {
        return repository.findAll();
    }

    public Produce getProduceById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void updateProduce(Produce produce) {
        repository.save(produce);
    }

    public void deleteProduce(Long id){
        repository.deleteById(id);
    }


    public List<Produce> getAllProduce() {
        return repository.findAll();
    }
}
