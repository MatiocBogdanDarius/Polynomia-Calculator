package Model.Monomial;

import java.text.DecimalFormat;

/**
 * This class contains the representation of a mathematical monomial that has real coefficients
 */
public class DoubleMonomial extends Monomial {

    public DoubleMonomial(double coefficient, int power) {
        super(power, coefficient);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("####0.00");
        if (getPower() == 0)
            if(getCoefficient() != 0)
                return String.valueOf(getCoefficient());
            else
                return "0.0";
        else if (getPower() == 1)
            if (Math.abs(getCoefficient()) != 1)
                return df.format(getCoefficient()) + "x";
            else if(getCoefficient() == 1)
                return "x";
            else
                return "-x";
        else {
            if (Math.abs(getCoefficient()) != 1)
                return df.format(getCoefficient()) + "x^" + getPower();
            else  if(getCoefficient() == 1)
                return "x^" + getPower();
            else
                return "-x^" + getPower();
        }
    }
}
