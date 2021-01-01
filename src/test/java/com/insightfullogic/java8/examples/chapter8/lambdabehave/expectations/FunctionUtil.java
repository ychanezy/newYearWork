package com.insightfullogic.java8.examples.chapter8.lambdabehave.expectations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FunctionUtil {
 
   public static <T,R> List<R> multiGetResult(List<Function<List<T>, R>> functions, List<T> list) {
     return functions.stream().map(f -> f.apply(list)).collect(Collectors.toList());
   }
 
   public static void main(String[] args) {
//     System.out.println(multiGetResult(
//         Arrays.asList(
//             list -> list.stream().collect(Collectors.summarizingInt(x->x)),
//             list -> list.stream().filter(x -> x < 50).sorted().collect(Collectors.toList()),
//             list -> list.stream().collect(Collectors.groupingBy(x->(x%2==0? "even": "odd"))),
//             list -> list.stream().sorted().collect(Collectors.toList()),
//             list -> list.stream().sorted().map(Math::sqrt).collect(Collectors.toMap(x->x, y->Math.pow(2,y)))),
//         Arrays.asList(64,49,25,16,9,4,1,81,36)));
     
     List<Integer> list = Arrays.asList(1,2,3,4,5);
     Supplier<Map<Integer,Integer>> mapSupplier = () -> list.stream().collect(Collectors.toMap(x->x, y-> y * y));
     Map<Integer,Integer> sf = list.stream().collect(Collectors.toMap(x->x, y-> y * y));
     System.out.println(sf.toString());
     Map<Integer, Integer> mapValueAdd = list.stream().collect(Collectors.toMap(x->x, y->y, (v1,v2) -> v1+v2, mapSupplier));
     System.out.println(mapValueAdd);
     /**
      * {1=1, 2=2, 3=3,  4=4,  5=5 }
      * {1=1, 2=4, 3=9,  4=16, 5=25}
      * {1=2, 2=6, 3=12, 4=20, 5=30}
      */
   }
   
  
}