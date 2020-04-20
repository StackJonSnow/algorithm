package algorithm.sword2offer.sort;

/**
 * @author JonSnow
 * @desc 快速排序
 * @date 2019/8/1
 */
public class QuickSort extends AbstractSortTest{

    public static void main(String[] args) {
        new QuickSort().doTest();
    }

    @Override
    void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public void quickSort(int arr[], int left, int right) {

        //边界条件：待排序区域只有一个元素
        if (right <= left) return;

        int l = left, r = right;
        int base = arr[l];
        while (r > l) {

            // 右指针扫描
            while (r > l) {
                if (arr[r] < base) {
                    arr[l] = arr[r];
                    break;
                }
                r--;
            }

            // 左指针扫描
            while (r > l) {
                if (arr[l] > base) {
                    arr[r] = arr[l];
                    break;
                }
                l++;
            }
        }

        // 基准元素归位
        arr[l] = base;

        // 排序基准元素左边区域
        quickSort(arr, left, l - 1);

        // 排序基准元素右边区域
        quickSort(arr, r + 1, right);

    }
}
