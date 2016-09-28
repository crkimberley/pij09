import java.util.*;

/**
 * @author crkimberley on 28/09/2016.
 */
public class ShuntingYardParser {

    private Collection<Operator> operators;
    private Map<Character, Operator> operatorMap;

    private static void addNode(Stack<BinaryTreeNode> stack, char operator) {
        BinaryTreeNode rightBinaryTreeNode = stack.pop();
        BinaryTreeNode leftBinaryTreeNode = stack.pop();
        stack.push(new BinaryTreeNode(operator, leftBinaryTreeNode, rightBinaryTreeNode));
    }

    public ShuntingYardParser() {
        operators = new ArrayList<Operator>();
        operators.add(new Operator('^', true, 4));
        operators.add(new Operator('*', false, 3));
        operators.add(new Operator('/', false, 3));
        operators.add(new Operator('+', false, 2));
        operators.add(new Operator('-', false, 2));
        operatorMap = new HashMap<Character, Operator>();
        for (Operator o : operators) {
            operatorMap.put(o.getSymbol(), o);
        }
    }

    public BinaryTreeNode convertInfixNotationToAST(String input) {
        Stack<Character> operatorStack = new Stack<Character>();
        Stack<BinaryTreeNode> operandStack = new Stack<BinaryTreeNode>();
        char[] chars = input.toCharArray();
        main:
        for (char c : chars) {
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
                        final Operator o1 = operatorMap.get(c);
                        Operator o2;
                        while (!operatorStack.isEmpty() && null != (o2 =
                                operatorMap.get(operatorStack.peek()))) {
                            if ((!o1.isRightAssociative() &&
                                    0 == o1.comparePrecedence(o2)) ||
                                    o1.comparePrecedence(o2) < 0) {
                                operatorStack.pop();
                                addNode(operandStack, o2.getSymbol());
                            } else {
                                break;
                            }
                        }
                        operatorStack.push(c);
                    } else {
                        operandStack.push(new BinaryTreeNode(c, null, null));
                    }
                    break;
            }
        }
        while (!operatorStack.isEmpty()) {
            addNode(operandStack, operatorStack.pop());
        }
        return operandStack.pop();
    }
}