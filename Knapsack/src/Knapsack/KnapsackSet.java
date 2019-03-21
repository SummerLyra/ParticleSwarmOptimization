package Knapsack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class KnapsackSet
{
    private int amount; //物品数量
    private int limitWeight; //背包容量
    private ArrayList<Integer> weight = new ArrayList<>(); //物品重量
    private ArrayList<Integer> value = new ArrayList<>(); //物品价值

    private int gBestValue; //全局最优价值
    private ArrayList<Double> gBestX = new ArrayList<>(); //全局最优位置

    private ArrayList<Knapsack> kSet = new ArrayList<>(); //粒子集

    //初始化粒子集，particleNum为粒子数量
    public KnapsackSet(String fileName, int particleNum)
    {
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String line = in.readLine();
            amount = Integer.parseInt(line);

            in.readLine();
            line = in.readLine();
            limitWeight = Integer.parseInt(line);

            in.readLine();
            for (int i = 0; i < amount; i++)
            {
                line = in.readLine();
                if (!line.equals(""))
                {
                    weight.add(Integer.parseInt(line));
                }
            }

            in.readLine();
            for (int i = 0; i < amount; i++)
            {
                line = in.readLine();
                if (!line.equals(""))
                {
                    value.add(Integer.parseInt(line));
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        gBestValue = 0;
        for (int i = 0; i < amount; i++)
        {
            gBestX.add(0.0);
        }

        for (int i = 0; i < particleNum; i++)
        {
            kSet.add(new Knapsack(amount));
        }
    }

    //计算粒子价值
    private void evaluate()
    {
        for (Knapsack k : kSet)
        {
            for (int i = 0; i < amount; i++)
            {
                //限制粒子的位置范围
                if (k.x.get(i) < 0.0)
                {
                    k.x.set(i, 0.0);
                }
                if (k.x.get(i) >= 1.0)
                {
                    k.x.set(i, 1.0);
                }

                //是否装入背包
                if (k.x.get(i) >= 0.5)
                {
                    k.totalWeight += weight.get(i);
                    k.totalValue += value.get(i);
                }
            }

            //超过背包容量
            if (k.totalWeight > limitWeight)
            {
                k.totalValue = 0;
            }
        }
    }

    //判断粒子价值是否为个体或全局最优
    private void findBetter()
    {
        for (Knapsack k : kSet)
        {
            //全局最优
            if (k.totalValue > gBestValue)
            {
                for (int i = 0; i < amount; i++)
                {
                    gBestX.set(i, k.x.get(i));
                    k.pBestX.set(i, k.x.get(i));
                }
                gBestValue = k.totalValue;
                k.pBestValue = k.totalValue;
            }

            //非全局最优的个体最优
            else if (k.totalValue > k.pBestValue)
            {
                for (int i = 0; i < amount; i++)
                {
                    k.pBestX.set(i, k.x.get(i));
                }
                k.pBestValue = k.totalValue;
            }
        }
    }

    //更新粒子速度和位置
    private void update()
    {
        for (Knapsack k : kSet)
        {
            for (int i = 0; i < amount; i++)
            {
                final double w = 1; //惯性权重因子
                final double c1 = 2; //个体认知常数
                final double c2 = 2; //社会经验常数
                double r1 = new Random().nextDouble();
                double r2 = new Random().nextDouble();
                double vt = w * k.v.get(i) + c1 * r1 * (k.pBestX.get(i) - k.x.get(i)) + c2 * r2 * (gBestX.get(i) - k.x.get(i));
                double xt = k.x.get(i) + vt;
                k.v.set(i, vt);
                k.x.set(i, xt);
            }
        }
    }

    private void output()
    {
        int totalWeight = 0;
        for (int i = 0; i < amount; i++)
        {
            if (gBestX.get(i) >= 0.5)
            {
                int index = i + 1;
                totalWeight += weight.get(i);
                System.out.println("No." + index + " weight = " + weight.get(i) + " value = " + value.get(i));
            }
        }
        System.out.println("limit weight = " + limitWeight);
        System.out.println("total weight = " + totalWeight);
        System.out.println("total value = " + gBestValue);
    }

    //迭代计算，iterTime为迭代次数
    public void iterate(int iterTime)
    {
        for (int i = 0; i < iterTime; i++)
        {
            evaluate();
            findBetter();
            update();
        }
        output();
    }
}
