import Particle.*;

import java.util.ArrayList;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        ParticleXYSet pys = new ParticleXYSet(100000, -4.5, 4.5, -4.5, 4.5, -2.0, 1.0);
        pys.iterate(10);

        System.out.println();

        ArrayList<Double> initX = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            initX.add(new Random().nextDouble() * 0.2 - 0.1);
        }
        ParticleXSSet pss = new ParticleXSSet(100000, 10, -5.12, 5.12, initX);
        pss.iterate(10);
    }
}
