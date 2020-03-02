package test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Traversal {

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int value;

        TreeNode(int value) {
            this.value = value;
        }
    }

    private TreeNode buildTree() {
        TreeNode root = new TreeNode(0);
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);

        root.left = t1;
        root.right = t2;

        t1.left = t3;
        t1.right = t4;
        t2.left = t5;
        t2.right = t6;

        t3.left = t7;

        return root;
    }

    @Test
    public void testInorderSimple() {
        TreeNode root = buildTree();

        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        System.out.println(list);
    }

    private void inorder(TreeNode node, List<Integer> list) {
        if (node == null)
            return;

        inorder(node.left, list);
        list.add(node.value);
        inorder(node.right, list);
    }

    @Test
    public void testInorderWithoutRecursion() {
        TreeNode root = buildTree();
        List<Integer> list = new ArrayList<>();
        List<Integer> lis2 = new ArrayList<>(list);

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(!stack.empty() || curr != null) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            list.add(curr.value);
            curr = curr.right;
        }

        System.out.println(list);
    }

    @Test
    public void testPreorderSimple() {
        TreeNode root = buildTree();
        List<Integer> list = new ArrayList<>();

        preorder(root, list);
        System.out.println(list);
    }

    private void preorder(TreeNode node, List<Integer> list) {
        if (node == null)
            return;

        list.add(node.value);
        preorder(node.left, list);
        preorder(node.right, list);
    }

    @Test
    public void testPreorderWithoutRecursion() {
        TreeNode root = buildTree();
        List<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode curr;
        while(!stack.empty()) {
            curr = stack.pop();
            list.add(curr.value);
            if (curr.right != null)
                stack.push(curr.right);
            if (curr.left != null)
                stack.push(curr.left);
        }
        System.out.println(list);
    }

    @Test
    public void testPostorderSimple() {
        TreeNode root = buildTree();
        List<Integer> list = new ArrayList<>();

        postorder(root, list);
        System.out.println(list);
    }

    private void postorder(TreeNode node, List<Integer> list) {
        if (node == null)
            return;

        postorder(node.left, list);
        postorder(node.right, list);
        list.add(node.value);
    }

    @Test
    public void testPostorderWithoutRecursion() {
        TreeNode root = buildTree();
        List<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();


    }
}
