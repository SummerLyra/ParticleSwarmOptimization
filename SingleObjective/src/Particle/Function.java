package Particle;

import java.util.ArrayList;

import static java.lang.Math.*;

class Function
{
    static final double w = 1; //惯性权重因子
    static final double c1 = 2; //个体认知常数
    static final double c2 = 2; //社会经验常数

    static double func1(ArrayList<Double> x, double A, int n)
    {
        double p1 = A * n;
        double p2 = 0.0;
        for (int i = 0; i < n; i++)
        {
            double xi = x.get(i);
            p2 += xi * xi - A * cos(2 * PI * xi);
        }
        return p1 + p2;
    }

    static double func5(double x, double y)
    {
        double p1 = pow((1.5 - x + x * y), 2);
        double p2 = pow((2.25 - x + x * y * y), 2);
        double p3 = pow((2.625 - x + x * y * y * y), 2);
        return p1 + p2 + p3;
    }

    static double func8(double x, double y)
    {
        double p1 = 100 * sqrt(abs(y - 0.01 * x * x));
        double p2 = 0.01 * abs(x + 10);
        return p1 + p2;
    }
}
