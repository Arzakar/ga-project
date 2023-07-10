package org.klimashin.ga.first.solution.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.klimashin.ga.first.solution.application.entity.OrbitEntity;
import org.klimashin.ga.first.solution.domain.model.Orbit;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class OrbitMapperTest {

    private final EasyRandom easyRandom = new EasyRandom();
    private final OrbitMapper mapper = Mappers.getMapper(OrbitMapper.class);

    @Test
    void domainToEntity_shouldMap() {
        var domain = easyRandom.nextObject(Orbit.class);

        var result = mapper.domainToEntity(domain);

        assertThat(result)
                .returns(null, OrbitEntity::getId)
                .returns(domain.getSemiMajorAxis(), OrbitEntity::getSemiMajorAxis)
                .returns(domain.getEccentricity(), OrbitEntity::getEccentricity);
    }

}