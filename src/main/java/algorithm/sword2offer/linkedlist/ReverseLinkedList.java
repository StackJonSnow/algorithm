package algorithm.sword2offer.linkedlist;

import algorithm.base.LinkedNode;

import java.util.Objects;

/**
 * @author JonSnow
 * @desc 题目描述
 *       输入一个链表，反转链表后，输出链表的所有元素。
 * @thought 反转链表即将当前节点的后一个节点的 next 域指向当前节点
 * @date 2020/4/10
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        LinkedNode l1 = new LinkedNode(5);
        l1.nextNode = new LinkedNode(2);
        l1.nextNode.nextNode = new LinkedNode(1);
        l1.nextNode.nextNode.nextNode = new LinkedNode(5);
        l1.nextNode.nextNode.nextNode.nextNode = new LinkedNode(9);

        solution(l1).print();
    }

    public static LinkedNode solution(LinkedNode head) {

        if (Objects.isNull(head)) return null;

        LinkedNode preNode = head, currentNode = head.nextNode;
        head.nextNode = null;

        while (Objects.nonNull(currentNode)) {

            // 临时存储当前节点的下一个节点
            LinkedNode nextNode = currentNode.nextNode;

            // 将当前节点的 next 域指向前置节点
            currentNode.nextNode = preNode;

            // 将当前节点作为前置节点
            preNode = currentNode;

            // 将本次遍历的节点的下一个节点当作下次遍历的节点
            currentNode = nextNode;
        }

        return preNode;
    }

}
