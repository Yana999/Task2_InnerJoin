package ru.abdramanova.join;

import ru.abdramanova.entity.Row;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinkedListJoin implements InnerJoin<LinkedList<Row>>{

    @Override
    public LinkedList<Row> convert(List<Row> table1) {
        LinkedList<Row> linkedTable1 =new LinkedList<>(table1);
        linkedTable1.sort(Comparator.comparing(Row::getId));
        return linkedTable1;
    }

    @Override
    public void innerJoin(LinkedList<Row> table1, LinkedList<Row> table2, String path) {
        try(FileWriter writer = new FileWriter(path)) {
            ListIterator<Row> table1Iterator = table1.listIterator();
            ListIterator<Row> table2Iterator = table2.listIterator();
            Row line1 = table1Iterator.next();
            Row line2 = table2Iterator.next();

            while (true) {
                int steps = 0;
                while (line1.getId() == line2.getId()) {
                    writer.write(line1.getId() + ", " + line1.getValue() + ", " + line2.getValue() + "\n");
                    if (table2Iterator.hasNext()) {
                        line2 = table2Iterator.next();
                        ++steps;
                    } else {
                        break;
                    }
                }

                if(steps > 0) {
                    while (steps >= 0) {
                        table2Iterator.previous();
                        --steps;
                    }
                    line2 = table2Iterator.next();
                }
                if (line1.getId() > line2.getId() && table2Iterator.hasNext()) {
                    line2 = table2Iterator.next();
                } else if (table1Iterator.hasNext()) {
                    line1 = table1Iterator.next();
                }else{
                    break;
                }
            }
        }catch (IOException e){
            System.out.println("Something went wrong! Cannot write to file " + path);
        }
    }

}
