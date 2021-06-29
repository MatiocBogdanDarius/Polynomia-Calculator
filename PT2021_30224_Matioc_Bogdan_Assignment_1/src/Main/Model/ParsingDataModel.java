package Model;

import Exceptions.InvalidDataInputException;
import Model.Monomial.*;
import Model.Polynomial.Polynomial;
import java.util.*;
import java.util.regex.*;

/**
 * This class contains the parsing of the input data
 */
public class ParsingDataModel {
    /**
     * This method parses a string into a monomial
     * @param inputString the string containing the polynomial
     * @return one polynomial
     */
    public Polynomial parseStingToPolynomial(String inputString) throws InvalidDataInputException {
        inputString = inputString.replaceAll("\\s+","");
        List<Monomial> polynomialTerms = new ArrayList<>();
        String polynomialString = "";
        Pattern pattern = Pattern.compile("([+-]?(?:(?:x\\^\\d+)|(?:\\d+x\\^\\d+)|(?:\\d+x)|(?:\\d+)|(?:x)))");
        Matcher matcher = pattern.matcher(inputString);
        while (matcher.find()) {
            polynomialTerms.add(parseStingToMonomial(String.valueOf(matcher.group(1))));
            polynomialString = costructpolynomialString(polynomialString, String.valueOf(matcher.group(1)));
        }
        if(!isValidInput(inputString, polynomialString))
            throw new InvalidDataInputException("!Invalid data input");

        return new Polynomial(polynomialTerms);
    }

    /**
     * This method parses a string into a monomial
     * @param inputString the string containing the monomial
     * @return one monomial
     */
    private Monomial parseStingToMonomial(String inputString) {
        int coefficient = 1;
        int power = 0;
        Pattern pattern = Pattern.compile("([+-]?\\d+)");
        Matcher matcher = pattern.matcher(inputString);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(String.valueOf(matcher.group(1)));
        }
        if (!inputString.startsWith("x") && !inputString.startsWith("+x") && !inputString.startsWith("-x")) {
            coefficient = Integer.parseInt(list.get(0));
            if(list.size() > 1)
            power = Integer.parseInt(list.get(1));
                //special case: often x ^ 1 is written as x
            else if(inputString.contains("x"))
                power = 1;
        } else {
            if(inputString.startsWith("-x"))
                coefficient = -1;
            if (inputString.contains("x^"))
                power = Integer.parseInt(list.get(0));
                //special case: often x ^ 1 is written as x
            else
                power = 1;
        }
        return new IntegerMonomial(coefficient, power);
    }

    //Auxiliary method for verification validity data

    /**
     * This method
     * @param s1
     * @param s2
     * @return true if they are not empty and from x you can get y by removing the spaces; false else
     */
    private boolean isValidInput(String s1, String s2) {
        if(s2.isEmpty())
            return false;
        return s2.equals(s1.replaceAll("\\s+",""));
    }

    /**
     * @param s1
     * @param s2
     * @return a string resulting from the concatenation of the strings s1 and s2 and possibly the string "+" between them
     */
    private String costructpolynomialString(String s1, String s2) {
        if(s1.equals("") || s2.startsWith("+") || s2.startsWith("-"))
            s1 += s2;
        else
            s1 += "+" + s2;
        return s1;
    }
}
