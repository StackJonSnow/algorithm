package algorithm.leetcode.string;

/**
 * @author JonSnow
 * @desc 题目描述
 *       一个合法的括号匹配序列有以下定义:
 *       空串""是一个合法的括号匹配序列
 *       如果"X"和"Y"都是合法的括号匹配序列,"XY"也是一个合法的括号匹配序列
 *       如果"X"是一个合法的括号匹配序列,那么"(X)"也是一个合法的括号匹配序列
 *       每个合法的括号序列都可以由以上规则生成。
 *       例如: "","()","()()","((()))"都是合法的括号序列 对于一个合法的括号序列我们又有以下定义它的深度:
 *
 *       空串""的深度是0
 *       如果字符串"X"的深度是x,字符串"Y"的深度是y,那么字符串"XY"的深度为max(x,y)
 *       如果"X"的深度是x,那么字符串"(X)"的深度是x+1
 *       例如: "()()()"的深度是1,"((()))"的深度是3。牛牛现在给你一个合法的括号序列,需要你计算出其深度
 *
 * @thought 定义一个计数变量，一个存储最大深度的变量，然后遍历字符串所有字符，遇到 '(' 计数变量 +1，否则计数变量 -1，
 *          如果计数变量大于当前最大深度，则将计数变量的值赋值给最大深度变量，直至遍历完字符串所有字符。
 * @date 2020/4/10
 */
public class BracketMatchingDepth {

    public static void main(String[] args) {

        String string1 = "()()";
        String string2 = "()((()))";
        String string3 = "";

        System.out.println(version1(string1));
        System.out.println(version1(string2));
        System.out.println(version1(string3));
    }

    public static int version1(String bracketMatchingString) {

        int maxDepth = 0, count = 0;

        char[] chars = bracketMatchingString.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                count ++;
            } else {
                count --;
            }

            maxDepth = Math.max(count, maxDepth);
//            if (chars[i] == '(') {
//                count ++;
//                continue;
//            }
//            count --;

        }

        return maxDepth;
    }
}
