package ru.abdramanova.join;

import ru.abdramanova.entity.Intersection;
import ru.abdramanova.entity.Table;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HashMapJoin implements InnerJoin{
    private HashMap<Long, List<Table>> table1;
    private HashMap<Long, List<Table>> table2;

    public HashMapJoin(HashMap<Long, List<Table>> table1, HashMap<Long, List<Table>> table2) {
        this.table1 = table1;
        this.table2 = table2;
    }

    @Override
    public List<Intersection> innerJoin() {
        List<Intersection> result = new LinkedList<>();
        for (Map.Entry<Long, List<Table>> line1 : table1.entrySet()){
            if(table2.containsKey(line1.getKey())){
                List<Table> t1 = line1.getValue();
                List<Table> t2 = table2.get(line1.getKey());
                for(int i = 0; i < t1.size(); ++i){
                    for(int j = 0; j < t2.size(); ++j) {
                        result.add(new Intersection(t1.get(i).getId(), t1.get(i).getValue(), t2.get(j).getValue()));
                    }
                }
            }
        }

        return result;
    }
}
