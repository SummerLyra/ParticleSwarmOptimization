package Particle;

import java.util.ArrayList;
import java.util.Random;

import static Particle.Function.*;

public class ParticleXSSet
{
    private int dim; //维度

    //全局最优
    private ArrayList<Double> gBestX = new ArrayList<>();
    private double gBestFx;

    private ArrayList<ParticleXS> pSet = new ArrayList<>(); //粒子集

    //初始化粒子集，particleNum为粒子数量
    public ParticleXSSet(int particleNum, int dm, double dw, double up)
    {
        dim = dm;

        for (int i = 0; i < dim; i++)
        {
            gBestX.add(new Random().nextDouble() * 0.1 - 0.05);

        }
        gBestFx = func1(gBestX, 10, dim);

        for (int i = 0; i < particleNum; i++)
        {
            pSet.add(new ParticleXS(dim, dw, up));
        }
    }

    //计算粒子适应值
    private void evaluate()
    {
        for (ParticleXS p : pSet)
        {
            //限制粒子的位置范围
            for (int i = 0; i < dim; i++)
            {
                if (p.x.get(i) < -5.12)
                {
                    p.x.set(i, -5.12);
                }
                if (p.x.get(i) > 5.12)
                {
                    p.x.set(i, 5.12);
                }
            }

            p.fx = func1(p.x, 10, dim);
        }
    }

    //判断粒子价值是否为个体或全局最优
    private void findBetter()
    {
        for (ParticleXS p : pSet)
        {
            //全局最优
            if (p.fx < gBestFx)
            {
                for (int i = 0; i < dim; i++)
                {
                    gBestX.set(i, p.x.get(i));
                    p.pBestX.set(i, p.x.get(i));
                }
                gBestFx = p.fx;
                p.pBestFx = p.fx;
            }

            //非全局最优的个体最优
            else if (p.fx < p.pBestFx)
            {
                for (int i = 0; i < dim; i++)
                {
                    p.pBestX.set(i, p.x.get(i));
                }
                p.pBestFx = p.fx;
            }
        }
    }

    //更新粒子速度和位置
    private void update()
    {
        for (ParticleXS p : pSet)
        {
            for (int i = 0; i < dim; i++)
            {
                final double w = 1; //惯性权重因子
                final double c1 = 2; //个体认知常数
                final double c2 = 2; //社会经验常数
                double r1 = new Random().nextDouble();
                double r2 = new Random().nextDouble();
                double vt = w * p.vx.get(i) + c1 * r1 * (p.pBestX.get(i) - p.x.get(i)) + c2 * r2 * (gBestX.get(i) - p.x.get(i));
                double xt = p.x.get(i) + vt;
                p.vx.set(i, vt);
                p.x.set(i, vt);
            }
        }
    }

    private void output()
    {
        for (int i = 0; i < dim; i++)
        {
            int index = i + 1;
            System.out.print("x" + index + " = " + gBestX.get(i) + " ");
        }
        System.out.println("f(x) = " + gBestFx);
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
