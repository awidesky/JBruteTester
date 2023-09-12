package io.github.awidesky.bruteTester;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class IntBruteTester {
	
	private final IntParameter[] params;
	
	/**
	 * Creates new brute force tester that uses given <code>int</code> type parameter.
	 * */
	public IntBruteTester(IntParameter... param) { params = param; }
	
	/**
	 * Returns length of parameters of this tester.
	 * */
	public int paramLen() { return params.length; }
	
	/**
	 * Find out how many tuples can satisfy the given condition, and returns a array that holds
	 * tuples that satisfies the condition.
	 * 
	 * @param condition condition to test
	 * @return <code>int</code> tuples that satisfies the condition.
	 * */
	public Stream<IntTuple> bruteTest(Predicate<IntTuple> condition) {
		Stream<IntTuple> ret = params[0].generateStream().mapToObj(num -> new IntTuple(params.length).add(num, 0));
		for (int i = 1; i < params.length; i++) {
			final IntParameter p = params[i];
			final int index = i;
			ret = ret.flatMap(root -> p.generateStream().mapToObj(num -> root.add(num, index)));
		}
		return ret.filter(condition);
	}

}
