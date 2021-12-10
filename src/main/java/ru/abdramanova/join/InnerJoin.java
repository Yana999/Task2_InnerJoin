package ru.abdramanova.join;

public interface InnerJoin <T> {
   void innerJoin(T table1, T table2, String directoryName, String fileName);
   void innerJoin(T table1, T table2, String path);
}

