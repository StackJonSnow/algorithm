package algorithm.sword2offer.datastructure;

import java.util.Stack;

/**
 * @author JonSnow
 * @desc 题目描述
 *       用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 * @thought 一个栈用来进队列，一个栈用来出队列，从进栈到出栈过程会将原序列颠倒一次，两个栈颠倒两次就跟原序列顺序一样了。
 * @date 2020/4/11
 */
public class QueueImplementsByTwoStack {

    public static void main(String[] args) {
        QueueImplementsByTwoStack queue = new QueueImplementsByTwoStack();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);

        while(!queue.isEmpty()) {
            System.out.print(queue.pop() + " ");
        }
    }

    private Stack<Integer> pushStack = new Stack<>();
    private Stack<Integer> popStack = new Stack<>();

    public void push(Integer integer) {
        pushStack.push(integer);
    }

    public Integer pop() {
        if (pushStack.isEmpty() && popStack.isEmpty()) {
            throw new RuntimeException("queue is empty");
        }

        if (!pushStack.isEmpty()) {
            while(!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }

        return popStack.pop();
    }

    public boolean isEmpty() {
        return pushStack.isEmpty() && popStack.isEmpty();
    }
}
