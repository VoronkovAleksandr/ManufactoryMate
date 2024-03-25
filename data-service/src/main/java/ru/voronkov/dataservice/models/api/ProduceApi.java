package ru.voronkov.dataservice.models.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("api.produce")
public class ProduceApi {
    private String basicUri;
}


