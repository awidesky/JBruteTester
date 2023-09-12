package io.github.awidesky.bruteTester;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * An n-tuple that holds a set of numbers in <code>int</code> type.
 * */
public class IntTuple {

	public static AtomicLong cnt = new AtomicLong();
	
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
		cnt.getAndIncrement();
		//System.out.println(this.toString() + " idx : " + idx + "*");
	}
	private IntTuple(IntTuple other, int num, int index) {
		arr = Arrays.copyOf(other.arr, other.arr.length);
		idx = index + 1;
		arr[index] = num;
		//System.out.println("Before : " + other + "\tnow : " + this);//TODO
		cnt.getAndIncrement();
		//System.out.println(this.toString() + " idx : " + idx);//TODO
	}
	
	/**
	 * Adds new number to given tuple.
	 * 
	 * @throws IllegalArgumentException If the tuple is full before addition.
	 * */
	public IntTuple add(int num, int index) {
		if(arr.length < index) {
			System.out.println("Tried to add " + num + " in : " + this.str());//TODO
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
	
	public static RootIntTupleBuilder builder(int len) {
		return new RootIntTupleBuilder(len);
	}
	
	String str() {
		return toString() + ", idx=" + idx;
	}

	@Override
	public String toString() {
		return "[" + Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(", ")) + "]";
	}

	public String id() {
		return "%010d".formatted(hashCode());
	}
	public void log(String s) {
		System.out.println(id() + " " + s);
	}
	
	public static class RootIntTupleBuilder {
		private final int len;
		
		private RootIntTupleBuilder(int len) {
			this.len = len;
		}
		
		public IntTuple build(int firstNum) {
			IntTuple ret = new IntTuple(len);
			ret.arr[0] = firstNum;
			ret.idx = 1;
			return ret;
		}
	}
}
