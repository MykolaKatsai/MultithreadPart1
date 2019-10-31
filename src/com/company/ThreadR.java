package com.company;

public class ThreadR implements Runnable {
    private Resource resource;

    public ThreadR(Resource resource) {
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
