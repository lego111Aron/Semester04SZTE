import java.io.Console;

public class HelloWorld {

    public static void main(String[] args) {
        Thread thread = new ExampleThread();
        Thread runnable = new Thread(new ExampleRunnable());

//        System.out.println(Runtime.getRuntime().availableProcessors());
        thread.start();
        runnable.start();
    }
}

class ExampleThread extends Thread {
    @Override
    public void run() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Hello World - Thread");
    }
}

class ExampleRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello World - Runnable");
    }
}

