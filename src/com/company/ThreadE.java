package com.company;

public class ThreadE extends Thread {
    private Resource resource;

    public ThreadE(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (resource.getCounter() < 100) {
            resource.incrementCounter();
            System.out.println(resource.getCounter() + " " + Thread.currentThread().getName());
        }
    }
}
