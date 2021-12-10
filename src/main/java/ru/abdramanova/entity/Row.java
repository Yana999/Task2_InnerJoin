package ru.abdramanova.entity;

public final class Row {
    private final long id;
    private final String value;

    public Row(long id, String value){
        this.id = id;
        this.value = value;
    }

    public static Row parseTable(String id, String value) throws IllegalArgumentException{
        long tryId = Long.parseLong(id.trim());
        return new Row(tryId, value.trim());
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
