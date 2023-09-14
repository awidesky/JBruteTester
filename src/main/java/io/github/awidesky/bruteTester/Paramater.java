package io.github.awidesky.bruteTester;

import java.util.stream.Stream;

public interface Paramater<T extends Tuple> {

	public Stream<T> rootTuple(int length);
	public Stream<T> addTuple(T tuple, int index);
}
