import Knapsack.KnapsackSet;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        String fileName = new Scanner(System.in).next();
        KnapsackSet ks = new KnapsackSet(fileName, 1000);
        ks.iterate(10);
    }
}
