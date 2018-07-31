package zzy.binarysearchtree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * created in 0:02 2018/7/29 by zhaozhenyu.
 *
 * 普通二分搜索树
 */

public class BST<E extends Comparable<E>> {

    private class Node {
        E e;
        Node left,right;

        Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    /*******************************************************/

    private Node root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
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
            size ++;
            return new Node(e);
        }
        // 相等元素不做处理
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left,e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right,e);
        }

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
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
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
            size --;
            return rightNode;
        }
        node.left = removeMin(node.left);
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
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e) {
        root = remove(root,e);
    }

    private Node remove(Node node, E e) {
        if (node == null) return null;
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left,e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right,e);
            return node;
        } else { // e == node.e

            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 有左右子树
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
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
