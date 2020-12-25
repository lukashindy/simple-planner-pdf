package ru.lukashin.simpleplannerpdf.service;

import org.springframework.stereotype.Service;
import ru.lukashin.simpleplannerpdf.dto.PlanDTO;
import ru.lukashin.simpleplannerpdf.mapper.PlanMapper;
import ru.lukashin.simpleplannerpdf.model.Plan;
import ru.lukashin.simpleplannerpdf.repository.PlanRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanService {

    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public List<PlanDTO> findAll() {
        List<Plan> list = new ArrayList<>();
        planRepository.findAllByOrderById().iterator().forEachRemaining(list::add);
        return list
                .stream()
                .map(PlanMapper.PLAN_MAPPER::planToPlanDTO)
                .collect(Collectors.toList());
    }

    public PlanDTO findById(Long id) {
        Plan plan = planRepository.findById(id).orElse(null);
        if (plan != null) {
            return PlanMapper.PLAN_MAPPER.planToPlanDTO(plan);
        } else {
            throw new RuntimeException("Нет такого плана");
        }
    }

    public PlanDTO save(PlanDTO planDTO) {
        Plan plan = PlanMapper.PLAN_MAPPER.planDtoToPlan(planDTO);
        Plan savedPlan = planRepository.save(plan);
        return PlanMapper.PLAN_MAPPER.planToPlanDTO(savedPlan);
    }

    public void deleteById(Long id) {
        planRepository.deleteById(id);
    }
}
