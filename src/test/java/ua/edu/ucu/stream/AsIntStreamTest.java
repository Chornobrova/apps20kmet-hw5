package ua.edu.ucu.stream;

import org.junit.Test;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AsIntStreamTest {

    @Test
    public void testConstruct() {
        int[] data = new int[] {1, 2, 3, 4, 5, -5, -4, -3, -2, -1};
        IntStream stream = AsIntStream.of(data);
        assertArrayEquals(data, stream.toArray());

        data = new int[] {7, 6, 5};
        stream = AsIntStream.of(data);
        assertArrayEquals(data, stream.toArray());

        data = new int[] {};
        stream = AsIntStream.of(data);
        assertArrayEquals(data, stream.toArray());
    }

    @Test
    public void testAverage() {
        int[] data = new int[] {1, 2, 3, 4, 5, -5, -4, -3, -2, -1};
        double actualResult = AsIntStream.of(data).average();
        double expectedResult = 0.0;

        assertEquals(expectedResult, actualResult, 0.001);

        data = new int[] {7, 6, 5};
        actualResult = AsIntStream.of(data).average();
        expectedResult = 6.0;
        assertEquals(expectedResult, actualResult, 0.001);

    }

    @Test
    public void testMin() {
        int[] data = new int[] {1, 2, 3, 4, 5, -5, -4, -3, -2, -1};
        int actualResult = AsIntStream.of(data).min();
        int expectedResult = -5;

        assertEquals(expectedResult, actualResult);

        data = new int[] {7, 6, 5};
        actualResult = AsIntStream.of(data).min();
        expectedResult = 5;
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testMax() {
        int[] data = new int[] {1, 2, 3, 4, 5, -5, -4, -3, -2, -1};
        int actualResult = AsIntStream.of(data).max();
        int expectedResult = 5;

        assertEquals(expectedResult, actualResult);

        data = new int[] {7, 6, 5};
        actualResult = AsIntStream.of(data).max();
        expectedResult = 7;
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testCount() {
        int[] data = new int[] {1, 2, 3, 4, 5, -5, -4, -3, -2, -1};
        long actualResult = AsIntStream.of(data).count();
        long expectedResult = 10;

        assertEquals(expectedResult, actualResult);

        data = new int[] {7, 6, 5};
        actualResult = AsIntStream.of(data).count();
        expectedResult = 3;
        assertEquals(expectedResult, actualResult);

        data = new int[] {};
        actualResult = AsIntStream.of(data).count();
        expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testSum() {
        int[] data = new int[] {1, 2, 3, 4, 5, -5, -4, -3, -2, -1};
        int actualResult = AsIntStream.of(data).sum();
        int expectedResult = 0;

        assertEquals(expectedResult, actualResult);

        data = new int[]{7, 6, 5};
        actualResult = AsIntStream.of(data).sum();
        expectedResult = 18;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testMap() {
        int[] data = new int[] {1, 2, 3, 4, 5, -5, -4, -3, -2, -1};
        int[] actualResult = AsIntStream.of(data).map(x -> 2 - x).toArray();
        int[] expectedResult = new int[] {1, 0, -1, -2, -3, 7, 6, 5, 4, 3};
        assertArrayEquals(expectedResult, actualResult);

        data = new int[] {7, 6, 5};
        actualResult = AsIntStream.of(data).map(x -> 2).toArray();
        expectedResult = new int[] {2, 2, 2};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void testForEach() {
        int[] data = new int[] {1, 2, 3, 4, 5, -5, -4, -3, -2, -1};
        int[] expectedResult = new int[] {10};
        int[] actualResult = {0};
        AsIntStream.of(data).forEach(x -> actualResult[0] += 1);
        assertArrayEquals(expectedResult, actualResult);

        data = new int[] {7, 6, 5};
        int[] secondActualResult = {0};
        expectedResult = new int[] {18};
        AsIntStream.of(data).forEach(x -> secondActualResult[0] += x);
        assertArrayEquals(expectedResult, secondActualResult);
    }

    @Test
    public void testFilter() {
        int[] data = new int[] {1, 2, 3, 4, 5, -5, -4, -3, -2, -1};
        int[] actualResult = AsIntStream.of(data).filter(x -> x % 2 != 0).toArray();
        int[] expectedResult = new int[] {1, 3, 5, -5, -3, -1};
        assertArrayEquals(expectedResult, actualResult);

        data = new int[] {7, 6, 5};
        actualResult = AsIntStream.of(data).filter(x -> x != 5 && x <= 6).toArray();
        expectedResult = new int[] {6};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void testFlatMap() {
        int[] data = new int[] {1, 2, 3, 4, 5, -5, -4, -3, -2, -1};
        int[] actualResult = AsIntStream.of(data).flatMap(x -> AsIntStream.of(1)).toArray();
        int[] expectedResult = new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        assertArrayEquals(expectedResult, actualResult);

        data = new int[] {7, 6, 5};
        actualResult = AsIntStream.of(data).flatMap(x -> AsIntStream.of(x, 1)).toArray();
        expectedResult = new int[] {7, 1, 6, 1, 5, 1};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void testReduce() {
        int[] data = new int[] {1, 2, 3, 4, 5, -5, -4, -3, -2, -1};
        int actualResult = AsIntStream.of(data).reduce(10, (a, b) -> a - b);
        int expectedResult = 10;

        assertEquals(expectedResult, actualResult);

        data = new int[] {7, 6, 5};
        actualResult = AsIntStream.of(data).reduce(11, (a, b) -> a + 1);
        expectedResult = 14;
        assertEquals(expectedResult, actualResult);

        data = new int[] {};
        actualResult = AsIntStream.of(data).reduce(11, (a, b) -> a + 1);
        expectedResult = 11;
        assertEquals(expectedResult, actualResult);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinError() {
        int[] data = new int[] {};
        AsIntStream.of(data).min();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxError() {
        int[] data = new int[] {};
        AsIntStream.of(data).max();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAvgError() {
        int[] data = new int[] {};
        AsIntStream.of(data).average();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSumError() {
        int[] data = new int[] {};
        AsIntStream.of(data).sum();
    }

    @Test
    public void testAfterProcessing() {
        int[] data = new int[] {1, 2, 3, 4, 5, -5, -4, -3, -2, -1};
        IntStream stream = AsIntStream.of(data);
        stream.sum();
        long actualResult = stream.count();
        long expectedResult = 0;
        assertEquals(expectedResult, actualResult);

        stream = AsIntStream.of(data);
        stream.min();
        actualResult = stream.count();
        assertEquals(expectedResult, actualResult);

        stream = AsIntStream.of(data);
        stream.max();
        actualResult = stream.count();
        assertEquals(expectedResult, actualResult);

        stream = AsIntStream.of(data);
        stream.average();
        actualResult = stream.count();
        assertEquals(expectedResult, actualResult);

        stream = AsIntStream.of(data);
        stream.reduce(1, (a, b) -> a*b);
        actualResult = stream.count();
        assertEquals(expectedResult, actualResult);

        stream = AsIntStream.of(data);
        stream.count();
        actualResult = stream.count();
        assertEquals(expectedResult, actualResult);

        stream = AsIntStream.of(data);
        int[] res = {0};
        stream.forEach(x -> res[0] += 1);
        actualResult = stream.count();
        assertEquals(expectedResult, actualResult);
    }

}
