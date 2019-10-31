package com.company.multithread2;

import java.util.List;
import java.util.concurrent.Callable;

public class CountNumbersSumFromList implements Runnable {
        private List<Integer> listOfNumbers;

    public CountNumbersSumFromList(List<Integer> listOfNumbers) {
        this.listOfNumbers = listOfNumbers;
    }

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + " started...");
        int sum = listOfNumbers.stream().mapToInt(x -> x).sum();
        System.out.println("Result of thread (" + Thread.currentThread().getName() + ") is: " + sum);
        Main.addToSum(sum);
    }
}
