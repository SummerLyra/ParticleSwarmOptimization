package Particle;

import java.util.ArrayList;
import java.util.Random;

import static Particle.Function.*;

public class ParticleXYSet
{
    //全局最优
    private double gBestX;
    private double gBestY;
    private double gBestFx;

    private ArrayList<ParticleXY> pSet = new ArrayList<>(); //粒子集

    //初始化粒子集，particleNum为粒子数量
    public ParticleXYSet(int particleNum)
    {
        gBestX = 0.0;
        gBestY = 0.0;
        gBestFx = func5(gBestX, gBestY);

        for (int i = 0; i < particleNum; i++)
        {
            pSet.add(new ParticleXY());
        }
    }

    //计算粒子适应值
    private void evaluate()
    {
        for (ParticleXY p : pSet)
        {
            //限制粒子的位置范围
            if (p.x < -4.5)
            {
                p.x = -4.5;
            }
            if (p.x > 4.5)
            {
                p.x = 4.5;
            }
            if (p.y < -4.5)
            {
                p.y = -4.5;
            }
            if (p.y > 4.5)
            {
                p.y = 4.5;
            }

            p.fx = func5(p.x, p.y);
        }
    }

    //判断粒子价值是否为个体或全局最优
    private void findBetter()
    {
        for (ParticleXY p : pSet)
        {
            //全局最优
            if (p.fx < gBestFx)
            {
                gBestX = p.x;
                gBestY = p.y;
                gBestFx = p.fx;
                p.pBestX = p.x;
                p.pBestY = p.y;
                p.pBestFx = p.fx;
            }

            //非全局最优的个体最优
            else if (p.fx < p.pBestFx)
            {
                p.pBestX = p.x;
                p.pBestY = p.y;
                p.pBestFx = p.fx;
            }
        }
    }

    //更新粒子速度和位置
    private void update()
    {
        for (ParticleXY p : pSet)
        {
            final double w = 1; //惯性权重因子
            final double c1 = 2; //个体认知常数
            final double c2 = 2; //社会经验常数
            double r1 = new Random().nextDouble();
            double r2 = new Random().nextDouble();
            p.vx = w * p.vx + c1 * r1 * (p.pBestX - p.x) + c2 * r2 * (gBestX - p.x);
            p.vy = w * p.vy + c1 * r1 * (p.pBestY - p.y) + c2 * r2 * (gBestY - p.y);
            p.x += p.vx;
            p.y += p.vy;
        }
    }

    private void output()
    {
        System.out.println("x = " + gBestX + " y = " + gBestY + " f(x,y) = " + gBestFx);
    }

    //迭代计算，iterTime为迭代次数
    public void iterate(int iterTime)
    {
        for (int i = 0; i < iterTime; i++)
        {
            evaluate();
            findBetter();
            update();
            output();
        }
    }
}
