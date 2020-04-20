package algorithm.sword2offer.linkedlist;

import algorithm.base.LinkedNode;

import java.util.Stack;

/**
 * @author JonSnow
 * @desc 传入头结点从尾到头反过来打印链表节点
 * @date 2019/6/18
 */
public class PrintLinkedListByDesc {

    public static void main(String[] args) {
        solveByStack(LinkedNode.buildLinkedListAndReturnHead(10));
        System.out.println();
        System.out.println("==========================================");
        solveByInsertHead(LinkedNode.buildLinkedListAndReturnHead(10));
    }

    /**
     * 使用栈实现
     * @param head
     */
    private static void solveByStack(LinkedNode head) {
        Stack<LinkedNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.getNextNode();
        }

        int nodeNumbers = stack.size();
        for (int i = 0; i < nodeNumbers; i++) {
            System.out.print(stack.pop() + " ");
        }
    }

    /**
     * 使用头插法重新构建链表
     * @param head
     */
    private static void solveByInsertHead(LinkedNode head) {

        LinkedNode nextNode = head.getNextNode();
        head.setNextNode(null);

        while(nextNode != null) {
            LinkedNode beforeNode = nextNode;
            nextNode = nextNode.getNextNode();
            beforeNode.setNextNode(head);
            head = beforeNode;
        }

        while (head != null) {
            System.out.print(head + " ");
            head = head.getNextNode();
        }
    }
}
