package io.github.awidesky.bruteTester.intTest;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import io.github.awidesky.bruteTester.Paramater;


public class IntParameter implements Paramater<IntTuple> {

	private final IntRange[] rangeArr;

	/**
	 * Create an <code>int</code> type parameter that has specified range.
	 * 
	 * @param rangeStart <i>inclusive</i>.
	 * @param rangeBound <i>exclusive</i>.
	 * */
	public IntParameter(int rangeStart, int rangeBound) {
		rangeArr = new IntRange[] {new IntRange(rangeStart, rangeBound)};
	}
	/**
	 * Create an <code>int</code>  type parameter that has specified ranges.
	 */
	public IntParameter(IntRange... ranges) {
		rangeArr = ranges;
	}

	/**
	 * Generate {@code IntStream} that has every possible numbers that this {@code IntParameter} can be.
	 * */
	private IntStream generateStream() {
		return Arrays.stream(rangeArr).flatMapToInt(IntRange::generate);
	}

	@Override
	public Stream<IntTuple> rootTuple(int length) {
		return generateStream().mapToObj(num -> new IntTuple(length).add(num, 0));
	}

	@Override
	public Stream<IntTuple> addTuple(IntTuple tuple, int index) {
		return generateStream().mapToObj(num -> tuple.add(num, index));
	}
	
	public static class IntRange {
		public final int rangeStart;
		public final int rangeBound;
		
		/**
		 * @param rangeStart <i>inclusive</i>.
		 * @param rangeBound <i>exclusive</i>.
		 * */
		public IntRange(int rangeStart, int rangeBound) {
			this.rangeStart = rangeStart;
			this.rangeBound = rangeBound;
		}

		/**
		 * Generate {@code IntStream} that has every possible numbers in this range.
		 * */
		public IntStream generate() {
			return IntStream.range(rangeStart, rangeBound);
		}
	}


}
