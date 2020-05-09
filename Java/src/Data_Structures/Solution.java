// TESLA INTERVIEW PROBlLEM //

import java.io.*;
import java.util.*;


/*
================ PROBLEM STATEMENT ================
I do not expect the solution to run or even compile in our 45 minute conversation.

Your task is to implement a PriorityExpiryCache cache with a max capacity. Specifically implement the evictItems function and add data structures to the PriorityExpiryCache object as needed.

You do NOT need to implement the get or set methods.

It should support these operations:
  Get: Get the value of the key if the key exists in the cache and is not expired.
  Set: Update or insert the value of the key with a priority value and expiretime.
    Set should never ever allow more items than maxItems to be in the cache.
    When evicting we need to evict the lowest priority item(s) which are least recently used.

================ EXAMPLE ================
p5 => priority 5
e10 => expires at 10 seconds since epoch

PriorityExpiryCache c = new PriorityExpiryCache(5);
c.set("A", 1, p5, e100)
c.set("B", 1, p15, e3)
c.set("C", 1, p5, e10)
c.set("D", 1, p1, e15)
c.set("E", 1, p5, e150)
//c.set("F", 1, p10, e200)
c.get("C")

time.Now() = e0
c.setMaxItems(5)
c.keys() = ["A", "B", "C", "D", "E"]
// 5 keys, all 5 items are included

time.Now() = e5
c.setMaxItems(4)
c.keys() = ["A", "C", "D", "E"]
// "B" is removed because it is expired.  e3 < e5

c.setMaxItems(3)
c.keys() = ["A", "C", "E"]
// "D" is removed because it the lowest priority
// D's expire time is irellevant.

c.setMaxItems(2)
c.keys() = ["C", "E"]
// "A" is removed because it is least recently used."
// A's expire time is irellevant.

c.setMaxItems(1)
c.keys() = ["C"]
// "E" is removed because C is more recently used (due to the Get("C") event).
================================
*/

/* INTERVIEWEE NOTES:
For now, we assume no duplicates will be passed in since the desired behavior in that case isn't defined in the problem statement.

(TODO) Desired functionality to add:
- Done :)
*/


class Solution {

    static class PriorityExpiryCache<T> {

        // Nested class we will use to create elements we put in the time PQ and Master map
        private class Node<T> implements Comparable<Node<T>> {
            private String key;
            private T value;
            private int priority;
            private long time;

            Node(String key, T value, int priority, long time) {
                this.key = key;
                this.value = value;
                this.priority = priority;
                this.time = time;
            }

            public long getTime() {
                return time;
            }

