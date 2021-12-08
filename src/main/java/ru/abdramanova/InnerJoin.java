package ru.abdramanova;

import ru.abdramanova.entity.Intersection;
import ru.abdramanova.entity.Table;
import ru.abdramanova.fileLoader.TableLoader;
import ru.abdramanova.fileLoader.txtFileLoader;
import ru.abdramanova.fileWriter.ListWriter;
import ru.abdramanova.join.ArrayListJoin;
import ru.abdramanova.join.HashMapJoin;
import ru.abdramanova.join.LinkedListJoin;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class InnerJoin {
    public static void main(String[] args){
        TableLoader tableLoader = new txtFileLoader();
        if(args.length == 2 || args.length == 5) {
            // write code with creating result files
            //---------------ArrayList-------------------------------------------
            List<Table> tableA = tableLoader.readTable(args[0]);
            List<Table> tableB = tableLoader.readTable(args[1]);
            ArrayListJoin arrayListJoin = new ArrayListJoin(new ArrayList<>(tableA), new ArrayList<>(tableB));
            Instant astart = Instant.now();
            List<Intersection> arrayListResult = arrayListJoin.innerJoin();
            Instant afinish = Instant.now();
            System.out.println(Duration.between(astart, afinish).toNanos());
            System.out.println();
            //----------------LinkedList-----------------------------------------
            LinkedListJoin listJoin =  new LinkedListJoin(new LinkedList<>(tableA), new LinkedList<>(tableB));
            Instant lstart = Instant.now();
            List<Intersection> linkedListResult = listJoin.innerJoin();
            Instant lfinish = Instant.now();
            System.out.println(Duration.between(lstart, lfinish).toNanos());

            System.out.println();
            //------------------HashMap----------------------------------------
            Map<Long, List<Table>> table1 = tableA.stream().collect(Collectors.groupingBy(Table::getId));
            Map<Long, List<Table>> table2 = tableB.stream().collect(Collectors.groupingBy(Table::getId));
            HashMapJoin hashMapJoin = new HashMapJoin(new HashMap<>(table1), new HashMap<>(table2));
            Instant hstart = Instant.now();
            List<Intersection> hashMapResult = hashMapJoin.innerJoin();
            Instant hfinish = Instant.now();
            System.out.println(Duration.between(hstart, hfinish).toNanos());
            ListWriter writer = new ListWriter();
            if(args.length == 2){
                writer.saveTable(args[0], "ArrayListResult.txt", arrayListResult);
                writer.saveTable(args[0], "LinkedListResult.txt", arrayListResult);
                writer.saveTable(args[0], "hashMapResult.txt", arrayListResult);
            }else {
                writer.saveTable(args[2], arrayListResult);
                writer.saveTable(args[3], arrayListResult);
                writer.saveTable(args[4], arrayListResult);
            }
        }else {
            System.out.print("Wrong input params ");
            for(String arg : args) {
                System.out.print(arg + " ");
            }
        }
    }
}
