import java.util.concurrent.Semaphore;

public class DiningPhilosophers {
    public static void main(String[] args) {
        Semaphore s1  = new Semaphore(1);
        Semaphore s2  = new Semaphore(1);
        Semaphore s3  = new Semaphore(1);
        Semaphore s4  = new Semaphore(1);
        Semaphore s5  = new Semaphore(1);
        // Semaphore guard = new Semaphore(4);

        new Philosopher(s1, s2).start();
        new Philosopher(s2, s3).start();
        new Philosopher(s3, s4).start();
        new Philosopher(s4, s5).start();
        new Philosopher(s5, s1).start();
    }
}

class Philosopher extends Thread{
    Semaphore leftFork;
    Semaphore rightFork;

    public Philosopher(Semaphore leftFork, Semaphore rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        for(int i = 0; i < 5; i++) {
            eat();
        }
    }

    private void eat() {
        try {
            this.leftFork.acquire();
            System.out.println(Thread.currentThread().threadId() + " picked up its left fork");
            this.rightFork.acquire();
            System.out.println(Thread.currentThread().threadId() + " picked up its right fork");

            Thread.sleep(100);
            System.out.println(Thread.currentThread().threadId() + " nom-nom...");


            System.out.println(Thread.currentThread().threadId() + " put down its right fork");
            this.rightFork.release();
            System.out.println(Thread.currentThread().threadId() + " put down its left fork");
            this.leftFork.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
