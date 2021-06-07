
public class LeetCodeTreeProblems {

    // Definition for a Tree
     public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) { this.val = val; }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
     }


    class DistributedCoinsMySolution {

        private int solution = 0;

        public int distributeCoins(TreeNode root) {
            goThroughTree(root);
            return solution;
        }

        private int goThroughTree(TreeNode curr) {
            int leftValue = 0;
            int rightValue = 0;
            if (curr.left != null) {
                leftValue = goThroughTree(curr.left);
            }
            if (curr.right != null) {
                rightValue = goThroughTree(curr.right);
            }

            if (leftValue == 0 && rightValue == 0) {
                if (curr.val == 0) {
                    solution += 1;
                    return -1;
                }
                if (curr.val > 1) {
                    int spare = curr.val - 1;
                    solution += spare;
                    return spare;
                }
            }

            int currCost = 0;
            if (curr.val == 0) {
                currCost -= 1;
            } else if (curr.val > 1) {
                currCost = curr.val - 1;
            }


            int tryBalanced = leftValue + rightValue + currCost;
            solution += Math.abs(tryBalanced);

            return tryBalanced;
        }
    }

    class DistributedCoinsOfficialSolution {
        int ans;

        public int distributeCoins(TreeNode root) {
            ans = 0;
            dfs(root);
            return ans;
        }

        public int dfs(TreeNode node) {
            if (node == null) return 0;
            int L = dfs(node.left);
            int R = dfs(node.right);
            ans += Math.abs(L) + Math.abs(R);
            return node.val + L + R - 1;
        }
    }
}
