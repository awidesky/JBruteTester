package io.github.awidesky.bruteTester;

import java.util.Arrays;
import java.util.stream.IntStream;


public class IntParameter {

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
	public IntStream generateStream() {
		return Arrays.stream(rangeArr).flatMapToInt(IntRange::generate);
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
