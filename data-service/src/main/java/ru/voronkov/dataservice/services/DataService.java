package ru.voronkov.dataservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.voronkov.dataservice.dto.DataResult;
import ru.voronkov.dataservice.models.Produce;
import ru.voronkov.dataservice.models.Product;
import ru.voronkov.dataservice.models.Scheduling;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class DataService {

    private final ProductService productService;
    private final ProduceService produceService;
    private final SchedulingService schedulingService;


    public List<DataResult> dataResultList(LocalDate date) {
        Map<Product,DataResult> dataResult = new HashMap<>();

        // Получаем список всех продуктов
        List<Product> productList = productService.getAllProducts();
        // Получаем список продуктов выпущенных за определенный день
        List<Produce> produceList = produceService.getProducesByDate(date);
        // Получаем список продуктов запланированных на определенный день
        List<Scheduling> schedulingList = schedulingService.getSchedulingByDate(date);

        // Проходим по списку продуктов
        for (Product product :
                productList) {

            // Если продукт с таким id есть в списке выпущенных продуктов, то добавляем его в MAP
            if (produceList.stream().anyMatch(p -> p.getProductId().equals(product.getId()))) {
                dataResult.put(
                        product,
                        new DataResult(
                                date,
                                product.getId(),
                                0.0f,
                                produceList.stream()
                                        .filter(p -> p.getProductId().equals(product.getId()))
                                        .findFirst()
                                        .get()
                                        .getQuantity()
                        )
                );
            }
            // Если продукт с таким id есть в списке запланированных к выпуску продуктов, то работаем с ним
            if (schedulingList.stream().anyMatch(p -> p.getProductId().equals(product.getId()))) {

                //Проверяем на наличие в MAP такого продукта, если есть - обновляем, если нет - добавляем
                if (dataResult.keySet().contains(product))  {
                    dataResult.get(product).setSchedulingQuantity(
                            schedulingList.stream()
                                    .filter(p -> p.getProductId().equals(product.getId()))
                                    .findFirst()
                                    .get()
                                    .getQuantity()
                    );
                } else {
                    dataResult.put(
                            product,
                            new DataResult(
                                    date,
                                    product.getId(),
                                    0.0f,
                                    schedulingList.stream()
                                            .filter(p -> p.getProductId().equals(product.getId()))
                                            .findFirst()
                                            .get()
                                            .getQuantity()
                            )
                    );
                }

            }
        }
        return dataResult.values().stream().toList();
    }

    ;


}
