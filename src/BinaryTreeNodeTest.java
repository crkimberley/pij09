/**
 * @author crkimberley on 28/09/2016.
 */
public class BinaryTreeNodeTest {

    public static void main(String[] args) {
        new BinaryTreeNodeTest().launch();
    }

    private void launch() {
        String[] expressions = {"(3 + 3) * (8 - 11 * 2)", "123 + 456", "2/3*4"};
        for (String expression : expressions) {
            BinaryTreeNode tree = BinaryTreeNode.mathExpressionToTree(expression);
            System.out.println("Mathematical expression: " + expression + "\nAbstract Syntax Tree: " + tree);
        }
    }
}
