/**
 * @author crkimberley on 28/09/2016.
 */
public class BinaryTreeNode {

    private String value;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(char value, BinaryTreeNode left, BinaryTreeNode right) {
        this(Character.toString(value), left, right);
    }

    public BinaryTreeNode(String value, BinaryTreeNode left, BinaryTreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return "[" + value + " L" + (left != null ? left : "[]")
                + " R" + (right != null ? right : "[]") + "]";
    }
}