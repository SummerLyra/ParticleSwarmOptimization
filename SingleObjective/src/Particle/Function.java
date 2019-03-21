package Particle;

import static java.lang.Math.*;

class Function
{
    static double func5(double x, double y)
    {
        double p1 = pow((1.5 - x + x * y), 2);
        double p2 = pow((2.25 - x + x * y * y), 2);
        double p3 = pow((2.625 - x + x * y * y * y), 2);
        return p1 + p2 + p3;
    }

    /*
    static double func8(double x, double y)
    {
        double p1 = 100 * sqrt(abs(y - 0.01 * x * x));
        double p2 = 0.01 * abs(x + 10);
        return p1 + p2;
    }
    */
}
