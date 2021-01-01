package com.insightfullogic.java8.exercises.java8;

public interface Stream<T> extends BaseStream<T, Stream<T>>  {

	 Stream<T> filter(Predicate<? super T> predicate);
}
