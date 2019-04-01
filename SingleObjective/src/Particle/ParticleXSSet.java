package Particle;

import java.util.ArrayList;
import java.util.Random;

import static Particle.Function.*;

/**
 * 适用于多元自变量的粒子集类
 */
public class ParticleXSSet {
    /**
     * 计算用函数编号
     */
    private int funcNum;

    /**
     * 计算维度
     */
    private int dim;

    /**
     * xi∈[lowerBound,upperBound)
     */
    private double lowerBound;
    private double upperBound;

    /**
     * 全局最优
     */
    private ArrayList<Double> gBestX = new ArrayList<>();
    private double gBestFx;

    /**
     * 粒子集
     */
    private ArrayList<ParticleXS> pSet = new ArrayList<>();

    /**
     * 初始化粒子集
     * xi∈[lb,ub)
     *
     * @param fn          计算用函数编号
     * @param particleNum 粒子数量
     * @param initXS      迭代初始x数组
     */
    public ParticleXSSet(int fn, int particleNum, double lb, double ub, ArrayList<Double> initXS) {
        funcNum = fn;
        dim = initXS.size();
        lowerBound = lb;
        upperBound = ub;

        for (int i = 0; i < dim; i++) {
            gBestX.add(initXS.get(i));
        }
        gBestFx = calculateXS(funcNum, initXS);

        for (int i = 0; i < particleNum; i++) {
            pSet.add(new ParticleXS(funcNum, dim, lowerBound, upperBound));
        }
    }

    /**
     * 计算粒子适应值
     */
    private void evaluate() {
        for (ParticleXS p : pSet) {
            // 限制粒子的位置范围
            for (int i = 0; i < dim; i++) {
                if (p.x.get(i) < lowerBound) {
                    p.x.set(i, lowerBound);
                }
                if (p.x.get(i) > upperBound) {
                    p.x.set(i, upperBound);
                }
            }

            p.fx = calculateXS(funcNum, p.x);
        }
    }

    /**
     * 判断粒子价值是否为个体或全局最优
     */
    private void findBetter() {
        for (ParticleXS p : pSet) {
            // 全局最优
            if (p.fx < gBestFx) {
                for (int i = 0; i < dim; i++) {
                    gBestX.set(i, p.x.get(i));
                    p.pBestX.set(i, p.x.get(i));
                }
                gBestFx = p.fx;
                p.pBestFx = p.fx;
            }

            // 非全局最优的个体最优
            else if (p.fx < p.pBestFx) {
                for (int i = 0; i < dim; i++) {
                    p.pBestX.set(i, p.x.get(i));
                }
                p.pBestFx = p.fx;
            }
        }
    }

    /**
     * 更新粒子速度和位置
     */
    private void update() {
        for (ParticleXS p : pSet) {
            for (int i = 0; i < dim; i++) {
                double r1 = new Random().nextDouble();
                double r2 = new Random().nextDouble();
                double vt = W * p.v.get(i) + C1 * r1 * (p.pBestX.get(i) - p.x.get(i)) + C2 * r2 * (gBestX.get(i) - p.x.get(i));
                double xt = p.x.get(i) + vt;
                p.v.set(i, vt);
                p.x.set(i, xt);
            }
        }
    }

    /**
     * 输出
     */
    private void output() {
        for (int i = 0; i < dim; i++) {
            int index = i + 1;
            System.out.print("x" + index + " = " + gBestX.get(i) + " ");
        }
        System.out.println("f(x) = " + gBestFx);
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
            System.out.print(i + " ");
            output();
        }
    }
}
