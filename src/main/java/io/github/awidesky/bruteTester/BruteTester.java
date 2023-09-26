package io.github.awidesky.bruteTester;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * A utility for a brute test that checks a list of parameters that each one has specific domain,
 * and find out all cases that satisfy a condition.
 * <p>
 * Followings are examples that can be solved via a subclass of {@code BruteTester} :<p>
 * <ul>
 * <li>Find all (x, y) that satisfies {@code x - y = 3} where 0 ≤ x ≤ 9, 0 ≤ y ≤ 9</li>
 * <li>Find all (x, y, z) that satisfies {@code x^2 + y^2 + z^2 = 1000} where -50 ≤ x ≤ 50, -50 ≤ y ≤ 50, -50 ≤ z ≤ 50</li>
 * <li>Find all (x, y, z) that x is a prime number and {@code y^2 + z^2 = x} where x, y, z ∈ [0, 100000000000)</li>
 * <li>Find all ({@code myEnum1obj}, {@code myEnum2obj}) that satisfy {@code myEnum1obj.valueA() == myEnum2obj.valueB()}
 * where {@code myEnum1obj} is one of all possible value of an enum class {@code MyEnum1},
 * and {@code myEnum2obj} is one of all possible value of an enum class {@code MyEnum2}.</li>
 * </ul>
 * @param <Tu> a type of input/output element of this brute tester.
 */
public abstract class BruteTester<Tu extends Tuple> { //TODO : add enumBruteTester, doubleBruteTester

	protected final Parameter<Tu>[] params;
	
	protected BruteTester(Parameter<Tu>[] parameters) {
		params = parameters;
	}
	
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
		Stream<Tu> ret = params[0].newTuple(params.length);
		for (int i = 1; i < params.length; i++) {
			final Parameter<Tu> p = params[i];
			final int index = i;
			ret = ret.flatMap(root -> p.addTuple(root, index));
		}
		return ret.filter(condition);
	}
}
