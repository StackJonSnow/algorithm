package algorithm.sword2offer.sort;

/**
 * @author JonSnow
 * @desc 插入排序
 * @date 2019/8/2
 */
public class InsertSort extends AbstractSortTest {

    public static void main(String[] args) {
        new InsertSort().doTest();
    }

    @Override
    void sort(int[] arr) {
        impl2(arr);
    }

    /**
     * 实现1
     * @param arr
     */
    void impl1(int[] arr) {

        // 长度为1不需要排序
        if (arr.length < 2) return;

        // arr[0] 为初始有序区域，从下标1开始插入排序
        int p = 1;

        // 待排序元素遍历完数组末尾时排序过程完成
        while (p < arr.length) {

            // t为排序区域下标指针，从排序区域最后一个元素开始，向前遍历，ti记录待排序元素
            int t = p, ti = arr[p++];

            // 遍历边界：排序区域首位
            while (t > 0) {

                // 如果当前元素值大于待排序元素则在数组中位置向后移动一位
                if (ti < arr[--t]) {
                    arr[t + 1] = arr[t];
                    if (t == 0) {
                        // 遍历至排序区首位时，证明待排序元素为当前排序区中值最小
                        arr[t] = ti;
                        break;
                    }
                    continue;
                }

                // 插入待排序元素
                arr[t + 1] = ti;
                break;
            }

        }
    }

    /**
     * 实现2
     * @param arr
     */
    void impl2(int[] arr) {

        int len = arr.length;

        if (len < 2) return;

        for (int i = 1; i < len; i++) {

            int cur = arr[i], j = i - 1;

            // while 实现
//            while (j >= 0 && arr[j] > cur) {
//                arr[j + 1] = arr[j];
//                j--;
//            }

            // for 实现
            for (; j >= 0 && cur < arr[j]; j--)
                arr[j + 1] = arr[j];

            arr[j + 1] = cur;

        }
    }
}
