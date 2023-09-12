package io.github.awidesky.bruteTester.forLoop;

import java.util.function.Predicate;
import java.util.stream.Stream;

import io.github.awidesky.bruteTester.IntParameter;
import io.github.awidesky.bruteTester.IntTuple;

public class ForLoopIntBruteTester {


	private final IntParameter[] params;
	
	/**
	 * Creates new brute force tester that uses given <code>int</code> type parameter.
	 * */
	public ForLoopIntBruteTester(IntParameter... param) { params = param; }
	
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
	public ForLoopIntTuple[] bruteTest(Predicate<ForLoopIntTuple> condition) {
		params[0].generateStream().parallel().mapToObj(firstNum -> {
			ForLoopIntTuple ret = new ForLoopIntTuple(params.length);
			ret.add(firstNum);
			for(int i = 1; i < params.length; i++) {
				
			}
			return null;
		});
		
		
		
		
		
		IntTuple firstTu = new IntTuple(params.length);
		Stream<IntTuple> ret = params[0].generateStream().mapToObj(num -> firstTu.add(num, 0));
		for (int i = 1; i < params.length; i++) {
			final IntParameter p = params[i];
			final int index = i;
			ret = ret.flatMap(tu -> p.generateStream().mapToObj(num -> tu.add(num, index)));
		}
		//return ret.parallel().filter(condition).toArray(IntTuple[]::new);
		return null;
	}
}
