package org.klimashin.ga.first.solution.application.mapper;

import org.klimashin.ga.first.solution.api.dto.InitialStateRequestDtoV1;
import org.klimashin.ga.first.solution.application.data.condition.ProximityOfTwoObjectsData;
import org.klimashin.ga.first.solution.application.data.condition.TargetStateData;
import org.klimashin.ga.first.solution.application.entity.InitialStateEntity;
import org.klimashin.ga.first.solution.application.entity.TargetStateType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class TargetStateMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public TargetStateType stateDataToStateTypeEntity(TargetStateData data) {
        if (Objects.isNull(data) || Objects.isNull(data.getType())) {
            return null;
        }

        return TargetStateType.valueOf(data.getType().name());
    }

    public String stateDataToStatePayloadEntity(TargetStateData data) {
        if (Objects.isNull(data) || Objects.isNull(data.getType())) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(data.getState());
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public TargetStateData initialStateEntityToData(InitialStateEntity entity) {
        if (Objects.isNull(entity)
                || Objects.isNull(entity.getTargetStateType())
                || Objects.isNull(entity.getTargetStatePayload())) {
            return null;
        }

        try {
            return switch (entity.getTargetStateType()) {
                case PROXIMITY_OF_TWO_OBJECTS ->
                        objectMapper.readValue(entity.getTargetStatePayload(), ProximityOfTwoObjectsData.class);
            };
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public TargetStateData requestDtoV1ToData(InitialStateRequestDtoV1 requestDtoV1) {
        if (Objects.isNull(requestDtoV1)
                || Objects.isNull(requestDtoV1.getTargetStateType())
                || Objects.isNull(requestDtoV1.getTargetStatePayload())) {
            return null;
        }

        return switch (requestDtoV1.getTargetStateType()) {
            case PROXIMITY_OF_TWO_OBJECTS ->
                    objectMapper.convertValue(requestDtoV1.getTargetStatePayload(), ProximityOfTwoObjectsData.class);
        };
    }
}
