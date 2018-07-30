package zzy.binarysearchtree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * created in 0:02 2018/7/29 by zhaozhenyu.
 *
 * 添加：floor, celiing , select , rank 方法
 */

public class BST2<E extends Comparable<E>> {

    private class Node {
        E e;
        Node left,right;
        int size; // 以该节点为根的子树中的节点总数

        Node(E e, int size) {
            this.e = e;
            this.left = null;
            this.right = null;
            this.size = size;
        }
    }

    /*******************************************************/

    private Node root;

    public BST2() {
        this.root = null;
    }

    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 向以node为根的二分搜索树中插入元素e
     * 返回插入新节点后二分搜索树的根
     * @param node
     * @param e
     * @return
     */
    private Node add(Node node, E e) {
        if (node == null) {
            return new Node(e,1);
        }
        // 相等元素不做处理
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left,e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right,e);
        }
        node.size = size(node.left)+size(node.right)+1;
        return node;
    }

    public boolean contains(E e) {
       return  contains(root,e);
    }

    /**
     * 查看以node为根节点的搜索树是否包含e
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node, E e) {
        if (node == null) return false;

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left,e);
        } else {
            return contains(node.right,e);
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        StringBuilder res = new StringBuilder();
        res.append("preOrder: ");
        preOrder(root,res);
        System.out.println(res.substring(0,res.length()-2).toString());
    }

    private void preOrder(Node node,StringBuilder res) {
        if (node == null) return ;
        res.append(node.e).append("->");
        preOrder(node.left,res);
        preOrder(node.right,res);
    }

    /**
     * 前序非递归遍历
     */
    public void preOrderNR1() {
        Stack<Node> stack = new Stack<>();
        StringBuilder res = new StringBuilder();
        res.append("preOrderNR1: ");
        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            res.append(cur.e).append("->");

            //模拟栈
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }

        System.out.println(res.substring(0,res.length()-2).toString());
    }

    /**
     *经典非递归遍历
     */
    public void preOrderNR2() {
        Stack<Node> stack = new Stack<>();
        StringBuilder res = new StringBuilder();
        res.append("preOrderNR2: ");
        if (root == null) {
            return ;
        }
        Node node = root;
        while (node != null || !stack.isEmpty()) {

            while (node != null) {
                res.append(node.e).append("->");
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            node = node.right;
        }
        System.out.println(res.substring(0,res.length()-2).toString());
    }

    /**
     * 模拟系统栈调用
     */
    public void preOrderNR3() {
        if (root == null) return ;
        StringBuilder res = new StringBuilder();
        res.append("preOrderNR3: ");
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go",root));
        while (!stack.isEmpty()) {
            Command command = stack.pop();
            String instruction = command.s;
            Node node = command.node;
            if (instruction.equals("print")) {
                res.append(node.e).append("->");
            } else { // go
                if (node.right != null) stack.push(new Command("go",node.right));
                if (node.left != null) stack.push(new Command("go",node.left));
                stack.push(new Command("print",node));
            }
        }
        System.out.println(res.substring(0,res.length()-2).toString());
    }

    private class Command {
        // 指令：go表示模拟系统栈操作相关节点; print表示访问输出此节点
        String s; // go,print
        Node node;
        Command(String s,Node node) {
            this.s = s;
            this.node = node;
        }
    }


    public void inOrder() {
        StringBuilder res = new StringBuilder();
        res.append("inOrder: ");
        inOrder(root,res);
        System.out.println(res.substring(0,res.length()-2).toString());
    }

    private void inOrder(Node node, StringBuilder res) {
        if (node == null) return ;
        inOrder(node.left,res);
        res.append(node.e).append("->");
        inOrder(node.right,res);
    }

    public void inOrderNR1() {
        if (root == null) return ;
        StringBuilder res = new StringBuilder();
        Stack<Node> stack = new Stack<>();
        res.append("inOrderNR1: ");
        Node node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            res.append(node.e).append("->");
            node = node.right;
        }

        System.out.println(res.substring(0,res.length()-2).toString());
    }

    public void inOrderNR2() {
        if (root == null) return ;
        StringBuilder res = new StringBuilder();
        Stack<Command> stack = new Stack<>();
        res.append("inOrderNR2: ");

        stack.push(new Command("go", root));

        while (!stack.isEmpty()) {
           String instruction = stack.peek().s;
           Node node = stack.peek().node;
           stack.pop();
           if (instruction.equals("print")) {
                res.append(node.e).append("->");
           } else { // go
               if (node.right != null) stack.push(new Command("go",node.right));
               stack.push(new Command("print",node));
               if (node.left != null) stack.push(new Command("go", node.left));
           }
        }


        System.out.println(res.substring(0,res.length()-2).toString());

    }

    public void postOrder() {
        StringBuilder res = new StringBuilder();
        res.append("postOrder: ");
        postOrder(root,res);
        System.out.println(res.substring(0,res.length()-2).toString());
    }

    private void postOrder(Node node, StringBuilder res) {
        if (node == null) return ;
        postOrder(node.left,res);
        postOrder(node.right,res);
        res.append(node.e).append("->");
    }

    public void postOrderNR1() {
        if (root == null) return ;
        StringBuilder res = new StringBuilder();
        res.append("postOrderNR1: ");
        Stack<Node> stack = new Stack<>();
        Node node = root;
        Node pre = null;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            Node right = stack.peek().right;
            if (right == null || pre == right ) {
                node = stack.pop();
                res.append(node.e).append("->");
                pre = node;
                node = null;
            } else {
                stack.push(right);
            }

        }
        System.out.println(res.substring(0,res.length()-2).toString());
    }

    public void postOrderNR2() {
        if (root == null) return;
        StringBuilder res = new StringBuilder();
        res.append("postOrderNR2: ");
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go",root));
        while (!stack.isEmpty()) {
            Command command = stack.pop();
            String instruction = command.s;
            Node node = command.node;

            if (instruction.equals("print")) {
                res.append(node.e).append("->");
            } else { // go
                stack.push(new Command("print",node));
                if (node.right != null) stack.push(new Command("go",node.right));
                if (node.left != null) stack.push(new Command("go",node.left));
            }
        }

        System.out.println(res.substring(0,res.length()-2).toString());
    }


    public int size() {
        return size(root);
    }

    private int size(Node node) {
        return node == null ? 0 : node.size;
    }
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 层序遍历（广度优先遍历）
     */
    public void levelOrder() {
         Queue<Node> queue = new LinkedList<>();
         queue.add(root);
         while (!queue.isEmpty()) {
             Node cur = queue.remove();
             System.out.println(cur.e);
             if (cur.left != null) queue.add(cur.left);
             if (cur.right != null) queue.add(cur.right);
         }
    }

    public E minimum() {
        if (isEmpty()) {
            throw new IllegalArgumentException("bst is empty.");
        }
        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    public E maximum() {
        if (isEmpty()) {
            throw new IllegalArgumentException("bst is empty.");
        }
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    /**
     * 删除以node为根节点的最小值，返回新的根节点
     * @param node
     * @return
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.left = null;
            return rightNode;
        }
        node.left = removeMin(node.left);
        node.size = size(node.left)+size(node.right)+1;
        return node;
    }

    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    /**
     * 删除以node为根节点的最大值，返回新的根节点
     * @param node
     * @return
     */
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            return leftNode;
        }
        node.right = removeMax(node.right);
        node.size = size(node.left)+size(node.right)+1;
        return node;
    }

    public void remove(E e) {
        root = remove(root,e);
    }

    private Node remove(Node node, E e) {
        if (node == null) return null;
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left,e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right,e);
        } else { // e == node.e

            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                return rightNode;
            }

            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                return leftNode;
            }

            // 有左右子树
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            node = successor;
        }
        node.size = size(node.left)+size(node.right)+1;
        return node;
    }


    /**
     * 向下取整：查询不大于（小于或等于）e的最大值
     * @param e 不一定存在于二叉搜索树中
     * @return
     */
    public E floor(E e) {
        Node node = floor(root, e);
        return node == null ? null : node.e;
    }

    private Node floor(Node node, E e) {
        if (node == null) return null;
        int cmp = e.compareTo(node.e);
        if (cmp == 0) return node;
        if (cmp < 0) return floor(node.left,e);
        Node cur = floor(node.right,e);
        return cur != null ? cur : node;
    }

    public E ceiling(E e) {
        Node node = ceiling(root, e);
        return node == null ? null : node.e;
}

    /**
     * 向上取整：查询不小于e的最小值
     * @param node
     * @param e
     * @return
     */
    private Node ceiling(Node node, E e) {
        if (node == null) return null;
        int cmp = e.compareTo(node.e);
        if (cmp == 0) return node;
        if (cmp > 0) return ceiling(node.right,e);
        Node cur = ceiling(node.left,e);
        return cur != null ? cur : node;
    }

    /**
     * 查询排名为k的值
     * @param k 从0开始
     * @return
     */
    public E select(int k) {
        return select(root,k).e;
    }

    private Node select(Node node, int k) {
        if (node == null) return null;
        int leftSize = size(node.left);
        if (leftSize > k) return select(node.left,k);
        else if (leftSize < k) return select(node.right, k-leftSize-1);
        else return node;
    }

    /**
     * 查询元素e的排名，从0开始
     * @param e
     * @return
     */
    public int rank(E e) {
        return rank(root,e);
    }

    private int rank(Node node, E e) {
        if (node == null) return 0;
        int cmp = e.compareTo(node.e);
        if (cmp < 0) return rank(node.left,e);
        else if (cmp > 0) return 1+size(node.left)+rank(node.right,e);
        else return size(node.left);
    }

    @Override
    public  String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root,0,res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth)+"null\n");
            return ;
        }
        res.append(generateDepthString(depth)+node.e+"\n");
        generateBSTString(node.left,depth+1,res);
        generateBSTString(node.right,depth+1,res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

}
