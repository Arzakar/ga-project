package org.klimashin.ga.first.solution.application.mapper;

import org.klimashin.ga.first.solution.application.data.condition.TargetStateData;
import org.klimashin.ga.first.solution.application.entity.TargetStateType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class TargetStateMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public TargetStateType stateDataToStateTypeEntity(TargetStateData data) {
        return TargetStateType.valueOf(data.getType().name());
    }

    public String stateDataToStatePayloadEntity(TargetStateData data) {
        try {
            return objectMapper.writeValueAsString(data.getState());
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
