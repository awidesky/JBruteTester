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
	public IntTuple[] bruteTest(Predicate<IntTuple> condition) {
		IntTuple firstTu = new IntTuple(params.length);
		Stream<IntTuple> ret = params[0].generateStream().mapToObj(firstTu::add);
		for (int i = 1; i < params.length; i++) {
			final IntParameter p = params[i];
			ret = ret.flatMap(tu -> p.generateStream().mapToObj(tu::add));
		}
		return ret.parallel().filter(condition).toArray(IntTuple[]::new);
	}

}
