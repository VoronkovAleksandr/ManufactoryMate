package ru.voronkov.WebClient.services;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.voronkov.WebClient.dto.FullReport;
import ru.voronkov.WebClient.models.Report;
import ru.voronkov.WebClient.models.api.ReportApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
@AllArgsConstructor
public class ReportService {

    private final ReportApi api;
    private final ProductService productService;


    public List<FullReport> getReportsByDate(LocalDate date) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Report>> response = template.exchange(
                api.getBasicUri() + "/" + date,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Report>>() {
                });
        return fillReport(response.getBody());
    }


    private List<FullReport> fillReport(List<Report> reportList){
        List<FullReport> fullReports = new ArrayList<>();
        String productTitle;
        Float schedulingQuantity;
        Float produceQuantity;
        String percent;
        for (Report report : reportList) {
            productTitle = productService.getProductById(report.getProductId()).getTitle();
            schedulingQuantity = report.getSchedulingQuantity();
            produceQuantity = report.getProduceQuantity();
            if (!schedulingQuantity.equals(0f)) {
                percent = String.format("%.2f", report.getProduceQuantity() / report.getSchedulingQuantity() * 100);
            } else {
                percent = "Произведено вне плана";
            }
            fullReports.add(
                    new FullReport(
                            report.getDate(),
                            productTitle,
                            String.format("%.2f", schedulingQuantity),
                            String.format("%.2f", produceQuantity),
                            percent
                    )
            );
        }
        return fullReports;
    }


}
