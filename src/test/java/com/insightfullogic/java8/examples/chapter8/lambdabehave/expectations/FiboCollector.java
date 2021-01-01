package com.insightfullogic.java8.examples.chapter8.lambdabehave.expectations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
public class FiboCollector implements Collector<Integer, List<Integer>, List<Integer>> {
 
  public Supplier<List<Integer>> supplier() {
    return () -> {
      List<Integer> result = new ArrayList<>();
      result.add(0); result.add(1);
      return result;
    };
  }
 
  @Override
  public BiConsumer<List<Integer>, Integer> accumulator() {
    return (res, num) -> {
      Integer next = res.get(res.size()-1) + res.get(res.size()-2);
      res.add(next);
    };
  }
 
  @Override
  public BinaryOperator<List<Integer>> combiner() {
    return null;
    //return (left, right) -> { left.addAll(right); return left; };
  }
 
  @Override
  public Function<List<Integer>, List<Integer>> finisher() {
    return res -> { res.remove(0); res.remove(1); return res; };
  }
 
  @Override
  public Set<Characteristics> characteristics() {
    return Collections.emptySet();
  }
 
  public static void main(String[] args) {
	  Supplier<List<Integer>> a = () ->  new FiboCollector().supplier().get();
	  System.out.println(Arrays.asList(a.get()));
	  List<Integer> fibo = Arrays.asList(1,2,3,4,5,6,7,8,9,10).stream().collect(new FiboCollector());
	  System.out.println(fibo);
	  
//	  List<Integer> nums = Arrays.asList(Arrays.asList(1,2,3), Arrays.asList(1,4,9), Arrays.asList(1,8,27))
//              .stream().flatMap(List<Integer>::stream).collect(Collectors.toList());
	  List<Integer> nums = Arrays.asList(Arrays.asList(1,2,3), Arrays.asList(1,4,9), Arrays.asList(1,8,27))
              .stream().flatMap(x -> x.stream()).collect(Collectors.toList());
System.out.println(nums);
  }
}
 
