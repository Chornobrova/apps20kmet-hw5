package ua.edu.ucu.iter;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilterIter implements Iterator<Integer> {
    private Iterator<Integer> iter;
    private IntPredicate pred;
    private Integer current;

    public FilterIter(Iterator<Integer> iterator, IntPredicate predicate) {
        iter = iterator;
        pred = predicate;
        current =  getNext();
    }

    private Integer getNext() {
        while (iter.hasNext()) {
            Integer val = iter.next();
            if (pred.test(val)) {
                return val;
            }
        }
        return null;
    }

    public boolean hasNext() {
        return current != null;
    }
    public Integer next() {
        if (current == null) {
            throw new NoSuchElementException();
        }
        Integer val = current;
        current = getNext();
        return val;
    }

}
