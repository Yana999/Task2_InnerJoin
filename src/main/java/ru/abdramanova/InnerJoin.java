package ru.abdramanova;

import ru.abdramanova.entity.Intersection;
import ru.abdramanova.entity.Table;
import ru.abdramanova.fileLoader.TableLoader;
import ru.abdramanova.fileLoader.txtFileLoader;
import ru.abdramanova.join.ArrayListJoin;
import ru.abdramanova.join.HashMapJoin;
import ru.abdramanova.join.LinkedListJoin;

import javax.xml.crypto.Data;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class InnerJoin {
    public static void main(String[] args) throws IOException {
        TableLoader tableLoader = new txtFileLoader();
        if(args.length == 2) {
            // write code with creating result files
            List<Table> tableA = tableLoader.readTable(args[0]);
            List<Table> tableB = tableLoader.readTable(args[1]);
            //tableA.forEach(System.out::println);
            System.out.println();
            //tableB.forEach(System.out::println);
            System.out.println();
            ArrayListJoin arrayListJoin = new ArrayListJoin(new ArrayList<>(tableA), new ArrayList<>(tableB));
            Instant astart = Instant.now();
            List<Intersection> arrayListResult = arrayListJoin.innerJoin();
            Instant afinish = Instant.now();
            System.out.println(Duration.between(astart, afinish).toNanos());
           // arrayListResult.stream().sorted(Comparator.comparing(Intersection::getId)).forEach(System.out::println);
            System.out.println();
            //---------------------------------------------------------
            LinkedListJoin listJoin =  new LinkedListJoin(new LinkedList<>(tableA), new LinkedList<>(tableB));
            Instant lstart = Instant.now();
            List<Intersection> linkedListResult = listJoin.innerJoin();
            Instant lfinish = Instant.now();
            System.out.println(Duration.between(lstart, lfinish).toNanos());
            //linkedListResult.stream().sorted(Comparator.comparing(Intersection::getId)).forEach(System.out::println);
            System.out.println();
            //----------------------------------------------------------
            Map<Long, List<Table>> table1 = tableA.stream().collect(Collectors.groupingBy(Table::getId));
            //table1.forEach((k,v) -> System.out.println (k + " " + v));
            System.out.println();
            Map<Long, List<Table>> table2 = tableB.stream().collect(Collectors.groupingBy(Table::getId));
            //table2.forEach((k,v) -> System.out.println (k + " " + v));
            System.out.println();
            HashMapJoin hashMapJoin = new HashMapJoin(new HashMap<>(table1), new HashMap<>(table2));
            Instant hstart = Instant.now();
            List<Intersection> hashMapResult = hashMapJoin.innerJoin();
            Instant hfinish = Instant.now();
            System.out.println(Duration.between(hstart, hfinish).toNanos());
            //hashMapResult.forEach(System.out::println);
            System.out.println("dct");
        } else if(args.length == 3){
            // write code for results
            System.out.println("results");
        }else {
            System.out.println("Wrong input params");
        }

        /*FileWriter writer = new FileWriter("C:\\Users\\Dns\\IdeaProjects\\Task2_InnerJoin\\src\\main\\resources\\sample4.txt");
        Random rnd = new Random(34);
        for(int i = 0; i < 1000; ++i){
            String s = rnd.nextInt(300) + "," + (char)(rnd.nextInt(26) + 'A') + "\n";
            writer.write(s);
        }
        writer.close();
         */
    }
}
