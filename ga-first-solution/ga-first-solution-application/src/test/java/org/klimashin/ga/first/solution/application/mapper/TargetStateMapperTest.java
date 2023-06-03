package org.klimashin.ga.first.solution.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.klimashin.ga.first.solution.application.configuration.EasyRandomConfiguration.defaultEasyRandom;

import org.klimashin.ga.first.solution.application.data.condition.ProximityOfTwoObjectsData;
import org.klimashin.ga.first.solution.application.data.condition.TargetStateTypeData;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class TargetStateMapperTest {

    private final EasyRandom easyRandom = defaultEasyRandom();

    @InjectMocks
    private TargetStateMapper mapper = Mappers.getMapper(TargetStateMapper.class);

    @Spy
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void stateDataToStateTypeEntity_shouldMapProximityOfTwoObjectsData() {
        var data = easyRandom.nextObject(ProximityOfTwoObjectsData.class);

        var result = mapper.stateDataToStateTypeEntity(data);

        assertThat(result.name()).isEqualTo(TargetStateTypeData.PROXIMITY_OF_TWO_OBJECTS.name());
    }

    @Test
    void profileDataToProfilePayloadEntity_shouldMapFixedVectorDeviationProfile() {
        var data = new ProximityOfTwoObjectsData()
                .setFirstObjectId(UUID.fromString("62799c01-6dd3-486d-b393-d909f90fb015"))
                .setSecondObjectId(UUID.fromString("c53fe731-21bc-4dcc-ae96-e89d4b31563a"))
                .setRequiredDistance(500d);
        var expectedResult = """
                {
                    "firstObjectId": "62799c01-6dd3-486d-b393-d909f90fb015",
                    "secondObjectId": "c53fe731-21bc-4dcc-ae96-e89d4b31563a",
                    "requiredDistance":500.0
                }
                """;

        var result = mapper.stateDataToStatePayloadEntity(data);

        assertThat(result).isEqualToIgnoringWhitespace(expectedResult);
    }
}