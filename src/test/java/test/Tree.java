package test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static test.BuildTree.buildTree;

public class Tree {

    @Test
    public void testInorderTraversalWithoutStack() {
        Integer[] values = {0, 1, 2, 3, 4, 5, 6, 7};
        TreeNode root = buildTree(values);

        TreeNode curr = root;

        List<Integer> list = new ArrayList<>();
        while(curr != null) {
            if (curr.left == null) {
                list.add(curr.val);
                curr = curr.right;
            }

            TreeNode rightMost = curr;
            while(curr.left != null) {
                curr = curr;
            }
        }
    }

    @Test
    public void testMaxWidthOfBinaryTree() {
        Integer[] values = {1,1,1,1,null,null,1,1,null,null,1};
        TreeNode root = buildTree(values);

        Deque<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        int maxWidth = 0;
        while(!queue.isEmpty()) {

            while(!queue.isEmpty() && queue.peekFirst() == null) {
                queue.removeFirst();
            }

            while(!queue.isEmpty() && queue.peekLast() == null) {
                queue.removeLast();
            }

            int currWidth = queue.size();
            for (int i=queue.size(); i>0; i--) {
                TreeNode node = queue.poll();
                if (node != null) {
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            if (currWidth > maxWidth) {
                maxWidth = currWidth;
            }
        }

        assertEquals(4, maxWidth);
    }

    private static final String splitter = ",";
    private static final String nnull = "X";

    @Test
    public void test_SerializeDeserializeTree() {
        Integer[] values = {1,2,3,null,null,4,5};

        TreeNode root = buildTree(values);

        StringBuilder sb = new StringBuilder();

        sb.append(root.val);
        serializePreorder(root.left, sb);
        serializePreorder(root.right, sb);

        System.out.println(sb.toString());

        String data = "alsdkfj";
    }

    void serializePreorder(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(splitter);
            sb.append(nnull);
            return;
        }

        sb.append(splitter);
        sb.append(node.val);

        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();


        serializePreorder(node.left, sb);
        serializePreorder(node.right, sb);
    }


    @Test
    public void testVerifyPreorder() {
        Integer[] values = {50,17,9,14,12,23,19,76,54,72,67};

        assertTrue(preorder(values, 0, values.length));
    }

    private boolean preorder(Integer[] values, int start, int end) {
        if (start == end)
            return true;
        int root = values[start];

        int i;
        for (i=start; i<end; i++) {
            if (values[i] > root)
                break;
        }

        if (end - start > 2 && i == end) {
            return false;
        }

        return preorder(values, start+1, i) && preorder(values, i, end);
    }


}
