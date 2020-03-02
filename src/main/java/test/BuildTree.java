package test;

public class BuildTree {

    static public TreeNode buildTree(Integer[] values) {
        return generateFromInorder(values, 0);
    }

    private static TreeNode generateFromInorder(Integer[] values, int i) {
        if (i >= values.length || values[i] == null)
            return null;

        TreeNode root = new TreeNode(values[i]);

        root.left = generateFromInorder(values, 2 * i + 1);
        root.right = generateFromInorder(values, 2 * i + 2);

        return root;
    }
}
