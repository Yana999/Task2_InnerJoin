package ru.abdramanova.join;

import ru.abdramanova.entity.Row;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProxyHashMapJoin implements InnerJoin <HashMap<Long, List<String>>>{
    @Override
    public void innerJoin(HashMap<Long, List<String>> table1, HashMap<Long, List<String>> table2, String directoryName, String fileName) {
        HashMapJoin hashMapJoin = new HashMapJoin();
        Instant hashStart = Instant.now();
        hashMapJoin.innerJoin(new HashMap<>(table1), new HashMap<>(table2), directoryName, "hashMapResult.txt");
        Instant hashFinish = Instant.now();
        System.out.println("Time of the hashMap  " + Duration.between(hashStart, hashFinish).toMillis()+ " ms");
    }

    @Override
    public void innerJoin(HashMap<Long, List<String>> table1, HashMap<Long, List<String>> table2, String path) {
        HashMapJoin hashMapJoin = new HashMapJoin();
        Instant hashStart = Instant.now();
        hashMapJoin.innerJoin(table1, table2, path);
        Instant hashFinish = Instant.now();
        System.out.println("Time of the hashMap  " + Duration.between(hashStart, hashFinish).toMillis()+ " ms");
    }

    public void innerJoin(List<Row> table1, List<Row> table2, String path) {
        Map<Long, List<String>> tableA = table1.stream().collect(Collectors.groupingBy(Row::getId, Collectors.mapping(Row::getValue, Collectors.toList())));
        Map<Long, List<String>> tableB = table2.stream().collect(Collectors.groupingBy(Row::getId, Collectors.mapping(Row::getValue, Collectors.toList())));
        innerJoin(new HashMap<>(tableA), new HashMap<>(tableB), path);
    }

    public void innerJoin(List<Row> table1, List<Row> table2, String directoryName, String fileName) {
        Map<Long, List<String>> tableA = table1.stream().collect(Collectors.groupingBy(Row::getId, Collectors.mapping(Row::getValue, Collectors.toList())));
        Map<Long, List<String>> tableB = table2.stream().collect(Collectors.groupingBy(Row::getId, Collectors.mapping(Row::getValue, Collectors.toList())));
        innerJoin(new HashMap<>(tableA), new HashMap<>(tableB), directoryName, fileName);
    }
}
