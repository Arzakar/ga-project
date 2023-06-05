package org.klimashin.ga.first.solution.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.klimashin.ga.first.solution.application.data.EngineData;
import org.klimashin.ga.first.solution.application.entity.EngineEntity;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class EngineMapperTest {

    private final EasyRandom easyRandom = new EasyRandom();

    private final EngineMapper mapper = Mappers.getMapper(EngineMapper.class);

    @Test
    void dataToEntity_shouldMap() {
        var data = easyRandom.nextObject(EngineData.class);

        var result = mapper.dataToEntity(data);

        assertThat(result)
                .returns(data.getId(), EngineEntity::getId)
                .returns(data.getName(), EngineEntity::getName)
                .returns(data.getThrust(), EngineEntity::getThrust)
                .returns(data.getFuelConsumption(), EngineEntity::getFuelConsumption);
    }

}