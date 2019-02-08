package CodeFightsInterview;

import java.util.*;

/**
 * Created by Matthieu J.B Capuano on 11/27/2017.
 */
public class codeFightsHashTables {
    /* =============================== HASH TABLES =============================== */

    /**
     * PROBLEM: groupingDishes
     *
     * DESCRIPTION: You have a list of dishes. Each dish is associated with a list of ingredients
     *              used to prepare it. You want to group the dishes by ingredients, so that for
     *              each ingredient you'll be able to find all the dishes that contain it (if there
     *              are at least 2 such dishes).
     *              Return an array where each element is a list with the first element equal to
     *              the name of the ingredient and all of the other elements equal to the names of
     *              dishes that contain this ingredient. The dishes inside each list should be
     *              sorted lexicographically. The result array should be sorted lexicographically
     *              by the names of the ingredients in its elements.
     *
     * THIS IS AN IMPORTANT EXAMPLE
     *
     * IMPORTANT LESSONS:
     * -> TreeMaps store entries in an ordered way (you can use comparator)
     * -> You can sort an ArrayList using Collections.sort()
     * -> Iterate through the entries in a HashMap with (replace Key_Type and Value_Type with actual types):
     *      Map.Entry<Key_Type, Value_Type> entry : map.entrySet()
     * -> Cast an ArrayList of Strings to an array of strings with arrlist.toArray(new String[0])
     */
    String[][] groupingDishes(String[][] dishes) {
        Map<String, ArrayList<String>> map = new TreeMap();

        for (String[] row: dishes) {
            String dish = row[0];
            for (int i = 1; i < row.length; i++) {
                if (!map.containsKey(row[i])) {
                    ArrayList<String> dishList = new ArrayList();
                    dishList.add(dish);
                    map.put(row[i], dishList);
                } else {
                    map.get(row[i]).add(dish);
                }
            }
        }

        for(String[] row : dishes) {
            for(int i = 1; i < row.length; i++) {
                if(map.get(row[i]).size() < 2) {
                    map.remove(row[i]);
                } else {
                    Collections.sort(map.get(row[i]));
                }
            }
        }

        String[][] toReturn = new String[map.size()][];
        int index = 0;

        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            entry.getValue().add(0, entry.getKey());
            toReturn[index++] = entry.getValue().toArray(new String[0]);
            // new String[0] above defines the runTime type of the .toArray created to be a String array
        }

        return toReturn;
    }


    /**
     * PROBLEM: areFollowingPatterns
     * DESCRIPTION: Given an array strings, determine whether it follows the sequence given in the
     *              patterns array. In other words, there should be no i and j for which
     *              strings[i] = strings[j] and patterns[i] ≠ patterns[j] or for which
     *              strings[i] ≠ strings[j] and patterns[i] = patterns[j].
     * @param strings
     * @param patterns
     * @return
     * SOLUTION 1
     */
    boolean areFollowingPatterns(String[] strings, String[] patterns) {
        Map<String, ArrayList<Integer>> pattMap = new HashMap();
        Map<String, ArrayList<Integer>> strMap = new HashMap();

        for (int i = 0; i < patterns.length; i++) {
            if (!pattMap.containsKey(patterns[i])) {
                ArrayList<Integer> indices = new ArrayList();
                indices.add(i);
                pattMap.put(patterns[i], indices);
            } else {
                pattMap.get(patterns[i]).add(i);
            }

            if (!strMap.containsKey(strings[i])) {
                ArrayList<Integer> indices = new ArrayList();
                indices.add(i);
                strMap.put(strings[i], indices);
            } else {
                strMap.get(strings[i]).add(i);
            }
        }

        boolean match = true;
        if (pattMap.size() != strMap.size()) {
            return false;
        }
        for (int i = 0; i < pattMap.size(); i++) {
            ArrayList<Integer> pattEntry = pattMap.get(patterns[i]);
            ArrayList<Integer> strEntry = strMap.get(strings[i]);
            if (!pattEntry.equals(strEntry)) {
                match = false;
            }
        }
        return match;
    }

    /* SOLUTION 2 */
    boolean areFollowingPatterns2(String[] strings, String[] patterns) {
        for(int i = 0; i < strings.length; i++){
            for(int j = i + 1; j < strings.length; j++){
                if((strings[i].equals(strings[j]) && !patterns[i].equals(patterns[j]))
                        || (!strings[i].equals(strings[j]) && patterns[i].equals(patterns[j]))){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * PROBLEM: containsCloseNums
     * DESCRIPTION: Given an array of integers nums and an integer k, determine whether there are
     *              two distinct indices i and j in the array where nums[i] = nums[j] and the
     *              absolute difference between i and j is less than or equal to k.
     * @param nums
     * @param k
     * @return
     */
    boolean containsCloseNums(int[] nums, int k) {
        if ((null == nums) || (nums.length < 2) || (k < 1)) return false;

        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < nums.length; i ++) {
            int key = nums[i];
            if (map.containsKey(key)) {
                if (Math.abs(i - map.get(key)) <= k) return true;
            }
            map.put(key, i);
        }
        return false;
    }
}
