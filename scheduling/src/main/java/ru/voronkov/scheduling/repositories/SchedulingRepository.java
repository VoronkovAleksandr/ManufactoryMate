package ru.voronkov.scheduling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.voronkov.scheduling.models.Scheduling;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
}
