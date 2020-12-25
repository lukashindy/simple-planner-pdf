package ru.lukashin.simpleplannerpdf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lukashin.simpleplannerpdf.model.Plan;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    List<Plan> findAllByOrderById();
}
