package algorithm.sword2offer.string;

import java.util.Objects;

/**
 * @author JonSnow
 * @desc 题目描述
 *       将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)
 *       ，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。
 *
 * @thought 从数字字符串高位开始遍历，然后逐步将字符串转换为数字
 * @date 2020/4/10
 */
public class SwitchStringToDigit {

    public static void main(String[] args) {
        System.out.println(version1("23143"));
        System.out.println(version1("-23143"));
        System.out.println(version1("48329482984294829"));
    }

    public static int version1(String digit) {

        if (Objects.isNull(digit)) {
            return 0;
        }

        char[] chars = digit.toCharArray();
        boolean isPositiveNumber = chars[0] == '-' ? false : true;
        int startIndex = isPositiveNumber ? 0 : 1;
        int theDigit = 0;

        for (int i = startIndex; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                int currentDigit = chars[i] - '0';
                if (10 * theDigit < Integer.MAX_VALUE && currentDigit <= Integer.MAX_VALUE - 10 * theDigit) {
                    theDigit = 10 * theDigit + currentDigit;
                } else {
                    System.out.println("warn: 超过整形最大值");
                    return 0;
                }
            } else {
                return 0;
            }
        }

        return isPositiveNumber ? theDigit : -theDigit;
    }
}
