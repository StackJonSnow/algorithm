package algorithm.leetcode.linkedlist;

import algorithm.base.LinkedNode;

import java.util.Objects;

/**
 * @author JonSnow
 * @desc 题目描述
 *       给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 *       你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * @thought 两个只有一位的十进制数相加要么产生一个进位，要么不会产生进位，所以可以定义一个变量用来存储进位，
 *          将该变量带入下一个节点的相加计算中
 * @date 2020/4/10
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        LinkedNode l1 = new LinkedNode(5);
        l1.nextNode = new LinkedNode(2);
        l1.nextNode.nextNode = new LinkedNode(1);
        LinkedNode l2 = new LinkedNode(6);
        l2.nextNode = new LinkedNode(4);
        l2.nextNode.nextNode = new LinkedNode(9);

        LinkedNode solution = solution(l1, l2);

        LinkedNode currentNode = solution;
        while(Objects.nonNull(currentNode)) {
            System.out.print(currentNode.value);
            if (Objects.nonNull(currentNode.nextNode)) {
                System.out.print("-->");
            }
            currentNode = currentNode.nextNode;
        }
    }

    public static LinkedNode solution(LinkedNode l1, LinkedNode l2) {

        LinkedNode addend1 = l1, addend2 = l2;
        LinkedNode head = new LinkedNode(null), currentNode = head;

        int carry = 0;

        while (Objects.nonNull(addend1) || Objects.nonNull(addend2)) {

            int x = Objects.nonNull(addend1) ? addend1.value : 0;
            int y = Objects.nonNull(addend2) ? addend2.value : 0;

            int sum = x + y + carry;
            currentNode.nextNode = new LinkedNode( sum % 10);
            currentNode = currentNode.nextNode;
            carry = sum / 10;

            if (Objects.nonNull(addend1)) addend1 = addend1.nextNode;
            if (Objects.nonNull(addend2)) addend2 = addend2.nextNode;
        }

        if (carry > 0) {
            currentNode.nextNode = new LinkedNode( carry);
        }

        return head.nextNode;
    }
}
