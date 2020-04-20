package algorithm.sword2offer.array;

import java.util.Objects;

/**
 * @author JonSnow
 * @desc 题目描述
 *       输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 *       所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * @thought 数组长度设为m，统计所有的奇数数量n，使用一个额外的空数组，然后定义两个指针，第一指针o为奇数位置指针，指向0位置，
 *          第二个指针e为偶数指针，指向 n，遍历原数组，若是奇数则放入o指针位置，o向后移动，若为偶数则放到指针e的位置，e向后移动
 * @date 2020/4/11
 */
public class OddNumbersInFrontOfEvenNumbers {

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,9,21,23,16};
        int[] solution = solution(array);

        for (int i = 0; i < solution.length; i++) {
            System.out.print(solution[i] + " ");
        }
    }

    public static int[] solution(int[] array) {

        if (Objects.isNull(array)) {
            return array;
        }

        int [] temp = new int[array.length];
        int o = 0, e = countOddNumbers(array);

        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 1) {
                temp[o++] = array[i];
            } else {
                temp[e++] = array[i];
            }
        }

        return temp;
    }

    public static int countOddNumbers(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 1) {
                count ++;
            }
        }
        return count;
    }
}
