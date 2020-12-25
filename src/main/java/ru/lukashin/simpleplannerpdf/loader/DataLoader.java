package ru.lukashin.simpleplannerpdf.loader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.lukashin.simpleplannerpdf.model.Plan;
import ru.lukashin.simpleplannerpdf.repository.PlanRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final PlanRepository planRepository;

    public DataLoader(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public void run(String... args) {
        Plan plan1 = new Plan();
        plan1.setId(1L);
        plan1.setName("Изучить Hibernate");
        planRepository.save(plan1);

        Plan plan2 = new Plan();
        plan2.setId(2L);
        plan2.setName("Разобраться, из чего состоит Spring Boot");
        planRepository.save(plan2);
    }
}
