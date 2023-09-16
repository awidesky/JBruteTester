package io.github.awidesky.bruteTester;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A tuple that holds N objects.
 * @param <T>
 */
public class ObjectTuple<T> implements Tuple {

	private final T[] arr;
	private int idx = 0; //index to put next data

	/**
	 * Creates an tuple that holds <code>len</code> objects.
	 * Created tuple is set to {@code null}.
	 * <p>
	 * Subclass should override this constructor and provide proper {@code Class<T>} instance.
	 * 
	 * @param len Size of the tuple
	 * */
	public ObjectTuple(Class<T> c, int len) {
		@SuppressWarnings("unchecked")
	    T[] a = (T[]) Array.newInstance(c, len);
		arr = a;
		Arrays.fill(arr, null);
	}
	/**
	 * Initiate a new tuple, copy data from given other tuple(which must not full), and add given element to {@code this} 
	 * <p>
	 * This constructor is for addition operation. At the first time adding element(via {@code ObjectTuple#add(Object, int)} method)
	 * in index {@code i}, the operand(root) is filled up to index {@code i-1}, so the element can simply reside in index {@code i} of backing array.
	 * <p>
	 * But after that, when the {@code ObjectTuple#add(Object, int)} method is called again on same {@code ObjectTuple} object,
	 * the given element cannot added in index {@code i}; new {@code ObjectTuple} instance must be created.
	 * The new object must have same length of element array, and element array must be copied from the original(at least up to index {@code i-1}).
	 * <p>
	 * This constructor initiates new tuple, copy the backing array of given {@code other}, and put the given element into the given index. 
	 * 
	 * @param other the root tuple instance to be copied from
	 * @param element {@code T} object to added in given index
	 * @param index the index to put the element
	 * 
	 * @throws IllegalArgumentException when the index is same of grater than the backing array's length
	 */
	protected ObjectTuple(ObjectTuple<T> other, T element, int index) {
		if(other.arr.length <= index) {
			throw new IllegalArgumentException("Cannot add element " + element.toString() + "in index "  + index + " : " + other.arr.length + "-tuple is full");
		}
		arr = Arrays.copyOf(other.arr, other.arr.length);
		idx = index + 1;
		arr[index] = element;
	}
	
	/**
	 * Adds new element to the tuple.
	 * 
	 * @throws IllegalArgumentException If the tuple is full before addition.
	 * */
	public ObjectTuple<T> add(T element, int index) {
		if(arr.length < index) {
			throw new IllegalArgumentException("Parameters are " + arr.length + "-tuple(max index is " + (arr.length - 1) + "), but requested index was " + index);
		}
		if(index != idx) return new ObjectTuple<T>(this, element, index);
		else {
			arr[idx++] = element;
			return this;
		} 
	}
	/**
	 * Get the element in the tuple whose index is <code>i</code>.
	 * 
	 * @throws IllegalArgumentException If <code>i</code> is out of bounds.
	 * */
	public T get(int i) {
		if(arr.length <= i) {
			throw new IllegalArgumentException("Parameters are " + arr.length + "-tuple(max index is " + (arr.length - 1) + "), but requested index was " + idx);
		}
		return arr[i];
	}

	/**
	 * Returns a String represents of this tuple
	 */
	@Override
	public String toString() {
		return "[" + Arrays.stream(arr).map(T::toString).collect(Collectors.joining(", ")) + "]";
	}
	
}
