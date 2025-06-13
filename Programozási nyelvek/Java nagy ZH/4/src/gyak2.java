import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class gyak2 {
    public static void main(String[] args) {
        int numberOfThreads = 4;
        Resource resource = new Resource(numberOfThreads);
        Thread[] threads = new Thread[numberOfThreads];
        CyclicBarrier cyclicBarrier = new CyclicBarrier(numberOfThreads, new myRunnable(threads));

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Calculator(resource, i*100, (i+1)*100, cyclicBarrier);
            threads[i].start();
        }
    }
}

class Resource {
    private final int numberOfThreads;
    int[] array;
    Random random = new Random();
    Resource(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;

        array = new int[numberOfThreads*100];
        for (int i = 0; i < array.length; i++) {
//            array[i] = random.nextInt(100);
            array[i] = 1;
        }
    }
}

class Calculator extends Thread {
    private Resource resource;
    private int begin;
    private int end;
    private CyclicBarrier cyclicBarrier;
    int sum = 0;
    Calculator(Resource resource, int begin, int end, CyclicBarrier cyclicBarrier) {
        this.resource = resource;
        this.begin = begin;
        this.end = end;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        for (int i = begin; i < end; i++) {
            sum += resource.array[i];
        }

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}

class myRunnable implements Runnable {
    private Thread[] threads;
    int sumAll = 0;
    myRunnable(Thread[] threads) {
        this.threads = threads;
    }

    @Override
    public void run() {
        for (Thread thread:threads){
            Calculator calculator = (Calculator) thread;
            this.sumAll += calculator.sum;
        }

        System.out.println(sumAll);
    }
}


/*

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.CyclicBarrier;

class Resource {
    int[] array;

    Resource(int numberOfThreads) {
        array = new int[numberOfThreads * 100];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100); // vagy bármilyen más logika a tömb feltöltésére
        }
    }
}



class Calculator extends Thread {
    private Resource resource;
    private int begin;
    private int end;
    private CyclicBarrier cyclicBarrier;
    int sum = 0;

    Calculator(Resource resource, int begin, int end, CyclicBarrier cyclicBarrier) {
        this.resource = resource;
        this.begin = begin;
        this.end = end;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        for (int i = begin; i < end; i++) {
            sum += resource.array[i];
        }

        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}

class myRunnable implements Runnable {
    private Thread[] threads;
    int sumAll = 0;

    myRunnable(Thread[] threads) {
        this.threads = threads;
    }

    @Override
    public void run() {
        for (Thread thread : threads) {
            Calculator calculator = (Calculator) thread;
            this.sumAll += calculator.sum;
        }

        System.out.println("Összeg: " + sumAll);
    }
}

public class gyak2 {
    public static void main(String[] args) {
        int numberOfThreads = 4;
        Resource resource = new Resource(numberOfThreads);
        Thread[] threads = new Thread[numberOfThreads];
        CyclicBarrier cyclicBarrier = new CyclicBarrier(numberOfThreads, new myRunnable(threads));

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Calculator(resource, i * 100, (i + 1) * 100, cyclicBarrier);
            threads[i].start();
        }
    }
}*/
