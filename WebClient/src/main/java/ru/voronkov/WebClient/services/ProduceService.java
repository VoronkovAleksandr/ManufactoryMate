package ru.voronkov.WebClient.services;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.voronkov.WebClient.dto.FullProduce;
import ru.voronkov.WebClient.models.Produce;
import ru.voronkov.WebClient.models.api.ProduceApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProduceService {

    private final ProduceApi api;
    private final ProductService productService;

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
        if (produceForUpdate != null){
            RestTemplate template = new RestTemplate();
            String path = api.getBasicUri() + "/" + id;
            produceForUpdate.setProduceDate(produce.getProduceDate());
            produceForUpdate.setProductId(produce.getProductId());
            produceForUpdate.setQuantity(produce.getQuantity());
            template.put(path, produceForUpdate);
        }
    }

    public void deleteProduce(Long id){
        RestTemplate template = new RestTemplate();
        String path = api.getBasicUri() + "/" + id;
        template.delete(path);
    }

    public List<Produce> getProducesByDate(LocalDate date){
        return getAllProduces().stream().filter(p -> p.getProduceDate().equals(date)).toList();
    };

    public List<FullProduce> getFullProduceList(List<Produce> produceList){
        List<FullProduce> fullProduceList = new ArrayList<>();
        for (Produce produce:
             produceList) {
                fullProduceList.add(getFullProduceDto(produce));
        }
        return fullProduceList;
    }

    public FullProduce getFullProduceDto(Produce produce){
        return new FullProduce(
                produce.getId(),
                produce.getProduceDate(),
                produce.getProductId(),
                productService.getProductById(produce.getProductId()).getTitle(),
                produce.getQuantity()
        );
    }

    public Produce getProduceDto(FullProduce fullProduce){
        return new Produce(
                fullProduce.getId(),
                fullProduce.getDate(),
                fullProduce.getProductId(),
                fullProduce.getQuantity()
        );
    }



}
