package ru.abdramanova.join;

import ru.abdramanova.entity.Row;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListJoin implements InnerJoin<LinkedList<Row>>{

    @Override
    public void innerJoin(LinkedList<Row> table1, LinkedList<Row> table2, String directoryName, String fileName) {
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

    @Override
    public void innerJoin(LinkedList<Row> table1, LinkedList<Row> table2, String path) {
        try(FileWriter writer = new FileWriter(path)) {
            ListIterator<Row> table1Iterator = table1.listIterator();
            ListIterator<Row> table2Iterator = table2.listIterator();
            Row line1 = table1Iterator.next();
            Row line2 = table2Iterator.next();

            while (table1Iterator.hasNext() && table2Iterator.hasNext()) {
                int steps = 0;
                while (line1.getId() == line2.getId()) {
                    writer.write(line1.getId() + ", " + line1.getValue() + ", " + line2.getValue() + "\n");
                    ++steps;
                    if (table2Iterator.hasNext()) {
                        line2 = table2Iterator.next();
                    } else {
                        break;
                    }
                }

                if(steps > 0) {
                    while (steps > 0) {
                        line2 = table2Iterator.previous();
                        --steps;
                    }
                    line2 = table2Iterator.previous();
                    line2 = table2Iterator.next();
                }
                if (line1.getId() > line2.getId() && table2Iterator.hasNext()) {
                    line2 = table2Iterator.next();
                } else if (table1Iterator.hasNext()) {
                    line1 = table1Iterator.next();
                }
            }
        }catch (IOException e){
            System.out.println("Something went wrong! Cannot write to file " + path);
        }
    }

}
