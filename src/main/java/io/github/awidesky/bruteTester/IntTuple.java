package io.github.awidesky.bruteTester;

import java.util.Arrays;
import java.util.stream.Collectors;

public class IntTuple {

	private final int[] arr;
	private int idx = 0;
	
	public IntTuple(int len) {
		arr = new int[len];
	}
	private IntTuple(IntTuple other) {
		arr = Arrays.copyOf(other.arr, other.arr.length);
		idx = other.idx;
	}
	
	public IntTuple add(int num) {
		if(arr.length <= idx) {
			throw new IllegalArgumentException("Parameters are " + arr.length + "-tuple(max index is " + (arr.length - 1) + "), but requested index was " + idx);
		}
		IntTuple ret = new IntTuple(this);
		ret.addToArray(num);
		return ret;
	}
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
