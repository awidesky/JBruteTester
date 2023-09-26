package io.github.awidesky.bruteTester.intTest;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import io.github.awidesky.bruteTester.Parameter;


public class IntParameter implements Parameter<IntTuple> {

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
	 * Create an <code>int</code> type parameter that has specified ranges.
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
		return addTuple(new IntTuple(length), 0);
	}

	@Override
	public Stream<IntTuple> addTuple(IntTuple root, int index) {
		return generateStream().mapToObj(num -> root.add(num, index));
	}
	
	/**
	 * A a single range of {@code int} data(e.g. [-100, 100), [-2147483647 ~ 2147483647), etc..)
	 * <p>A {@code IntParameter} can have multiple {@code IntRange}, for example like y ∈ [0, 10) U (15, 20].
	 */
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
