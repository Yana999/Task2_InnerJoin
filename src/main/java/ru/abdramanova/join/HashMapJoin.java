package ru.abdramanova.join;

import ru.abdramanova.entity.Row;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class HashMapJoin implements InnerJoin <HashMap<Long, List<String>>>{

    @Override
    public HashMap<Long, List<String>> convert(List<Row> table1) {
        return new HashMap<>(table1.stream().collect(Collectors.groupingBy(Row::getId, Collectors.mapping(Row::getValue, Collectors.toList()))));
    }

    @Override
    public void innerJoin(HashMap<Long, List<String>> table1, HashMap<Long, List<String>> table2, String path) {
        try(FileWriter writer = new FileWriter(path)) {
            for (Map.Entry<Long, List<String>> line1 : table1.entrySet()) {
                if (table2.containsKey(line1.getKey())) {
                    List<String> t1 = line1.getValue();
                    List<String> t2 = table2.get(line1.getKey());
                    for (String value1 : t1) {
                        for (String value2 : t2) {
                            writer.write(line1.getKey() + ", " + value1 + ", " + value2 + "\n");
                        }
                    }
                }
            }
        }catch (IOException e){
            System.out.println("Something went wrong! Cannot write to file ");
        }
    }
}
