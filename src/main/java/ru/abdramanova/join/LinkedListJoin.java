package ru.abdramanova.join;

import ru.abdramanova.entity.Intersection;
import ru.abdramanova.entity.Table;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LinkedListJoin implements InnerJoin{

    private LinkedList<Table> table1;
    private LinkedList<Table> table2;

    public LinkedListJoin(LinkedList<Table> table1, LinkedList<Table> table2) {
        this.table1 = table1;
        this.table2 = table2;
        this.table1.sort(Comparator.comparing(Table::getId));
        this.table2.sort(Comparator.comparing(Table::getId));
    }

    @Override
    public List<Intersection> innerJoin() {
        List<Intersection> result = new LinkedList<>();
        for(int i = 0, j = 0; i < table1.size() && j < table2.size();){
            if(table1.get(i).getId() == table2.get(j).getId()) {
                for(int k = j; table1.get(i).getId() == table2.get(k).getId(); ++k){
                    result.add(new Intersection(table1.get(i).getId(), table1.get(i).getValue(), table2.get(k).getValue()));
                }
                ++i;
            }else if(table1.get(i).getId() > table2.get(j).getId()){
                ++j;
            }else {
                ++i;
            }
        }
        return result;
    }
}
