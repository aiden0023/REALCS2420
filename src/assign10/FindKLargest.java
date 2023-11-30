package assign10;

import java.util.*;

/**
 * This class contains generic static methods for finding the k largest items in a list.
 *
 * @author Aaron Wood and Aiden Fornalski
 * @version ??
 */
public class FindKLargest {

	/**
	 * Determines the k largest items in the given list, using a binary max heap and the
	 * natural ordering of the items.
	 *
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestHeap(List<E> items, int k) throws IllegalArgumentException {
		if (k < 1 || k > items.size()) { //exception check
			throw new IllegalArgumentException();
		}

		BinaryMaxHeap<E> heap = new BinaryMaxHeap<>(items); //creating heap object
		List<E> list = new ArrayList<>();
		for (int i = 0; i < k; i++) { //loops for the amount of the largest values we want
			list.add(heap.extractMax()); //adds largest to list
		}
		return list;
	}

	/**
	 * Determines the k largest items in the given list, using a binary max heap.
	 *
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestHeap(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		if (k < 1 || k > items.size()) { //exception check
			throw new IllegalArgumentException();
		}

		BinaryMaxHeap<E> heap = new BinaryMaxHeap<>(items, cmp); //creating heap object
		List<E> list = new ArrayList<>();
		for (int i = 0; i < k; i++) { //loops for the amount of the largest values we want
			list.add(heap.extractMax()); //adds largest to list
		}
		return list;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine and the
	 * natural ordering of the items.
	 *
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestSort(List<E> items, int k) throws IllegalArgumentException {
		if (k < 1 || k > items.size()) { //exception check
			throw new IllegalArgumentException();
		}

		Collections.sort(items); //sort items
		Collections.reverse(items); //reverse order to largest to smallest
		List<E> list = new ArrayList<>();
		for (int i = 0; i < k; i++) { //loops for the amount of the largest values we want
			list.add(items.get(i)); //adds largest to list
		}
		return list;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine.
	 *
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestSort(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		if (k < 1 || k > items.size()) { //exception check
			throw new IllegalArgumentException();
		}

		items.sort(cmp); //reverse order to largest to smallest
		Collections.reverse(items); //reverse order to largest to smallest
		List<E> list = new ArrayList<>();
		for (int i = 0; i < k; i++) { //loops for the amount of the largest values we want
			list.add(items.get(i)); //adds largest to list
		}
		return list;
	}
}
