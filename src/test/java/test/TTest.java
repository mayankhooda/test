package test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TTest {

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
    public void testInorder() {
        TreeNode root = buildTree();

        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        System.out.println(list);
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        inorder(root.left, list);
        list.add(root.value);
        inorder(root.right, list);
    }

    @Test
    public void testInorderWithStack() {
        TreeNode root = buildTree();

        List<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        stack.clear();
        TreeNode curr = root;

        while(!stack.isEmpty() || curr != null) {

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
    public void testIOWS() {
        TreeNode root = buildTree();

        List<Integer> list = new ArrayList<>();

        TreeNode curr = root;

        while(curr != null) {

            if (curr.left == null) {
                list.add(curr.value);
                curr = curr.right;
            } else {
                TreeNode rightmost = curr.left;
                while(rightmost.right != null && rightmost.right != curr) {
                    rightmost = rightmost.right;
                }

                if (rightmost.right == null) {
                    rightmost.right = curr;
                    curr = curr.left;
                } else {
                    list.add(curr.value);
                    curr = curr.right;
                    rightmost.right = null;
                }
            }
        }

        System.out.println(list);
    }
}
