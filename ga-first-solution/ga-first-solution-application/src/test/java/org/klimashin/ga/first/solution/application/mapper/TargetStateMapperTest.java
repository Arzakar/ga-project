package org.klimashin.ga.first.solution.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.klimashin.ga.first.solution.application.configuration.EasyRandomConfiguration.defaultEasyRandom;

import org.klimashin.ga.first.solution.application.data.condition.ProximityOfTwoObjectsData;
import org.klimashin.ga.first.solution.application.data.condition.TargetStateData;
import org.klimashin.ga.first.solution.application.data.condition.TargetStateTypeData;
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
class TargetStateMapperTest {

    private EasyRandom easyRandom = defaultEasyRandom();

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
                .setFirstParticle(new PointParticle(100, Point.of(1, 1, 1)))
                .setSecondParticle(new PointParticle(200, Point.of(2, 2, 2)))
                .setRequiredDistance(500d);
        var expectedResult = """
                {
                    "firstParticle": {
                        "mass": 100.0,
                        "position": {
                            "x": 1.0,
                            "y": 1.0,
                            "z": 1.0
                        }
                    },
                    "secondParticle": {
                        "mass": 200.0,
                        "position": {
                            "x": 2.0,
                            "y": 2.0,
                            "z": 2.0
                        }
                    },
                    "requiredDistance":500.0
                }
                """;

        var result = mapper.stateDataToStatePayloadEntity(data);

        assertThat(result).isEqualToIgnoringWhitespace(expectedResult);
    }
}