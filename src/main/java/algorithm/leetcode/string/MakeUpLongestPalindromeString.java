package algorithm.leetcode.string;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author JonSnow
 * @desc 题目描述
 *          给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。在构造过程中，请注意区分大小写。
 *          比如"Aa"不能当做一个回文字符串。注 意:假设字符串的长度不会超过 1010。
 *          回文串：“回文串”是一个正读和反读都一样的字符串，比如“level”或者“noon”等等就是回文串。
 * @thought 能组成回文串的字符串只有两种情况
 *          1. 字符重复次数为双数
 *          2. 只有一个字符出现次数为单数，其他字符出现次数都为双数
 *          方案1：
 *          可以利用 hashset 来判断字符是否双数出现
 *
 * @date 2020/4/9
 */
public class MakeUpLongestPalindromeString {

    public static void main(String[] args) {
        System.out.println(version1("aauiuifjk"));
        System.out.println(version1("bbbab"));
    }


    public static int version1(String string) {

        if (Objects.isNull(string) || string.isEmpty()) {
            return 0;
        }

        Set<Character> characterSet = new HashSet<>();

        char[] chars = string.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (!characterSet.add(chars[i])) {
                characterSet.remove(chars[i]);
                count ++;
            }
        }
        return !characterSet.isEmpty() ? count * 2 + 1 : count * 2;
    }

}
