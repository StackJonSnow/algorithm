package algorithm.sword2offer.datastructure;

import java.util.Stack;

/**
 * @author JonSnow
 * @desc 题目描述
 *       输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 *       例如序列1,2,3,4,5是某栈的压入顺序，序列4，5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 *      （注意：这两个序列的长度是相等的）
 * @thought 一个栈用来进队列，一个栈用来出队列，从进栈到出栈过程会将原序列颠倒一次，两个栈颠倒两次就跟原序列顺序一样了。
 * @date 2020/4/11
 */
public class MatchPushPopSequence {

    public static void main(String[] args) {
        int[] pushSeq = {1, 2, 3, 4, 5};
        int[] popSeq1 = {4, 5, 3, 2, 1};
        int[] popSeq2 = {4, 3, 5, 1, 2};

        System.out.println(solution(pushSeq, popSeq1));
        System.out.println(solution(pushSeq, popSeq2));
    }

    public static boolean solution(int[] pushSeq, int[] popSeq) {

        if (pushSeq.length != popSeq.length) {
            return false;
        }

        Stack<Integer> stack = new Stack<>();
        int popPointer = 0;
        for (int i = 0; i < pushSeq.length; i++) {
            stack.push(pushSeq[i]);
            int stackTop = pushSeq[i];
            while (popPointer < popSeq.length && stackTop == popSeq[popPointer] && !stack.isEmpty()) {
                stack.pop();
                popPointer ++;
                if (!stack.isEmpty()) {
                    stackTop = stack.peek();
                }
            }
        }

        return stack.isEmpty();
    }
}
