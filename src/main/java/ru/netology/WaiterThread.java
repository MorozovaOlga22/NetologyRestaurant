package ru.netology;

public class WaiterThread extends Thread {
    private static final int TIME_TO_TAKE_ORDER = 1_000;

    private final Restaurant restaurant;

    public WaiterThread(Restaurant restaurant, String threadName) {
        this.restaurant = restaurant;
        setName(threadName);
    }

    @Override
    public void run() {
        System.out.println(getName() + " на работе!");
        try {
            while (!restaurant.isClosed()) {
                processOrder();
            }
            System.out.println(getName() + " идет домой!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processOrder() throws InterruptedException {
        final GuestThread guest = restaurant.selectGuest();
        if (guest == null) {
            return;
        }
        System.out.println(guest.getName() + " обсуждает заказ с официантом");
        Thread.sleep(TIME_TO_TAKE_ORDER);
        System.out.println(getName() + " взял заказ");
        restaurant.getCook().cook();
        System.out.println(getName() + " несет заказ");
        guest.getOrder();
    }
}
