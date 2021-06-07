package DynamicProgramming;

/**
 * Created by Matthieu J. Capuano on 30/05/2021.
 */
public class CodeSignalDPBasicProblems {

    public static void main(String[] args) {
    }

    /**
     * You are climbing a staircase that has n steps. You can take the steps
     * either 1 or 2 at a time. Calculate how many distinct ways you can climb
     * to the top of the staircase.
     *
     * Example
     * - For n = 1, the output should be
     *   climbingStairs(n) = 1;
     *
     * - For n = 2, the output should be
     *   climbingStairs(n) = 2.
     *   You can either climb 2 steps at once or climb 1 step two times.
     *
     * Input/Output
     * - [execution time limit] 3 seconds (java)
     * - [input] integer n
     *      Guaranteed constraints:
     *      1 ≤ n ≤ 50.
     * - [output] integer
     *      It's guaranteed that the answer will fit in a 32-bit integer.
     */
    class climbingStairs {
        /**
         * My genius solution using Advancement Operators to solve this in O(1) time
         * and O(0) space.
         */
        int climbingStairs1(int n) {
            /* This is just a recurrence problem with recurrence relation:
             *     a_n = a_n-1 + a_n-2;
             * Solving it gives the formula below.
             */
            double A = ((Math.pow(5, 0.5) + 5) / 10);
            double B = -((Math.pow(5, 0.5) - 5) / 10);

            double term1 = (1 + Math.pow(5, 0.5)) / 2;
            double term2 = (1 - Math.pow(5, 0.5)) / 2;

            double ans = A * Math.pow(term1, n) + B * Math.pow(term2, n);
            return (int) Math.round(ans);
        }


        /**
         * ITERATIVE DP solution.
         */
        int climbingStairs2(int n) {
            // For step i, you have all the ways of climbing to steps i-1 (then take a single step)
            // AND all the ways of climbing to steps i-2 (then take a double step, if
            // you take just one step then that's the i-1 case).
            // Recurrence relation: s_i = s_(i-1) + s_(i-2)
            if (n <= 2) return n;
            int[] solutions = new int[n + 1];
            solutions[1] = 1;
            solutions[2] = 2;
            for (int i = 3; i <= n; i++)
                solutions[i] = solutions[i - 1] + solutions[i - 2];

            return solutions[n];
        }

        /**
         * RECURSIVE DP solution.
         */
        int[] climbingStairsSolutions;
        int climbingStairs3(int n) {
        if (n <= 2) return n;
            climbingStairsSolutions = new int[n + 1];
            climbingStairsSolutions[1] = 1;
            climbingStairsSolutions[2] = 2;

            climbingStairs3Recursion(n);

            return climbingStairsSolutions[n];
        }

        int climbingStairs3Recursion(int n) {
            if (climbingStairsSolutions[n] == 0) {
                climbingStairsSolutions[n] = climbingStairs3Recursion(n - 1) + climbingStairs3Recursion(n - 2);
            }
            return climbingStairsSolutions[n];
        }
    }


    /**
     * You are planning to rob houses on a specific street, and you know that
     * every house on the street has a certain amount of money hidden. The only
     * thing stopping you from robbing all of them in one night is that adjacent
     * houses on the street have a connected security system. The system will
     * automatically trigger an alarm if two adjacent houses are broken into on
     * the same night.
     *
     * Given a list of non-negative integers nums representing the amount of
     * money hidden in each house, determine the maximum amount of money you can
     * rob in one night without triggering an alarm.
     *
     * Example:
     * For nums = [1, 1, 1], the output should be
     * houseRobber(nums) = 2.
     *
     * The optimal way to get the most money in one night is to rob the first
     * and the third houses for a total of 2.
     *
     * Input/Output
     * - [execution time limit] 3 seconds (java)
     * - [input] array.integer nums
     *      An array representing the amount of money that each house on the street has.
     *      Guaranteed constraints:
     *      0 ≤ nums.length ≤ 100,
     *      0 ≤ nums[i] ≤ 500.
     * - [output] integer
     */
    class houseRobber {
        /**
         *  My original ITERATIVE DP solution from a while ago.
         */
        int houseRobber1(int[] nums) {
            if (nums.length == 0) return 0;
            if (nums.length == 1) return nums[0];
            if (nums.length == 2) return (nums[1] > nums[0] ? nums[1] : nums[0]);

            nums[2] +=  nums[0];
            for (int i = 3; i < nums.length; i++) {
                nums[i] = nums[i] + (nums[i - 2] > nums[i - 3] ? nums[i - 2] : nums[i - 3]);
            }
            Arrays.sort(nums);
            return nums[nums.length - 1];
        }

        /**
         *  My new ITERATIVE DP solution.
         */
        int houseRobber2(int[] nums) {
            // ITERATIVE DP approach.
            int[] solutions  = new int[nums.length];

            if (nums.length == 0) return 0;
            if (nums.length == 1) return nums[0];
            if (nums.length == 2) return Math.max(nums[0], nums[1]);

            // Terminating conditions:
            solutions[0] = nums[0];
            solutions[1] = Math.max(nums[0], nums[1]);

            int max = 0;
            for (int i = 2; i < nums.length; i++) {
                solutions[i] = Math.max(nums[i] + solutions[i - 2], solutions[i - 1]);
                if (solutions[i] > max)
                    max = solutions[i];
            }
            return max;
        }
    }


    /**
     * Given a sorted integer array that does not contain any duplicates, return
     * a summary of the number ranges it contains.
     *
     * Example:
     * For nums = [-1, 0, 1, 2, 6, 7, 9], the output should be
     * composeRanges(nums) = ["-1->2", "6->7", "9"].
     *
     * Input/Output
     * - [execution time limit] 3 seconds (java)
     * - [input] array.integer nums
     *      A sorted array of unique integers.
     *      Guaranteed constraints:
     *      0 ≤ nums.length ≤ 15,
     *      -(231 - 1) ≤ nums[i] ≤ 231 - 1.
     * - [output] array.string
     */
    class composeRanges {

        String[] composeRanges1(int[] nums) {
            // CHEATING SOLUTION: Using a pointer instead of DP.
            if (nums.length == 0) return new String[0];

            ArrayList<String> sol = new ArrayList<>();
            int startInd = 0;
            for (int i = 1; i <= nums.length; i++) {
                if ((i == nums.length) || (nums[i] != nums[i - 1] + 1)) {
                    if (startInd == i - 1) {
                        sol.add(String.valueOf(nums[startInd]));
                    } else {
                        sol.add(String.valueOf(nums[startInd]) + "->" + String.valueOf(nums[i - 1]));
                    }
                    startInd = i;
                }
            }

            return sol.toArray(new String[sol.size()]);
        }

        String[] composeRanges2(int[] nums) {
            // I COULD NOT FIND A DECENT DP SOLUTION TO THIS PROBLEM, it definitely
            // seems better suited to the implementation from above, and I wonder
            // if they missplaced it in the DP section.

            // I guess the above solution IS dynamic programming, but that seems
            // ill-construed to me. What would the recurrence relation be?
            // What are the sub-problems? Etc. The top java solution is effectively
            // the same as what I did:
            List<String> res = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                int start = nums[i];
                while (i+1 < nums.length && nums[i]+1 == nums[i+1]) i++;
                int end = nums[i];
                res.add(start != end ? start + "->" + end : "" + start);
            }
            return res.toArray(new String[res.size()]);
        }
    }

}
