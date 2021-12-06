package ru.abdramanova.fileLoader;

import ru.abdramanova.entity.Table;

import java.util.List;

public interface TableLoader {
    List<Table> readTable(String path);
}
