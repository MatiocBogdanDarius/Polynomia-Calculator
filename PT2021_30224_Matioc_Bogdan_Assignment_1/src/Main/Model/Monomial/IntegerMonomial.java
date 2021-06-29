package Model.Monomial;

/**
 * This class contains the representation of a mathematical monomial that has integer coefficients
 */
public class IntegerMonomial extends Monomial {

    public IntegerMonomial(double coefficient, int power) {
        super(power, coefficient);
    }

    @Override
    public String toString() {
        String coefficientString = String.valueOf((int) getCoefficient());
        if (getPower() == 0)
            return coefficientString;
        else if (getPower() == 1)
            if (Math.abs(getCoefficient()) != 1)
                return coefficientString + "x";
            else if(getCoefficient() == 1)
                return "x";
            else
                return "-x";
        else {
            if (Math.abs(getCoefficient()) != 1)
                return coefficientString + "x^" + getPower();
            else  if(getCoefficient() == 1)
                return "x^" + getPower();
            else
                return "-x^" + getPower();
        }
    }

}
