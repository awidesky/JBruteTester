package io.github.awidesky.bruteTester;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * An n-tuple that holds a set of numbers in <code>long</code> type.
 * */
public class LongTuple {

	private final long[] arr;
	private int idx = 0;
	
	/**
	 * Creates an tuple that holds <code>len</code> integers as <code>long</code> type.
	 * Created tuple is set to zero, call {@code LongTuple#add(long)} to add numbers(up to <code>len</code> numbers can be added).
	 * 
	 * @param len Size of the tuple
	 * */
	public LongTuple(int len) {
		arr = new long[len];
	}
	private LongTuple(LongTuple other) {
		arr = Arrays.copyOf(other.arr, other.arr.length);
		idx = other.idx;
	}
	
	/**
	 * Adds new number to given tuple.
	 * 
	 * @throws IllegalArgumentException If the tuple is full before addition.
	 * */
	public LongTuple add(long num) {
		if(arr.length <= idx) {
			throw new IllegalArgumentException("Parameters are " + arr.length + "-tuple(max index is " + (arr.length - 1) + "), but requested index was " + idx);
		}
		LongTuple ret = new LongTuple(this);
		ret.addToArray(num);
		return ret;
	}
	/**
	 * Get member of the tuple whose index is <code>i</code>.
	 * 
	 * @throws IllegalArgumentException If <code>i</code> is out of bounds.
	 * */
	public long get(int i) {
		if(arr.length <= i) {
			throw new IllegalArgumentException("Parameters are " + arr.length + "-tuple(max index is " + (arr.length - 1) + "), but requested index was " + idx);
		}
		return arr[i];
	}
	
	@Override
	public String toString() {
		return "[" + Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(", ")) + "]";
	}
	
	private void addToArray(long n) { arr[idx++] = n; }
}
