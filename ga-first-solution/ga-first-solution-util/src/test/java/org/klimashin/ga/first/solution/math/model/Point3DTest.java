package org.klimashin.ga.first.solution.math.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.klimashin.ga.first.solution.util.math.model.Point3D;
import org.klimashin.ga.first.solution.util.math.model.Vector3D;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

class Point3DTest {

    private final EasyRandom easyRandom = new EasyRandom();

    @Test
    void of_shouldCreateNewPoint() {
        var x = easyRandom.nextDouble();
        var y = easyRandom.nextDouble();
        var z = easyRandom.nextDouble();

        var result = Point3D.of(x, y, z);

        assertThat(result)
                .returns(x, Point3D::getX)
                .returns(y, Point3D::getY)
                .returns(z, Point3D::getZ);
    }

    @Test
    void move_shouldMove() {
        var startX = easyRandom.nextDouble();
        var startY = easyRandom.nextDouble();
        var startZ = easyRandom.nextDouble();
        var point = Point3D.of(startX, startY, startZ);
        var vector = Vector3D.of(easyRandom.nextDouble(), easyRandom.nextDouble(), easyRandom.nextDouble());

        var result = point.move(vector);

        assertThat(result)
                .isSameAs(point)
                .returns(startX + vector.getX(), Point3D::getX)
                .returns(startY + vector.getY(), Point3D::getY)
                .returns(startZ + vector.getZ(), Point3D::getZ);
    }

    @Test
    void copy_shouldCopy() {
        var original = Point3D.of(easyRandom.nextDouble(), easyRandom.nextDouble(), easyRandom.nextDouble());

        var result = original.copy();

        assertThat(result)
                .isNotSameAs(original)
                .returns(original.getX(), Point3D::getX)
                .returns(original.getY(), Point3D::getY)
                .returns(original.getZ(), Point3D::getZ);
    }
}