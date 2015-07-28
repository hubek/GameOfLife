package com.hubson.lifegame.processor;

public interface Processor<T> {
	public T execute(T t);
}
