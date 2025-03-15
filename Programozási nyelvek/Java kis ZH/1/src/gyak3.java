public class gyak3 {
    public static void main(String[] args) throws InterruptedException {
        int threadsCount = 4; // Number of threads
        Resource resource = new Resource(threadsCount);

        // Initialize the array with some values
        for (int i = 0; i < resource.array.length; i++) {
            resource.array[i] = i + 1;
        }

        Calculator[] calculators = new Calculator[threadsCount];
        int chunkSize = resource.array.length / threadsCount; // 100

        // Create and start Calculator threads
        for (int i = 0; i < threadsCount; i++) { // 0, 1, 2, 3
            int start = i * chunkSize;
            int end = (i == threadsCount - 1) ? resource.array.length : (i + 1) * chunkSize;
            calculators[i] = new Calculator(resource, start, end);
            calculators[i].start();
        }

        // Wait for all threads to finish
        for (Calculator calculator : calculators) {
            calculator.join();
        }

        // Calculate the total sum
        long totalSum = 0;
        for (Calculator calculator : calculators) {
            totalSum += calculator.sum;
        }

        System.out.println("Total sum: " + totalSum);
    }
}

class Resource {
//    int threadsCount;
    int[] array;
    Resource(int threadsCount) {
//        this.threadsCount = threadsCount;
        array = new int[threadsCount*100];
    }
}

class Calculator extends Thread {
    Resource resource;
    int start;
    int end;
    long sum;
    Calculator(Resource resource, int start, int end) {
        this.resource = resource;
        this.start = start;
        this.end = end;

    }

    @Override
    public void run() {
        for (int i = start; i < end; ++i) {
            sum+=resource.array[i];
        }
    }
}