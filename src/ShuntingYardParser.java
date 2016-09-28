import java.util.*;

import static java.lang.Character.isDigit;

/**
 * @author crkimberley on 28/09/2016.
 */
public class ShuntingYardParser {

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
}