package org.klimashin.ga.first.solution.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.klimashin.ga.first.solution.application.configuration.EasyRandomConfiguration.defaultEasyRandom;

import org.klimashin.ga.first.solution.application.data.CelestialBodyData;
import org.klimashin.ga.first.solution.application.entity.CelestialBodyEntity;
import org.klimashin.ga.first.solution.application.entity.OrbitEntity;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CelestialBodyMapperTest {

    private EasyRandom easyRandom = defaultEasyRandom();

    @InjectMocks
    private CelestialBodyMapper mapper = Mappers.getMapper(CelestialBodyMapper.class);

    @Spy
    private OrbitMapper orbitMapper = Mappers.getMapper(OrbitMapper.class);

    @Test
    void dataToEntity_shouldMap() {
        var data = easyRandom.nextObject(CelestialBodyData.class);
        var orbit = data.getOrbit();

        var result = mapper.dataToEntity(data);

        assertThat(result)
                .returns(data.getId(), CelestialBodyEntity::getId)
                .returns(data.getName(), CelestialBodyEntity::getName)
                .returns(data.getMass(), CelestialBodyEntity::getMass)
                .extracting(CelestialBodyEntity::getOrbit)
                .returns(orbit.getId(), OrbitEntity::getId)
                .returns(orbit.getSemiMajorAxis(), OrbitEntity::getSemiMajorAxis)
                .returns(orbit.getEccentricity(), OrbitEntity::getEccentricity)
                .returns(orbit.getInclination(), OrbitEntity::getInclination)
                .returns(orbit.getLongitudeAscNode(), OrbitEntity::getLongitudeAscNode)
                .returns(orbit.getPerihelionArgument(), OrbitEntity::getPerihelionArgument);
    }
}