package io.github.awidesky.bruteTester;

import java.util.Arrays;
import java.util.stream.LongStream;

public class LongParameter {

	private final LongRange[] rangeArr;

	/**
	 * @param rangeStart <i>inclusive</i>.
	 * @param rangeBound <i>exclusive</i>.
	 * */
	public LongParameter(long rangeStart, long rangeBound) {
		rangeArr = new LongRange[] { new LongRange(rangeStart, rangeBound) };
	}
	public LongParameter(LongRange... ranges) {
		rangeArr = ranges;
	}

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

		public LongStream generate() {
			return LongStream.range(rangeStart, rangeBound);
		}
	}
}
