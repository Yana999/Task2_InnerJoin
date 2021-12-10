package ru.abdramanova.join;

import ru.abdramanova.entity.Row;
import ru.abdramanova.join.InnerJoin;
import ru.abdramanova.join.LinkedListJoin;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ProxyLinkedListJoin implements InnerJoin<LinkedList<Row>> {
    @Override
    public void innerJoin(LinkedList<Row> table1, LinkedList<Row> table2, String directoryName, String fileName) {
            LinkedListJoin listJoin =  new LinkedListJoin();
            Instant lstart = Instant.now();
            listJoin.innerJoin(table1, table2, directoryName, "LinkedListResult.txt");
            Instant lfinish = Instant.now();
            System.out.println("Time of the sorted LinkedList " + Duration.between(lstart, lfinish).toMillis()+ " ms");
        }

    @Override
    public void innerJoin(LinkedList<Row> table1, LinkedList<Row> table2, String path) {
        LinkedListJoin listJoin =  new LinkedListJoin();
        Instant lstart = Instant.now();
        listJoin.innerJoin(table1, table2, path);
        Instant lfinish = Instant.now();
        System.out.println("Time of the sorted LinkedList " + Duration.between(lstart, lfinish).toMillis()+ " ms");
    }

    public void innerJoin(List<Row> table1, List<Row> table2, String path) {
        LinkedList<Row> linkedTable1 =new LinkedList<>(table1);
        LinkedList<Row> linkedTable2 =new LinkedList<>(table2);
        linkedTable1.sort(Comparator.comparing(Row::getId));
        linkedTable2.sort(Comparator.comparing(Row::getId));
        table1.sort(Comparator.comparing(Row::getId));
        innerJoin(linkedTable1, linkedTable2, path);
    }

    public void innerJoin(List<Row> table1, List<Row> table2, String directoryName, String fileName) {
        LinkedList<Row> linkedTable1 =new LinkedList<>(table1);
        LinkedList<Row> linkedTable2 =new LinkedList<>(table2);
        linkedTable1.sort(Comparator.comparing(Row::getId));
        linkedTable2.sort(Comparator.comparing(Row::getId));
        table1.sort(Comparator.comparing(Row::getId));
        innerJoin(linkedTable1, linkedTable2, directoryName, fileName);
    }
}
