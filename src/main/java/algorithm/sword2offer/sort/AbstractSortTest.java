package algorithm.sword2offer.sort;

import java.util.Random;

/**
 * @author JonSnow
 * @desc 排序算法测试基类
 * @date 2019/7/31
 */
public abstract class AbstractSortTest {

    private static final Random random = new Random();
    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 10;
    private static final int MAX_NUMBER = 1000;
    private static final int TEST_TIMES = 10000;

    abstract void sort(int[] arr);

    protected void doTest() {
        long startTime = System.currentTimeMillis();

        for (int t = 0; t < TEST_TIMES; t++) {

            int arrLenth = random.nextInt(MAX_LENGTH + 1);
            while(arrLenth < MIN_LENGTH) {
                arrLenth = random.nextInt(MAX_LENGTH + 1);
            }

            int[] sampleArr = new int[arrLenth];

            for(int i = 0; i < arrLenth; i++) {
                sampleArr[i] = random.nextInt(MAX_NUMBER + 1);
            }

            System.out.println("=============================================================================================");
            System.out.print("排序前：");
            printArr(sampleArr);

            sort(sampleArr);

            System.out.print("排序后：");
            printArr(sampleArr);

            boolean isScuccess = isASC(sampleArr);
            System.out.println("排序结果：" + isScuccess);
            System.out.println("=============================================================================================");
            if (!isScuccess) break;
        }

        long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime) + "ms");
    }

    /**
     * 异或换位
     * @param arr
     * @param i
     * @param j
     */
    protected void swap1(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * 使用临时变量换位
     * @param arr
     * @param i
     * @param j
     */
    protected void swap2(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 打印数组
     * @param arr
     */
    protected static void printArr(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    /**
     * 检查数组是否有序（升序）
     * @param arr
     * @return
     */
    protected boolean isASC(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) return false;
        }
        return true;
    }

    /**
     * [from,to]范围内二分查找特定值插入位置
     * 包含from和to，from->to范围内为有序区域（升序）
     * @param arr
     * @param from
     * @param to
     * @param target
     * @return
     */
    protected static int binarySearchPosition(int arr[], int from, int to, int target) {

        int mid = (from + to)/2;

        if (from >= to) return mid;

        if (target > arr[mid])
            return binarySearchPosition(arr, mid + 1, to, target);
        else if (target < arr[mid])
            return binarySearchPosition(arr, from, mid - 1, target);
        else
            return mid;
    }

//    public static void main(String[] args) {
//        long startTime = System.currentTimeMillis();
//
//        for (int t = 0; t < TEST_TIMES; t++) {
//
//            int arrLenth = random.nextInt(MAX_LENGTH + 1);
//            while(arrLenth < MIN_LENGTH) {
//                arrLenth = random.nextInt(MAX_LENGTH + 1);
//            }
//
//            int[] sampleArr = new int[arrLenth];
//
//            for(int i = 0; i < arrLenth; i++) {
//                sampleArr[i] = random.nextInt(MAX_NUMBER + 1);
//            }
//
//            for (int i = 0; i < sampleArr.length - 1; i++) {
//                // 从除已排序好数字外的数字中选出最大数字
//                for (int j = 0; j < sampleArr.length - 1 - i; j++) {
//                    if (sampleArr[j] > sampleArr[j + 1]) {
//                        int temp = sampleArr[j];
//                        sampleArr[j] = sampleArr[j + 1];
//                        sampleArr[j + 1] = temp;
//                    }
//                }
//            }
//
//            int rv = random.nextInt(MAX_NUMBER + 1);
//            int p = binarySearchPosition(sampleArr, 0, arrLenth - 1, rv);
//
//            if ((p - 1 >= 0 && rv >= sampleArr[p - 1] && rv <= sampleArr[p]) || (p - 1 < 0 && rv <= sampleArr[p])) {
//                System.out.println("====================================================================");
//                System.out.println("sucess");
//                System.out.println("====================================================================");
//            } else {
//                System.out.println("fail:" + rv + "," + p);
//                printArr(sampleArr);
//                break;
//            }
//        }
//
//        long endTime = System.currentTimeMillis();
//        System.out.println("耗时：" + (endTime - startTime) + "ms");
//    }
}
