package org.klimashin.ga.first.solution.application.mapper;

import org.klimashin.ga.first.solution.api.dto.FixedDeviationCommandProfileRequestDtoV1;
import org.klimashin.ga.first.solution.application.data.profile.CommandProfileData;
import org.klimashin.ga.first.solution.application.data.profile.FixedVectorDeviationProfileData;
import org.klimashin.ga.first.solution.application.entity.CommandProfileType;
import org.klimashin.ga.first.solution.application.entity.InitialStateEntity;
import org.klimashin.ga.first.solution.domain.model.LongPair;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class CommandProfileMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public CommandProfileType profileDataToProfileTypeEntity(CommandProfileData data) {
        if (Objects.isNull(data) || Objects.isNull(data.getType())) {
            return null;
        }

        return CommandProfileType.valueOf(data.getType().name());
    }

    public String profileDataToProfilePayloadEntity(CommandProfileData data) {
        if (Objects.isNull(data) || Objects.isNull(data.getProfile())) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(data.getProfile());
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public CommandProfileData initialStateEntityToData(InitialStateEntity entity) {
        if (Objects.isNull(entity)
                || Objects.isNull(entity.getCommandProfileType())
                || Objects.isNull(entity.getCommandProfilePayload())) {
            return null;
        }

        try {
            return switch (entity.getCommandProfileType()) {
                case FIXED_VECTOR_DEVIATION ->
                        objectMapper.readValue(entity.getCommandProfilePayload(), FixedVectorDeviationProfileData.class);
            };
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public FixedVectorDeviationProfileData requestDtoV1ToData(FixedDeviationCommandProfileRequestDtoV1 requestDtoV1) {
        if (Objects.isNull(requestDtoV1)) {
            return null;
        }

        if (Objects.isNull(requestDtoV1.getIntervals())) {
            return null;
        }

        var intervals = requestDtoV1.getIntervals().stream()
                .collect(Collectors.toMap(
                        interval -> LongPair.of(interval.getLeftValue(), interval.getRightValue()),
                        FixedDeviationCommandProfileRequestDtoV1.Interval::getDeviation));

        return new FixedVectorDeviationProfileData()
                .setStartVectorObjectId(requestDtoV1.getStartVectorObjectId())
                .setEndVectorObjectId(requestDtoV1.getEndVectorObjectId())
                .setIntervals(intervals);
    }
}
