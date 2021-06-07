
class LeetCodeStackproblems {

    class validateStackSequences {
        public boolean validateStackSequencesMySolution(int[] pushed, int[] popped) {
            // Solution 1: Pointer based using stack & set for things on the stack
            Stack<Integer> stack = new Stack<>();

            int i = 0, j = 0;
            while (i < pushed.length && j < popped.length) {
                if (!stack.empty() && (popped[j] == stack.peek())) {
                    stack.pop();
                    j++;
                } else {
                    while (i < pushed.length && (pushed[i] != popped[j])) {
                        stack.push(pushed[i++]);
                    }

                    if (i == pushed.length && popped[j] != stack.peek()) {
                        return false;
                    }
                    i++;
                    j++;
                }
            }

            while (j < popped.length) {
                // Everything has been pushed on, check if we can pop the rest
                if (stack.peek() == popped[j++]) {
                    stack.pop();
                } else {
                    return false;
                }
            }

            return stack.empty();
        }

        public boolean validateStackSequencesMySolutionImproved(int[] pushed, int[] popped) {
            // Solution 1: Pointer based using stack & set for things on the stack
            Stack<Integer> stack = new Stack<>();
            int i = 0, j = 0;
            while (i < pushed.length || j < popped.length) {
                if (i >= pushed.length) {
                    if (stack.peek() == popped[j++]) {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else if (!stack.empty() && (popped[j] == stack.peek())) {
                    stack.pop();
                    j++;
                } else {
                    while (i < pushed.length && (pushed[i] != popped[j])) {
                        stack.push(pushed[i++]);
                    }
                    i++;
                    j++;
                }
            }

            return stack.empty();
        }
    }
}
