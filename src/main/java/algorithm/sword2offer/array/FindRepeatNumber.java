package algorithm.sword2offer.array;

/**
 * @author JonSnow
 * @desc 寻找数组内重复数字
 *       一个数组长度为 n，数组元素值范围为 0~n-1
 *       元素有可能重复，从数组中找到重复的元素
 *       时间复杂度要求 O(N)
 *       空间复杂度要求 O(1)
 * @thought
 *      如果能够将所有的数字都放到数组中跟数字相等的下标位置去
 *      那么就可以根据判断当前位置的值与与该值相等的数组下标
 *      对应的值是否相等来判断是否有重复数字
 *      其实这就是一个对号入座的问题，如果位置被占用，则存在座位号重复情况
 *
 * @date 2019/4/10
 */
public class FindRepeatNumber {

    public static void main(String[] args) {
        int[] nums = {0, 1, 9, 3, 5, 4, 2, 6, 8, 7};
        System.out.println(findRepeatNumberAndPrintTheTrace(nums));
    }

    public static boolean findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return true;
                }
                swap(nums, nums[i], i);
            }
        }
        return false;
    }

    public static boolean findRepeatNumberAndPrintTheTrace(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                printArr(nums);
                if (nums[i] == nums[nums[i]]) {
                    return true;
                }
                swap(nums, nums[i], i);
            }
        }
        return false;
    }

    private static void printArr(int[] nums) {
        for (int i = 0; i< nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
