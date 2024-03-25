package ru.voronkov.dataservice.services;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.voronkov.dataservice.models.Produce;
import ru.voronkov.dataservice.models.api.ProduceApi;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ProduceService {

    private final ProduceApi api;

    public List<Produce> getAllProduces() {
        RestTemplate template = new RestTemplate();
        String path = api.getBasicUri();
        ResponseEntity<List<Produce>> response = template.exchange(
                path,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Produce>>() {
                });
        return response.getBody();
    }

    public Produce getProduceById(Long id) {
        RestTemplate template = new RestTemplate();
        String path = api.getBasicUri() + "/" + id;
        return template.getForObject(path, Produce.class, id);
    }

    public void addProduce(Produce produce) {
        RestTemplate template = new RestTemplate();
        String path = api.getBasicUri();
        template.postForEntity(path, produce, Produce.class);
    }

    public void updateProduce(Long id, Produce produce) {
        Produce produceForUpdate = getProduceById(id);
        if (produceForUpdate != null) {
            RestTemplate template = new RestTemplate();
            String path = api.getBasicUri();
            produceForUpdate.setProduceDate(produce.getProduceDate());
            produceForUpdate.setProductId(produce.getProductId());
            produceForUpdate.setQuantity(produce.getQuantity());
            template.put(path, produceForUpdate);
        }
    }

    public void deleteScheduling(Long id) {
        RestTemplate template = new RestTemplate();
        String path = api.getBasicUri() + "/" + id;
        template.delete(path);
    }

    public List<Produce> getProducesByDate(LocalDate date) {
        return getAllProduces().stream()
                .filter(p -> p.getProduceDate().equals(date))
                .toList();
    }

    ;


}
