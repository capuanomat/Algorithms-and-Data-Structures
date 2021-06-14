#include <iostream>
#include <vector>

/**
 * Created by Matthieu J. Capuano on 12/06/2021.
 */
class CodeFightsArrays {
public:
    /**
     * PROBLEM: firstDuplicate
     *
     * NOTE: O(n) time, O(1) space
     *
     * DESCRIPTION: Given an array a that contains only numbers in the range from 1 to a.length,
     *              find the first duplicate number for which the second occurrence has the minimal
     *              index. In other words, if there are more than 1 duplicated numbers, return the
     *              number for which the second occurrence has a smaller index than the second
     *              occurrence of the other number does. If there are no such elements, return
     *              -1.
     *
     * EXAMPLES: For a = [2, 3, 3, 1, 5, 2], the output should be: firstDuplicate(a) = 3.
     *              - There are 2 duplicates: numbers 2 and 3. The second occurrence of 3 has a
     *                smaller index than than second occurrence of 2 does, so the answer is 3.
     *           For a = [2, 4, 3, 5, 1], the output should be firstDuplicate(a) = -1.
     * @param a
     * @return
     */
    int firstDuplicate(std::vector<int> a) {
        for (int val: a) {
            if (a[abs(val) - 1] < 0)
                return abs(val);
            a[abs(val) - 1] *= -1;
        }
        return -1;
    }


    /**
     * PROBLEM: firstNotRepeatingCharacter
     *
     * NOTE: Iterate through string once, and only use O(1) space
     *
     * DESCRIPTION: Given a string s consisting of small English letters, find and return
     *              the first instance of a non-repeating character in it.
     *              If there is no such character, return '_'.
     *
     * EXAMPLES: For s = "abacabad", the output should be firstNotRepeatingCharacter(s) = 'c'.
     *           ...
     *           For s = "abacabaabacaba", the output should be firstNotRepeatingCharacter(s) = '_'.
     *
     * @param s
     * @return
     */
     /* MY SOLUTION */
    char firstNotRepeatingCharacter(std::string s) {

    std::vector<int> count(26, 0);
    for (int i = 0; i < s.size(); i++) {
        count[int(s[i]) - 97]++;
    }

    for (int i = 0; i < s.size(); i++) {
        if (count[int(s[i]) - 97] == 1)
            return s[i];
    }

    return '_';
    }

    /* TOP SOLUTION (doens't seem more optimal than mine): */
    char firstNotRepeatingCharacter_Best(std::string s) {
        int count[26] = {};
        int order[26];
        int i = 0;
        for (char c : s)
            if (!count[c-'a']++)
                order[i++] = c-'a';
        for (int j = 0; j < i; ++j)
            if (count[order[j]] == 1)
                return order[j]+'a';
        return '_';
    }


    /**
     * PROBLEM: rotateImage
     *
     * NOTES: Solve this task in-place, using O(1) space
     *
     * DESCRIPTION: You are given an n x n 2D matrix that represents an image. Rotate the image by
     *              90 degrees (clockwise).
     *
     * EXAMPLE: For
     *              a = [[1, 2, 3],
     *                   [4, 5, 6],
     *                   [7, 8, 9]]
     *          the output should be:
     *              rotateImage(a) =
     *                  [[7, 4, 1],
     *                   [8, 5, 2],
     *                   [9, 6, 3]]
     *
     * @param a
     * @return
     */
     /* MY SOLUTION */
    std::vector<std::vector<int>> rotateImage(std::vector<std::vector<int>> a) {
        for (int i = 0; i < a.size(); i++) {
            for (int j = i; j < a[i].size(); j++) {
                int entry = a[i][j];
                a[i][j] = a[j][i];
                a[j][i] = entry;
            }
        }

        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a[i].size() / 2; j++) {
                int entry = a[i][j];
                a[i][j] = a[i][a[i].size() - j - 1];
                a[i][a[i].size() - j - 1] = entry;
            }
        }

        return a;
    }

    /* TOP SOLUTION 1 */
    std::vector<std::vector<int>> rotateImage_Best(std::vector<std::vector<int>> a) {
        int n = a.size();
        for(int i = 0; i < n; ++i) {
            for(int j = i; j < n-i-1; ++j) {
                std::swap(a[i][j],a[j][n-i-1]);
                std::swap(a[i][j],a[n-i-1][n-j-1]);
                std::swap(a[i][j],a[n-j-1][i]);
            }
        }

        return a;
    }
};

int main(int argc, char const *argv[]) {
    std::cout << "----- Starting the main function: CodeFightsArrays (C++) -----" << std::endl;
    // Default no-arg constructor is created since I didn't specify one
    CodeFightsArrays* test = new CodeFightsArrays();

    std::cout << "--- Running firstDuplicatewith [2, 3, 3, 1, 5, 2] ---" << std::endl;
    std::cout << test->firstDuplicate(std::vector<int> {2, 3, 3, 1, 5, 2}) << std::endl;

    return 0;
}
