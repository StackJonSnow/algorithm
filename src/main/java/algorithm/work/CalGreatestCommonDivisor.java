package algorithm.work;

/**
 * @author JonSnow
 * @desc 求两个整数的最大公约数(辗转相除法)
 *       引理：gcd(a,b)=gcd(a,b%a)
 * @date 2019/9/4
 */
public class CalGreatestCommonDivisor {

    public static void main(String[] args) {
        System.out.println(cal1(12, 12));
        System.out.println(cal2(6, 12));
        System.out.println(cal3(-1, 12));
    }

    /**
     * 递归求解
     * @param a
     * @param b
     * @return
     */
    public static int cal1(int a, int b) {
        int max = Math.max(a, b), min = Math.min(a, b);

        int mod = max % min;

        if (mod == 0) {
            return min;
        }

        return cal1(min, mod);

    }

    /**
     * 非递归求解
     * @param a
     * @param b
     * @return
     */
    public static int cal2(int a, int b) {
        int mode;
        while((mode = a % b) != 0) {
            a = b;
            b = mode;
        }
        return b;
//
//        int max = Math.max(a, b), min = Math.min(a, b);
//
//        int mod = max % min;
//
//        while(mod != 0) {
//           max = min;
//           min = mod;
//           mod = max % min;
//        }
//
//        return min;
    }

    /**
     * 最简递归求解
     * @param a
     * @param b
     * @return
     */
    public static int cal3(int a, int b) {
        return b == 0 ? a : cal3(b, a % b);
    }
}
