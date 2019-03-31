package Particle;

import java.util.ArrayList;
import java.util.Random;

import static Particle.Function.*;

public class ParticleXSSet
{
    private int funcNum; //函数编号
    private int dim; //x维度
    private double lowerBound; //x下界
    private double upperBound; //x上界

    //全局最优
    private ArrayList<Double> gBestX = new ArrayList<>();
    private double gBestFx;

    private ArrayList<ParticleXS> pSet = new ArrayList<>(); //粒子集

    //初始化粒子集，particleNum为粒子数量，initX为迭代初始数组
    public ParticleXSSet(int fn, int particleNum, double lb, double ub, ArrayList<Double> initXA)
    {
        funcNum = fn;
        dim = initXA.size();
        lowerBound = lb;
        upperBound = ub;

        for (int i = 0; i < dim; i++)
        {
            gBestX.add(initXA.get(i));
        }
        gBestFx = calculateXS(funcNum, initXA);

        for (int i = 0; i < particleNum; i++)
        {
            pSet.add(new ParticleXS(funcNum, dim, lowerBound, upperBound));
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
                if (p.x.get(i) < lowerBound)
                {
                    p.x.set(i, lowerBound);
                }
                if (p.x.get(i) > upperBound)
                {
                    p.x.set(i, upperBound);
                }
            }

            p.fx = calculateXS(funcNum, p.x);
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
                double r1 = new Random().nextDouble();
                double r2 = new Random().nextDouble();
                double vt = w * p.v.get(i) + c1 * r1 * (p.pBestX.get(i) - p.x.get(i)) + c2 * r2 * (gBestX.get(i) - p.x.get(i));
                double xt = p.x.get(i) + vt;
                p.v.set(i, vt);
                p.x.set(i, xt);
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
            System.out.print(i + " ");
            output();
        }
    }
}
