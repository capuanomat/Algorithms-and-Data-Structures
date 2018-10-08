import java.util.Comparator;
import java.util.Random;

/**
 * Created by Matthieu J.B Capuano on 10/25/2017.
 * This is my personal reference of all the sorting algorithms I have learned (except maybe radix)
 * in Java.
 */
public class Algos {
    /* IN-PLACE ALGORITHMS: An in-place algorithm is one that does not copy the elements being sorted
                            into a new array/list/other structure during the sorting process. They are
                            "in-place" because the sorting takes place within the passed in structure.
                            An out of place sort would allocate a variable amount of space for external
                            storage.
       STABLE SORT:         If there are duplicate items in the list to be sorted, their order is not
                            mixed up when they are sorted. That is called being "stable".
       COMPARISON SORT:     All the sorting alogrithms below except radix sort are "comparison"
                            algorithms because they compare items within list.
     */

    /**
     * BUBBLE SORT: In-place. Stable.
     *              Best-case: O(n)
     *              Average-case: O(n^2)
     *              Worst-case: O(n^2)
     * @param arr           The array to be sorted
     * @param comparator    The comparator to use for individual comparisons of T
     * @param <T>           The type of object to be sorted in the array
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if ((comparator == null) || arr == null) {
            throw new IllegalArgumentException("Passed in array or comparator is null.");
        }

        int len = arr.length;
        boolean swapped = true;
        int i = 0;
        while ((i < len) && (swapped)) {
            swapped = false;
            for (int j = 1; j < len - i; j++) {
                if (comparator.compare(arr[j], arr[j - 1]) < 0) {
                    swapped = true;
                    T temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
            i++;
        }
    }

    /**
     * INSERTION SORT:  In-place. Stable.
     *                  Best-case: O(n)
     *                  Average-case: O(n^2)
     *                  Worst-case: O(n^2)
     * @param arr           The array to be sorted
     * @param comparator    The comparator to use for individual comparisons of T
     * @param <T>           The type of object to be sorted in the array
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if ((arr == null) || comparator == null) {
            throw new IllegalArgumentException("Passed in array or comparator is null.");
        }

        int len = arr.length;
        for (int i = 0; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (comparator.compare(arr[j - 1], arr[j]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }

    /**
     * SELECTION SORT:  In-place. NOT stable.
     *                  Best-case: O(n^2)
     *                  Average-case: O(n^2)
     *                  Worst-case: O(n^2)
     * @param arr           The array to be sorted
     * @param comparator    The comparator to use for individual comparisons of T
     * @param <T>           The type of object to be sorted in the array
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if ((arr == null) || comparator == null) {
            throw new IllegalArgumentException("Input array or comparator is null.");
        }

        int len = arr.length;
        boolean swapped = true;
        int i = 0;
        while (i < len) {   // Could replace with for loop
            swapped = false;
            int min  = i;
            for (int j = i + 1; j < len; j++) {
                if (comparator.compare(arr[j], arr[min]) < 0) {
                    swapped = true;
                    min = j;
                }
            }
            if (swapped == true) {
                T temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
            i++;
        }
    }

    // NOTE: The arrays above are iterative, Merge sort and Quick sort below are recursive.

    /**
     * MERGE SORT:  OUT-of-place. Stable.
     *              Best-case: O(nlog(n))
     *              Average-case: O(nlog(n))
     *              Worst-case: O(nlog(n))
     * @param arr           The array to be sorted
     * @param comparator    The comparator to use for individual comparisons of T
     * @param <T>           The type of object to be sorted in the array
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if ((arr == null) || (comparator == null)) {
            throw new IllegalArgumentException("Input array or comparator is null.");
        }

        int len = arr.length;
        int middle = len / 2;
        if (len > 1) {
            T[] leftArray = (T[]) new Object[middle];
            T[] rightArray = (T[]) new Object[len - middle];
            for (int i = 0; i < leftArray.length; i++) {
                leftArray[i] = arr[i];
                rightArray[i] = arr[middle + i];
            }

            if (rightArray.length > leftArray.length) {
                rightArray[rightArray.length - 1] = arr[len - 1];
            }

            if (leftArray.length > 1) {
                mergeSort(leftArray, comparator);
            }
            if (rightArray.length > 1) {
                mergeSort(rightArray, comparator);
            }

            int mainIndex = 0;
            int leftIndex = 0;
            int rightIndex = 0;
            while ((leftIndex < leftArray.length) && (rightIndex < rightArray.length)) {
                if (comparator.compare(leftArray[leftIndex], rightArray[rightIndex]) < 0) {
                    arr[mainIndex++] = leftArray[leftIndex++];
                } else {
                    arr[mainIndex++] = rightArray[rightIndex++];
                }
            }

            while (leftIndex < leftArray.length) {
                arr[mainIndex++] = leftArray[leftIndex++];
            }

            while (rightIndex < rightArray.length) {
                arr[mainIndex++] = rightArray[rightIndex++];
            }
        }
    }

    /**
     * QUICKSORT:   In-place. NOT stable. Can be made stable but out-of-place.
     *              Best-case:      O(nlog(n))
     *              Average-case:   O(nlog(n))
     *              Worst-case:     O(n^2)
     * @param arr           The array to be sorted
     * @param comparator    The comparator to use for individual comparisons of T
     * @param <T>           The type of object to be sorted in the array
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator) {
        if ((arr == null) || (comparator == null)) {
            throw new IllegalArgumentException("Input array or comparator is null.");
        }

        if (arr.length > 1) {
            quickSortHelp(arr, 0, arr.length - 1, comparator);
        }
    }

    /**
     * Quick Sort helper method.
     * @param arr           The array to be sorted
     * @param left          The left bound of the part of the array to be sorted
     * @param right         The right bound of the part of the array to be sorted
     * @param comparator    The comparator to use for individual comparisons of T
     * @param <T>           The type of object to be sorted in the array
     */
    public static <T> void quickSortHelp(T[] arr, int left, int right, Comparator comparator) {
        if ((right - left + 1) == 2) {
            if (comparator.compare(arr[left], arr[right]) > 0) {
                T temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        } else {
            Random rand = new Random();
            int pivotIndex = rand.nextInt(right - left + 1) + left;
        /* Swapping pivot with first position: */
            T pivot = arr[pivotIndex];
            arr[pivotIndex] = arr[left];
            arr[left] = pivot;

        /* Setting up the two indices and starting the sort */
            int leftIndex = left + 1;
            int rightIndex = right;
            while (leftIndex < rightIndex) {
                while ((leftIndex < rightIndex) && (comparator.compare(arr[leftIndex], pivot) <= 0)) {
                    leftIndex++;
                }
                while ((leftIndex <= rightIndex) && (comparator.compare(arr[rightIndex], pivot) >= 0)) {
                    rightIndex--;
                }

                if (leftIndex < rightIndex) {
                    T temp2 = arr[leftIndex];
                    arr[leftIndex] = arr[rightIndex];
                    arr[rightIndex] = temp2;
                }
            }

            arr[left] = arr[rightIndex];
            arr[rightIndex] = pivot;

            if ((rightIndex - left) > 0) {
                quickSortHelp(arr, left, rightIndex - 1, comparator);
            }
            if ((right - rightIndex) > 1) {
                quickSortHelp(arr, rightIndex + 1, right, comparator);
            }
        }
    }

    /* To be added: Radix sort (LSD and MSD), which can only be used on numbers of a base
                    (e.g. base 10, 8, 16, ...). Radix is NOT comparative, like the previous sorts.
    */
}
