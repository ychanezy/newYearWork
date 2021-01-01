package com.insightfullogic.java8.answers.chapter5;

import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import com.insightfullogic.java8.answers.chapter5.LongestName;
import org.junit.Test;
import org.testng.collections.Lists;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class LongestNameTest {

    @Test
    public void findsLongestNameByReduce() {
        Artist artist = com.insightfullogic.java8.answers.chapter5.LongestName.byReduce(SampleData.getThreeArtists());
        assertEquals(SampleData.johnColtrane, artist);
    }

    @Test
    public void findsLongestNameByCollecting() {
        Artist artist = LongestName.byCollecting(SampleData.getThreeArtists());
        assertEquals(SampleData.johnColtrane, artist);
    }
    
    @Test
    public void testReduce() {
    	ArrayList<Integer> accResult_ = Stream.of(1, 2, 3, 4)
    	        .reduce(new ArrayList<Integer>(),
    	                new BiFunction<ArrayList<Integer>, Integer, ArrayList<Integer>>() {
    	                    @Override
    	                    public ArrayList<Integer> apply(ArrayList<Integer> acc, Integer item) {
    	 
    	                        acc.add(item);
    	                        System.out.println("item: " + item);
    	                        System.out.println("acc+ : " + acc);
    	                        System.out.println("BiFunction");
    	                        return acc;
    	                    }
    	                }, new BinaryOperator<ArrayList<Integer>>() {
    	                    @Override
    	                    public ArrayList<Integer> apply(ArrayList<Integer> acc, ArrayList<Integer> item) {
    	                        System.out.println("BinaryOperator");
    	                        acc.addAll(item);
    	                        System.out.println("item: " + item);
    	                        System.out.println("acc+ : " + acc);
    	                        System.out.println("--------");
    	                        return acc;
    	                    }
    	                });
    	System.out.println("accResult_: " + accResult_);
    }
    
    @Test
    public void testCollectNum() {
    	final Integer[] integers = Lists.newArrayList(1, 2, 3, 4, 5)
    	        .stream()
    	        .collect(() -> new Integer[]{0}, (a, x) -> a[0] += x, (a1, a2) -> a1[0] += a2[0]);
    	System.out.println(integers);
    }

}
