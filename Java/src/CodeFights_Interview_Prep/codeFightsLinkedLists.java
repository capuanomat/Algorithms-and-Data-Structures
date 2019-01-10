package CodeFightsInterview;



/**
 * Created by Matthieu J.B Capuano on 11/27/2017.
 */
public class codeFightsLinkedLists {
    /* =============================== LINKED LISTS =============================== */

    /**
     * PROBLEM: removeKFromList
     * NOTE: O(n) time, O(1) space
     * DESCRIPTION: Given a singly linked list of integers l and an integer k, remove all elements
     *              from list l that have a value equal to k.
     * EXAMPLES: ...
     * @param l
     * @param k
     * @return
     */
    class ListNode<T> {
        ListNode(T x) {
            value = x;
        }
        T value;
        ListNode<T> next;
    }
    ListNode<Integer> removeKFromList(ListNode<Integer> l, int k) {
        if (l == null) return null;

        ListNode<Integer> prev = null;
        ListNode<Integer> temp = l;
        while (temp != null) {
            if (temp.value.equals(k)) {
                if (prev == null) {
                    l = l.next;
                } else {
                    prev.next = temp.next;
                }
            } else {
                prev = temp;
            }
            temp = temp.next;
        }
        return l;
    }


    /**
     * PROBLEM: isListPalindrome
     * NOTE: O(n) time, O(1) space
     * DESCRIPTION: Given a singly linked list of integers, determine whether or not it's a
     *              palindrome.
     * EXAMPLES: ...
     * @param l
     * @return
     */
    boolean isListPalindrome(ListNode<Integer> l) {
        if (null == l) return true;

        ListNode slow = l, fast = l;
        while ((null != fast) && (null != fast.next)) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode end = reverse(slow);
        ListNode front = l;
        while (null != end) {
            if (!front.value.equals(end.value)) {
                System.out.println(front.value);
                System.out.println(end.value);
                return false;
            }
            end = end.next;
            front = front.next;
        }
        return true;
    }

    ListNode reverse(ListNode<Integer> slow) {
        ListNode prev = null;
        while (null != slow) {
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        return prev;
    }

    /**
     * PROBLEM: addTwoHugeNumbers
     * DESCRIPTION: You're given 2 huge integers represented by linked lists. Each linked list
     *              element is a number from 0 to 9999 that represents a number with exactly 4
     *              digits. The represented number might have leading zeros. Your task is to add
     *              up these huge integers and return the result in the same format.
     * @param a
     * @param b
     * @return
     *
     * SOLUTION 1 (My solution)
     */
    ListNode<Integer> addTwoHugeNumbers(ListNode<Integer> a, ListNode<Integer> b) {
        if (null == a) return b;
        if (null == b) return a;

        ListNode head1 = reverseList(a);
        ListNode head2 = reverseList(b);

        Integer carry = 0;
        ListNode prev = new ListNode(0);
        ListNode toRet = prev;
        while ((null != head1) || (null != head2))  {
            Integer sum, remainder;
            if ((null != head1) && (null != head2)) {
                sum = (Integer) head1.value + (Integer) head2.value + carry;
            } else if (null == head1) {
                sum = (Integer) head2.value + carry;
            } else {
                sum = (Integer) head1.value + carry;
            }

            remainder = Math.floorMod(sum, 10000);
            carry = (sum - remainder) / 10000;

            ListNode temp = new ListNode(remainder);

            prev.next = temp;
            prev = temp;

            if (head1 != null) { head1 = head1.next; }
            if (head2 != null) { head2 = head2.next; }
        }

        if (carry != 0) {
            prev.next = new ListNode(carry);
        }


        toRet = toRet.next;
        return reverseList(toRet);
    }

    ListNode<Integer> reverseList(ListNode head) {
        ListNode prev = null;
        while (null != head) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    /**
     * PROBLEM: mergeTwoLinkedLists
     * NOTE: O(l1.length + l2.length) time
     * DESCRIPTION: Given two singly linked lists sorted in non-decreasing order, your task is to
     *              merge them. In other words, return a singly linked list, also sorted in
     *              non-decreasing order, that contains the elements from both original lists.
     * @param l1
     * @param l2
     * @return
     *
     * SOLUTION 1:
     */
    ListNode<Integer> mergeTwoLinkedLists(ListNode<Integer> l1, ListNode<Integer> l2) {
        if ((null == l1) && (null == l2)) return null;
        if (null == l1) return l2;
        if (null == l2) return l1;

        ListNode toRet;
        if (l1.value <= l2.value) {
            toRet = new ListNode(l1.value);
            l1 = l1.next;
        } else {
            toRet = new ListNode(l2.value);
            l2 = l2.next;
        }
        ListNode curr = toRet;

        while ((null != l1) && (null != l2)) {
            if (l1.value <= l2.value) {
                ListNode newN = new ListNode(l1.value);
                curr.next = newN;
                curr = curr.next;
                l1 = l1.next;
            } else {
                ListNode newN = new ListNode(l2.value);
                curr.next = newN;
                curr = curr.next;
                l2 = l2.next;
            }
        }
        if (null != l1) {
            curr.next = l1;
        }
        if (null != l2) {
            curr.next = l2;
        }

        return toRet;
    }

    /* SOLUTION 2 (Recursive) */
    ListNode<Integer> mergeTwoLinkedLists2(ListNode<Integer> l1, ListNode<Integer> l2) {
        if(l1==null) return l2;
        if(l2==null) return l1;
        if(l1.value<l2.value) {
            l1.next = mergeTwoLinkedLists(l1.next,l2);
            return l1;
        } else {
            l2.next = mergeTwoLinkedLists(l1,l2.next);
            return l2;
        }
    }
}
