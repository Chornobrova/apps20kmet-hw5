package ua.edu.ucu.iter;


import ua.edu.ucu.function.IntUnaryOperator;

import java.util.Iterator;

public class MapIter implements Iterator<Integer> {
    private Iterator<Integer> iter;
    private IntUnaryOperator map;

    public MapIter(Iterator<Integer> iterator, IntUnaryOperator oper) {
        iter = iterator;
        map = oper;
    }

    public boolean hasNext() {
        return iter.hasNext();
    }
    public Integer next() {
        return map.apply(iter.next());
    }

}
