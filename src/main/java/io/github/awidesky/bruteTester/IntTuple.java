package io.github.awidesky.bruteTester;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * An n-tuple that holds a set of numbers in <code>int</code> type.
 * */
public class IntTuple {

	private final int[] arr;
	private int idx = 0;
	
	/**
	 * Creates an tuple that holds <code>len</code> integers as <code>int</code> type.
	 * Created tuple is set to zero, call {@code IntTuple#add(int)} to add numbers(up to <code>len</code> numbers can be added).
	 * 
	 * @param len Size of the tuple
	 * */
	public IntTuple(int len) {
		arr = new int[len];
	}
	private IntTuple(IntTuple other) {
		arr = Arrays.copyOf(other.arr, other.arr.length);
		idx = other.idx;
	}
	
	/**
	 * Adds new number to given tuple.
	 * 
	 * @throws IllegalArgumentException If the tuple is full before addition.
	 * */
	public IntTuple add(int num) {
		if(arr.length <= idx) {
			throw new IllegalArgumentException("Parameters are " + arr.length + "-tuple(max index is " + (arr.length - 1) + "), but requested index was " + idx);
		}
		IntTuple ret = new IntTuple(this);
		ret.addToArray(num);
		return ret;
	}
	/**
	 * Get member of the tuple whose index is <code>i</code>.
	 * 
	 * @throws IllegalArgumentException If <code>i</code> is out of bounds.
	 * */
	public int get(int i) {
		if(arr.length <= i) {
			throw new IllegalArgumentException("Parameters are " + arr.length + "-tuple(max index is " + (arr.length - 1) + "), but requested index was " + idx);
		}
		return arr[i];
	}
	
	@Override
	public String toString() {
		return "[" + Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(", ")) + "]";
	}
	
	private void addToArray(int n) { arr[idx++] = n; }
}
