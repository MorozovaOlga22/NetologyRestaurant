package ru.netology;

public class Cook {
    private static final int TIME_TO_COOK = 2_000;

    public Cook() {
        System.out.println("Повар на работе!");
    }

    public synchronized void cook() throws InterruptedException {
        System.out.println("Повар готовит блюдо");
        Thread.sleep(TIME_TO_COOK);
        System.out.println("Повар закончил готовить");
    }
}
