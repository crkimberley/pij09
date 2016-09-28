/**
 * @author crkimberley on 28/09/2016.
 */
public class ShuntingYardParserTest {

    public static void main(String[] args) {
        new ShuntingYardParserTest().launch();
    }

    private void launch() {
        ShuntingYardParser parser = new ShuntingYardParser();
        String[] expressions = {"(3 + 3) * (8 - 11 * 2)", "123 + 456", "2/3*4"};
        for (String expression : expressions) {
            BinaryTreeNode tree = parser.expressionToTree(expression);
            System.out.println("Mathematical expression: " + expression + "\nAbstract Syntax Tree: " + tree);
        }
    }
}
