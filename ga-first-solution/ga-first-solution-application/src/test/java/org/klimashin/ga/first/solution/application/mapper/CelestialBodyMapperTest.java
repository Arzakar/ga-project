package org.klimashin.ga.first.solution.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.klimashin.ga.first.solution.application.configuration.EasyRandomConfiguration.defaultEasyRandom;
import static org.mockito.Mockito.verify;

import org.klimashin.ga.first.solution.application.entity.CelestialBodyEntity;
import org.klimashin.ga.first.solution.application.entity.OrbitEntity;
import org.klimashin.ga.first.solution.domain.model.CelestialBody;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CelestialBodyMapperTest {

    private final EasyRandom easyRandom = defaultEasyRandom();

    @InjectMocks
    private final CelestialBodyMapper mapper = Mappers.getMapper(CelestialBodyMapper.class);

    @Spy
    private OrbitMapper orbitMapper = Mappers.getMapper(OrbitMapper.class);

    @Test
    void domainToEntity_shouldMap() {
        var domain = easyRandom.nextObject(CelestialBody.class);
        var orbit = domain.getOrbit();

        var result = mapper.domainToEntity(domain);

        assertThat(result)
                .returns(null, CelestialBodyEntity::getId)
                .returns(domain.getName(), CelestialBodyEntity::getName)
                .returns(domain.getMass(), CelestialBodyEntity::getMass)
                .extracting(CelestialBodyEntity::getOrbit)
                .returns(null, OrbitEntity::getId)
                .returns(orbit.getSemiMajorAxis(), OrbitEntity::getSemiMajorAxis)
                .returns(orbit.getEccentricity(), OrbitEntity::getEccentricity);

        verify(orbitMapper).domainToEntity(orbit);
    }
}