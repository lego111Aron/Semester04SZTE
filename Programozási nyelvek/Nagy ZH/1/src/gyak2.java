import java.util.Random;
import java.util.SimpleTimeZone;

public class gyak2 {

    public static void main(String[] args) throws InterruptedException {
        final int MIN = 400;
        final int MAX = 400;
        final int threadCount = 4;

        Random random = new Random();
        int size = random.nextInt((MAX-MIN)+1)+MIN;
        Resource resource = new Resource(size);
//        resource.array = random.ints(size).toArray();
        for (int i = 0; i < size; i++) {
            resource.array[i] = i + 1;
        }

        Calculator[] calculators = new Calculator[threadCount];
        final int chunksize = size / threadCount;
        for (int i = 0; i < threadCount; i++){
            int start = i * chunksize;
            int end = (i == threadCount-1) ? size : (i+1) * chunksize;
            calculators[i] = new Calculator(resource, start, end);
            calculators[i].start();
        }

        long totalSum = 0;
        for (Calculator calculator:calculators) {
            calculator.join();
            totalSum+= calculator.sum;
            System.out.println(calculator.sum);
        }

        System.out.println("-----------------");
        System.out.println(totalSum);
    }
}

class Resource {
//    private final int threadCount;
    int[] array;
    Resource(int size) {
//        this.threadCount = threadCount;
        array = new int[size];
    }
}

class Calculator extends Thread {
    private Resource resource;
    private int begin;
    private int end;
    int sum = 0;
    Calculator(Resource resource, int begin, int end) {
        this.resource = resource;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = begin; i < end; i++) {
            sum += resource.array[i];
        }
    }
}