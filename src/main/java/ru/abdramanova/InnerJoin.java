package ru.abdramanova;

import ru.abdramanova.entity.Intersection;
import ru.abdramanova.entity.Table;
import ru.abdramanova.fileLoader.TableLoader;
import ru.abdramanova.fileLoader.txtFileLoader;
import ru.abdramanova.join.ArrayListJoin;
import ru.abdramanova.join.LinkedListJoin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class InnerJoin {
    public static void main(String[] args) {
        TableLoader tableLoader = new txtFileLoader();
        if(args.length == 2) {
            // write code with creating result files
            List<Table> tableA = tableLoader.readTable(args[0]);
            List<Table> tableB = tableLoader.readTable(args[1]);
            tableA.forEach(System.out::println);
            System.out.println();
            tableB.forEach(System.out::println);
            System.out.println();
            List<Intersection> arrayListResult = new ArrayListJoin(new ArrayList<>(tableA), new ArrayList<>(tableB)).innerJoin();
            arrayListResult.stream().sorted(Comparator.comparing(Intersection::getId)).forEach(System.out::println);
            System.out.println();
            List<Intersection> linkedListResult = new LinkedListJoin(new LinkedList<>(tableA), new LinkedList<>(tableB)).innerJoin();
            linkedListResult.stream().sorted(Comparator.comparing(Intersection::getId)).forEach(System.out::println);
            System.out.println("dct");
        } else if(args.length == 3){
            // write code for results
            System.out.println("results");
        }else {
            System.out.println("Wrong input params");
        }
    }
}
