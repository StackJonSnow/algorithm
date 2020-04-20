package algorithm.sword2offer.linkedlist;

import algorithm.base.LinkedNode;

import java.util.Objects;

/**
 * @author JonSnow
 * @desc 题目描述
 *       输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 * @thought 比方有两个链表 L1, L2 首先比较两个链表的头节点，若 L1 的头节点比 L2 小，则 L1 的头节点将作为新的头节点，
 *          继续合并除去头节点的 L1，与 L2，将合并后结果的头节点作为 L1 头节点的 next 域，其实这个一个递归的过程，可以使用递归实现。
 * @date 2020/4/10
 */
public class MergeSortedLinkedList {

    public static void main(String[] args) {
        LinkedNode l1 = new LinkedNode(1);
        l1.nextNode = new LinkedNode(2);
        l1.nextNode.nextNode = new LinkedNode(3);
        LinkedNode l2 = new LinkedNode(2);
        l2.nextNode = new LinkedNode(4);
        l2.nextNode.nextNode = new LinkedNode(5);

        LinkedNode linkedNode = solution(l1, l2);
        linkedNode.print();
    }

    public static LinkedNode solution(LinkedNode node1, LinkedNode node2) {
        if (Objects.isNull(node1)) {
            return node2;
        }
        if (Objects.isNull(node2)) {
            return node1;
        }

        if (node1.value < node2.value) {
            node1.nextNode = solution(node1.nextNode, node2);
            return node1;
        } else {
            node2.nextNode = solution(node1, node2.nextNode);
            return node2;
        }
    }
}
