package org.klimashin.ga.first.solution.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.klimashin.ga.first.solution.application.data.OrbitData;
import org.klimashin.ga.first.solution.application.entity.OrbitEntity;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class OrbitMapperTest {

    private final EasyRandom easyRandom = new EasyRandom();
    private final OrbitMapper mapper = Mappers.getMapper(OrbitMapper.class);

    @Test
    void dataToEntity_shouldMap() {
        var data = easyRandom.nextObject(OrbitData.class);

        var result = mapper.dataToEntity(data);

        assertThat(result)
                .returns(data.getId(), OrbitEntity::getId)
                .returns(data.getSemiMajorAxis(), OrbitEntity::getSemiMajorAxis)
                .returns(data.getEccentricity(), OrbitEntity::getEccentricity)
                .returns(data.getInclination(), OrbitEntity::getInclination)
                .returns(data.getLongitudeAscNode(), OrbitEntity::getLongitudeAscNode)
                .returns(data.getPerihelionArgument(), OrbitEntity::getPerihelionArgument);
    }

}