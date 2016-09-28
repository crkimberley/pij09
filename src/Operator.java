/**
 * @author crkimberley on 28/09/2016.
 */
public class Operator {

    private char symbol;
    private boolean rightAssociative;
    private int precedence;

    public Operator(char symbol, boolean rightAssociative, int precedence) {
        this.symbol = symbol;
        this.rightAssociative = rightAssociative;
        this.precedence = precedence;
    }

    public int comparePrecedence(Operator o) {
        return precedence > o.precedence ? 1 : o.precedence == precedence ? 0 : -1;
    }

    public boolean isRightAssociative() {
        return rightAssociative;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return Character.toString(symbol);
    }
}