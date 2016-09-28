/**
 * @author crkimberley on 28/09/2016.
 */
public class Operator {

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