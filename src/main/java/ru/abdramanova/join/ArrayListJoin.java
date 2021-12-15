package ru.abdramanova.join;

import ru.abdramanova.entity.Row;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class ArrayListJoin implements InnerJoin<ArrayList<Row>>{

    @Override
    public void innerJoin(ArrayList<Row> table1, ArrayList<Row> table2, String path) {
        try(FileWriter writer = new FileWriter(path)) {
                table1.
                        forEach(row -> table2.
                                forEach(row1 -> {
                                    try {
                                    if(row.getId() == row1.getId()) {
                                        writer.write(row.getId() + ", " + row.getValue() + ", " + row1.getValue() + "\n");
                                    }

                                }catch (IOException e){
                    System.out.println("Something went wrong! Cannot write to file " + path);
                }}));

        }catch (IOException e){
            System.out.println("Something went wrong! Cannot write to file " + path);
        }
    }

    @Override
    public void innerJoin(ArrayList<Row> table1, ArrayList<Row> table2, String directoryName, String fileName) {
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
