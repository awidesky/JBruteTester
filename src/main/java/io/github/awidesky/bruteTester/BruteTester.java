package io.github.awidesky.bruteTester;

import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class BruteTester<Tu extends Tuple> {

	protected Paramater<Tu>[] params;
	
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
	public Stream<Tu> bruteTest(Predicate<Tu> condition) {
		Stream<Tu> ret = params[0].rootTuple(params.length);
		for (int i = 1; i < params.length; i++) {
			final Paramater<Tu> p = params[i];
			final int index = i;
			ret = ret.flatMap(root -> p.addTuple(root, index));
		}
		return ret.filter(condition);
	}
}
