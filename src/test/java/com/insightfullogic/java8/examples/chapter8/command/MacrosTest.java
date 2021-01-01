package com.insightfullogic.java8.examples.chapter8.command;

import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;


public class MacrosTest {
	
	@Test
	public void testPredicate() {
		Predicate<Integer> predicate = x -> x > 3;
		System.out.println(predicate.test(10));
		
		
//		Integer[] a = new Integer[]{3, 1, 2, 4, 6, 5};
//		Comparator<Integer> comparator = Integer::compare;
//
//		Arrays.sort(a, comparator);
//		System.out.println("升序：" + Arrays.toString(a));
//
//		Arrays.sort(a,comparator.reversed());
//		System.out.println("降序："+Arrays.toString(a));
		
		String[] strs = {"java8", "is", "easy", "to", "use"};
//	    List<String[]> distinctStrs = Arrays.stream(strs) .map(str -> str.split("")).distinct().collect(Collectors.toList()) ;
//	    distinctStrs.forEach(x ->System.out.println(Arrays.toString(x)));
//		List<String[]> distinctStrs =Arrays.stream(strs).flatMap(Arrays::stream).
				// 映射成为Stream<String[]> .distinct() .collect(Collectors.toList());
		Integer[] a = new Integer[]{3, 1, 2, 5, 11, 4, 6, 5, 3, 1}; 
		List<Integer> aList = Arrays.stream(a) .distinct() .skip(1) .filter((e) -> e < 6) .peek(e -> System.out.println("循环1次")) .limit(4) .sorted() .collect(Collectors.toList()); 
		System.out.println(aList);
		
	}

    @Test
    public void classBasedCommand() {
    	
    	
    	
    	
        MockEditor editor = new MockEditor();

// BEGIN classBasedCommand
Macro macro = new Macro();
macro.record(new Open(editor));
macro.record(new Save(editor));
macro.record(new Close(editor));
macro.run();
// END classBasedCommand

        editor.check();
    }

    @Test
    public void lambdaBasedCommand() {
        MockEditor editor = new MockEditor();

// BEGIN lambdaBasedCommand
Macro macro = new Macro();
macro.record(() -> editor.open());
macro.record(() -> editor.save());
macro.record(() -> editor.close());
macro.run();
// END lambdaBasedCommand

        editor.check();
    }

    @Test
    public void referenceBasedCommand() {
        MockEditor editor = new MockEditor();

// BEGIN referenceBasedCommand
Macro macro = new Macro();
macro.record(editor::open);
macro.record(editor::save);
macro.record(editor::close);
macro.run();
// END referenceBasedCommand

        editor.check();
    }

}
