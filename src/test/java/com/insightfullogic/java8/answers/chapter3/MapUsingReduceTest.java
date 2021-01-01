package com.insightfullogic.java8.answers.chapter3;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.IntStream;

import org.junit.Test;

public class MapUsingReduceTest {

    @Test
    public void emptyList() {
        assertMapped(Function.<Object>identity(), Collections.<Object>emptyList(), Collections.<Object>emptyList());
    }

    @Test
    public void identityMapsToItself() {
        assertMapped((Integer x) -> x, asList(1, 2, 3), asList(1, 2, 3));
    }

    @Test
    public void incrementingNumbers() {
        assertMapped((Integer x) -> x + 2, asList(1, 2, 3), asList(3, 4, 5));
    }

    private <I, O> void assertMapped(Function<I, O> mapper, List<I> input, List<O> expectedOutput) {
        List<O> output = MapUsingReduce.map(input.stream(), mapper);
        assertEquals(expectedOutput, output);

        List<O> parallelOutput = MapUsingReduce.map(input.parallelStream(), mapper);
        assertEquals(expectedOutput, parallelOutput);
    }
    
    /**
     * 你的代码中每次BinaryOperator的两个形参是相等的原因是reduce操作的第三个参数BinaryOperator<U> 
     * combiner 操作的对象是第二个参数BiFunction<U,? super T,U> accumulator的返回值。
     * 你的accumulator中将累加的值添加到初始值中，然后把初始值返回。也就是说所有线程运行的accumlator
     * 都操作的是同一个list ，返回值也都是同一个list。所以combiner 接收的参数都是同一个list。
     * 如果你的accumulator中不改变初始值而是返回一个新的值，combiner接收的结果就是不同线程的accumulator
     * 的不同的结果了。
	 * 代码如下:
     */
    public static void main(String[] args) {
        //accumulator不写入list,不需要线程同步,初始值使用普通的list
        List<Integer> list = new ArrayList<>();
        AtomicInteger accumulateCount = new AtomicInteger(0);
        AtomicInteger combineCount = new AtomicInteger(0);
        List<Integer> reduceResult = IntStream.range(0, 100)
                .parallel()
                .boxed()
                .reduce(list, (i, j) -> {
                    accumulateCount.incrementAndGet();
                    //不改变初始的i,而是返回一个新的i
                    ArrayList<Integer> newI = new ArrayList<>(i);
                    newI.add(j);
                    return newI;
                }, (i, j) -> {
                    combineCount.incrementAndGet();
                    System.out.println(String.format("i==j: %s, thread name:%s", i == j, Thread.currentThread().getName()));
                    ArrayList<Integer> newI = new ArrayList<>(i);
                    newI.addAll(j);
                    return newI;
                });
        System.out.println("---------------------------------------");
        System.out.println("reduce result size: "+reduceResult.size());
        System.out.println("reduce result : "+reduceResult);
        System.out.println("accumulateCount: "+accumulateCount.get());
        System.out.println("combineCount: "+combineCount.get());
    }

}
