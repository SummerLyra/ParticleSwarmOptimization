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

    //x∈[dx,ux),y∈[dy,uy)
    ParticleXY(double dx, double ux, double dy, double uy)
    {
        x = new Random().nextDouble() * (ux - dx) + dx;
        y = new Random().nextDouble() * (uy - dy) + dy;
        fx = func5(x, y);

        vx = (new Random().nextDouble() * (ux - dx) + dx) * 0.2;
        vy = (new Random().nextDouble() * (uy - dy) + dy) * 0.2;

        pBestX = 0.0;
        pBestY = 0.0;
        pBestFx = func5(pBestX, pBestY);
    }
}
