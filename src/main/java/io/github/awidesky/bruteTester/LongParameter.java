package io.github.awidesky.bruteTester;

import java.util.Arrays;
import java.util.stream.LongStream;

public class LongParameter {

	private final LongRange[] rangeArr;

	/**
	 * Create an <code>long</code> type parameter that has specified range.
	 * 
	 * @param rangeStart <i>inclusive</i>.
	 * @param rangeBound <i>exclusive</i>.
	 * */
	public LongParameter(long rangeStart, long rangeBound) {
		rangeArr = new LongRange[] { new LongRange(rangeStart, rangeBound) };
	}
	
	/**
	 * Create an <code>long</code>  type parameter that has specified ranges.
	 */
	public LongParameter(LongRange... ranges) {
		rangeArr = ranges;
	}

	/**
	 * Generate {@code LongStream} that has every possible numbers that this {@code LongParameter} can be.
	 * */
	public LongStream generateStream() {
		return Arrays.stream(rangeArr).flatMapToLong(LongRange::generate);
	}
	
	public static class LongRange {
		public final long rangeStart;
		public final long rangeBound;
		
		/**
		 * @param rangeStart <i>inclusive</i>.
		 * @param rangeBound <i>exclusive</i>.
		 * */
		public LongRange(long rangeStart, long rangeBound) {
			this.rangeStart = rangeStart;
			this.rangeBound = rangeBound;
		}

		/**
		 * Generate {@code LongStream} that has every possible numbers in this range.
		 * */
		public LongStream generate() {
			return LongStream.range(rangeStart, rangeBound);
		}
	}
}
