package io.github.awidesky.bruteTesterTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;

import io.github.awidesky.bruteTester.intTest.IntBruteTester;
import io.github.awidesky.bruteTester.intTest.IntParameter;
import io.github.awidesky.bruteTester.intTest.IntTuple;
import io.github.awidesky.bruteTester.intTest.IntParameter.IntRange;

class IntTupleTest {

	@org.junit.jupiter.api.Test
	void iteration() {
		IntBruteTester it = new IntBruteTester(new IntParameter(0, 5), new IntParameter(new IntRange(0,9)));
		assertEquals("[0, 0] [0, 1] [0, 2] [0, 3] [0, 4] [0, 5] [0, 6] [0, 7] [0, 8] [1, 0] [1, 1] [1, 2] [1, 3] "
				+ "[1, 4] [1, 5] [1, 6] [1, 7] [1, 8] [2, 0] [2, 1] [2, 2] [2, 3] [2, 4] [2, 5] [2, 6] [2, 7] [2, 8] "
				+ "[3, 0] [3, 1] [3, 2] [3, 3] [3, 4] [3, 5] [3, 6] [3, 7] [3, 8] [4, 0] [4, 1] [4, 2] [4, 3] [4, 4] [4, 5] [4, 6] [4, 7] [4, 8]", 
				it.bruteTest(t -> true).map(IntTuple::toString).collect(Collectors.joining(" ")));
	}
	@org.junit.jupiter.api.Test
	void rangeParts() {
		IntBruteTester it = new IntBruteTester(new IntParameter(0, 5), new IntParameter(new IntRange(0,3), new IntRange(5,9)));
		assertEquals("[0, 0] [0, 1] [0, 2] [0, 5] [0, 6] [0, 7] [0, 8] [1, 0] [1, 1] [1, 2] [1, 5] [1, 6] [1, 7] [1, 8] [2, 0] "
				+ "[2, 1] [2, 2] [2, 5] [2, 6] [2, 7] [2, 8] [3, 0] [3, 1] [3, 2] [3, 5] [3, 6] [3, 7] [3, 8] [4, 0] [4, 1] [4, 2] "
				+ "[4, 5] [4, 6] [4, 7] [4, 8]", 
				it.bruteTest(t -> true).map(IntTuple::toString).collect(Collectors.joining(" ")));
	}
	/**
	 * Find the set : {(x, y) | x = y, x ∈ [0, 10), y ∈ [0, 10)}
	 */
	@org.junit.jupiter.api.Test
	void test1() {
		IntBruteTester it = new IntBruteTester(new IntParameter(0, 10), new IntParameter(new IntRange(0,10)));
		assertEquals("[0, 0] [1, 1] [2, 2] [3, 3] [4, 4] [5, 5] [6, 6] [7, 7] [8, 8] [9, 9]",
				it.bruteTest(t -> t.get(0) == t.get(1)).map(IntTuple::toString).collect(Collectors.joining(" ")));
	}
	/**
	 * Find the set : {(x, y) | x - y = 3, 0 <= x <= 9, 0 <= y <= 9}
	 */
	@org.junit.jupiter.api.Test
	void test2() {
		IntBruteTester it = new IntBruteTester(new IntParameter(0, 10), new IntParameter(new IntRange(0,10)));
		assertEquals("[3, 0] [4, 1] [5, 2] [6, 3] [7, 4] [8, 5] [9, 6]",
				it.bruteTest(t -> t.get(0) - t.get(1) == 3).map(IntTuple::toString).collect(Collectors.joining(" ")));
	}

}
