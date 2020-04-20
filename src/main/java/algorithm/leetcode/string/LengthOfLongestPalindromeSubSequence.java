package algorithm.leetcode.string;

import java.util.Objects;

/**
 * @author JonSnow
 * @desc 题目描述
 *         最长回文子序列 给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。
 *         最长回文子序列和上一题最长回文子串的区别是，子串是字符串中连续的一个序列，而子序列是字符串中保持相对位置的字符序列，
 *         例如，"bbbb"可以是字符串"bbbab"的子序列但不是子串。
 *         特别需要注意的是这个题目中子序列的概念，子序列是指在原字符串中相对位置不变的序列。
 * @thought 使用动态规划思想，设 f(i, j) 为下标从 i 到 j 的子串中回文子序列的长度，那么就可以得到如下状态转移方程
 *          当 s[i] = s[j] 时(s代表目标字符串)，f(i, j) = f(i + 1, j -1) + 2,
 *          当 s[i] != s[j] 时(s代表目标字符串)，f(i, j) = f(i + 1, j) 和 (i, j - 1) 之间的最大值
 *
 * @date 2020/4/10
 */
public class LengthOfLongestPalindromeSubSequence {

    public static void main(String[] args) {
        System.out.println(version1("bbbab"));
        System.out.println(version1("cbbd"));
    }

    public static int version1(String string) {

        if (Objects.isNull(string) || string.isEmpty()) {
            return 0;
        }

        int length = string.length();
        int[][] dep = new int[length][length];

        for (int i = length - 2; i >= 0; i--) {
            // dep 数组对角线位置的值应当初始化为1，因为只有一个字符的字符串的回文子序列长度为1
            dep[i][i] = 1;
            for (int j = i + 1; j < length; j++) {
                if (string.charAt(i) == string.charAt(j)) {
                    dep[i][j] = dep[i + 1][j -1] + 2;
                } else {
                    dep[i][j] = Math.max(dep[i + 1][j],dep[i][j -1]);
                }
            }
        }

        return dep[0][length - 1];
    }
}
