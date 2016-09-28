import java.util.ArrayList;
import java.util.Collection;

/**
 * @author crkimberley on 28/09/2016.
 */
public class ShuntingYardParserTest {

    public static void main(String[] args) {
        ShuntingYardParser parser = new ShuntingYardParser();
        String expression = "(3+3)*(8-9*2)";
        BinaryTreeNode tree = parser.convertInfixNotationToAST(expression);
        System.out.println("Abstract Syntax Tree: " + tree);
    }
}
