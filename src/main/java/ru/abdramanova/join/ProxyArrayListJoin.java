package ru.abdramanova.join;

import ru.abdramanova.entity.Row;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ProxyArrayListJoin implements InnerJoin<ArrayList<Row>>{

    @Override
    public void innerJoin(ArrayList<Row> table1, ArrayList<Row> table2, String directoryName, String fileName) {
        ArrayListJoin arrayListJoin = new ArrayListJoin();
        Instant astart = Instant.now();
        arrayListJoin.innerJoin(new ArrayList<>(table1), new ArrayList<>(table2), directoryName, fileName);
        Instant afinish = Instant.now();
        System.out.println("Time of the ArrayList " + Duration.between(astart, afinish).toMillis() + " ms");
    }

    @Override
    public void innerJoin(ArrayList<Row> table1, ArrayList<Row> table2, String path) {
        ArrayListJoin arrayListJoin = new ArrayListJoin();
        Instant astart = Instant.now();
        arrayListJoin.innerJoin(new ArrayList<>(table1), new ArrayList<>(table2), path);
        Instant afinish = Instant.now();
        System.out.println("Time of the ArrayList " + Duration.between(astart, afinish).toMillis() + " ms");
    }

    public void innerJoin(List<Row> table1, List<Row> table2, String path) {
        innerJoin(new ArrayList<>(table1), new ArrayList<>(table2), path);
    }

    public void innerJoin(List<Row> table1, List<Row> table2, String directoryName, String fileName) {
        innerJoin(new ArrayList<>(table1), new ArrayList<>(table2), directoryName, fileName);
    }
}
