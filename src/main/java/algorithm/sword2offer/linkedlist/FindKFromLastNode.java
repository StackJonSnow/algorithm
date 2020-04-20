package algorithm.sword2offer.linkedlist;

import algorithm.base.LinkedNode;

import java.util.Objects;

/**
 * @author JonSnow
 * @desc 题目描述
 *       输入一个链表，输出该链表中倒数第k个结点。
 * @thought 首先两个节点/指针，一个节点 node1 先开始跑，指针 node1 跑到 k-1 个节点后，另一个节点 node2 开始跑，
 *          当 node1 跑到最后时，node2 所指的节点就是倒数第k个节点也就是正数第(L-K+1)个节点。
 * @date 2020/4/10
 */
public class FindKFromLastNode {

    public static void main(String[] args) {
        LinkedNode l1 = new LinkedNode(5);
        l1.nextNode = new LinkedNode(2);
        l1.nextNode.nextNode = new LinkedNode(1);
        l1.nextNode.nextNode.nextNode = new LinkedNode(5);
        l1.nextNode.nextNode.nextNode.nextNode = new LinkedNode(9);

        System.out.println(solution(l1, 1).value);
    }

    public static LinkedNode solution(LinkedNode head, int k) {

        LinkedNode fastNode = head, slowNode = head;

        int count = 1;
        while(Objects.nonNull(fastNode)) {
            fastNode = fastNode.nextNode;
            count ++;
            if (count >= k + 2) {
                slowNode = slowNode.nextNode;
            }
        }

        return slowNode;
    }

}
