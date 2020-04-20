package algorithm.base;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

/**
 * <p>
 *     此类表示二叉树，仅用作简单二叉树遍历demo用，非线程安全
 */
public class BinaryTree<T> {

    public static void main(String[] args) {
        BinaryTree<String> binaryTree = new BinaryTree<>();
        String[] strs = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R"};

        for (String str : strs) {
            binaryTree.addNode(str);
        }
        System.out.println("----------------------先序遍历begin------------------------");
        System.out.println("【递归遍历】");
        binaryTree.preOrderRecursive(binaryTree.getRootNode());
        System.out.println("\n\n【非递归遍历】");
        binaryTree.preOrderAndPrint();
        System.out.println("----------------------先序遍历end------------------------");

    }

    private Node<T>[] nodes;
    private static final int DEFAULT_SIZE = 10;
    private int size;

    /**
     * <p>
     *     添加节点
     *
     */
    public void addNode(T t) {
        ensureCapacity();

        if (size == 0) {
            nodes[size++] = new Node<>(t);
            return;
        }

        /**
         * 从前到后遍历，先看左节点是否空，如果左节点空则放到左节点，
         * 然后看右节点是否空，如果右节点空则放到右节点，否则检查下一个元素
         */
        Node<T> curNode = new Node<>(t);
        nodes[size++] = curNode;
        for (int i = 0; i < size; i++) {
            if (nodes[i].left == null) {
                nodes[i].left = curNode;
                return;
            }

            if (nodes[i].right == null) {
                nodes[i].right = curNode;
                return;
            }
        }

    }

    /**
     * <p>
     *     非递归遍历
     *     先序遍历二叉树并输出遍历顺序
     *
     */
    public void preOrderAndPrint() {
        if (size != 0) {
            Stack<Node> stack = new Stack<>();
            Queue<T> queue = new ArrayDeque<>();

            Node<T> curNode = nodes[0];
            while (curNode != null || stack.size() > 0) {
                if (curNode != null) {
                    queue.add(curNode.val);
                    stack.push(curNode);
                    curNode = curNode.left;
                } else {
                    Node<T> parent = stack.pop();
                    curNode = parent.right;
                }
            }
            printTrack(queue);
        }
    }

    private void printTrack(Queue<T> queue) {
        StringBuilder sbl = new StringBuilder(queue.poll().toString());
        while (!queue.isEmpty()) {
            sbl.append("->");
            sbl.append(queue.poll());
        }
        System.out.println(sbl.toString());
    }

    /**
     * <p>
     *     递归遍历
     *     先序遍历二叉树并输出遍历顺序
     *
     */
    public void preOrderRecursive(Node<T> node) {
        System.out.print(node.val + " ");

        if (node.left != null)
            preOrderRecursive(node.left);
        if (node.right != null)
            preOrderRecursive(node.right);
    }

    /**
     * <p>
     *     检查数组容量，超量时进行必要扩容
     *
     */
    private void ensureCapacity() {
        if (nodes == null) nodes = new Node[DEFAULT_SIZE];
        if (size == nodes.length) {
            int oldCapacity = nodes.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            nodes = Arrays.copyOf(nodes, newCapacity);
        }
    }

    /**
     * <p>
     *     获取根节点
     *
     */
    public Node<T> getRootNode() {
        if (nodes != null)
            return nodes[0];
        return null;
    }

    /**
     * <p>
     *     获取二叉树节点数量
     *
     */
    public int size() {
        return size;
    }

    /**
     * <p>
     *     此类表示二叉树节点
     *     val：     节点值
     *     left：    左节点
     *     right：   右节点
     */
    static class Node<T> {

        private T val;
        private Node<T> left;
        private Node<T> right;

        public Node() {}

        public Node(T val) {
            this.val = val;
        }

        public T getVal() {
            return val;
        }

        public Node setVal(T val) {
            this.val = val;
            return this;
        }

        public Node<T> getLeft() {
            return left;
        }

        public Node setLeft(Node<T> left) {
            this.left = left;
            return this;
        }

        public Node<T> getRight() {
            return right;
        }

        public Node setRight(Node<T> right) {
            this.right = right;
            return this;
        }
    }
}
