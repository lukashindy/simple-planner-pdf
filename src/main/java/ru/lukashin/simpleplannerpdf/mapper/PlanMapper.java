package ru.lukashin.simpleplannerpdf.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.lukashin.simpleplannerpdf.dto.PlanDTO;
import ru.lukashin.simpleplannerpdf.model.Plan;

@Mapper(componentModel = "spring")
public interface PlanMapper {

    PlanMapper PLAN_MAPPER = Mappers.getMapper(PlanMapper.class);

    @Mappings({
        @Mapping(target = "id", source = "planDTO.id"),
        @Mapping(target = "name", source = "planDTO.name")
    })
    Plan planDtoToPlan(PlanDTO planDTO);

    @Mappings({
            @Mapping(target = "id", source = "plan.id"),
            @Mapping(target = "name", source = "plan.name")
    })
    PlanDTO planToPlanDTO(Plan plan);
}
