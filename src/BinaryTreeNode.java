/**
 * @author crkimberley on 28/09/2016.
 */
public class BinaryTreeNode {

    private String value;
    private BinaryTreeNode leftBinaryTreeNode;
    private BinaryTreeNode rightBinaryTreeNode;

    public BinaryTreeNode(char value, BinaryTreeNode leftBinaryTreeNode, BinaryTreeNode rightBinaryTreeNode) {
        this(""+value, leftBinaryTreeNode, rightBinaryTreeNode);
    }

    public BinaryTreeNode(String value, BinaryTreeNode leftBinaryTreeNode, BinaryTreeNode rightBinaryTreeNode) {
        this.value = "" + value;
        this.leftBinaryTreeNode = leftBinaryTreeNode;
        this.rightBinaryTreeNode = rightBinaryTreeNode;
    }

    public String toString() {
        return "[" + value + " L" + (leftBinaryTreeNode != null ? leftBinaryTreeNode : "[]")
                + " R" + (rightBinaryTreeNode != null ? rightBinaryTreeNode : "[]") + "]";
    }
}