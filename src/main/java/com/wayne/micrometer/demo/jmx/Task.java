package com.wayne.micrometer.demo.jmx;

public class Task implements TaskMBean {
    private String id;
    private int number;

    public Task(String id, int number) {
        this.id = id;
        this.number = number;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
