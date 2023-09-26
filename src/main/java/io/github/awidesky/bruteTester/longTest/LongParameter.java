package io.github.awidesky.bruteTester.longTest;

import java.util.Arrays;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import io.github.awidesky.bruteTester.Parameter;

/**
 * Denotes a parameter of a brute test whose type is {@code long}.
 * 
 * @see LongBruteTester
 * @see LongTuple
 */
public class LongParameter implements Parameter<LongTuple> {

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
		return addTuple(new LongTuple(length), 0);
	}

	@Override
	public Stream<LongTuple> addTuple(LongTuple root, int index) {
		return generateStream().mapToObj(num -> root.add(num, index));
	}
	
	/**
	 * A a single range of {@code long} data(e.g. [-100, 100), [-9223372036854775805, 9223372036854775807), etc..)
	 * <p>A {@code LongParameter} can have multiple {@code LongRange}, for example like y ∈ [0, 10) U (15, 20].
	 */
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
