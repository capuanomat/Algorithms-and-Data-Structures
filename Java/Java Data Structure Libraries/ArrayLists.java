import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import javax.print.event.PrintJobListener;

class ArrayLists {
  /**
    Documentation: https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html

    Important methods to know for Arraylists:
    - add(E e)                                --> Appends at end of list
    - add(int index, E element)
    - addAll(Collection<? extends E> c)
    - set(int index, E element)

    - size()

    - get(int index)
    - indexOf(Object o)                       --> Gets first occurence of o or -1 if o is not in
    - lastIndexOf(Object o)
    - subList(int fromIndex, int toIndex)     --> returns sublist as a List<T>

    - isEmpty()
    - contains(Object o)

    - remove(int index)
    - remove(Object o)                        --> Removes the first occurence
    - removeAll(Collection<?> c)
    - removeRange(int fromIndex, int toIndex) --> You CANNOT actually use this because it is protected! (https://stackoverflow.com/questions/20748848/why-removerange-method-of-arraylist-class-is-not-working)

    - toArray()                               --> Can only return Object array
    - toArray(T[] a)                          --> Will return array of type T
    - clone()
    - clear()

    - iterator()

    Available methods not covered:
    - addAll(int index, Collection<? extends E> c)
    - ensureCapacity(int minCapacity)
    - forEach(Consumer<? super E> action)
    - listIterator(int index)
    - removeIf(Predicate<? super E> filter)
    - replaceAll(UnaryOperator<E> operator)
    - retainAll(Collection<?> c)
    - sort(Comparator<? super E> c)
    - spliterator()
    - trimToSize()                            --> Trims the capacity of this ArrayList instance to be the list's current size.
  */

  public static void main(String[] args) {
    /* Arraylists take in OBJECTS, not primitives:
    -> The line throws "unexpected type" error
    List<int> arrlist = new ArrayList<>();
    */

    // Declaring Arraylists
    ArrayList<Integer> arrlist = new ArrayList<>();
    List<Integer> arrlist2 = new ArrayList<>();

    // Adding elements sequentially to array lists
    arrlist.add(00);
    arrlist.add(11);
    arrlist.add(22);
    arrlist.add(33);
    System.out.println(arrlist);

    // Adding elements at specific index
    arrlist.add(1, 44);   // arlist = [00, 44, 11, 22, 33]
    System.out.println(arrlist);

    /*/ You cannot add an element at an index beyond the current highest used index + 1 /*/
    // arrlist.add(6, 66);    // Doesn't work because arlist.length = 5
    arrlist.add(5, 55);       // arlist = [00, 44, 11, 22, 33, 55]
    System.out.println(arrlist);
    arrlist.add(6, 66);       // Now it works because arlist.length = 6
    System.out.println(arrlist);

    // Adding a whole collection of elements
    ArrayList<Integer> to_add = new ArrayList<>();
    to_add.add(000); to_add.add(111); to_add.add(222);
    System.out.println("Adding the following ArrayList to the previous ArrayList: " + to_add);

    arrlist.addAll(to_add);
    System.out.println(arrlist);  // arlist = [0, 44, 11, 22, 33, 55, 66, 0, 111, 222]


    // Replacing an element
    arrlist.set(7, 77);
    arrlist.set(8, 88);
    arrlist.set(9, 99);
    System.out.println(arrlist);
    // arrlist.set(10, 100);    // Index out of Bounds Exception

    // Getting size of the array list
    System.out.println("arrlist size: " + arrlist.size());

    // Retrieving element at specific index
    int retrieved = arrlist.get(5);
    System.out.println(retrieved);

    // Getting index of first instance of element
    int first_index_of = arrlist.indexOf(77);
    System.out.println("First index of 77: " + first_index_of); // 7

    // Getting index of last instance of element
    arrlist.set(0, 22);
    arrlist.set(9, 22);
    int last_index_of = arrlist.lastIndexOf(22);
    System.out.println("Last index of 22: " + last_index_of);   // 9

    // Getting a sublist from the Arraylist
    List<Integer> sublist = arrlist.subList(5, 8);
    System.out.println(sublist);                                            // [55, 66, 77]
    System.out.println(arrlist.subList(first_index_of, last_index_of));     // [77, 88]

    // Is ArrayList empty
    System.out.println(arrlist.isEmpty());        // false

    // Does ArrayList contain
    System.out.println(arrlist.contains(88));     // true
    System.out.println(arrlist.contains(123));    // false

    System.out.println(arrlist);    // [22, 44, 11, 22, 33, 55, 66, 77, 88, 22]

    // Cloning an Arraylist
    ArrayList<Integer> arrlist_copy = (ArrayList) arrlist.clone();
    System.out.println(arrlist_copy);   // [22, 44, 11, 22, 33, 55, 66, 77, 88, 22]
    arrlist_copy.remove(0); arrlist_copy.remove(0); arrlist_copy.remove(0); arrlist_copy.remove(0);
    System.out.println(arrlist_copy);   // [33, 55, 66, 77, 88, 22]
    System.out.println(arrlist);        // [22, 44, 11, 22, 33, 55, 66, 77, 88, 22]


    // Removing element at index
    arrlist.remove(9);
    System.out.println(arrlist);    // [22, 44, 11, 22, 33, 55, 66, 77, 88]

    // Removing specific object
    arrlist.remove(new Integer(88));
    System.out.println(arrlist);    // [22, 44, 11, 22, 33, 55, 66, 77]

    // Removing all occurances of all elements from a given collection
    ArrayList<Integer> to_remove = new ArrayList<>();
    to_remove.add(22); to_remove.add(44); to_remove.add(77);
    arrlist.removeAll(to_remove);
    System.out.println(arrlist);    // [11, 33, 55, 66]

    // Again, this is a way of removing all occurences of a single element as well
    arrlist.add(22); arrlist.add(22); arrlist.add(22);
    System.out.println(arrlist);    // [11, 33, 55, 66, 22, 22, 22]
    to_remove.clear(); to_remove.add(22);
    arrlist.removeAll(to_remove);
    System.out.println(arrlist);    // [11, 33, 55, 66]

    // Removing elements within a specific range
    // arrlist.removeRange(1, 3);   // Method is protected, does NOT work


    // Converting ArrayList to Object array
    Object[] to_arr = arrlist.toArray();    // Note that using Integer[] to_arr throws an eror, it has to be Object, and you cannot cast it

    // Converting ArrayList to Integer array
    Integer[] to_arr_int = arrlist.toArray(new Integer[arrlist.size()]);

    // While loop & iterator()
    System.out.println("Running while loop");
    Iterator<Integer> iter = arrlist.iterator();
    while(iter.hasNext()) {
      System.out.println(iter.next());
    }

    // Regular for loop
    System.out.println("Running regular for loop");
    for(Iterator<Integer> i = arrlist.iterator(); i.hasNext();) {
      System.out.println(i.next());
    }

    // Enhanced for loop
    System.out.println("Running enhanced for loop");
    for (Integer i : arrlist) {
      System.out.println(i);
    }

    // Emptying the ArrayList
    arrlist.clear();
    System.out.println(arrlist);
  }
}
