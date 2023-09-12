package io.github.awidesky.bruteTester;

import java.util.function.Predicate;
import java.util.stream.Stream;

import io.github.awidesky.bruteTester.IntTuple.RootIntTupleBuilder;

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
	public IntTuple[] bruteTest(Predicate<IntTuple> condition) { //TODO : return stream
		RootIntTupleBuilder builder = IntTuple.builder(params.length);
		Stream<IntTuple> ret = params[0].generateStream().mapToObj(builder::build);
		for (int i = 1; i < params.length; i++) {
			final IntParameter p = params[i];
			final int index = i;
			ret = ret.flatMap(root -> p.generateStream().mapToObj(num -> root.add(num, index)));//.map(IntTuple::resetRank);
			
		}
		//ret.map(IntTuple::toString).forEach(System.out::println);//TODO
		return ret.filter(condition).toArray(IntTuple[]::new);
	}

}
