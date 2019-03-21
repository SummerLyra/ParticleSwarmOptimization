import Particle.*;

public class Main
{
    public static void main(String[] args)
    {
        ParticleXYSet pys = new ParticleXYSet(1000, -4.5, 4.5, -4.5, 4.5);
        pys.iterate(10);
        System.out.println();
        ParticleXSSet pss = new ParticleXSSet(1000, 10, -5.12, 5.12);
        pss.iterate(10);
    }
}
