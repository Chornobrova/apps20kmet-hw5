package ua.edu.ucu.iter;

import ua.edu.ucu.function.IntToIntStreamFunction;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FlatMapIter implements Iterator<Integer> {
    private Iterator<Integer> iter;
    private IntToIntStreamFunction streamFunc;
    private int[] stream;
    private int index;
    private Integer current;

    public FlatMapIter(
            Iterator<Integer> iterator,
            IntToIntStreamFunction func) {
        iter = iterator;
        streamFunc = func;
        index = 0;
        stream = new int[] {};
        current = getNext();
    }

    private Integer getNext() {
        if (index != stream.length) {
            index += 1;
            return stream[index - 1];
        }
        while (iter.hasNext()) {
            stream = streamFunc.applyAsIntStream(iter.next()).toArray();
            if (stream.length != 0) {
                index = 1;
                return stream[0];
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
