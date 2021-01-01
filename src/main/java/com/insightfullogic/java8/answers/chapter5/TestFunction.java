package com.insightfullogic.java8.answers.chapter5;

import java.util.stream.Stream;

public interface TestFunction<T,R> {

	R test(T t);
	
	default  <V> TestFunction<V,R> compose(TestFunction<? super V, ? extends T> before) {
		return (V v) -> test(before.test(v));
		
	}
	default  TestFunction<T,T> identity(){
		return t -> t;
		
	}
}
