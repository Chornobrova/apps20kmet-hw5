package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iter.FilterIter;
import ua.edu.ucu.iter.FlatMapIter;
import ua.edu.ucu.iter.MapIter;

import java.util.Iterator;
import java.util.LinkedList;

public class AsIntStream implements IntStream {
    private Iterator<Integer> iter;

    private AsIntStream(Iterator<Integer> iterator) {
        iter = iterator;
    }

    private void alreadyProcessed() {
        if (!iter.hasNext()) {
            throw new IllegalArgumentException();
        }
    }

    public static IntStream of(int... values) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int val: values) {
            list.add(val);
        }
        return new AsIntStream(list.iterator());
    }

    public double average() {
        alreadyProcessed();
        int[] res = {0, 0};
        forEach(x -> {
            res[0] += x;
            res[1] += 1;
        });
        return (double)res[0]/res[1];
    }


    public int max() {
        alreadyProcessed();
        int[] res = {Integer.MIN_VALUE};
        forEach(x -> res[0] = Math.max(res[0], x));
        return res[0];
    }

    public int min() {
        alreadyProcessed();
        int[] res = {Integer.MAX_VALUE};
        forEach(x -> res[0] = Math.min(res[0], x));
        return res[0];
    }

    public long count() {
        int[] res = {0};
        forEach(x -> res[0] += 1);
        return res[0];
    }

    public int sum() {
        alreadyProcessed();
        int[] res = {0};
        forEach(x -> res[0] += x);
        return res[0];
    }

    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIter(iter, predicate));
    }

    public void forEach(IntConsumer action) {
        while (iter.hasNext()) {
            action.accept(iter.next());
        }
    }

    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIter(iter, mapper));
    }

    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIter(iter, func));
    }

    public int reduce(int identity, IntBinaryOperator op) {
        int[] res = {identity};
        forEach(x -> res[0] = op.apply(res[0], x));
        return res[0];
    }

    public int[] toArray() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        forEach(x -> list.add(x));
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++){
            res[i] = list.get(i);
        }
        return res;
    }

}
