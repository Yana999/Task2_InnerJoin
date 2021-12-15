package ru.abdramanova.join;

import ru.abdramanova.entity.Row;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class ProxyInnerJoin<T, V extends InnerJoin<T>> implements InnerJoin<T>{
    private V innerJoin;

    public ProxyInnerJoin(V innerJoin) {
        this.innerJoin = innerJoin;
    }

    @Override
    public T convert(List<Row> table1) {
        return innerJoin.convert(table1);
    }

    @Override
    public void innerJoin(T table1, T table2, String path) {
        Instant start = Instant.now();
        innerJoin.innerJoin(table1, table2, path);
        Instant finish = Instant.now();
        System.out.println("Time of the ArrayList " + Duration.between(start, finish).toMillis() + " ms");
    }

    public void innerJoin(T table1, T table2, String directoryName, String fileName) {
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
