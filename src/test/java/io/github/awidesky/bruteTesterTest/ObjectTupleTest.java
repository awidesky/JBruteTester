package io.github.awidesky.bruteTesterTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.github.awidesky.bruteTester.ObjectTuple;

class ObjectTupleTest {

	private static final ObjectTuple<String> tuple = new ObjectTuple<String>(String.class, 3);
	
	@BeforeAll
	static void initTuple() {
		tuple.add("aaa", 0);
		tuple.add("bbb", 1);
		tuple.add("ccc", 2);
	}
	
	@Test
	void addTest() {
		ObjectTuple<String> tu = new ObjectTuple<String>(String.class, 3);
		tu.add("aaa", 0);
		tu.add("bbb", 1);
		tu.add("ccc", 2);
		assertEquals(tuple.toString(), tu.toString());
	}
	@Test
	void getTest() {
		assertAll(() -> tuple.get(0).equals("aaa"), () -> tuple.get(1).equals("bbb"), () -> tuple.get(2).equals("ccc"));
	}
	@Test
	void derivedTupleTest() {
		ObjectTuple<String> tu1 = new ObjectTuple<String>(String.class, 3);
		tu1.add("aaa", 0);
		tu1.add("bbb", 1);
		ObjectTuple<String> tu2 = tu1.add("ccc", 1);
		assertNotEquals(tu1.toString(), tu2.toString());
	}
	
	/**
	 * Test exceptions on {@code ObjectTuple#add(Object, int)}
	 */
	@org.junit.jupiter.api.Test
	void exceptionAddTest() {
		assertThrows(IllegalArgumentException.class, () -> tuple.add("Hello", 100)); //TODO : tuple.length()
	}
	/**
	 * Test exceptions on {@code ObjectTuple#get(int)}
	 */
	@org.junit.jupiter.api.Test
	void exceptionGetTest() {
		assertThrows(IllegalArgumentException.class, () -> tuple.get(100));
	}
}
