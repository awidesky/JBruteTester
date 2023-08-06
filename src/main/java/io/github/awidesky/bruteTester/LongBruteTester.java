package io.github.awidesky.bruteTester;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class LongBruteTester {
	
	private final LongParameter[] params;
	
	public LongBruteTester(LongParameter... param) { params = param; }
	
	public int paramLen() { return params.length; }
	
	public LongTuple[] bruteTest(Predicate<LongTuple> condition) {
		LongTuple firstTu = new LongTuple(params.length);
		Stream<LongTuple> ret = params[0].generateStream().mapToObj(firstTu::add);
		for (int i = 1; i < params.length; i++) {
			final LongParameter p = params[i];
			ret = ret.flatMap(tu -> p.generateStream().mapToObj(tu::add));
		}
		return ret.parallel().filter(condition).toArray(LongTuple[]::new);
	}
	
}
