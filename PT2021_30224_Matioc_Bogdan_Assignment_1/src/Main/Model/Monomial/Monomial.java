package Model.Monomial;

/**
 * This class represents the structure of a mathematical monomial
 */
public abstract class Monomial implements Comparable<Monomial>{
    private int power;
    private double coefficient;

    public Monomial(int power, double coefficient) {
        this.power = power;
        this.coefficient = coefficient;
    }

    public int getPower() {
        return power;
    }

    public double getCoefficient() {
        return coefficient;
    }

    @Override
    public int compareTo(Monomial o) {
        //implemented to sort descending by power
        return Integer.compare(power, o.power) * (-1);
    }

}
