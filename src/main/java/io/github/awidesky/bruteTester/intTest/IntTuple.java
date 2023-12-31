package io.github.awidesky.bruteTester.intTest;

import java.util.Arrays;
import java.util.stream.Collectors;

import io.github.awidesky.bruteTester.Tuple;

/**
 * An n-tuple that holds a set <code>int</code>.
 * */
public class IntTuple implements Tuple {

	private final int[] arr;
	private int idx = 0; //index to put next data

	/**
	 * Creates an tuple that holds <code>len</code> integers as <code>int</code> type.
	 * Created tuple is set to zero, call {@code IntTuple#add(int)} to add numbers(up to <code>len</code> numbers can be added).
	 * 
	 * @param len Size of the tuple
	 * */
	public IntTuple(int len) {
		arr = new int[len];
		Arrays.fill(arr, -1);
	}
	private IntTuple(IntTuple other, int num, int index) {
		arr = Arrays.copyOf(other.arr, other.arr.length);
		idx = index + 1;
		arr[index] = num;
	}
	
	/**
	 * Adds new number to given tuple.
	 * 
	 * @throws IllegalArgumentException If the tuple is full before addition.
	 * */
	public IntTuple add(int num, int index) {
		if(arr.length <= index) {
			throw new IllegalArgumentException("Parameters are " + arr.length + "-tuple(max index is " + (arr.length - 1) + "), but requested index was " + index);
		}
		if(index != idx) return new IntTuple(this, num, index);
		else {
			arr[idx++] = num;
			return this;
		} 
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
	public int length() {
		return arr.length;
	}
	
	@Override
	public String toString() {
		return "[" + Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(", ")) + "]";
	}
}
