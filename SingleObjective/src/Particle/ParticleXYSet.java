package Particle;

import java.util.ArrayList;
import java.util.Random;

import static Particle.Function.*;

/**
 * 适用于二元自变量的粒子集类
 */
public class ParticleXYSet {
    /**
     * 计算用函数编号
     */
    private int funcNum;

    /**
     * x∈[lowerBoundX,upperBoundX)
     */
    private double lowerBoundX;
    private double upperBoundX;

    /**
     * y∈[lowerBoundY,upperBoundY)
     */
    private double lowerBoundY;
    private double upperBoundY;

    /**
     * 全局最优
     */
    private double gBestX;
    private double gBestY;
    private double gBestFx;

    /**
     * 粒子集
     */
    private ArrayList<ParticleXY> pSet = new ArrayList<>();

    /**
     * 初始化粒子集
     * x∈[lx,ux), y∈[ly,uy)
     *
     * @param fn          计算用函数编号
     * @param particleNum 粒子数量
     * @param initX       迭代初始x
     * @param initY       迭代初始y
     */
    public ParticleXYSet(int fn, int particleNum, double lx, double ux, double ly, double uy, double initX, double initY) {
        funcNum = fn;
        lowerBoundX = lx;
        upperBoundX = ux;
        lowerBoundY = ly;
        upperBoundY = uy;

        gBestX = initX;
        gBestY = initY;
        gBestFx = calculateXY(funcNum, gBestX, gBestY);

        for (int i = 0; i < particleNum; i++) {
            pSet.add(new ParticleXY(funcNum, lowerBoundX, upperBoundX, lowerBoundY, upperBoundY));
        }
    }

    /**
     * 计算粒子适应值
     */
    private void evaluate() {
        for (ParticleXY p : pSet) {
            // 限制粒子的位置范围
            if (p.x < lowerBoundX) {
                p.x = lowerBoundX;
            }
            if (p.x > upperBoundX) {
                p.x = upperBoundX;
            }
            if (p.y < lowerBoundY) {
                p.y = lowerBoundY;
            }
            if (p.y > upperBoundY) {
                p.y = upperBoundY;
            }

            p.fx = calculateXY(funcNum, p.x, p.y);
        }
    }

    /**
     * 判断粒子价值是否为个体或全局最优
     */
    private void findBetter() {
        for (ParticleXY p : pSet) {
            // 全局最优
            if (p.fx < gBestFx) {
                gBestX = p.x;
                gBestY = p.y;
                gBestFx = p.fx;
                p.pBestX = p.x;
                p.pBestY = p.y;
                p.pBestFx = p.fx;
            }

            // 非全局最优的个体最优
            else if (p.fx < p.pBestFx) {
                p.pBestX = p.x;
                p.pBestY = p.y;
                p.pBestFx = p.fx;
            }
        }
    }

    /**
     * 更新粒子速度和位置
     */
    private void update() {
        for (ParticleXY p : pSet) {
            double r1 = new Random().nextDouble();
            double r2 = new Random().nextDouble();
            p.vx = W * p.vx + C1 * r1 * (p.pBestX - p.x) + C2 * r2 * (gBestX - p.x);
            p.vy = W * p.vy + C1 * r1 * (p.pBestY - p.y) + C2 * r2 * (gBestY - p.y);
            p.x += p.vx;
            p.y += p.vy;
        }
    }

    /**
     * 输出
     */
    private void output() {
        System.out.println("x = " + gBestX + " y = " + gBestY + " f(x,y) = " + gBestFx);
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
