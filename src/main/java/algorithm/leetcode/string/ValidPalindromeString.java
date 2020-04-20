package algorithm.leetcode.string;


import java.util.Objects;

/**
 * @author JonSnow
 * @desc 题目描述
 *          给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *          说明：本题中，我们将空字符串定义为有效的回文串。
 * @thought 定义两个指针，一个指向字符串开头字符，一个指向字符串结尾字符，同时向字符串中间移动
 *          如果出现字符不一致情况，则不是回文串，否则当两个指针重合或者相交证明该字符串是回文串
 * @date 2020/4/9
 */
public class ValidPalindromeString {

    public static void main(String[] args) {
        String string1 =  "A man, ,,,,a plan, a canal: Panama";
        String string2 =  "race a car";

        System.out.println(version1(string1));
        System.out.println(version1(string2));
    }

    public static boolean version1(String string) {
        if (Objects.isNull(string) || string.isEmpty()) {
            return true;
        }

        int low = 0, high = string.length() - 1;
        char[] chars = string.toCharArray();

        while(low < high) {
            if (!Character.isLetterOrDigit(chars[low])) {
                low ++;
            } else if (!Character.isLetterOrDigit(chars[high])) {
                high --;
            } else {
                if (Character.toLowerCase(chars[low]) != Character.toLowerCase(chars[high])) {
                    return false;
                }
                low ++;
                high --;
            }

        }

        return true;
    }
}
