public class gyak2 {
    public static void main(String[] args) throws InterruptedException {
        MyRunnable myRunnable = new MyRunnable(10);
        MyThread myThread = new MyThread(myRunnable);
        myThread.start();
    }
}

class MyRunnable implements Runnable{
    int count;
    MyRunnable(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        for(int i = this.count; i >= 0; --i) {
           System.out.println(i);
        }
    }
}

class MyThread extends Thread{
    Thread myRunnable;
    MyThread(MyRunnable myRunnable) {
        this.myRunnable = new Thread(myRunnable);
    }

    @Override
    public void run() {
        myRunnable.start();
    }
}