package org.klimashin.ga.math.domain.resolver;

import static org.klimashin.ga.math.domain.PhysicConstant.EARTH_ANGULAR_SPEED;
import static org.klimashin.ga.math.domain.PhysicConstant.EARTH_GRAVITY_SPHERE_RADIUS;
import static org.klimashin.ga.math.domain.PhysicConstant.EARTH_ORBIT_RADIUS;
import static org.klimashin.ga.math.domain.PhysicConstant.SOLAR_GRAVITY_PARAMETER;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.klimashin.ga.math.domain.systems.PMPFirstSystem;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class PontryaginMaximumPrincipleResolver {

    double muSolar = SOLAR_GRAVITY_PARAMETER;
    double engF = 0.250d;
    double gamma = 50;
    double omega = EARTH_ANGULAR_SPEED;


    public void calculate() {
        var system = new PMPFirstSystem();
        var xVars = new double[]{
                EARTH_ORBIT_RADIUS - EARTH_GRAVITY_SPHERE_RADIUS,
                0,
                0,
                Math.sqrt(SOLAR_GRAVITY_PARAMETER / EARTH_ORBIT_RADIUS - EARTH_GRAVITY_SPHERE_RADIUS),
                200,
                EARTH_ORBIT_RADIUS,
                0,
                0,
                Math.sqrt(SOLAR_GRAVITY_PARAMETER / EARTH_ORBIT_RADIUS)
        };

        var psiVars = new double[] {
                0, 0, 0, 0, 0, 0, 0, 0, 0
        };

        if (xVars.length != system.X_VARS_LENGTH || psiVars.length != system.PSI_VARS_LENGTH) {
            throw new RuntimeException("Размерность массивов начальных значений должна совпадать с рассматриваемой системой");
        }


    }
}
