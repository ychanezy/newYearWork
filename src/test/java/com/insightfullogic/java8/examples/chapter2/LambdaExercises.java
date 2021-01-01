package com.insightfullogic.java8.examples.chapter2;

import org.junit.Test;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter3.Refactor.LongTrackFinder;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toSet;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class LambdaExercises {

    @Test
    public void _1a() {
    	//predicate
    	Predicate<Integer> predicate = x -> x > 3;

    	predicate.test(10);//true
    	System.out.println(predicate.test(10));
    	predicate.negate().test(10);//false
    	predicate.or(x -> x < 1).and(x -> x > -1).negate().test(-1);//true
    	
    	//Comparator
    	Integer[] a = new Integer[]{3, 1, 2, 4, 6, 5};
    	Comparator<Integer> comparator = Integer::compare;

    	Arrays.sort(a, comparator);
    	System.out.println("升序：" + Arrays.toString(a));

    	Arrays.sort(a,comparator.reversed());
    	System.out.println("降序："+Arrays.toString(a));
    	//
    	/**
    	 * java.util.function.Supplier
			该类只包含方法：
			T get();
			Supplier接口是在1.8中新出现的函数接口，用于支持函数式编程。它用于返回一个任意泛型的实例对象，与工厂的功能类似。
		 */
    	
    	
    	
        assertTrue("Shown in the next chapter", true);
    }

    @Test
    public void _1b() {
        // If you were to model each operation on a calculator as a function.
        Function<Double, Double> negate = (x) -> -1 * x;
        Function<Double, Double> square = (x) -> x * x;
        Function<Double, Double> percent = (x) -> 100 * x;
    }

    @Test
    public void _1c() {
        Function<Integer, Integer> one = x -> x + 1;
        // 2 isn't
        Function<Integer, Boolean> three = x -> x == 1;
    }

    @Test
    public void _2a() {
        assertTrue("ThreadLocal.withInitial", true);
    }

    @Test
    public void _2b() {
        ThreadLocal<DateFormat> threadSafeFormatter = ThreadLocal.withInitial(() -> DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.UK));
        DateFormat formatter = threadSafeFormatter.get();
        assertEquals("01-Jan-1970", formatter.format(new Date(0)));
    }

    @Test
    public void _3a() {
        // yes
        Runnable helloWorld = () -> System.out.println("hello world");
    }

    @Test
    public void _3b() {
        // Yes
        JButton button = new JButton();
        button.addActionListener(event -> System.out.println(event.getActionCommand()));
    }

    @Test
    public void _3c() {
        // No
        // check(x -> x > 5);
    	 Frame f = new Frame("Test");
         Button button = new Button("Press me!");
//    	 button.addActionListener(new ActionListener() {
//    		public void actionPerformed(ActionEvent event) {
//    			System.out.println("button clicked");
//    		}
//    	});
         button.addActionListener(evetn -> System.out.println("\"button clicked\""));
         f.add(button,BorderLayout.CENTER);
         f.pack();//窗口包围按钮
         f.setVisible(true);
         
         	
    }
//    public Set<Album> findLongTracks(List<Album> albums) {
// 	    return albums.stream()
// 	    		.filter(a -> a.getTracks().anyMatch(t -> (t.rating >= 4)))
// 	            .sorted(Comparator.comparing(a -> a.name))
// 	            .collect(toSet());
// 	}
    private boolean check(Predicate<Integer> predicate) {
        System.out.println("wat?");
        return true;
    }

    interface IntPred {
        boolean test(Integer value);
    }

    private boolean check(IntPred predicate) {
        return true;
    }
    
    

}
