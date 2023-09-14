package io.github.awidesky.bruteTester.longTest;

import java.util.Arrays;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import io.github.awidesky.bruteTester.Paramater;

public class LongParameter implements Paramater<LongTuple> {

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
	private LongStream generateStream() {
		return Arrays.stream(rangeArr).flatMapToLong(LongRange::generate);
	}

	@Override
	public Stream<LongTuple> rootTuple(int length) {
		return generateStream().mapToObj(num -> new LongTuple(length).add(num, 0));
	}

	@Override
	public Stream<LongTuple> addTuple(LongTuple tuple, int index) {
		return generateStream().mapToObj(num -> tuple.add(num, index));
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
