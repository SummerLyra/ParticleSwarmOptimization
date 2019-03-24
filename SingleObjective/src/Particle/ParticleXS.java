package Particle;

import java.util.ArrayList;
import java.util.Random;

import static Particle.Function.*;

class ParticleXS
{
    //粒子位置
    ArrayList<Double> x = new ArrayList<>();
    double fx;

    //粒子速度
    ArrayList<Double> v = new ArrayList<>();

    //个体最优
    ArrayList<Double> pBestX = new ArrayList<>();
    double pBestFx;

    //xi∈[lb,ub)
    ParticleXS(int dim, double lb, double ub)
    {
        for (int i = 0; i < dim; i++)
        {
            x.add(new Random().nextDouble() * (ub - lb) + lb);
            v.add((new Random().nextDouble() * (ub - lb) + lb) * 0.2);
            pBestX.add(new Random().nextDouble() * (ub - lb) + lb);
        }
        fx = func1(x, 10, dim);
        pBestFx = func1(pBestX, 10, dim);
    }
}
