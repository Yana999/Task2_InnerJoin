package ru.abdramanova;

import ru.abdramanova.entity.Intersection;
import ru.abdramanova.entity.Table;
import ru.abdramanova.fileLoader.TableLoader;
import ru.abdramanova.fileLoader.txtFileLoader;
import ru.abdramanova.fileWriter.ListWriter;
import ru.abdramanova.join.ArrayListJoin;
import ru.abdramanova.join.HashMapJoin;
import ru.abdramanova.join.LinkedListJoin;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class InnerJoin {
    public static void main(String[] args){
        TableLoader tableLoader = new txtFileLoader();
        if(args.length == 2 || args.length == 5) {
            List<Table> tableA = tableLoader.readTable(args[0]);
            List<Table> tableB = tableLoader.readTable(args[1]);
            //---------------ArrayList-------------------------------------------
            ArrayListJoin arrayListJoin = new ArrayListJoin();
            Instant astart = Instant.now();
            List<Intersection> arrayListResult = arrayListJoin.innerJoin(new ArrayList<>(tableA), new ArrayList<>(tableB));
            Instant afinish = Instant.now();
            System.out.println("Time of the ArrayList " + Duration.between(astart, afinish).toMillis() + " ms");
            //----------------LinkedList-----------------------------------------
            LinkedListJoin listJoin =  new LinkedListJoin();
            LinkedList<Table> linkedTable1 =new LinkedList<>(tableA);
            LinkedList<Table> linkedTable2 =new LinkedList<>(tableB);
            linkedTable1.sort(Comparator.comparing(Table::getId));
            linkedTable2.sort(Comparator.comparing(Table::getId));
            tableB.sort(Comparator.comparing(Table::getId));
            Instant lstart = Instant.now();
            List<Intersection> linkedListResult = listJoin.innerJoin(linkedTable1, linkedTable2);
            Instant lfinish = Instant.now();
            System.out.println("Time of the sorted LinkedList " + Duration.between(lstart, lfinish).toMillis()+ " ms");
            //------------------HashMap----------------------------------------
            Map<Long, List<Table>> table1 = tableA.stream().collect(Collectors.groupingBy(Table::getId));
            Map<Long, List<Table>> table2 = tableB.stream().collect(Collectors.groupingBy(Table::getId));
            HashMapJoin hashMapJoin = new HashMapJoin();
            Instant hashStart = Instant.now();
            long htstart = System.nanoTime();
            List<Intersection> hashMapResult = hashMapJoin.innerJoin(new HashMap<>(table1), new HashMap<>(table2));
            long htfinish = System.nanoTime();
            Instant hashFinish = Instant.now();
            System.out.println("Time of the hashMap  " + Duration.between(hashStart, hashFinish).toMillis()+ " ms");
            System.out.println( Math.ceil((htfinish - htstart)/1000000.0) + " ms");
            ListWriter writer = new ListWriter();
            if(args.length == 2){
                writer.saveTable(args[0], "ArrayListResult.txt", arrayListResult);
                writer.saveTable(args[0], "LinkedListResult.txt", linkedListResult);
                writer.saveTable(args[0], "hashMapResult.txt", hashMapResult);
            }else {
                writer.saveTable(args[2], arrayListResult);
                writer.saveTable(args[3], linkedListResult);
                writer.saveTable(args[4], hashMapResult);
            }
        }else {
            System.out.print("Wrong input params ");
            for(String arg : args) {
                System.out.print(arg + " ");
            }
        }
    }
}
