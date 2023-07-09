package org.klimashin.ga.first.solution.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.klimashin.ga.first.solution.application.entity.EngineEntity;
import org.klimashin.ga.first.solution.application.entity.SpacecraftEntity;
import org.klimashin.ga.first.solution.domain.model.Spacecraft;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SpacecraftMapperTest {

    private final EasyRandom easyRandom = new EasyRandom();

    @InjectMocks
    private final SpacecraftMapper mapper = Mappers.getMapper(SpacecraftMapper.class);

    @Spy
    private EngineMapper engineMapper = Mappers.getMapper(EngineMapper.class);

    @Test
    void domainToEntity_shouldMap() {
        var data = easyRandom.nextObject(Spacecraft.class);
        var engine = data.getEngine();

        var result = mapper.domainToEntity(data);

        assertThat(result)
                .returns(null, SpacecraftEntity::getId)
                .returns(null, SpacecraftEntity::getDryMass)
                .returns(data.getFuelMass(), SpacecraftEntity::getStartFuelMass)
                .returns(data.getEngineCount(), SpacecraftEntity::getEngineCount)
                .extracting(SpacecraftEntity::getEngine)
                .returns(null, EngineEntity::getId)
                .returns(null, EngineEntity::getName)
                .returns(engine.getThrust(), EngineEntity::getThrust)
                .returns(engine.getFuelConsumption(), EngineEntity::getFuelConsumption);
    }
}