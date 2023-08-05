package org.klimashin.ga.first.solution.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.klimashin.ga.first.solution.application.entity.CelestialBodyEntity;
import org.klimashin.ga.first.solution.domain.model.CelestialBody;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CelestialBodyMapperTest {

    private final EasyRandom easyRandom = new EasyRandom();

    @InjectMocks
    private final CelestialBodyMapper mapper = Mappers.getMapper(CelestialBodyMapper.class);

//    @Test
//    void domainToEntity_shouldMap() {
//        var domain = easyRandom.nextObject(CelestialBody.class);
//        var orbit = domain.getOrbit();
//
//        var result = mapper.domainToEntity(domain);
//
//        assertThat(result)
//                .returns(domain.getName(), CelestialBodyEntity::getName)
//                .returns(domain.getMass(), CelestialBodyEntity::getMass)
//                .returns(orbit.getSemiMajorAxis(), CelestialBodyEntity::getSemiMajorAxis)
//                .returns(orbit.getEccentricity(), CelestialBodyEntity::getEccentricity);
//    }
}