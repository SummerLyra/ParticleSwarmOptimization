package Knapsack;

import java.util.ArrayList;
import java.util.Random;

class Knapsack
{

    int totalWeight; //背包总重量
    int totalValue; //背包总价值

    ArrayList<Double> x = new ArrayList<>(); //粒子位置
    ArrayList<Double> v = new ArrayList<>(); //粒子速度

    int pBestValue; //个体最优价值
    ArrayList<Double> pBestX = new ArrayList<>(); //个体最优位置

    Knapsack(int amount)
    {
        totalWeight = 0;
        totalValue = 0;

        pBestValue = 0;

        for (int i = 0; i < amount; i++)
        {
            //位置为[0,1)范围内的浮点数，趋向0时丢弃的可能性增大，反之装入背包的可能性增大
            x.add(new Random().nextDouble());
            v.add(new Random().nextDouble() * 0.5);

            pBestX.add(0.0);
        }
    }
}
