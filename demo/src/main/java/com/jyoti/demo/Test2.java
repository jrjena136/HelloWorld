package com.jyoti.demo;

public class Test2 extends Thread implements Runnable {

    @Override
    public void run () {
        System.out.println("xyz");
    }

    public static void main(String[] args) throws InterruptedException {
        Test2 t = new Test2();
        t.run();
        t.start();
    }
}
