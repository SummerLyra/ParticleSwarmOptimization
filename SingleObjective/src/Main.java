import Particle.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        double initX = 0.0, initY = 0.0;
        ArrayList<Double> initXS = new ArrayList<>();
        ParticleXYSet pys;
        ParticleXSSet pss;

        System.out.println("Please choose a function to calculate:");
        System.out.println("1  Rastrigin function");
        System.out.println("2  Ackley function");
        System.out.println("3  Sphere function");
        System.out.println("4  Rosenbrock function");
        System.out.println("5  Beale function");
        System.out.println("6  Schaffer function N.4");
        System.out.println("7  Styblinski-Tang function");
        System.out.println("8  Bukin function N.6");
        System.out.println("9  Himmelblau's function");
        System.out.println("10 Cross-in-tray function");
        int funcNum = new Scanner(System.in).nextInt();
        if (funcNum < 1 | funcNum > 10)
        {
            System.exit(-1);
        }

        //粒子集大小
        System.out.println("Please input the quantity of particles:");
        int particleNum = new Scanner(System.in).nextInt();

        //迭代次数
        System.out.println("Please input the times of iteration:");
        int iterTime = new Scanner(System.in).nextInt();

        //for function 1, 3, 4, 7
        if (funcNum == 1 | funcNum == 3 | funcNum == 4 | funcNum == 7)
        {
            int dim = 10;
            //for function 7
            if (funcNum == 7)
            {
                System.out.println("Please input the dimension of x0 array:");
                dim = new Scanner(System.in).nextInt();
            }

            System.out.println("Please input x0:");
            double x0 = new Scanner(System.in).nextDouble();
            for (int i = 0; i < dim; i++)
            {
                initXS.add(x0);
            }
        }
        //for function 2, 5, 6, 8, 9, 10
        else
        {
            System.out.println("Please input x0:");
            initX = new Scanner(System.in).nextDouble();

            System.out.println("Please input y0:");
            initY = new Scanner(System.in).nextDouble();
        }

        switch (funcNum)
        {
            case 1:
                pss = new ParticleXSSet(1, particleNum, -5.12, 5.12, initXS);
                pss.iterate(iterTime);
                break;
            case 2:
                pys = new ParticleXYSet(2, particleNum, -5.0, 5.0, -5.0, 5.0, initX, initY);
                pys.iterate(iterTime);
                break;
            case 3:
                pss = new ParticleXSSet(3, particleNum, -100.0, 100.0, initXS);
                pss.iterate(iterTime);
                break;
            case 4:
                pss = new ParticleXSSet(4, particleNum, -30.0, 30.0, initXS);
                pss.iterate(iterTime);
                break;
            case 5:
                pys = new ParticleXYSet(5, particleNum, -4.5, 4.5, -4.5, 4.5, initX, initY);
                pys.iterate(iterTime);
                break;
            case 6:
                pys = new ParticleXYSet(6, particleNum, -100.0, 100.0, -100.0, 100.0, initX, initY);
                pys.iterate(iterTime);
                break;
            case 7:
                pss = new ParticleXSSet(7, particleNum, -5.0, 5.0, initXS);
                pss.iterate(iterTime);
                break;
            case 8:
                pys = new ParticleXYSet(8, particleNum, -15.0, -5.0, -3.0, 3.0, initX, initY);
                pys.iterate(iterTime);
                break;
            case 9:
                pys = new ParticleXYSet(9, particleNum, -5.0, 5.0, -5.0, 5.0, initX, initY);
                pys.iterate(iterTime);
                break;
            case 10:
                pys = new ParticleXYSet(10, particleNum, -10.0, 10.0, -10.0, 10.0, initX, initY);
                pys.iterate(iterTime);
                break;
        }
    }
}
