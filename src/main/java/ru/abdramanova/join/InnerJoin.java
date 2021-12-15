package ru.abdramanova.join;

import ru.abdramanova.entity.Row;

import java.util.ArrayList;
import java.util.List;

public interface InnerJoin <T> {
   void innerJoin(T table1, T table2, String directoryName, String fileName);
   void innerJoin(T table1, T table2, String path);
}

