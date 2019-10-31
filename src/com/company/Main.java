package com.company;

public class Main {

    public static void main(String[] args) {
        Resource resource = new Resource();

        Thread thread1 = new Thread(new ThreadR(resource), "ThreadR");
        ThreadE thread2 = new ThreadE(resource);
        thread2.setName("ThreadE");

        thread1.start();
        thread2.start();
    }
}
