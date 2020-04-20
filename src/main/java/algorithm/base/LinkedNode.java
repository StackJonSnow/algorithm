package algorithm.base;

import java.util.Objects;

/**
 * @author JonSnow
 * @desc 链表节点，单向链实现，提供构建链表静态方法
 * @date 2019/6/18
 */
public class LinkedNode {

    public LinkedNode nextNode;
    public Integer value;

    public LinkedNode(Integer value) {
        this.value = value;
    }

    /**
     * 构建链表，并返回头节点
     * @param totalNumberOfNodes
     * @return
     */
    public static LinkedNode buildLinkedListAndReturnHead(int totalNumberOfNodes) {
        LinkedNode head = new LinkedNode(0);
        LinkedNode beforeNode = head;
        for (int i = 1; i < totalNumberOfNodes; i++) {
            beforeNode.nextNode = new LinkedNode(Integer.valueOf(i));
            beforeNode = beforeNode.nextNode;
        }
        return head;
    }

    public LinkedNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(LinkedNode nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public String toString() {
        return "" + value;
    }


    public void print() {
        LinkedNode head = this;
        while (Objects.nonNull(head)) {
            System.out.print(head.value);
            if (Objects.nonNull(head.nextNode)) {
                System.out.print("-->");
            }
            head = head.nextNode;
        }
    }
}
