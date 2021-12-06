package ru.abdramanova.entity;

public class Intersection {
    private long id;
    private String valueA;
    private String valueB;

    public Intersection(long id, String valueA, String valueB) {
        this.id = id;
        this.valueA = valueA;
        this.valueB = valueB;
    }

    public long getId() {
        return id;
    }

    public String getValueA() {
        return valueA;
    }

    public String getValueB() {
        return valueB;
    }

    @Override
    public String toString() {
        return id + " " + valueA + " " + valueB;
    }
}
