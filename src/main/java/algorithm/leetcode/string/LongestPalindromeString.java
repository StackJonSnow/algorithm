package algorithm.leetcode.string;

import java.util.Arrays;

/**
 * @author JonSnow
 * @desc 题目描述
 *          最长回文子串 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 * @thought 回文串有两种情况，一种是偶数个字符，一种是奇数个字符，以此分为两种情况分别查找由某个元素为中心的回文串
 *          遍历完整个字符串所有字符即可得到最长回文串
 * @date 2020/4/10
 */
public class LongestPalindromeString {

    public static void main(String[] args) {
        String string1 = "babad";
        String string2 = "cbbd";

        System.out.println(version1(string1));
        System.out.println(version1(string2));
    }

    public static String version1(String string) {

        String longestPalindromeString = "";
        char[] chars = string.toCharArray();

        for (int i = 0; i < string.length(); i++) {
            String oddNumberPalindromeString = findPalindromeString(i, i, chars);
            if (oddNumberPalindromeString.length() > longestPalindromeString.length()) {
                longestPalindromeString = oddNumberPalindromeString;
            }

            String evenNumberPalindromeString = findPalindromeString(i, i + 1, chars);
            if (evenNumberPalindromeString.length() > longestPalindromeString.length()) {
                longestPalindromeString = evenNumberPalindromeString;
            }
        }

        return longestPalindromeString;
    }

    private static String findPalindromeString(int left, int right, char[] chars) {

        while(left > -1 && right < chars.length) {
            if (chars[left] == chars[right]) {
                left --;
                right ++;
            } else {
                break;
            }
        }

        if (left < right) {
            return new String(Arrays.copyOfRange(chars, left + 1, right));
        }

        return "";
    }
}
