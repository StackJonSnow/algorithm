package algorithm.sword2offer.array;

/**
 * @author JonSnow
 * @desc 从一个二维数组中查找一个数字是否存在
 *       该二维数组有如下特性
 *       针对每一行，每一列
 *       1. 从左到右依次增大
 *       2. 从上到下依次增大
 *       时间复杂度要求 O(M + N)
 *       空间复杂度要求 O(1)
 * @thought
 *       首先得想到如何利用此数组的特性
 *       由于是查找数据，可以利用近似二分法的思想去寻找解决方案
 *       利用数组的有序性，可以从数组右上角开始查找，
 *       然后类似二分的方法迭代遍历，直到遍历完数组
 * @date 2019/4/10
 */
public class FindNumFrom2DArray {

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 6, 8}, {5, 7, 9}};
        System.out.println(findNumFrom2DArray(arr, 9));
    }

    public static boolean findNumFrom2DArray(int[][] arr, int target) {
        int row = 0;
        int colum = arr[0].length - 1;

        while(row < arr.length && colum >= 0) {
            if (target == arr[row][colum]) {
                return true;
            } else if (target < arr[row][colum]) {
                colum--;
            } else {
                row++;
            }
        }

        return false;
    }
}
