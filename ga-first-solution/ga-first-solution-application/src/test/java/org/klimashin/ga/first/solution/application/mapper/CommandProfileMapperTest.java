package org.klimashin.ga.first.solution.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.klimashin.ga.first.solution.application.configuration.EasyRandomConfiguration.defaultEasyRandom;

import org.klimashin.ga.first.solution.application.data.profile.CommandProfileTypeData;
import org.klimashin.ga.first.solution.application.data.profile.FixedVectorDeviationProfile;
import org.klimashin.ga.first.solution.domain.math.Point;
import org.klimashin.ga.first.solution.domain.model.Pair;
import org.klimashin.ga.first.solution.domain.model.PointParticle;

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

@ExtendWith(MockitoExtension.class)
class CommandProfileMapperTest {

    private EasyRandom easyRandom = defaultEasyRandom();

    @InjectMocks
    private CommandProfileMapper mapper = Mappers.getMapper(CommandProfileMapper.class);

    @Spy
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void profileDataToProfileTypeEntity_shouldMapFixedVectorDeviationProfile() {
        var data = easyRandom.nextObject(FixedVectorDeviationProfile.class);

        var result = mapper.profileDataToProfileTypeEntity(data);

        assertThat(result.name()).isEqualTo(CommandProfileTypeData.FIXED_VECTOR_DEVIATION.name());
    }

    @Test
    void profileDataToProfilePayloadEntity_shouldMapFixedVectorDeviationProfile() {
        var data = new FixedVectorDeviationProfile()
                .setStartVectorObject(new PointParticle(100, Point.of(1, 1, 1)))
                .setEndVectorObject(new PointParticle(200, Point.of(2, 2, 2)))
                .setIntervals(Map.of(Pair.of(10, 20), 500d))
                .setTimeBounds(List.of(Pair.of(10, 20)));
        var expectedResult = """
                {
                    "startVectorObject": {
                        "mass": 100.0,
                        "position": {
                            "x": 1.0,
                            "y": 1.0,
                            "z": 1.0
                        }
                    },
                    "endVectorObject": {
                        "mass":200.0,
                        "position": {
                            "x": 2.0,
                            "y": 2.0,
                            "z": 2.0
                        }
                    },
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