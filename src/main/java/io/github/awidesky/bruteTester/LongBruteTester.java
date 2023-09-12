package io.github.awidesky.bruteTester;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class LongBruteTester {
	
	private final LongParameter[] params;
	
	/**
	 * Creates new brute force tester that uses given long type parameter.
	 * */
	public LongBruteTester(LongParameter... param) { params = param; }
	
	/**
	 * Returns length of parameters of this tester.
	 * */
	public int paramLen() { return params.length; }
	
	/**
	 * Find out how many tuples can satisfy the given condition, and returns a array that holds
	 * tuples that satisfies the condition.
	 * 
	 * @param condition condition to test
	 * @return <code>long</code> type tuples that satisfies the condition.
	 * */
	public Stream<LongTuple> bruteTest(Predicate<LongTuple> condition) {
		Stream<LongTuple> ret = params[0].generateStream().mapToObj(num -> new LongTuple(params.length).add(num, 0));
		for (int i = 1; i < params.length; i++) {
			final LongParameter p = params[i];
			final int index = i;
			ret = ret.flatMap(root -> p.generateStream().mapToObj(num -> root.add(num, index)));
		}
		return ret.filter(condition);
	}
	
}
