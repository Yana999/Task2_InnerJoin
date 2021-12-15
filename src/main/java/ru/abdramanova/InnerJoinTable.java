package ru.abdramanova;

import ru.abdramanova.entity.Row;
import ru.abdramanova.fileLoader.TableLoader;
import ru.abdramanova.fileLoader.txtFileLoader;
import ru.abdramanova.join.*;
import java.util.*;

public class InnerJoinTable {
    public static void main(String[] args){
        TableLoader tableLoader = new txtFileLoader();
        if(args.length == 2 || args.length == 5) {
            List<Row> tableA = tableLoader.readTable(args[0]);
            List<Row> tableB = tableLoader.readTable(args[1]);
            ProxyInnerJoin<ArrayList<Row>, ArrayListJoin> arrayProxy = new ProxyInnerJoin<>(new ArrayListJoin());
            ProxyInnerJoin<LinkedList<Row>, LinkedListJoin> linkedProxy= new ProxyInnerJoin<>(new LinkedListJoin());
            ProxyInnerJoin<HashMap<Long, List<String>>, HashMapJoin> mapProxy = new ProxyInnerJoin<>(new HashMapJoin());
            if(args.length == 2){
                arrayProxy.innerJoin(arrayProxy.convert(tableA), arrayProxy.convert(tableB), args[0], "ArrayListResult.txt");
                linkedProxy.innerJoin(linkedProxy.convert(tableA), linkedProxy.convert(tableB), args[0], "LinkedListResult.txt");
                mapProxy.innerJoin(mapProxy.convert(tableA), mapProxy.convert(tableB), args[0], "hashMapResult.txt");
            }else {
                arrayProxy.innerJoin(arrayProxy.convert(tableA), arrayProxy.convert(tableB), args[2]);
                linkedProxy.innerJoin(linkedProxy.convert(tableA), linkedProxy.convert(tableB), args[3]);
                mapProxy.innerJoin(mapProxy.convert(tableA), mapProxy.convert(tableB), args[4]);
            }
        }else {
            System.out.print("Wrong input params ");
            for(String arg : args) {
                System.out.print(arg + " ");
            }
        }
    }
}
