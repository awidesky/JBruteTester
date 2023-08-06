package io.github.awidesky.bruteTester;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LongTuple {

	private final long[] arr;
	private int idx = 0;
	
	public LongTuple(int len) {
		arr = new long[len];
	}
	private LongTuple(LongTuple other) {
		arr = Arrays.copyOf(other.arr, other.arr.length);
		idx = other.idx;
	}
	
	public LongTuple add(long num) {
		if(arr.length <= idx) {
			throw new IllegalArgumentException("Parameters are " + arr.length + "-tuple(max index is " + (arr.length - 1) + "), but requested index was " + idx);
		}
		LongTuple ret = new LongTuple(this);
		ret.addToArray(num);
		return ret;
	}
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
