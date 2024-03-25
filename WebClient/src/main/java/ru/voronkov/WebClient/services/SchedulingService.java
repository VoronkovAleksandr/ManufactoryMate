package ru.voronkov.WebClient.services;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.voronkov.WebClient.dto.FullProduce;
import ru.voronkov.WebClient.dto.FullScheduling;
import ru.voronkov.WebClient.models.Scheduling;
import ru.voronkov.WebClient.models.api.SchedulingApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SchedulingService {

    private final SchedulingApi api;
    private final ProductService productService;

    public List<Scheduling> getAllShedulings(){
        RestTemplate template = new RestTemplate();
        String path = api.getBasicUri();
        ResponseEntity<List<Scheduling>> response = template.exchange(
                path,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Scheduling>>() {
                });
        return response.getBody();
    }

    public Scheduling getSchedulingById(Long id){
        RestTemplate template = new RestTemplate();
        String path = api.getBasicUri() + "/" + id;
        return template.getForObject(path, Scheduling.class, id);
    }

    public void addScheduling(Scheduling scheduling){
        RestTemplate template = new RestTemplate();
        String path = api.getBasicUri();
        template.postForEntity(path, scheduling, Scheduling.class);
    }

    public void updateScheduling(Long id, Scheduling changedScheduling){
        Scheduling schedulingForUpdate = getSchedulingById(id);
        if (schedulingForUpdate !=null){
            RestTemplate template = new RestTemplate();
            String path = api.getBasicUri() + "/" + id;
            schedulingForUpdate.setSchedulingDate(changedScheduling.getSchedulingDate());
            schedulingForUpdate.setProductId(changedScheduling.getProductId());
            schedulingForUpdate.setQuantity(changedScheduling.getQuantity());
            template.put(path,schedulingForUpdate);
        }
    }

    public void deleteScheduling(Long id){
        RestTemplate template = new RestTemplate();
        String path = api.getBasicUri() + "/" + id;
        template.delete(path);
    }

    public List<Scheduling> getSchedulingByDate(LocalDate date){
        return getAllShedulings().stream().filter(s-> s.getSchedulingDate().equals(date)).toList();
    }

    public List<FullScheduling> getFullSchedulingList(List<Scheduling> schedulingList) {
        List<FullScheduling> fullSchedulingList = new ArrayList<>();
        for (Scheduling scheduling:
             schedulingList) {
            fullSchedulingList.add(getFullSchedulingDto(scheduling));
        }
        return fullSchedulingList;
    }

    public FullScheduling getFullSchedulingDto(Scheduling scheduling){
        return new FullScheduling(
                scheduling.getId(),
                scheduling.getSchedulingDate(),
                scheduling.getProductId(),
                productService.getProductById(scheduling.getProductId()).getTitle(),
                scheduling.getQuantity()
        );
    }

    public Scheduling getSchedulingDto(FullScheduling fullScheduling){
        return new Scheduling(
                fullScheduling.getId(),
                fullScheduling.getDate(),
                fullScheduling.getProductId(),
                fullScheduling.getQuantity()
        );
    }


}
