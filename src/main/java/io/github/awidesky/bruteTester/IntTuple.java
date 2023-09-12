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
	private int nextIdx = 0; //index to put next data
	private int filledIdx = -1; //last filled index

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
		//System.out.println(this.toString() + " nextIdx : " + nextIdx + "*");
	}
	private IntTuple(IntTuple other, int num) {
		arr = Arrays.copyOf(other.arr, other.arr.length);
		nextIdx = other.nextIdx;
		arr[filledIdx = nextIdx] = num;
		//System.out.println("Before : " + other + "\tnow : " + this);//TODO
		cnt.getAndIncrement();
		//System.out.println(this.toString() + " nextIdx : " + nextIdx);//TODO
	}
	
	/**
	 * Adds new number to given tuple.
	 * 
	 * @throws IllegalArgumentException If the tuple is full before addition.
	 * */
	public IntTuple add(int num) {
		if(arr.length <= nextIdx) {
			System.out.println("Tried to add " + num + " in : " + this.str());//TODO
			throw new IllegalArgumentException("Parameters are " + arr.length + "-tuple(max index is " + (arr.length - 1) + "), but requested index was " + nextIdx);
		}
		if(filledIdx == nextIdx) return new IntTuple(this, num);
		else {
			arr[++filledIdx] = num;
			return this;
		} 
	}
	
	void resetRank() {
		this.nextIdx++;
	}
	/**
	 * Get member of the tuple whose index is <code>i</code>.
	 * 
	 * @throws IllegalArgumentException If <code>i</code> is out of bounds.
	 * */
	public int get(int i) {
		if(arr.length <= i) {
			throw new IllegalArgumentException("Parameters are " + arr.length + "-tuple(max index is " + (arr.length - 1) + "), but requested index was " + nextIdx);
		}
		return arr[i];
	}
	
	public static RootIntTupleBuilder builder(int len) {
		return new RootIntTupleBuilder(len);
	}
	
	String str() {
		return toString() + ", nextIdx=" + nextIdx + ", filledIdx=" + filledIdx;
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
			ret.nextIdx = 1;
			ret.filledIdx = 0;
			return ret;
		}
	}
}
