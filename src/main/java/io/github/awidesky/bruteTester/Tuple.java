package io.github.awidesky.bruteTester;

/**
 * Denotes that a class implementing this interface is a <code>Tuple</code> - an enumeration of data in same type.
 * The main job of type {@code Tuple} is for generic type argument of {@code Parameter} and {@code BruteTester}.
 * <p>
 * The most common behavior of a tuple(e.g. getting data from the tuple, adding data to the tuple)
 * have different signature depends on the type of the tuple.
 * Also, the type cannot be a generic since it'll generate boxing/unboxing overhead for those who's element type is primitive
 * (e.g. {@code IntTuple}, {@code LongTuple}).
 * <br>
 * This is why there's no many abstract methods in this interface. 
 * */
public interface Tuple {
	/**
	 * Fixed length of this tuple.
	 * @return the length of this tuple.
	 * */
	public int length();
}
