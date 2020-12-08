package me.soreal.java8to11;

public class ComFutureApp {

    public static void main(String[] args) throws InterruptedException {

//        MyThread myThread = new MyThread();
//        myThread.start();
//
        Thread thread = new Thread(() ->  {

            // sleep 재우는 법
//            try {
//                Thread.sleep(1000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("Thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });
        thread.start();

        // main Thread
        System.out.println("Hello: " + Thread.currentThread().getName());
        thread.join();
        System.out.println(thread + " is finished");

    }

//    static class MyThread extends Thread {
//        @Override
//        public void run() {
//            System.out.println("Thread: " + Thread.currentThread().getName());
//        }
//    }

}
