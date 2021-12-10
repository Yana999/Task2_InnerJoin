package ru.abdramanova;

import ru.abdramanova.entity.Row;
import ru.abdramanova.fileLoader.TableLoader;
import ru.abdramanova.fileLoader.txtFileLoader;
import ru.abdramanova.join.*;
import java.util.*;

public class InnerJoin {
    public static void main(String[] args){
        TableLoader tableLoader = new txtFileLoader();
        if(args.length == 2 || args.length == 5) {
            List<Row> tableA = tableLoader.readTable(args[0]);
            List<Row> tableB = tableLoader.readTable(args[1]);
            ProxyArrayListJoin proxyArray = new ProxyArrayListJoin();
            if(args.length == 2){
                //          //---------------ArrayList-------------------------------------------
                proxyArray.innerJoin(tableA, tableB, args[0], "ArrayListResult.txt");
            //----------------LinkedList-----------------------------------------
                ProxyLinkedListJoin proxyLinkedListJoin = new ProxyLinkedListJoin();
                proxyLinkedListJoin.innerJoin(tableA, tableB, args[0], "LinkedListResult.txt");
            //------------------HashMap------------------------------------------
                ProxyHashMapJoin proxyHashMapJoin = new ProxyHashMapJoin();
                proxyHashMapJoin.innerJoin(tableA, tableB, args[0], "hashMapResult.txt");
            }else {
                //написать для указанного пути
                //            //---------------ArrayList-------------------------------------------
                proxyArray.innerJoin(tableA, tableB, args[2]);
                //----------------LinkedList-----------------------------------------
                ProxyLinkedListJoin proxyLinkedListJoin = new ProxyLinkedListJoin();
                proxyLinkedListJoin.innerJoin(tableA, tableB, args[3]);
                //------------------HashMap----------------------------------------
                ProxyHashMapJoin proxyHashMapJoin = new ProxyHashMapJoin();
                proxyHashMapJoin.innerJoin(tableA, tableB, args[4]);
            }
        }else {
            System.out.print("Wrong input params ");
            for(String arg : args) {
                System.out.print(arg + " ");
            }
        }
    }
}
