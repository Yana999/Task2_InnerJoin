package ru.abdramanova.join;

import ru.abdramanova.entity.Intersection;
import java.util.List;

public interface InnerJoin <T> {
   List<Intersection> innerJoin(T table1, T table2);
}

