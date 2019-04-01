package particle;

import java.util.ArrayList;
import java.util.Random;

import static particle.Function.*;

/**
 * 适用于多元自变量的粒子类
 */
class ParticleS {
    /**
     * 粒子位置
     */
    ArrayList<Double> x = new ArrayList<>();
    double fx;

    /**
     * 粒子速度
     */
    ArrayList<Double> v = new ArrayList<>();

    /**
     * 个体最优
     */
    ArrayList<Double> pBestX = new ArrayList<>();
    double pBestFx;

    /**
     * 初始化粒子
     *
     * @param fn  计算用函数编号
     * @param dim 计算维度
     * @param lb  xi的下界
     * @param ub  xi的上界
     */
    ParticleS(int fn, int dim, double lb, double ub) {
        for (int i = 0; i < dim; i++) {
            x.add(new Random().nextDouble() * (ub - lb) + lb);
            v.add((new Random().nextDouble() * (ub - lb) + lb) * 0.2);
            pBestX.add(new Random().nextDouble() * (ub - lb) + lb);
        }
        fx = calculateS(fn, x);
        pBestFx = calculateS(fn, pBestX);
    }
}
