package io.github.awidesky.bruteTesterTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Disabled;

import io.github.awidesky.bruteTester.IntBruteTester;
import io.github.awidesky.bruteTester.IntParameter;
import io.github.awidesky.bruteTester.IntParameter.IntRange;
import io.github.awidesky.bruteTester.IntTuple;
import io.github.awidesky.bruteTester.LongBruteTester;
import io.github.awidesky.bruteTester.LongParameter;
import io.github.awidesky.bruteTester.LongParameter.LongRange;
import io.github.awidesky.bruteTester.LongTuple;

class Test {

	@Disabled
	@org.junit.jupiter.api.Test
	void intCompleteEnumeration() {
		IntBruteTester it = new IntBruteTester(new IntParameter(0, 5), new IntParameter(new IntRange(0,3), new IntRange(5,9)));
		assertEquals(Arrays.stream(it.bruteTest(t -> true)).map(IntTuple::toString).collect(Collectors.joining(" ")),
				"[0, 0] [0, 1] [0, 2] [0, 5] [0, 6] [0, 7] [0, 8] [1, 0] [1, 1] [1, 2] [1, 5] [1, 6] [1, 7] [1, 8] [2, 0] [2, 1] [2, 2] [2, 5] "
				+ "[2, 6] [2, 7] [2, 8] [3, 0] [3, 1] [3, 2] [3, 5] [3, 6] [3, 7] [3, 8] [4, 0] [4, 1] [4, 2] [4, 5] [4, 6] [4, 7] [4, 8]");
	}

	@Disabled
	@org.junit.jupiter.api.Test
	void intTest1() {
		IntBruteTester it = new IntBruteTester(new IntParameter(0, 10), new IntParameter(new IntRange(0,10)));
		assertEquals(Arrays.stream(it.bruteTest(t -> t.get(0) == t.get(1))).map(IntTuple::toString).collect(Collectors.joining(" ")),
				"[0, 0] [1, 1] [2, 2] [3, 3] [4, 4] [5, 5] [6, 6] [7, 7] [8, 8] [9, 9]");
	}

	@Disabled
	@org.junit.jupiter.api.Test
	void intTest2() {
		IntBruteTester it = new IntBruteTester(new IntParameter(0, 10), new IntParameter(new IntRange(0,10)));
		assertEquals(Arrays.stream(it.bruteTest(t -> t.get(0) - t.get(1) == 3)).map(IntTuple::toString).collect(Collectors.joining(" ")),
				"[3, 0] [4, 1] [5, 2] [6, 3] [7, 4] [8, 5] [9, 6]");
	}

	@Disabled
	@org.junit.jupiter.api.Test
	void longCompleteEnumeration() {
		LongBruteTester it = new LongBruteTester(new LongParameter(0, 5), new LongParameter(new LongRange(0,3), new LongRange(5,9)));
		assertEquals(Arrays.stream(it.bruteTest(t -> true)).map(LongTuple::toString).collect(Collectors.joining(" ")),
				"[0, 0] [0, 1] [0, 2] [0, 5] [0, 6] [0, 7] [0, 8] [1, 0] [1, 1] [1, 2] [1, 5] [1, 6] [1, 7] [1, 8] [2, 0] [2, 1] [2, 2] [2, 5] "
						+ "[2, 6] [2, 7] [2, 8] [3, 0] [3, 1] [3, 2] [3, 5] [3, 6] [3, 7] [3, 8] [4, 0] [4, 1] [4, 2] [4, 5] [4, 6] [4, 7] [4, 8]");
	}

	@Disabled
	@org.junit.jupiter.api.Test
	void longTest1() {
		LongBruteTester it = new LongBruteTester(new LongParameter(0, 10), new LongParameter(new LongRange(0,10)));
		assertEquals(Arrays.stream(it.bruteTest(t -> t.get(0) == t.get(1))).map(LongTuple::toString).collect(Collectors.joining(" ")),
				"[0, 0] [1, 1] [2, 2] [3, 3] [4, 4] [5, 5] [6, 6] [7, 7] [8, 8] [9, 9]");
	}

	@Disabled
	@org.junit.jupiter.api.Test
	void longTest2() {
		LongBruteTester it = new LongBruteTester(new LongParameter(0, 10), new LongParameter(new LongRange(0,10)));
		assertEquals(Arrays.stream(it.bruteTest(t -> t.get(0) - t.get(1) == 3)).map(LongTuple::toString).collect(Collectors.joining(" ")),
				"[3, 0] [4, 1] [5, 2] [6, 3] [7, 4] [8, 5] [9, 6]");
	}
	@org.junit.jupiter.api.Test
	void cntTest() {
		int n = 10;
		IntBruteTester it = new IntBruteTester(new IntParameter(0,n), new IntParameter(0,n), new IntParameter(0,n), new IntParameter(0,n));
		long i = Arrays.stream(it.bruteTest(t -> false)).count();
		System.out.println(IntTuple.cnt.getAcquire());
		assertEquals(0, i);
	}
}
