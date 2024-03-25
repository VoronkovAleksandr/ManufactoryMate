package ru.voronkov.dataservice.models.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("api.scheduling")
public class SchedulingApi {
    private String basicUri;
}
