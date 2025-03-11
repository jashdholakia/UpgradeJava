package com.mastercard.upgradejava.Threads.Locks;

import java.util.concurrent.locks.ReentrantLock;

public class ReEntrenetLockExample2  {
    ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        ReEntrenetLockExample2 reEntrenetLockExample2 = new ReEntrenetLockExample2();
        reEntrenetLockExample2.outerMethod();
    }

    /**
     * First it will lock the outer method and then it will call inner method and lock the inner method.
     * Once inner method is completed, it will release the lock of inner method and then outer method.
     */
    public void outerMethod() {
        System.out.println("Outer Method");
        lock.lock();
        System.out.println("Outer method locked");
        try {
            Thread.sleep(1000);
            innerMethod();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
            System.out.println("Unlocked by outer method");
        }

    }

    public void innerMethod() {
        System.out.println("Inner Method");
        lock.lock(); //same locked will be used by inner method
        System.out.println("Inner method locked");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock(); // here , it will release a lock of inner method
            System.out.println("Unlocked by inner method");
        }
    }
}
