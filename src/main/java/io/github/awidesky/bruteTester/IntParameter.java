package io.github.awidesky.bruteTester;

import java.util.Arrays;
import java.util.stream.IntStream;


public class IntParameter {

	private final IntRange[] rangeArr;

	/**
	 * @param rangeStart <i>inclusive</i>.
	 * @param rangeBound <i>exclusive</i>.
	 * */
	public IntParameter(int rangeStart, int rangeBound) {
		rangeArr = new IntRange[] {new IntRange(rangeStart, rangeBound)};
	}
	public IntParameter(IntRange... ranges) {
		rangeArr = ranges;
	}

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

		public IntStream generate() {
			return IntStream.range(rangeStart, rangeBound);
		}
	}
}
