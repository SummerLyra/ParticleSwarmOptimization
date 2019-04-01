package Particle;

import java.util.ArrayList;

import static java.lang.Math.*;

/**
 * 常量与计算用函数类
 */
class Function {
    /**
     * 惯性权重因子
     */
    static final double W = 0.5;
    /**
     * 个体认知常数
     */
    static final double C1 = 2.0;
    /**
     * 社会经验常数
     */
    static final double C2 = 2.0;

    /**
     * function 1
     * n = 10, -5.12 <= xi <= 5.12
     * xm = 0.0
     * fm = 0.0
     */
    private static double rastrigin(ArrayList<Double> x) {
        double sum = 0.0;
        for (double xi : x) {
            sum += xi * xi - 10.0 * cos(2 * PI * xi);
        }
        return 10.0 * x.size() + sum;
    }

    /**
     * function 2
     * -5.0 <= x,y <= 5.0
     * xm = 0.0, ym = 0.0
     * fm = 0.0
     */
    private static double ackley(double x, double y) {
        return -20 * pow(E, -0.2 * sqrt(0.5 * (x * x + y * y))) - pow(E, 0.5 * (cos(2 * PI * x) + cos(2 * PI * y))) + E + 20;
    }

    /**
     * function 3
     * n = 10, -100.0 <= xi <= 100.0
     * xm = 0.0
     * fm = 0.0
     */
    private static double sphere(ArrayList<Double> x) {
        double sum = 0.0;
        for (double xi : x) {
            sum += xi * xi;
        }
        return sum;
    }

    /**
     * function 4
     * n = 10, -30.0 <= xi <= 30.0
     * xm = 1.0
     * fm = 0.0
     */
    private static double rosenbrock(ArrayList<Double> x) {
        double s1 = 0.0;
        double s2 = 0.0;
        for (int i = 0; i < (x.size() - 1); i++) {
            double xi = x.get(i);
            double xii = x.get(i + 1);
            s1 += 100 * pow((xii - xi * xi), 2);
            s2 += pow((1 - xi), 2);
        }
        return s1 + s2;
    }

    /**
     * function 5
     * -4.5 <= x,y <= 4.5
     * xm = 3.0, ym = 0.5
     * fm = 0.0
     */
    private static double beale(double x, double y) {
        return pow((1.5 - x + x * y), 2) + pow((2.25 - x + x * y * y), 2) + pow((2.625 - x + x * y * y * y), 2);
    }

    /**
     * function 6
     * -100.0 <= x,y <= 100.0
     * xm = +-1.25313, ym = 0.0
     * fm = 0.292579
     */
    private static double schafferN4(double x, double y) {
        return 0.5 + (pow(cos(sin(abs(x * x - y * y))), 2) - 0.5) / pow(1 + 0.001 * (x * x + y * y), 2);
    }

    /**
     * function 7
     * -5.0 <= xi <= 5.0, 1 <= i <= n
     * xm = -2.903534
     * fm = (-39.16617n,-39.16616n)
     */
    private static double styblinskiTang(ArrayList<Double> x) {
        double sum = 0.0;
        for (double xi : x) {
            sum += xi * xi * xi * xi - 16 * xi * xi + 5 * xi;
        }
        return sum / 2;
    }

    /**
     * function 8
     * -15.0 <= x <= -5.0, -3.0 <= y <= 3.0
     * xm = -10.0, ym = 1.0
     * fm = 0.0
     */
    private static double bukinN6(double x, double y) {
        return 100 * sqrt(abs(y - 0.01 * x * x)) + 0.01 * abs(x + 10);
    }

    /**
     * function 9
     * -5.0 <= x,y <= 5.0
     * xm1 =  3.0     , ym1 =  2.0     , fm = 0.0
     * xm2 = -2.805118, ym2 =  3.131312, fm = 0.0
     * xm3 = -3.779310, ym3 = -3.283186, fm = 0.0
     * xm4 =  3.584428, ym4 = -1.848126, fm = 0.0
     */
    private static double himmelblaus(double x, double y) {
        return pow((x * x + y - 11), 2) + pow((x + y * y - 7), 2);
    }

    /**
     * function 10
     * -10.0 <= x,y <= 10.0
     * xm = +-1.34941, ym = +-1.34941
     * fm = -2.06261
     */
    private static double crossInTray(double x, double y) {
        return -0.0001 * pow(abs(sin(x) * sin(y) * pow(E, abs(100 - sqrt(x * x + y * y) / PI))) + 1, 0.1);
    }

    /**
     * for function 1, 3, 4, 7
     */
    static double calculateXS(int funcNum, ArrayList<Double> x) {
        switch (funcNum) {
            case 1:
                return rastrigin(x);
            case 3:
                return sphere(x);
            case 4:
                return rosenbrock(x);
            case 7:
                return styblinskiTang(x);
            default:
                return Double.NaN;
        }
    }

    /**
     * for function 2, 5, 6, 8, 9, 10
     */
    static double calculateXY(int funcNum, double x, double y) {
        switch (funcNum) {
            case 2:
                return ackley(x, y);
            case 5:
                return beale(x, y);
            case 6:
                return schafferN4(x, y);
            case 8:
                return bukinN6(x, y);
            case 9:
                return himmelblaus(x, y);
            case 10:
                return crossInTray(x, y);
            default:
                return Double.NaN;
        }
    }
}
