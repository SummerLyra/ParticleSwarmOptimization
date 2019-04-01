package Knapsack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class KnapsackSet {
    /**
     * 惯性权重因子
     */
    private final double W = 1.0;

    /**
     * 个体认知常数
     */
    private final double C1 = 2.0;

    /**
     * 社会经验常数
     */
    private final double C2 = 2.0;

    /**
     * 可选物品总数
     */
    private int amount;

    /**
     * 背包最大容量
     */
    private int limitWeight;

    /**
     * 每件物品重量
     */
    private ArrayList<Integer> weight = new ArrayList<>();

    /**
     * 每件物品价值
     */
    private ArrayList<Integer> value = new ArrayList<>();

    /**
     * 全局最优价值
     **/
    private int gBestValue;

    /**
     * 全局最优位置
     **/
    private ArrayList<Double> gBestX = new ArrayList<>();

    /**
     * 粒子集
     */
    private ArrayList<Knapsack> kSet = new ArrayList<>();

    /**
     * 初始化粒子集
     *
     * @param fileName    数据文件名
     * @param particleNum 粒子数量
     */
    public KnapsackSet(String fileName, int particleNum) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            // 读取可选物品总数
            String line = in.readLine();
            amount = Integer.parseInt(line);

            in.readLine();
            // 读取背包最大容量
            line = in.readLine();
            limitWeight = Integer.parseInt(line);

            in.readLine();
            // 读取每件物品重量
            for (int i = 0; i < amount; i++) {
                line = in.readLine();
                if (!"".equals(line)) {
                    weight.add(Integer.parseInt(line));
                }
            }

            in.readLine();
            // 读取每件物品价值
            for (int i = 0; i < amount; i++) {
                line = in.readLine();
                if (!"".equals(line)) {
                    value.add(Integer.parseInt(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        gBestValue = 0;
        for (int i = 0; i < amount; i++) {
            gBestX.add(0.0);
        }

        for (int i = 0; i < particleNum; i++) {
            kSet.add(new Knapsack(amount));
        }
    }

    /**
     * 计算粒子价值
     */
    private void evaluate() {
        for (Knapsack k : kSet) {
            k.totalWeight = 0;
            k.totalValue = 0;

            for (int i = 0; i < amount; i++) {
                // 限制粒子的位置范围
                if (k.x.get(i) < 0.0) {
                    k.x.set(i, 0.0);
                }
                if (k.x.get(i) >= 1.0) {
                    k.x.set(i, 1.0);
                }

                // 以0.5为界决定是否装入背包
                if (k.x.get(i) >= 0.5) {
                    k.totalWeight += weight.get(i);
                    k.totalValue += value.get(i);
                }
            }

            // 超过背包容量
            if (k.totalWeight > limitWeight) {
                k.totalValue = 0;
            }
        }
    }

    /**
     * 判断粒子价值是否为个体或全局最优
     */
    private void findBetter() {
        for (Knapsack k : kSet) {
            // 全局最优
            if (k.totalValue > gBestValue) {
                for (int i = 0; i < amount; i++) {
                    gBestX.set(i, k.x.get(i));
                    k.pBestX.set(i, k.x.get(i));
                }
                gBestValue = k.totalValue;
                k.pBestValue = k.totalValue;
            }

            // 非全局最优的个体最优
            else if (k.totalValue > k.pBestValue) {
                for (int i = 0; i < amount; i++) {
                    k.pBestX.set(i, k.x.get(i));
                }
                k.pBestValue = k.totalValue;
            }
        }
    }

    /**
     * 更新粒子速度和位置
     */
    private void update() {
        for (Knapsack k : kSet) {
            for (int i = 0; i < amount; i++) {
                double r1 = new Random().nextDouble();
                double r2 = new Random().nextDouble();
                double vt = W * k.v.get(i) + C1 * r1 * (k.pBestX.get(i) - k.x.get(i)) + C2 * r2 * (gBestX.get(i) - k.x.get(i));
                double xt = k.x.get(i) + vt;
                k.v.set(i, vt);
                k.x.set(i, xt);
            }
        }
    }

    /**
     * 输出
     */
    private void output() {
        int totalWeight = 0;
        for (int i = 0; i < amount; i++) {
            if (gBestX.get(i) >= 0.5) {
                int index = i + 1;
                totalWeight += weight.get(i);
                System.out.println("No." + index + " weight = " + weight.get(i) + " value = " + value.get(i));
            }
        }
        System.out.println("limit weight = " + limitWeight);
        System.out.println("total weight = " + totalWeight);
        System.out.println("total value = " + gBestValue);
    }

    /**
     * 迭代计算
     *
     * @param iterTime 迭代次数
     */
    public void iterate(int iterTime) {
        for (int i = 0; i < iterTime; i++) {
            evaluate();
            findBetter();
            update();
        }
        output();
    }
}
