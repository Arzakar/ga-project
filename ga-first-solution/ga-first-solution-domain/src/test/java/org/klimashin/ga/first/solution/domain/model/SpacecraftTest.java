package org.klimashin.ga.first.solution.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.klimashin.ga.first.solution.domain.TestUtils;
import org.klimashin.ga.first.solution.util.math.model.Point2D;
import org.klimashin.ga.first.solution.util.math.model.Vector2D;

import org.junit.jupiter.api.Test;

class SpacecraftTest {

    @Test
    void buildSpacecraft_shouldThrowException() {
        assertThatIllegalArgumentException().isThrownBy(() -> Spacecraft.builder()
                .mass(10d)
                .position(Point2D.of(0, 0))
                .speed(Vector2D.of(0, 0))
                .fuelMass(20d)
                .engine(new Engine(10, 10))
                .engineCount(2)
                .build());
    }

    @Test
    void move_shouldMoveSpacecraft() {
        var spacecraft = Spacecraft.builder()
                .position(Point2D.of(10, 10))
                .speed(Vector2D.of(5, 0))
                .fuelMass(50d)
                .mass(100d)
                .engine(null)
                .engineCount(0)
                .build();

        var forceVector = Vector2D.of(5, 3);

        var result = spacecraft.move(forceVector, 2);

        assertThat(result)
                .isSameAs(spacecraft)
                .returns(50d, Spacecraft::getFuelMass)
                .returns(100d, Spacecraft::getMass)
                .returns(null, Spacecraft::getEngine)
                .returns(0, Spacecraft::getEngineCount);

        assertThat(result.getPosition())
                .returns(2020d, Point2D::getX)
                .returns(1210d, Point2D::getY);

        assertThat(result.getSpeed())
                .returns(1005d, Vector2D::getX)
                .returns(600d, Vector2D::getY);

        assertThat(forceVector)
                .returns(5d, Vector2D::getX)
                .returns(3d, Vector2D::getY);
    }

    @Test
    void getEngineSystemThrust_shouldReturnEngineSystemThrust() {
        var spacecraft = TestUtils.spacecraftGenerator()
                .withEngine(new Engine(10, 10))
                .withEngineCount(2)
                .generate();

        var result = spacecraft.getEngineSystemThrust();

        assertThat(result).isEqualTo(20d);
    }

    @Test
    void reduceFuel_shouldReduceFuel() {
        var spacecraft = Spacecraft.builder()
                .position(Point2D.of(10, 10))
                .speed(Vector2D.of(5, 0))
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
                .returns(10d, Point2D::getX)
                .returns(10d, Point2D::getY);

        assertThat(result.getSpeed())
                .returns(5d, Vector2D::getX)
                .returns(0d, Vector2D::getY);

        assertThat(result.getEngine())
                .returns(10d, Engine::getThrust)
                .returns(10d, Engine::getFuelConsumption);
    }
}