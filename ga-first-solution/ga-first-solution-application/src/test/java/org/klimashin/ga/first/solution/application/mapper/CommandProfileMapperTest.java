package org.klimashin.ga.first.solution.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.klimashin.ga.first.solution.application.configuration.EasyRandomConfiguration.defaultEasyRandom;

import org.klimashin.ga.first.solution.application.data.profile.CommandProfileTypeData;
import org.klimashin.ga.first.solution.application.data.profile.FixedVectorDeviationProfileData;
import org.klimashin.ga.first.solution.domain.model.LongPair;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CommandProfileMapperTest {

    private final EasyRandom easyRandom = defaultEasyRandom();

    @InjectMocks
    private final CommandProfileMapper mapper = Mappers.getMapper(CommandProfileMapper.class);

    @Spy
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void profileDataToProfileTypeEntity_shouldMapFixedVectorDeviationProfile() {
        var data = easyRandom.nextObject(FixedVectorDeviationProfileData.class);

        var result = mapper.profileDataToProfileTypeEntity(data);

        assertThat(result.name()).isEqualTo(CommandProfileTypeData.FIXED_VECTOR_DEVIATION.name());
    }

    @Test
    void profileDataToProfilePayloadEntity_shouldMapFixedVectorDeviationProfile() {
        var data = new FixedVectorDeviationProfileData()
                .setStartVectorObjectId(UUID.fromString("62799c01-6dd3-486d-b393-d909f90fb015"))
                .setEndVectorObjectId(UUID.fromString("c53fe731-21bc-4dcc-ae96-e89d4b31563a"))
                .setIntervals(Map.of(LongPair.of(10, 20), 500d))
                .setTimeBounds(List.of(LongPair.of(10, 20)));
        var expectedResult = """
                {
                    "startVectorObjectId": "62799c01-6dd3-486d-b393-d909f90fb015",
                    "endVectorObjectId": "c53fe731-21bc-4dcc-ae96-e89d4b31563a",
                    "intervals": {
                        "Pair[leftValue=10, rightValue=20]": 500.0
                    },
                    "timeBounds": [
                        {
                            "leftValue": 10,
                            "rightValue": 20
                        }
                    ]
                }
                """;

        var result = mapper.profileDataToProfilePayloadEntity(data);

        assertThat(result).isEqualToIgnoringWhitespace(expectedResult);
    }

}