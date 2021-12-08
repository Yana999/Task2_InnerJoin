package ru.abdramanova.entity;

public final class Table {
    private final long id;
    private final String value;

    public Table(long id, String value){
        this.id = id;
        this.value = value;
    }

    public static Table parseTable(String id, String value) throws IllegalArgumentException{
        long tryId = Long.parseLong(id.trim());
        return new Table(tryId, value.trim());
    }

    public long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return id + " " + value;
    }
}
