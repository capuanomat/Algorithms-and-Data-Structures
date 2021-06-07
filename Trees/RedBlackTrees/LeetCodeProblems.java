public class LeetCodeProblems {

    /**
     * Difficulty: Medium
     *
     * Given an array of integers nums, a move consists of choosing any nums[i],
     * and incrementing it by 1.
     * Return the least number of moves to make every value in nums unique.
     *
     * Example 1:
     *  Input: nums = [1,2,2]
     *  Output: 1
     *  Explanation:  After 1 move, the array could be [1, 2, 3].
     *
     * Example 2:
     *  Input: nums = [3,2,1,2,1,7]
     *  Output: 6
     *  Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
     *                It can be shown with 5 or less moves that it is impossible
     *                for the array to have all unique values.
     *
     * Note:
     * 0 <= nums.length <= 40000
     * 0 <= nums[i] < 40000
     */
    class minIncrementForUnique {

        /** MY SOLUTION **/
        public int minIncrementForUniqueMySolution(int[] nums) {
            // Solution 1: Iterate through entries, throw distinct ones in hashset, if already in then increment until
            //             distinct, keep track of num increments.
            // --> Proof of correctness --> yes
            // --> Runtime? No gouda --> Improvements? Keep track of ranges.. how?
            // Solution 1 (improved): Keep track of the ranges for which we already have values, so you don't need to
            //                        increment by one until it's a distinct character.
            int solution = 0;
            TreeMap<Integer, Integer> ranges = new TreeMap<>();
            for (int i = 0; i < nums.length; i++) {
                int curr = nums[i];

                Map.Entry<Integer, Integer> rangeLo = ranges.floorEntry(curr);
                Map.Entry<Integer, Integer> rangeUp = ranges.ceilingEntry(curr);
                if (rangeLo != null && (curr == rangeLo.getKey()))
                    rangeUp = ranges.ceilingEntry(curr + 1);

                if (rangeLo == null && rangeUp == null) {
                    ranges.put(curr, curr);
                    continue;
                }

                if (rangeLo != null && (curr <= rangeLo.getValue() + 1)) {
                    // The curr value fits in the lower range + 1

                    int newUpper = curr <= rangeLo.getValue() ? rangeLo.getValue() + 1 : curr;
                    // Update solution
                    solution += newUpper - curr;

                    // Update the ranges
                    if (rangeUp != null && (newUpper == rangeUp.getKey() - 1)) {
                        ranges.put(rangeLo.getKey(), rangeUp.getValue());
                        ranges.remove(rangeUp.getKey());
                    } else {
                        ranges.put(rangeLo.getKey(), newUpper);
                    }
                } else if (rangeUp != null && (curr == rangeUp.getKey() - 1)) {
                    // The curr value fits in the upper range - 1
                    ranges.put(curr, rangeUp.getValue());
                    ranges.remove(rangeUp.getKey());
                } else {
                    // The curr value doesn't fit in either the closest range + 1 or upper range - 1
                    ranges.put(curr, curr);
                }
                // ranges.put(curr, curr == rangeUp.getKey() - 1 ? rangeUp.getValue() : curr);
            }

            return solution;
        }

        /** Official Solution 1:
         * Approach 1: Counting
         *
         * Intuition:
         * Let's count the quantity of each element. Clearly, we want to
         * increment duplicated values.
         * For each duplicate value, we could do a "brute force" solution of
         * incrementing it repeatedly until it is not unique. However, we might
         * do a lot of work - consider the work done by an array of all ones.
         * We should think of how to amend our solution to solve this case as
         * well.
         *
         * What we can do instead is lazily evaluate our increments. If for
         * example we have [1, 1, 1, 1, 3, 5], we don't need to process all the
         * increments of duplicated 1s. We could take three ones
         * (taken = [1, 1, 1]) and continue processing. When we find an empty
         * place like 2, 4, or 6, we can then recover that our increment will be
         * 2-1, 4-1, and 6-1.
         *
         * Algorithm
         * Count the values. For each possible value x:
         *  - If there are 2 or more values x in A, save the extra duplicated
         *    values to increment later.
         *  - If there are 0 values x in A, then a saved value v gets incremented
         *    to x.
         * In Java, the code is less verbose with a slight optimization: we
         * record only the number of saved values, and we subtract from the
         * answer in advance. In the [1, 1, 1, 1, 3, 5] example, we do taken = 3
         * and ans -= 3 in advance, and later we do ans += 2; ans += 4; ans += 6.
         * This optimization is also used in Approach 2.
         */
        public int minIncrementForUnique1(int[] A) {
            int[] count = new int[100000];
            for (int x: A) count[x]++;

            int ans = 0, taken = 0;

            for (int x = 0; x < 100000; ++x) {
                if (count[x] >= 2) {
                    taken += count[x] - 1;
                    ans -= x * (count[x] - 1);
                } else if (taken > 0 && count[x] == 0) {
                    taken--;
                    ans += x;
                }
            }

            return ans;
        }
    }
}
