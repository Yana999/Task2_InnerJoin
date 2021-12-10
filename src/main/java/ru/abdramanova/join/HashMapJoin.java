package ru.abdramanova.join;

import ru.abdramanova.entity.Row;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HashMapJoin implements InnerJoin <HashMap<Long, List<Row>>>{

    @Override
    public void innerJoin(HashMap<Long, List<Row>> table1, HashMap<Long, List<Row>> table2, String path) {
        try(FileWriter writer = new FileWriter(path)) {
            for (Map.Entry<Long, List<Row>> line1 : table1.entrySet()) {
                if (table2.containsKey(line1.getKey())) {
                    List<Row> t1 = line1.getValue();
                    List<Row> t2 = table2.get(line1.getKey());
                    for (Row value1 : t1) {
                        for (Row value2 : t2) {
                            writer.write(value1.getId() + ", " + value1.getValue() + ", " + value2.getValue() + "\n");
                        }
                    }
                }
            }
        }catch (IOException e){
            System.out.println("Something went wrong! Cannot write to file ");
        }
    }

    @Override
    public void innerJoin(HashMap<Long, List<Row>> table1, HashMap<Long, List<Row>> table2, String directoryName, String fileName) {
        try {
            String path = directoryName.substring(0, directoryName.lastIndexOf("\\") + 1) + fileName;
            File file = new File(path);
            if (!file.createNewFile()){
                if(file.exists()){
                    innerJoin(table1, table2, file.getAbsolutePath());
                }else{
                    System.out.println("Cannot create the file for writing");
                }
            }else {
                innerJoin(table1, table2, file.getAbsolutePath());
            }
        }catch (IOException e){
            System.out.println("Something went wrong! Cannot write to file ");
        }
    }
}
