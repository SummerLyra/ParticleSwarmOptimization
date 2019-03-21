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

    ParticleXY()
    {
        x = new Random().nextDouble() * 9 - 4.5;
        y = new Random().nextDouble() * 9 - 4.5;
        fx = func5(x, y);

        vx = (new Random().nextDouble() * 9 - 4.5) * 0.2;
        vy = (new Random().nextDouble() * 9 - 4.5) * 0.2;

        pBestX = 0.0;
        pBestY = 0.0;
        pBestFx = func5(pBestX, pBestY);
    }
}
