package ru.abdramanova.join;

import ru.abdramanova.entity.Intersection;
import ru.abdramanova.entity.Table;

import java.util.ArrayList;
import java.util.List;

public class ArrayListJoin implements InnerJoin{
    private ArrayList<Table> table1;
    private ArrayList<Table> table2;

    public ArrayListJoin(ArrayList<Table> table1, ArrayList<Table> table2) {
        this.table1 = table1;
        this.table2 = table2;
    }

    @Override
    public List<Intersection> innerJoin() {
        ArrayList<Intersection> result = new ArrayList<>();
        for(Table line1 : table1){
            for (Table line2 : table2) {
                if(line1.getId() == line2.getId()){
                    result.add(new Intersection(line1.getId(), line1.getValue(), line2.getValue()));
                }
            }
        }
        return result;
    }
}
