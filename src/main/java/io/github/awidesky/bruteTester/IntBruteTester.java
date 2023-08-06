package io.github.awidesky.bruteTester;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class IntBruteTester {
	
	private final IntParameter[] params;
	
	public IntBruteTester(IntParameter... param) { params = param; }
	
	public int paramLen() { return params.length; }
	
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
