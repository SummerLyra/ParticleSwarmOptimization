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
    ArrayList<Double> vx = new ArrayList<>();

    //个体最优
    ArrayList<Double> pBestX = new ArrayList<>();
    double pBestFx;

    //xi∈[d,u)
    ParticleXS(int dim, double d, double u)
    {
        for (int i = 0; i < dim; i++)
        {
            x.add(new Random().nextDouble() * (u - d) + d);
            vx.add((new Random().nextDouble() * (u - d) + d) * 0.2);
            pBestX.add(new Random().nextDouble() * (u - d) + d);
        }
        fx = func1(x, 10, dim);
        pBestFx = func1(pBestX, 10, dim);
    }
}
