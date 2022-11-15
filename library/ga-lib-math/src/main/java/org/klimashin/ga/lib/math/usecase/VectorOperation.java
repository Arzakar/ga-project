package org.klimashin.ga.lib.math.usecase;

import lombok.experimental.UtilityClass;
import org.klimashin.ga.lib.math.domain.Vector;

@UtilityClass
public class VectorOperation {

    public Vector add(Vector firstVector, Vector secondVector) {
        return new Vector(firstVector).add(secondVector);
    }

    public Vector subtract(Vector firstVector, Vector secondVector) {
        return new Vector(firstVector).subtract(secondVector);
    }

    public Vector multiply(Vector vector, double ratio) {
        return new Vector(vector).multiply(ratio);
    }

    public Vector divide(Vector vector, double ratio) {
        return new Vector(vector).divide(ratio);
    }

    public double getScalar(Vector vector) {
        return vector.getScalar();
    }

    public Vector getUnit(Vector vector) {
        return new Vector(vector).toUnit();
    }

    public Vector getRotatedByZ(Vector vector, double angleInRadian) {
        return new Vector(vector).rotateByZ(angleInRadian);
    }
}
