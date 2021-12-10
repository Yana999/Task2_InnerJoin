package ru.abdramanova.fileLoader;

import ru.abdramanova.entity.Row;

import java.util.List;

public interface TableLoader {
    List<Row> readTable(String path);
}
