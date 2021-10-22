package ru.netology;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    public static final int MAX_VISITORS = 5;

    private final Cook cook = new Cook();
    private final List<GuestThread> guests = new ArrayList<>();

    private int numberOfVisitorsServed = 0;
    private boolean isClosed = false;

    public boolean isClosed() {
        return isClosed;
    }

    public Cook getCook() {
        return cook;
    }

    public synchronized void acceptNewGuest(GuestThread guest) throws InterruptedException {
        guests.add(guest);
        notify();
    }

    public synchronized GuestThread selectGuest() throws InterruptedException {
        if (guests.size() > 0) {
            return guests.remove(0);
        } else {
            wait();
            return null;
        }
    }

    public synchronized void updateNumberOfVisitorsServed() {
        numberOfVisitorsServed++;
        if (numberOfVisitorsServed == MAX_VISITORS) {
            isClosed = true;
            System.out.println("Повар идет домой!");
            notifyAll();
        }
    }
}
