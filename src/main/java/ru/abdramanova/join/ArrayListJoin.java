package ru.abdramanova.join;

import ru.abdramanova.entity.Row;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArrayListJoin implements InnerJoin<ArrayList<Row>>{

    @Override
    public void innerJoin(ArrayList<Row> table1, ArrayList<Row> table2, String path) {
        try(FileWriter writer = new FileWriter(path)) {
            for (Row line1 : table1) {
                for (Row line2 : table2) {
                    if (line1.getId() == line2.getId()) {
                        writer.write((line1.getId() + ", " + line1.getValue() + ", " + line2.getValue() + "\n"));
                    }
                }
            }
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
