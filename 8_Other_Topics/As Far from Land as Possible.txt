class Solution {   
    class Point {
        int x;
        int y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int maxDistance(int[][] grid) {
        /*
        SOLUTION 1
        1. Find indices (points) for all the 1s
        2. Go through grid, for each 0 compute Manhattan distance to each 1
            -> Keep minimum only
            -> Keep track of maximum distance
        */
        /*
        // Step 1: Keep track of coordinates of all 1s
        Set<Point> ones = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            int[] row = grid[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 1) {
                    Point p = new Point(i, j);
                    ones.add(p);
                }
            }
        }
        
        if ((ones.size() == 0) || (ones.size() == grid.length * grid[0].length))
            return -1;

        int fartherst = 0;
        // Step 2: For each 0 in the grid, compute its Manhattan distance to every 1 (land)
        for (int i = 0; i < grid.length; i++) {
            int[] row = grid[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 0) {
                       // Find nearest land
                        int closest = findNearestLand(new Point(i, j), ones);
                        
                        // If current point's closest piece of land is farther away than the current fartherst, update it
                        if (closest > fartherst) {
                            fartherst = closest;
                        } 
                }
            }
        }
        // LATER: Combine steps 1 and 2?
        
        return fartherst;
        */
        
        /*
        SOLUTION 2:
        1. Create a grid to keep track of explored grid elements
        2. Add all the ones to a queue
        3. Perform BFS from every one
        */

        int n = grid.length;    // Num rows
        int m = grid[0].length; // Num cols
        boolean[][] visited = new boolean[n][m];

        Queue<Point> frontier = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    Point p = new Point(i, j);
                    frontier.offer(p);
                    visited[i][j] = true;
                }
            }
        }
        
        System.out.println(visited);
        
        if ((frontier.size() == 0) || (frontier.size() == grid.length * grid[0].length))
            return -1;
        
        int[][] NEIGHBORS = {{0, -1}, {-1, 0}, {1, 0}, {0, 1}};
        int dist = 0;
        while (!frontier.isEmpty()) {
            int curr_size = frontier.size();
            for (int i = 0; i < curr_size; i++) {
                Point p = frontier.poll();
                
                for (int[] neighbor : NEIGHBORS) {
                    int new_x = p.x + neighbor[0];
                    int new_y = p.y + neighbor[1];
                    
                    if (!(new_x < 0) && !(new_x >= n) && !(new_y < 0) && !(new_y >= m) && !visited[new_x][new_y]) {
                        frontier.offer(new Point(new_x, new_y));
                        visited[new_x][new_y] = true;
                    }
                }
            }
            dist++;
        }
        

        return dist - 1;
    }
    
    /*
    public int findNearestLand(Point p, Set<Point> ones) {
        int nearest = Integer.MAX_VALUE;
        for (Point one : ones) {
            int dist = manhattanDist(p, one);
            if (dist < nearest) {
                nearest = dist;
            }
        }
        return nearest;
    }
    
    public int manhattanDist(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
    */
}