            // Compare two Nodes to order them from earliest time to latest
            @Override
            public int compareTo(Node<T> node) {
                if(this.getTime() > node.getTime()) {
                    return 1;
                } else if (this.getTime() < node.getTime()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }

        private int maxItems;

        // Priority Queues to keep track of order based on time and priority
        private Queue<Node<T>> time_pq;
        private Queue<Integer> priority_pq;

        // HashMap to map each priority to a list of elements with that priority
        private Map<Integer, LinkedList<Node<T>>> priority_map;

        // The "Master" hashmap to keep track of the elements in the PriorityExpiryCache
        private Map<String, Node<T>> curr_elements;

        // Current time, to see if anything expired
        private int current_time;

        // Constructor
        public PriorityExpiryCache(int maxItems) {
            this.maxItems = maxItems;

            time_pq = new PriorityQueue<>();
            priority_pq = new PriorityQueue<>();
            priority_map = new HashMap<>();
            curr_elements = new HashMap<>();
            current_time = 0;
        }

        public void updateTime(int time) {
            current_time = time;
        }

        public void setMaxItems(int maxItems) {
            this.maxItems = maxItems;
            evict();
        }

        private void set(String key, T value, int priority, long time) {
            // "assume implemented"
            if (curr_elements.size() < maxItems) {
                // Step 1: Create a new node that will be added to the time pq, the priority map, the master map, and the order list
                Node<T> node = new Node<>(key, value, priority, time);

                // Step 2: Add node to time pq
                time_pq.offer(node);

                // Step 3: Add priority to the priority pq
                priority_pq.offer(priority); // TODO: Be sure to remove all nodes of lowest priority from this q if the queue in Map 1 is ever 0

                // Step 4: Add node to the priority map
                if (priority_map.containsKey(priority)) {
                    priority_map.get(priority).addLast(node);
                } else {
                    LinkedList<Node<T>> llist = new LinkedList<>();
                    llist.add(node);
                    priority_map.put(priority, llist);
                }

                // Step 5: Add node to the master map
                curr_elements.put(key, node);
            }
        }


        // evict() will evict count items from the cache to make room for new ones.
        private void evict() {
            // **: Not strictly necessary, simplifies things but could work around if desired

            System.out.println("Start: " + time_pq.size() + " " + priority_pq.size() + " " + priority_map.size() + " " + curr_elements.size());
            if (maxItems <= 0) {
                time_pq.clear();
                priority_pq.clear();
                priority_map.clear();
                curr_elements.clear();

            }
            // Step 1: Remove any that are expired (time wise)
            long current_oldest = time_pq.peek().getTime();

            while (current_oldest < current_time) {
                // The current top of the time pq is expired, we must remove it

                // Pop from time pq -> O(log(n))
                Node<T> node = time_pq.poll();

                // **Remove from priority pq -> O(n)
                // priority_pq.remove(node.priority);

                // **Remove from priority map -> O(n)
                // priority_map.get(node.priority).remove(node);

                // Remove from master map -> O(1)
                curr_elements.remove(node.key);

                // Update time of new oldest node:
                current_oldest = time_pq.peek().getTime();
            }

            // Step 2: If we still need to remove some more, do so based on priority
            int lowest_priority;
            while (curr_elements.size() > maxItems) {
                // We remove one element at a time, starting with the ones at lowest priority
                lowest_priority = priority_pq.poll();

                // Among the entries with the lowests_priority, we remove the least recently used one (first one)
                Node<T> node = priority_map.get(lowest_priority).removeFirst();
                // Note: Here we judge it acceptable to leave empty queues in the priority map, there is little benefit to removing

                // Then we remove it from master map -> O(1)
                curr_elements.remove(node.key);

            }
            System.out.println("End: " + time_pq.size() + " " + priority_pq.size() + " " + priority_map.size() + " " + curr_elements.size());
        }

        private T get(String key) {
            // "assume implemented"
            // NOTE: Assuming no null keys are passed in for now, otherwise we would throw error

            Node<T> node = curr_elements.get(key); // -> O(1)

            // We have to go to the priority map and put the node back in the most recently used position (at the tail)
            // priority_map.get(node.priority).remove(node); // -> O(n)
            priority_map.get(node.priority).addLast(node); // -> O(1)

            return node.value;
        }

        private void printKeys() {
            System.out.print("[");
            for (Map.Entry<String, Node<T>> entry : curr_elements.entrySet()) {
                System.out.print(entry.getKey() + " ");
                // System.out.print("(" + entry.getKey() + " " + entry.getValue().getTime() + ")");
            }
            System.out.println("]");
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // --- Test: Given example case  --- //
        System.out.println("=== Test ===");
        PriorityExpiryCache<Integer> c = new PriorityExpiryCache<>(5);

        c.set("A", 1, 5, 100);
        c.set("B", 1, 15, 3);
        c.set("C", 1, 5, 10);
        c.set("D", 1, 1, 15);
        c.set("E", 1, 5, 150);
        c.get("C");

        c.printKeys(); // ["A", "B", "C", "D", "E"]

        c.updateTime(5);
        c.setMaxItems(4);
        c.printKeys(); // ["A", "C", "D", "E"]
        //"B" is removed because it is expired.  e3 < e5


        c.setMaxItems(3);
        c.printKeys(); // ["A", "C", "E"]
        // "D" is removed because it the lowest priority
        // D's expire time is irellevant.

        c.setMaxItems(2);
        c.printKeys(); // ["C", "E"]
        // "A" is removed because it is least recently used."
        // A's expire time is irellevant.

        c.setMaxItems(1);
        c.printKeys(); // ["C"]
        // "E" is removed because C is more recently used (due to the Get("C") event).



        // --- Test: Remove based on time alone --- //
        System.out.println("=== Test ===");
        PriorityExpiryCache<Integer> c2 = new PriorityExpiryCache<>(5);
        c2.set("A", 1, 5, 10);
        c2.set("B", 2, 5, 20);
        c2.set("C", 3, 5, 30);
        c2.set("D", 4, 5, 40);
        c2.set("E", 5, 5, 50);

        c2.printKeys(); // Everything
        c2.setMaxItems(3); // All have same priority, remove A & B
        c2.printKeys();
        c2.setMaxItems(1);
        c2.printKeys();
        c2.setMaxItems(5);
        c2.set("A", 1, 5, 10);
        c2.set("B", 2, 5, 20);
        c2.printKeys();

        // --- Test: Remove based on priority alone --- //
        System.out.println("=== Test ===");
        PriorityExpiryCache<String> c3 = new PriorityExpiryCache<>(5);
        c3.set("A", "I", 5, 10);
        c3.set("B", "really", 1, 10);
        c3.set("C", "reallllyy", 3, 10);
        c3.set("D", "like", 2, 10);
        c3.set("E", "Cybertrucks", 4, 10);

        c3.printKeys(); // Everything
        c3.setMaxItems(3); // All have same time, remove B & D
        c3.printKeys();
        c3.setMaxItems(1); // Last remaining should be I, A, which has highest priority
        c3.printKeys();
        c3.setMaxItems(5);
        c3.set("A", "also", 7, 10);
        c3.set("B", "roadster", 8, 10);
        c3.printKeys();

    /* Other tests to write:
    - Correct ordering (i.e. removes correctly based on multiple gets and sets)
    - Trying to get nonexisting elements
    - Duplicates (define desired behavior first)
    - Further test expiry time, with multiple updates of time and various expiry time elements
    - etc.

    */
    }


}