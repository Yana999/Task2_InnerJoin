package ru.abdramanova.fileLoader;

import ru.abdramanova.entity.Row;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class txtFileLoader implements TableLoader {
    public List<Row> readTable(String path){
        List<Row> table = new LinkedList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(path))){
            int lineNumber = 0;
            while (fileReader.ready()){
                try{
                    String[] line = fileReader.readLine().split(",");
                    if(line.length == 2) {
                        table.add(Row.parseTable(line[0], line[1]));
                    }else{
                        System.out.println("wrong format line " + lineNumber);
                    }
                }catch (IllegalArgumentException e){
                    System.out.println("Impossible to read the id in the line " + lineNumber);
                }
                ++lineNumber;
            }
        }catch (FileNotFoundException e){
            System.out.println("File " + path + "not found");
        }catch (IOException e){
            System.out.println("Something went wrong! Impossible to read file " + path);
        }

        return table;
    }
}
