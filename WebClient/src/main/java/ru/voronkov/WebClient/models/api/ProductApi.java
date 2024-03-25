package ru.voronkov.WebClient.models.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("api.product")
public class ProductApi {
    private String basicUri;
}
