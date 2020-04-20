package algorithm.sword2offer.recursive;

/**
 * @author JonSnow
 * @desc 求解斐波拉契数列任意项的值（int表示范围内）
 * @date 2019/6/18
 */
public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(calByRecursive(-2));
        System.out.println("===========================================");
        System.out.println(dynamicProgramming(40));
    }

    /**
     * 递归求解斐波拉契数列第n项的值
     * 使用递归求解，有很多项被重复计算
     * f(n) = f(n-1) + f(n-2)
     * f(n-1) = f(n-2) + f(n-3)
     * f(n-2)被重复计算
     * @param n
     * @return
     */
    private static int calByRecursive(int n) {
        if (n < 0) {
            return 0;
        }

        if (n < 2) {
            return n;
        }
        return calByRecursive(n - 1) + calByRecursive(n - 2);
    }

    /**
     * 非递归求解
     */
    private static int dynamicProgramming(int n) {
        if (n < 0) {
            return 0;
        }

        if (n < 2) {
            return n;
        }

        int pre1 = 0, pre2 = 1, fn = 0;
        for (int i = 2; i <= n; i++) {
            fn = pre1 + pre2;
            pre1 = pre2;
            pre2 = fn;
        }

        return fn;
    }

}
