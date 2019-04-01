package particle;

import java.util.ArrayList;
import java.util.Random;

import static particle.Function.*;

/**
 * 适用于多元自变量的粒子集类
 */
public class ParticleSset {
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
    private ArrayList<ParticleS> pSet = new ArrayList<>();

    /**
     * 初始化粒子集
     *
     * @param fn          计算用函数编号
     * @param particleNum 粒子数量
     * @param lb          xi的下界
     * @param ub          xi的上界
     * @param initS      迭代初始x数组
     */
    public ParticleSset(int fn, int particleNum, double lb, double ub, ArrayList<Double> initS) {
        funcNum = fn;
        dim = initS.size();
        lowerBound = lb;
        upperBound = ub;

        for (int i = 0; i < dim; i++) {
            gBestX.add(initS.get(i));
        }
        gBestFx = calculateS(funcNum, initS);

        for (int i = 0; i < particleNum; i++) {
            pSet.add(new ParticleS(funcNum, dim, lowerBound, upperBound));
        }
    }

    /**
     * 计算粒子适应值
     */
    private void evaluate() {
        for (ParticleS p : pSet) {
            // 限制粒子的位置范围
            for (int i = 0; i < dim; i++) {
                if (p.x.get(i) < lowerBound) {
                    p.x.set(i, lowerBound);
                }
                if (p.x.get(i) > upperBound) {
                    p.x.set(i, upperBound);
                }
            }

            p.fx = calculateS(funcNum, p.x);
        }
    }

    /**
     * 判断粒子价值是否为个体或全局最优
     */
    private void findBetter() {
        for (ParticleS p : pSet) {
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
        for (ParticleS p : pSet) {
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
