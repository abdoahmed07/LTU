package Lab2;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/*
javac Lab2/MyArrayList.java Lab2/MyArrayListTest.java
java -ea Lab2.MyArrayListTest
*/


@SuppressWarnings("serial")
public class MyArrayList<E> implements Serializable, Cloneable, Iterable<E>,
		Collection<E>, List<E>, RandomAccess {

    	// ---------------------------------------------------------------
		private E[] array;
		private int size;
		private static final int DEFAULT_CAPACITY = 10;



	public static void main(String[] args) {
		
	}

    // ---------------------------------------------------------------

	/**
 	* Constructs an empty list with the specified initial capacity.
 	*
 	* @param initialCapacity the initial capacity of the list
 	* @throws IllegalArgumentException if the specified capacity is negative
 	*/
	public MyArrayList(int initialCapacity) {
		if (initialCapacity < 0) 
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);

		array = (E[]) new Object[initialCapacity];
		size = 0;
	}

	/**
	 * A default constructor that creates an empty list with an initial
	 * capacity of ten in this case.
	 */
	public MyArrayList() {
		this(DEFAULT_CAPACITY);
	}

	// -- 1 --
 
	/**
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * @return true if this list contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	@Override
	public void clear() {
		for (int i = 0; i < size; i++) 
			array[i] = null;
		size = 0;
	}

	// -- 2 --

	/**
	 * Increases the capacity of this MyArrayList instance, if
	 * necessary, to ensure that it can hold at least the number of
	 * elements specified by the minimum capacity argument.
	 * @param minCapacity the desired minimum capacity
	 */
	public void ensureCapacity(int minCapacity) {
		if (minCapacity > array.length) {
			int newCapacity = Math.max(array.length * 2 + 1, minCapacity);
			E[] newArray = (E[]) new Object[newCapacity];
			for (int i = 0; i < size; i++) 
				newArray[i] = array[i];
			array = newArray;
		}
	}

	/**
	 * Trims the capacity of List to be the list's current size.
	 */
	public void trimToSize() {
		if (size < array.length) {
			E[] newArray = (E[]) new Object[size];
			for (int i = 0; i < size; i++) 
				newArray[i] = array[i];
			array = newArray;
		}
	}
    
	// -- 3 --
    
	/**
	 * Inserts the specified element at the specified position in this list.
	 * Shifts the element currently at that position (if any) and any
	 * subsequent elements to the right (adds one to their indices).
	 */
	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size) 
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		ensureCapacity(size + 1);
		for (int i = size; i > index; i--) 
			array[i] = array[i - 1];
		array[index] = element;
		size++;
	}

	/**
	 * Appends the specified element to the end of this list.
	 */
	@Override
	public boolean add(E e) {
		add(size, e);
		return true;
	}

    // -- 4 --
    
	/**
	 * Returns the element at the specified position in this list.
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size) 
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		return array[index];
	}

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element.
	 */
	@Override
	public E set(int index, E element) {
		if (index < 0 || index >= size) 
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		E oldValue = array[index];
		array[index] = element;
		return oldValue;
	}

	// -- 5 --

	/**
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices).
	 * Returns the element that was removed from the list.
	 */
	@Override
	public E remove(int index) {
	    if (index < 0 || index >= size)
	        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	
	    E removed = array[index];
	    removeRange(index, index + 1);
	    return removed;
	}

	/**
	 * Removes from this list all of the elements whose index is between fromIndex,
	 * inclusive, and toIndex, exclusive. Shifts any succeeding elements to the
	 * left (reduces their index).
	 */
	protected void removeRange(int fromIndex, int toIndex) {
		if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) 
			throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", toIndex: " + toIndex + ", Size: " + size);
		int remcount = toIndex - fromIndex;
		for (int i = toIndex; i < size; i++) 
			array[i - remcount] = array[i];
		for (int i = size - remcount; i < size; i++) 
			array[i] = null;
		size -= remcount;
	}

	// -- 6 --

	/**
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element.
	 */
	@Override
	public int indexOf(Object o) {
		for (int i = 0; i < size; i++) 
			if (o == null ? array[i] == null : o.equals(array[i])) 
				return i;
		return -1;
	}

	/**
	 * Removes the first occurrence of the specified element from this list, if
	 * it is present. If the list does not contain the element, it is unchanged, 
	 * and returns false. Otherwise, it removes the element and returns true.
	 */
	@Override
	public boolean remove(Object o) {
		 int index = indexOf(o);
	        if (index != -1) {
	            remove(index);
	            return true;
	        }
		return false;
	}
    
	/**
	 * Returns true if this list contains the specified element.
	 */
	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	// -- 7 --

	/**
	 * Returns a shallow copy of this MyArrayList instance. (The elements
	 * themselves are not copied.)
	 * @return a clone of this MyArrayList instance
	 */
	@Override
	public Object clone() {
		MyArrayList<E> cloned = new MyArrayList<>(size);
		for (int i = 0; i < size; i++) 
        	cloned.array[i] = array[i];
		cloned.size = this.size;  // not sure this is needed but just to be safe
		return cloned;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element).
	 * @return an array containing all of the elements in this list in proper
	 *         sequence (from first to last element)
	 */
	@Override
	public Object[] toArray() {
		 Object[] result = new Object[size];
	        for (int i = 0; i < size; i++) 
	            result[i] = array[i];
	        return result;	
	}

	// --- Rör ej nedanstående kod -----------------------------------

	public MyArrayList(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	private class InternalIterator implements Iterator {
		int current = 0;

		@Override
		public boolean hasNext() {
			return current < size();
		}

		@Override
		public Object next() {
			return get(current++);

		}

	}

	@Override
	public Iterator<E> iterator() {
		return new InternalIterator();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void forEach(Consumer<? super E> action) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Spliterator<E> spliterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeIf(Predicate<? super E> filter) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void replaceAll(UnaryOperator<E> operator) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void sort(Comparator<? super E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
}