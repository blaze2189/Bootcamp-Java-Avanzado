package org.benek.bootcamp.virtualthreads;

import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        startVirtualThreads();
    }

    public static void startVirtualThreads() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 1_000_000; i++) {
                executor.submit(() -> {
                    try {
                        Thread.sleep(10000);
                        System.out.println("Thread: " + Thread.currentThread());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }
}
