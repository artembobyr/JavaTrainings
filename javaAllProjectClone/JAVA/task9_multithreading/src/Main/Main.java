package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Input first number");
        int firstNumber = sc.nextInt();
        System.out.println("Input last number");
        int lastNumber = sc.nextInt();
        System.out.println("Input count of threads");
        int countOfThreads = sc.nextInt();
        getThreads(firstNumber, lastNumber, countOfThreads);
    }

    public static void getThreads(int firstNumber, int lastNumber, int countOfThreads) throws InterruptedException {
        List<Integer> primeList = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();
        int rangeInEachThread = lastNumber / countOfThreads;

        for (int i = 0; i < countOfThreads; i++) {
            lastNumber = rangeInEachThread * (i + 1);
            Runnable r = new MyThread(firstNumber, lastNumber, primeList);
            threads.add(new Thread(r));
            threads.get(i).setName("THREAD " + (i + 1));
            firstNumber = rangeInEachThread * (i + 1) + 1;
            threads.get(i).start();
        }

        for (int i = 0; i < threads.size(); i++) {
            Thread.sleep(1000);
            threads.get(i).join();
        }
    }
}

