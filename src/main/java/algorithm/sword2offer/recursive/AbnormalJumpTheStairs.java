package algorithm.sword2offer.recursive;

/**
 * @author JonSnow
 * @desc 题目描述
 *       一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * @thought 设f(n)为跳上n阶台阶的所有跳法种数，那么
 *          当最后一步跳n级时，有1种跳法
 *          当最后一步跳n-1级时，有f(1)种跳法
 *          当最后一步跳n-2级时，有f(2)种跳法
 *          …………
 *          当最后一步跳3级时，有f(n-3)种跳法
 *          当最后一步跳2级时，有f(n-2)种跳法
 *          当最后一步跳1级时，有f(n-1)种跳法
 *          =>f(n) = 1 + f(1) + f(2) + …… + f(n-2) + f(n-1)
 *          则f(n-1) = 1 + f(1) + f(2) + …… + f(n-2)
 *          =>f(n) = 2f(n-1),并且当n=1时跳法种数为1
 * @date 2020/4/11
 */
public class AbnormalJumpTheStairs {

    public static void main(String[] args) {
        System.out.println(solution(1));
        System.out.println(solution(2));
        System.out.println(solution(3));
    }

    public static int solution(int n) {
        return 1 << n - 1;
//        return 1 << --n;
    }
}
