package Particle;

import java.util.Random;

import static Particle.Function.*;

class ParticleXY
{
    //粒子位置
    double x;
    double y;
    double fx;

    //粒子速度
    double vx;
    double vy;

    //个体最优
    double pBestX;
    double pBestY;
    double pBestFx;

    //x∈[lx,ux),y∈[ly,uy)
    ParticleXY(double lx, double ux, double ly, double uy)
    {
        x = new Random().nextDouble() * (ux - lx) + lx;
        y = new Random().nextDouble() * (uy - ly) + ly;
        fx = func5(x, y);

        vx = (new Random().nextDouble() * (ux - lx) + lx) * 0.2;
        vy = (new Random().nextDouble() * (uy - ly) + ly) * 0.2;

        pBestX = new Random().nextDouble() * (ux - lx) + lx;
        pBestY = new Random().nextDouble() * (uy - ly) + ly;
        pBestFx = func5(pBestX, pBestY);
    }
}
