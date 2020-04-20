package algorithm.leetcode.string;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author JonSnow
 * @desc 题目描述
 *          编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
 * @thought 将数组排序，然后找出第一个元素与最后一个元素的公共前缀。
 *          或者只找出数组中最大的字符串与最小的字符串，再求两个字符串的最大公共前缀
 * @date 2020/4/9
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] sampleStrings1 = {"112", "1113", "11134", "1156"};
        String[] sampleStrings2 = {"112", "1113", "11134", "5353"};

        System.out.println(version1(sampleStrings1));
        System.out.println(version1(sampleStrings2));
        System.out.println("================================================");
        System.out.println(version2(sampleStrings1));
        System.out.println(version2(sampleStrings2));
    }

    public static String version1(String[] strings) {

        if (Objects.isNull(strings) || strings.length == 0) {
            return "";
        }

        if (strings.length == 1 && Objects.nonNull(strings[0])) {
            return strings[0];
        }

        Arrays.sort(strings);

        String minString = strings[0], maxString = strings[strings.length - 1];
        char[] minChars = minString.toCharArray(), maxChars = maxString.toCharArray();

        int compareLimit = Math.min(minChars.length, maxChars.length);

        StringBuilder commonPrefixBuilder = new StringBuilder();
        for (int i = 0; i < compareLimit; i++) {
            if (minChars[i] == maxChars[i]) {
                commonPrefixBuilder.append(minChars[i]);
            } else {
                break;
            }
        }

        return commonPrefixBuilder.toString();
    }

    public static String version2(String[] strings) {

        if (Objects.isNull(strings) || strings.length == 0) {
            return "";
        }

        if (strings.length == 1 && Objects.nonNull(strings[0])) {
            return strings[0];
        }

        String minString = findMinString(strings), maxString = findMaxString(strings);
        char[] minChars = minString.toCharArray(), maxChars = maxString.toCharArray();

        int compareLimit = Math.min(minChars.length, maxChars.length);

        StringBuilder commonPrefixBuilder = new StringBuilder();
        for (int i = 0; i < compareLimit; i++) {
            if (minChars[i] == maxChars[i]) {
                commonPrefixBuilder.append(minChars[i]);
            } else {
                break;
            }
        }

        return commonPrefixBuilder.toString();
    }

    public static String findMaxString(String[] strings) {
        String maxString = strings[0];
        for (int i = 0; i < strings.length; i++) {
            if (maxString.compareTo(strings[i]) > 0) {
                maxString = strings[i];
            }
        }
        return maxString;
    }

    public static String findMinString(String[] strings) {
        String minString = strings[0];
        for (int i = 0; i < strings.length; i++) {
            if (minString.compareTo(strings[i]) < 0) {
                minString = strings[i];
            }
        }
        return minString;
    }
}
