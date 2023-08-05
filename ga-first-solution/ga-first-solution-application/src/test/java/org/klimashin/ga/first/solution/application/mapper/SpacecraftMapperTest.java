package org.klimashin.ga.first.solution.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.klimashin.ga.first.solution.application.entity.SpacecraftEntity;
import org.klimashin.ga.first.solution.domain.model.Engine;
import org.klimashin.ga.first.solution.domain.model.Spacecraft;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SpacecraftMapperTest {

    private final EasyRandom easyRandom = new EasyRandom();

    @InjectMocks
    private final SpacecraftMapper mapper = Mappers.getMapper(SpacecraftMapper.class);

//    @Test
//    void entityToDomain_shouldMap() {
//        var entity = easyRandom.nextObject(SpacecraftEntity.class);
//
//        var result = mapper.entityToDomain(entity);
//
//        assertThat(result)
//                .returns(entity.getDryMass() + entity.getStartFuelMass(), Spacecraft::getMass)
//                .returns(null, Spacecraft::getPosition)
//                .returns(null, Spacecraft::getSpeed)
//                .returns(entity.getEngineCount(), Spacecraft::getEngineCount)
//                .extracting(Spacecraft::getEngine)
//                .returns(entity.getEngineThrust(), Engine::getThrust)
//                .returns(entity.getEngineFuelConsumption(), Engine::getFuelConsumption);
//    }
}