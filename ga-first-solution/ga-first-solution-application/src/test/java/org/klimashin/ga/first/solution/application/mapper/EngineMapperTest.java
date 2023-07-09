package org.klimashin.ga.first.solution.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.klimashin.ga.first.solution.application.entity.EngineEntity;
import org.klimashin.ga.first.solution.domain.model.Engine;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class EngineMapperTest {

    private final EasyRandom easyRandom = new EasyRandom();

    private final EngineMapper mapper = Mappers.getMapper(EngineMapper.class);

    @Test
    void domainToEntity_shouldMap() {
        var data = easyRandom.nextObject(Engine.class);

        var result = mapper.domainToEntity(data);

        assertThat(result)
                .returns(null, EngineEntity::getId)
                .returns(null, EngineEntity::getName)
                .returns(data.getThrust(), EngineEntity::getThrust)
                .returns(data.getFuelConsumption(), EngineEntity::getFuelConsumption);
    }

}