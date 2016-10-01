import java.util.List;

/**
 * @author crkimberley on 28/09/2016.
 */
public class BinaryTreeNodeTest {

    public static void main(String[] args) {
        new BinaryTreeNodeTest().launch();
    }

    private void launch() {
        String[] expressions = {"(3 + 3) * (8 - 11 * 2)", "123 + 456", "2/3*4", "235+31*78+24/97"};
        for (String expression : expressions) {
            BinaryTreeNode tree = BinaryTreeNode.mathExpressionToTree(expression);
            System.out.println("Mathematical expression: " + expression + "\nAbstract Syntax Tree: " + tree);
            List<BinaryTreeNode> list = tree.inOrderTreeToList();
            System.out.print("list: ");
            for (BinaryTreeNode node : list) {
                System.out.print(node.getValue() + " ");
            }
            System.out.println("\n");
        }
    }
}
