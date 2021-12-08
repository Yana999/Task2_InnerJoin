package ru.abdramanova.fileWriter;

import ru.abdramanova.entity.Intersection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ListWriter {
    public void saveTable(String path, List<Intersection> listToWrite){
        try ( FileWriter arrayWriter = new FileWriter(path)){
            for (Intersection line : listToWrite) {
                arrayWriter.write(line.toString() + " \n");
            }
        }catch (IOException e){
            System.out.println("Something went wrong! Cannot write to file " + path);
        }
    }

    public void saveTable(String directoryName, String fileName, List<Intersection> listToWrite){
        try {
            File file = new File(directoryName.substring(0, directoryName.lastIndexOf("\\")) + fileName);
            file.createNewFile();
            saveTable(file.getAbsolutePath(), listToWrite);
        }catch (IOException e){
            System.out.println("Something went wrong! Cannot write to file ");
        }
    }

}