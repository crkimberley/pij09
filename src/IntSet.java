/**
 * @author crkimberley on 23/09/2016.
 */
public interface IntSet {

    /**
     * adds a new int to the set; if it is there already, nothing happens.
     */

    void add(int newNumber);

    /**
     * returns true if the number is in the set, false otherwise
     */

    boolean contains(int numberToCheck);

    /**
     * returns the same values as contains(int), but for every element that is checked
     * prints its value on the screen
     */

    boolean containsVerbose(int numberToCheck);

    /**
     * returns a string with the values of the elements in the set separated by commas
     */

    String toString();
}