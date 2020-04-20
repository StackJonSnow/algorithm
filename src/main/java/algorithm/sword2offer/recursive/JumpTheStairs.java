package algorithm.sword2offer.recursive;

/**
 * @author JonSnow
 * @desc 题目描述
 *       一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * @thought 根据最后一步是跳两级台阶还是一级台阶来分为两种情况讨论，发现其实这就是一个非波拉契数列
 *          但是当台阶数小于3时，跳法种数跟台阶数一致
 * @date 2020/4/11
 */
public class JumpTheStairs {

    public static void main(String[] args) {
        System.out.println(solution1(1));
        System.out.println(solution1(2));
        System.out.println(solution1(3));
        System.out.println(solution1(4));
        System.out.println("===========================");
        System.out.println(solution2(1));
        System.out.println(solution2(2));
        System.out.println(solution2(3));
        System.out.println(solution2(4));
    }

    /**
     * 递归版本
     * */
    public static int solution1(int n) {

        if (n <= 0) {
            return 0;
        }

        if (n < 3) {
            return n;
        }

        return solution1(n - 1) + solution1(n - 2);
    }

    /**
     * 非递归版本，采用迭代法
     * */
    public static int solution2(int n) {

        if (n <= 0) {
            return 0;
        }

        if (n < 3) {
            return n;
        }

        int pre1 = 2, pre2 = 1, current = 0, count = 3;
        while(count <= n) {
            current = pre1 + pre2;
            pre2 = pre1;
            pre1 = current;
            count ++;
        }

        return current;
    }
}
