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
        var data = easyRandom.nextObject(Orbit.class);

        var result = mapper.domainToEntity(data);

        assertThat(result)
                .returns(null, OrbitEntity::getId)
                .returns(data.getSemiMajorAxis(), OrbitEntity::getSemiMajorAxis)
                .returns(data.getEccentricity(), OrbitEntity::getEccentricity);
    }

}