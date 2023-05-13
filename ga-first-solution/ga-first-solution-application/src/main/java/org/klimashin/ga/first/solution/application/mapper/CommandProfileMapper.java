package org.klimashin.ga.first.solution.application.mapper;

import org.klimashin.ga.first.solution.application.data.profile.CommandProfileData;
import org.klimashin.ga.first.solution.application.entity.CommandProfileType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class CommandProfileMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public CommandProfileType profileDataToProfileTypeEntity(CommandProfileData data) {
        return CommandProfileType.valueOf(data.getType().name());
    }

    public String profileDataToProfilePayloadEntity(CommandProfileData data) {
        try {
            return objectMapper.writeValueAsString(data.getProfile());
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
