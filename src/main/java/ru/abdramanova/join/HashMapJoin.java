package ru.abdramanova.join;

import ru.abdramanova.entity.Intersection;
import ru.abdramanova.entity.Table;

import java.util.*;

public class HashMapJoin implements InnerJoin <HashMap<Long, List<Table>>>{

    @Override
    public List<Intersection> innerJoin(HashMap<Long, List<Table>> table1, HashMap<Long, List<Table>> table2) {
        List<Intersection> result = new LinkedList<>();
        for (Map.Entry<Long, List<Table>> line1 : table1.entrySet()){
            if(table2.containsKey(line1.getKey())){
                List<Table> t1 = line1.getValue();
                List<Table> t2 = table2.get(line1.getKey());
                for (Table value1 : t1) {
                    for (Table value2 : t2) {
                        result.add(new Intersection(value1.getId(), value1.getValue(), value2.getValue()));
                    }
                }
            }
        }
        return result;
    }
}
