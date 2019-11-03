package com.company.multithread2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final int THREADS_NUM = 20;
    private static final int LIST_SIZE = 1000000;
    private static final int ELEMENTS_PER_THREAD = LIST_SIZE / THREADS_NUM;
    private static final int NUMBERS_RANGE = 10;
    private static List<Integer> listOFIntegers = new ArrayList<>();

    private volatile static int sum = 0;

    public synchronized static void addToSum(int increment) {
        Main.sum += increment;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < LIST_SIZE; i++) {
            listOFIntegers.add((int) (Math.random() * NUMBERS_RANGE));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_NUM);
        int bottom = 0;
        int top = ELEMENTS_PER_THREAD;
        while (top <= LIST_SIZE) {
            executorService.submit(new CountNumbersSumFromList(listOFIntegers
                    .subList(bottom, top)));
            bottom = top;
            top += ELEMENTS_PER_THREAD;
        }
        executorService.shutdown();
        Thread.sleep(200);
        System.out.println("Total sum = " + sum);
        System.out.println();
        System.out.println();

        sum = 0;
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREADS_NUM);
        bottom = 0;
        top = ELEMENTS_PER_THREAD;
        while (top <= LIST_SIZE) {
            forkJoinPool.submit(new CountNumbersSumFromListRecursiveAction(listOFIntegers
                    .subList(bottom, top)));
            bottom = top;
            top += ELEMENTS_PER_THREAD;
        }
        Thread.sleep(200);
        System.out.println("Total sum = " + sum);
    }
}
