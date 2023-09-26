package io.github.awidesky.bruteTester;

import java.util.stream.Stream;

/**
 * A parameter that used in brute force testing.
 * For example, if one need to find out all cases that satisfy {@code x - y = 5} in domain x ∈ [5, 15),
 * y ∈ [0, 10) U (15, 20],
 * there are two parameters (x, y), and range of each parameters are [5, 15) and [0, 10) U (15, 20].
 * <p>
 * {@code Parameter} objects denote each variables like x or y, and have range of the variables.
 * And it's job is to take account of all possible values of the parameter and put each in appropriate tuple
 * in order to provide all possible combination of all parameters. 
 * <p>
 * {@code Parameter} class is associated with {@code Tuple} as a generic type, since the parameter(variable)'s type
 * must be the same with that of the tuple, where the parameters(variables) resides.<p>
 * For an example, see {@code IntParameter} and {@code IntTuple}
 * 
 * @param <T> subclass of {@code Tuple} that this parameter can put values in.
 */
public interface Parameter<T extends Tuple> {
	/**
	 * Generate a stream of new tuples, compute all possible values of this parameter, and put each one
	 * in the first element of the tuples.
	 * So length of the returned Stream is as same as the number of possible values of this parameter.
	 * <p>
	 * If given length is 2, and all possible values of this parameter is {3, 4, 5}, returned stream of tuples
	 * can be expressed like : {[3, 0], [4, 0], [5, 0]}<p>
	 * Note : the second element may be 0, but the {@code Tuple} subclass may define another default value.
	 * 
	 * @param length length of each tuple  
	 * @return generated Stream of the tuples whose first element is one of all possible values of this parameter.
	 */
	public Stream<T> rootTuple(int length); //TODO : rename to newTuples
	/**
	 * Compute all possible values of this parameter, and derive stream of tuples from the given root.
	 * <p>
	 * gi
	 * Generated tuples have same elements from the root, and each one of the  appended
	 * 
	 * @param root the "root tuple" - a tuple that
	 * @param index
	 * @return
	 */
	public Stream<T> addTuple(T root, int index);
}
