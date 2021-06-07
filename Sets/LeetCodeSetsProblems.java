class LeetCodeSetsProblems {

    /**
    You are given the array paths, where paths[i] = [cityAi, cityBi] means there
    exists a direct path going from cityAi to cityBi. Return the destination city,
    that is, the city without any path outgoing to another city.
    It is guaranteed that the graph of paths forms a line without any loop,
    therefore, there will be exactly one destination city.

    Example 1:
    Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
    Output: "Sao Paulo"
    Explanation: Starting at "London" city you will reach "Sao Paulo" city which
                 is the destination city. Your trip consist of:
                 "London" -> "New York" -> "Lima" -> "Sao Paulo".

    Example 2:
    Input: paths = [["B","C"],["D","B"],["C","A"]]
    Output: "A"
    Explanation: All possible trips are:
                 "D" -> "B" -> "C" -> "A".
                 "B" -> "C" -> "A".
                 "C" -> "A".
                 "A".
                 Clearly the destination city is "A".

    Example 3:
    Input: paths = [["A","Z"]]
    Output: "Z"

    Constraints:
    1 <= paths.length <= 100
    paths[i].length == 2
    1 <= cityAi.length, cityBi.length <= 10
    cityAi != cityBi
    All strings consist of lowercase and uppercase English letters and the space character.
    */
    class DestinationCitySolution {
        public String destCity(List<List<String>> paths) {
            // If I had a graph structure already, I could just do a DFS until I find the note with no neighbors.

            // Instead, this problem is first about how you efficiently parse through paths (can you avoid DFS? Yes).
            Set<String> hasDestination = new HashSet<>();
            Set<String> noDestination = new HashSet<>();

            for (List<String> tuple: paths) {
                String source = tuple.get(0);
                String destination = tuple.get(1);

                // I know for a fact this source has a destination, so add it to hasDestination and remove noDestination
                hasDestination.add(source);
                noDestination.remove(source);

                if (!hasDestination.contains(destination))
                    noDestination.add(destination);
            }

            for (String entry: noDestination) {
                return entry;
            }
            return "Oh no";
        }
    }
}
