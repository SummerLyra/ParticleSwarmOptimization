import knapsack.KnapsackSet;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please choose a question to solve(1, 2, 3 or 4):");
        String fileName = new Scanner(System.in).next();
        KnapsackSet ks = new KnapsackSet(fileName, 100);
        ks.iterate(1000);
    }
}
