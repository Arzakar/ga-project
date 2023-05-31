package org.klimashin.ga.first.solution.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.klimashin.ga.first.solution.domain.TestUtil;
import org.klimashin.ga.first.solution.domain.math.Point;
import org.klimashin.ga.first.solution.domain.math.Vector;

import org.junit.jupiter.api.Test;

class SpacecraftTest {

    @Test
    void buildSpacecraft_shouldThrowException() {
        assertThatIllegalArgumentException().isThrownBy(() -> Spacecraft.builder()
                .mass(10d)
                .position(new Point(0, 0, 0))
                .speed(new Vector(0, 0, 0))
                .acceleration(new Vector(0, 0, 0))
                .fuelMass(20d)
                .engine(new Engine(10, 10))
                .engineCount(2)
                .build());
    }

    //@Test
    void changeDynamicState_shouldChangeDynamicState() {
        var spacecraft = Spacecraft.builder()
                .position(new Point(10, 10, 10))
                .speed(new Vector(5, 0, 0))
                .acceleration(new Vector(0, 5, 10))
                .fuelMass(50d)
                .mass(100d)
                .engine(null)
                .engineCount(0)
                .build();

        var forceVector = new Vector(5, 3, 1);

        var result = spacecraft.changeDynamicState(forceVector, 2);

        assertThat(result)
                .isSameAs(spacecraft)
                .returns(50d, Spacecraft::getFuelMass)
                .returns(100d, Spacecraft::getMass)
                .returns(null, Spacecraft::getEngine)
                .returns(0, Spacecraft::getEngineCount);

        assertThat(result.getPosition())
                .returns(2020d, Point::getX)
                .returns(1210d, Point::getY)
                .returns(410d, Point::getZ);

        assertThat(result.getSpeed())
                .returns(1005d, Vector::getX)
                .returns(600d, Vector::getY)
                .returns(200d, Vector::getZ);

        assertThat(result.getAcceleration())
                .returns(500d, Vector::getX)
                .returns(300d, Vector::getY)
                .returns(100d, Vector::getZ);

        assertThat(forceVector)
                .returns(5d, Vector::getX)
                .returns(3d, Vector::getY)
                .returns(1d, Vector::getZ);
    }

    @Test
    void getEngineSystemThrust_shouldReturnEngineSystemThrust() {
        var spacecraft = TestUtil.spacecraftGenerator()
                .withEngine(new Engine(10, 10))
                .withEngineCount(2)
                .generate();

        var result = spacecraft.getEngineSystemThrust();

        assertThat(result).isEqualTo(20d);
    }

    @Test
    void reduceFuel_shouldReduceFuel() {
        var spacecraft = Spacecraft.builder()
                .position(new Point(10, 10, 10))
                .speed(new Vector(5, 0, 0))
                .acceleration(new Vector(0, 5, 10))
                .fuelMass(50d)
                .mass(100d)
                .engine(new Engine(10, 10))
                .engineCount(2)
                .build();

        var result = spacecraft.reduceFuel(2);

        assertThat(result)
                .isSameAs(spacecraft)
                .returns(10d, Spacecraft::getFuelMass)
                .returns(60d, Spacecraft::getMass)
                .returns(2, Spacecraft::getEngineCount);

        assertThat(result.getPosition())
                .returns(10d, Point::getX)
                .returns(10d, Point::getY)
                .returns(10d, Point::getZ);

        assertThat(result.getSpeed())
                .returns(5d, Vector::getX)
                .returns(0d, Vector::getY)
                .returns(0d, Vector::getZ);

        assertThat(result.getAcceleration())
                .returns(0d, Vector::getX)
                .returns(5d, Vector::getY)
                .returns(10d, Vector::getZ);

        assertThat(result.getEngine())
                .returns(10d, Engine::getThrust)
                .returns(10d, Engine::getFuelConsumption);
    }
}