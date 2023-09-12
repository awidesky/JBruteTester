package io.github.awidesky.bruteTester;


import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

public class TestMain {

	public static void main(String[] args) {
		int n = 5;
		IntTuple.cnt = new AtomicLong();
		IntBruteTester it = new IntBruteTester(new IntParameter(0, n), new IntParameter(0, n), new IntParameter(0, n));
		long i = Arrays.stream(it.bruteTest(t -> false)).count();
		System.out.println(i + " " + IntTuple.cnt.getAcquire());
	}

}
