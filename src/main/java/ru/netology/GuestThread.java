package ru.netology;

public class GuestThread extends Thread {
    private static final int TIME_TO_EAT = 5_000;

    private final Restaurant restaurant;

    public GuestThread(Restaurant restaurant, String threadName) {
        this.restaurant = restaurant;
        setName(threadName);
    }

    @Override
    public synchronized void run() {
        try {
            System.out.println(getName() + " пришел в ресторан");
            restaurant.acceptNewGuest(this);
            wait();
            eat();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eat() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " приступил к еде");
        Thread.sleep(TIME_TO_EAT);
        System.out.println(Thread.currentThread().getName() + " вышел из ресторана");
        restaurant.updateNumberOfVisitorsServed();
    }

    public synchronized void getOrder() {
        notify();
    }
}
