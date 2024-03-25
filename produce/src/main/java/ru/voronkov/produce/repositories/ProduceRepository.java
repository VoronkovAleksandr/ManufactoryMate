package ru.voronkov.produce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.voronkov.produce.models.Produce;

@Repository
public interface ProduceRepository extends JpaRepository<Produce, Long> {
}
