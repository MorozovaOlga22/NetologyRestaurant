package ru.netology;


public class Main {
    private static final int WAITERS_COUNT = 3;

    public static void main(String[] args) {
        final Restaurant restaurant = new Restaurant();

        for (int i = 0; i < WAITERS_COUNT; i++) {
            new WaiterThread(restaurant, "Официант" + (i + 1)).start();
        }

        for (int i = 0; i < Restaurant.MAX_VISITORS; i++) {
            new GuestThread(restaurant, "Посетитель" + (i + 1)).start();
        }
    }
}
