import java.util.*;
import static java.lang.Character.isDigit;

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

    public String getValue() {
        return value;
    }

    public String toString() {
        return "[" + value + " L" + (left != null ? left : "[]")
                + " R" + (right != null ? right : "[]") + "]";
    }

    public static BinaryTreeNode mathExpressionToTree(String expression) {
        return new ShuntingYardParser().expressionToTree(expression);
    }

    /**
     * Dijkstra's Shunting-Yard algorithm is using for converting the mathematical expression
     * in a given String to a BinaryNodeTree
     *
     * Basis for implementation is taken from
     * https://www.klittlepage.com/2013/12/22/twelve-days-2013-shunting-yard-algorithm/
     */
    private static class ShuntingYardParser {

        private Collection<Operator> operators;
        private Map<Character, Operator> operatorMap;

        private static void addNode(Stack<BinaryTreeNode> stack, char operator) {
            BinaryTreeNode right = stack.pop();
            BinaryTreeNode left = stack.pop();
            stack.push(new BinaryTreeNode(operator, left, right));
        }

        public ShuntingYardParser() {
            // Define mathematical operators
            operators = new ArrayList<Operator>();
            operators.add(new Operator('*', 2));
            operators.add(new Operator('/', 2));
            operators.add(new Operator('+', 1));
            operators.add(new Operator('-', 1));
            operatorMap = new HashMap<Character, Operator>();
            for (Operator operator : operators) {
                operatorMap.put(operator.getSymbol(), operator);
            }
        }

        public BinaryTreeNode expressionToTree(String input) {
            Stack<Character> operatorStack = new Stack<Character>();
            Stack<BinaryTreeNode> operandStack = new Stack<BinaryTreeNode>();
            char[] chars = input.toCharArray();
            main:
            for (int i=0; i<chars.length; i++) {
                char c = chars[i];
                char popped;
                switch (c) {
                    case ' ':
                        break;
                    case '(':
                        operatorStack.push('(');
                        break;
                    case ')':
                        while (!operatorStack.isEmpty()) {
                            popped = operatorStack.pop();
                            if ('(' == popped) {
                                continue main;
                            } else {
                                addNode(operandStack, popped);
                            }
                        }
                    default:
                        if (operatorMap.containsKey(c)) {
                            Operator operator1 = operatorMap.get(c);
                            Operator operator2;
                            while (!operatorStack.isEmpty() && null != (operator2 =
                                    operatorMap.get(operatorStack.peek()))) {
                                if (operator1.comparePrecedence(operator2) <= 0) {
                                    operatorStack.pop();
                                    addNode(operandStack, operator2.getSymbol());
                                } else {
                                    break;
                                }
                            }
                            operatorStack.push(c);
                        } else {
                            StringBuilder numberBuilder = new StringBuilder(Character.toString(c));
                            while ((i+1) < chars.length && isDigit(chars[i+1])) {
                                numberBuilder.append(chars[i+1]);
                                i++;
                            }
                            operandStack.push(new BinaryTreeNode(numberBuilder.toString(), null, null));
                        }
                        break;
                }
            }
            while (!operatorStack.isEmpty()) {
                addNode(operandStack, operatorStack.pop());
            }
            return operandStack.pop();
        }

        private static class Operator {

            private char symbol;
            private int precedence;

            public Operator(char symbol, int precedence) {
                this.symbol = symbol;
                this.precedence = precedence;
            }

            public int comparePrecedence(Operator operator) {
                return precedence > operator.precedence ? 1 : operator.precedence == precedence ? 0 : -1;
            }

            public char getSymbol() {
                return symbol;
            }

            @Override
            public String toString() {
                return Character.toString(symbol);
            }
        }
    }

    public List<BinaryTreeNode> inOrderTreeToList() {
        List<BinaryTreeNode> list = new LinkedList<BinaryTreeNode>();
        inOrderTraversal(this, list);
        return list;
    }

    public void inOrderTraversal(BinaryTreeNode node, List<BinaryTreeNode> list) {
        if (node != null) {
            inOrderTraversal(node.left, list);
            list.add(node);
            inOrderTraversal(node.right, list);
        }
    }
}