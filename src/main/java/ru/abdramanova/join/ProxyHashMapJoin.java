package ru.abdramanova.join;

import ru.abdramanova.entity.Row;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProxyHashMapJoin implements InnerJoin <HashMap<Long, List<Row>>>{
    @Override
    public void innerJoin(HashMap<Long, List<Row>> table1, HashMap<Long, List<Row>> table2, String directoryName, String fileName) {
        HashMapJoin hashMapJoin = new HashMapJoin();
        Instant hashStart = Instant.now();
        long htstart = System.nanoTime();
        hashMapJoin.innerJoin(new HashMap<>(table1), new HashMap<>(table2), directoryName, "hashMapResult.txt");
        long htfinish = System.nanoTime();
        Instant hashFinish = Instant.now();
        System.out.println("Time of the hashMap  " + Duration.between(hashStart, hashFinish).toMillis()+ " ms");
    }

    @Override
    public void innerJoin(HashMap<Long, List<Row>> table1, HashMap<Long, List<Row>> table2, String path) {
        HashMapJoin hashMapJoin = new HashMapJoin();
        Instant hashStart = Instant.now();
        long htstart = System.nanoTime();
        hashMapJoin.innerJoin(table1, table2, path);
        long htfinish = System.nanoTime();
        Instant hashFinish = Instant.now();
        System.out.println("Time of the hashMap  " + Duration.between(hashStart, hashFinish).toMillis()+ " ms");
    }

    public void innerJoin(List<Row> table1, List<Row> table2, String path) {
        Map<Long, List<Row>> tableA = table1.stream().collect(Collectors.groupingBy(Row::getId));
        Map<Long, List<Row>> tableB = table2.stream().collect(Collectors.groupingBy(Row::getId));
        innerJoin(new HashMap<>(tableA), new HashMap<>(tableB), path);
    }

    public void innerJoin(List<Row> table1, List<Row> table2, String directoryName, String fileName) {
        Map<Long, List<Row>> tableA = table1.stream().collect(Collectors.groupingBy(Row::getId));
        Map<Long, List<Row>> tableB = table2.stream().collect(Collectors.groupingBy(Row::getId));
        innerJoin(new HashMap<>(tableA), new HashMap<>(tableB), directoryName, fileName);
    }
}
