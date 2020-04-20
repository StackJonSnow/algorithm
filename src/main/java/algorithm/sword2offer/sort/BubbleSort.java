package algorithm.sword2offer.sort;

/**
 * @author JonSnow
 * @desc 冒泡排序
 * @date 2019/7/31
 */
public class BubbleSort extends AbstractSortTest {

    public static void main(String[] args) {
        new BubbleSort().doTest();
    }

    @Override
    void sort(int[] arr) {

        if (arr == null | arr.length < 2) return;

        // 数组长度减一次冒泡即可排序完整个数组
        for (int i = 0; i < arr.length - 1; i++) {
            // 从除已排序好数字外的数字中选出最大数字
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) swap2(arr, j, j + 1);
            }
        }
    }


}
