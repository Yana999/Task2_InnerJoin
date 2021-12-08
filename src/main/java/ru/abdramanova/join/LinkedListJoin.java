package ru.abdramanova.join;

import ru.abdramanova.entity.Intersection;
import ru.abdramanova.entity.Table;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListJoin implements InnerJoin<LinkedList<Table>>{

    @Override
    public List<Intersection> innerJoin(LinkedList<Table> table1, LinkedList<Table> table2) {
        List<Intersection> result = new LinkedList<>();
        table1.sort(Comparator.comparing(Table::getId));
        table2.sort(Comparator.comparing(Table::getId));
        for(int i = 0, j = 0; i < table1.size() && j < table2.size();){
            if(table1.get(i).getId() == table2.get(j).getId()) {
                for(int k = j; k < table2.size() && table1.get(i).getId() == table2.get(k).getId(); ++k){
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

    /*while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                result.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]){
                i++;
            } else {
                j++;
            }
        }*/
}
