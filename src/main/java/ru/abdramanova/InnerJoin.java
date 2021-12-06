package ru.abdramanova;

import ru.abdramanova.entity.Intersection;
import ru.abdramanova.entity.Table;
import ru.abdramanova.fileLoader.TableLoader;
import ru.abdramanova.fileLoader.txtFileLoader;
import ru.abdramanova.join.ArrayListJoin;

import java.util.ArrayList;
import java.util.Comparator;
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
            List<Intersection> arrayListResult = ArrayListJoin.innerJoin(new ArrayList<>(tableA), new ArrayList<>(tableB));
            arrayListResult.stream().sorted(Comparator.comparing(Intersection::getId)).forEach(System.out::println);
        } else if(args.length == 3){
            // write code for results
            System.out.println("results");
        }else {
            System.out.println("Wrong input params");
        }
    }
}
