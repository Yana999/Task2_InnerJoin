package ru.abdramanova.join;

import ru.abdramanova.entity.Row;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArrayListJoin implements InnerJoin<ArrayList<Row>>{

    @Override
    public ArrayList<Row> convert(List<Row> table1) {
        return new ArrayList<>(table1);
    }

    @Override
    public void innerJoin(ArrayList<Row> table1, ArrayList<Row> table2, String path) {

        try (FileWriter writer = new FileWriter(path)) {
            table1.
                    forEach(row -> table2.
                            forEach(row1 -> {
                                try {
                                    if (row.getId() == row1.getId()) {
                                        writer.write(row.getId() + ", " + row.getValue() + ", " + row1.getValue() + "\n");
                                    }

                                } catch (IOException e) {
                                    System.out.println("Something went wrong! Cannot write to file " + path);
                                }
                            }));

        } catch (IOException e) {
            System.out.println("Something went wrong! Cannot write to file " + path);
        }
    }
}
