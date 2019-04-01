package particle;

import java.util.Random;

import static particle.Function.*;

/**
 * 适用于二元自变量的粒子类
 */
class ParticleY {
    /**
     * 粒子位置
     */
    double x;
    double y;
    double fx;

    /**
     * 粒子速度
     */
    double vx;
    double vy;

    /**
     * 个体最优
     */
    double pBestX;
    double pBestY;
    double pBestFx;

    /**
     * 初始化粒子
     *
     * @param fn 计算用函数编号
     * @param lx x的下界
     * @param ux x的上界
     * @param ly y的下界
     * @param uy y的上界
     */
    ParticleY(int fn, double lx, double ux, double ly, double uy) {
        x = new Random().nextDouble() * (ux - lx) + lx;
        y = new Random().nextDouble() * (uy - ly) + ly;
        fx = calculateY(fn, x, y);

        vx = (new Random().nextDouble() * (ux - lx) + lx) * 0.2;
        vy = (new Random().nextDouble() * (uy - ly) + ly) * 0.2;

        pBestX = new Random().nextDouble() * (ux - lx) + lx;
        pBestY = new Random().nextDouble() * (uy - ly) + ly;
        pBestFx = calculateY(fn, pBestX, pBestY);
    }
}
