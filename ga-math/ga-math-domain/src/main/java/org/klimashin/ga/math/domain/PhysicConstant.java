package org.klimashin.ga.math.domain;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;

@UtilityClass
@FieldDefaults(makeFinal = true)
public class PhysicConstant {

    /** Гравитационная постоянная Солнца, м^3/с^2 */
    public double SOLAR_GRAVITY_PARAMETER = 132712440018000d;

    /** Угловая скорость вращения Земли вокруг Солнца, 1/с */
    public double EARTH_ANGULAR_SPEED = 1.991021 * Math.pow(10, -7);

    /** Радиус орбиты Земли вокруг Солнца, м */
    public double EARTH_ORBIT_RADIUS = 149.6 * Math.pow(10, 9);

    /** Радиус сферы гравитационного притяжения Земли */
    public double EARTH_GRAVITY_SPHERE_RADIUS = Math.pow(10, 9);

}
