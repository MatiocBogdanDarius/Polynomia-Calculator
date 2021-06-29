package Model;

import Model.Monomial.DoubleMonomial;
import Model.Monomial.IntegerMonomial;
import Model.Monomial.Monomial;
import Model.Polynomial.Polynomial;
import Exceptions.DivideByZeroPolynomialException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class contains the calculation logic of polynomials
 */
public class OperationModel {

    /**
     * @param polynomial1
     * @param polynomial2
     * @return the sum of the two polynomials received as a parameter
     */
    public Polynomial addsPolynomials(Polynomial polynomial1, Polynomial polynomial2) {
        List<Monomial> sumPolynomialTerms = new ArrayList<>();
        sumPolynomialTerms.addAll(convertToListOfDoubleMonomial(polynomial1.getTerms()));
        sumPolynomialTerms.addAll(convertToListOfDoubleMonomial(polynomial2.getTerms()));
        return groupMonomialsWithSamePower(sumPolynomialTerms);
    }

    /**
     * @param polynomial1
     * @param polynomial2
     * @return the difference of the two polynomials received as a parameter
     */
    public Polynomial decreasesPolynomials(Polynomial polynomial1, Polynomial polynomial2) {
        List<Monomial> differencePolynomialTerms = new ArrayList<>(convertToListOfDoubleMonomial(polynomial1.getTerms()));
        for (Monomial term : polynomial2.getTerms())
            differencePolynomialTerms.add(new DoubleMonomial((-1.0) * term.getCoefficient(), term.getPower()));
        return groupMonomialsWithSamePower(differencePolynomialTerms);
    }

    /**
     * @param polynomial1
     * @param polynomial2
     * @return the product of the two polynomials received as a parameter
     */
    public Polynomial multipliesPolynomials(Polynomial polynomial1, Polynomial polynomial2) {
        List<Monomial> resultPolynomialTerms = new ArrayList<>();
        for (Monomial term1 : polynomial1.getTerms()) {
            List<Monomial> intermediaryPolynomialTerms = new ArrayList<>();
            intermediaryPolynomialTerms.add(new DoubleMonomial(0, 0));
            for (Monomial term2 : polynomial2.getTerms()) {
                Monomial result = multipliesMonomials(term1, term2);
                    intermediaryPolynomialTerms.add(result);
            }
            resultPolynomialTerms = addsPolynomials(new Polynomial(resultPolynomialTerms), new Polynomial(intermediaryPolynomialTerms)).getTerms();
        }
        return new Polynomial(resultPolynomialTerms);
    }

    /**
     * @param polynomial1
     * @param polynomial2
     * @return the division of the two polynomials received as a parameter
     */
    public Polynomial[] dividesPolynomials(Polynomial polynomial1, Polynomial polynomial2) {
        polynomial2 = groupMonomialsWithSamePower(polynomial2.getTerms());
        List<Monomial> quotientPolynomialsTerms = new ArrayList<>();
        //mute significant terms on the first position
        Collections.sort(polynomial1.getTerms());
        Collections.sort(polynomial2.getTerms());

        if (polynomial2.getDegree() == 0)
            if (polynomial2.getTerms().get(0).getCoefficient() == 0)
                throw new DivideByZeroPolynomialException("Divide by zero polynomial"); //Zero polynomial is the one having coeff and degree both zero.
        if (polynomial1.getDegree() < polynomial2.getDegree() || (polynomial1.getTerms().get(0).getCoefficient() == 0 && polynomial1.getDegree() == 0)) {
            quotientPolynomialsTerms.add(new DoubleMonomial(0, 0));
            return new Polynomial[]{new Polynomial(quotientPolynomialsTerms), polynomial1};
        }

        DoubleMonomial quotientMonomialsDivide = dividesMonomial(polynomial1.getTerms().get(0), polynomial2.getTerms().get(0));
        quotientPolynomialsTerms.add(quotientMonomialsDivide);
        Polynomial quotient = new Polynomial(quotientPolynomialsTerms);
        Polynomial remainder = decreasesPolynomials(polynomial1, multipliesPolynomials(polynomial2, quotient));
        Polynomial[] result = dividesPolynomials(remainder, polynomial2);
        quotient = addsPolynomials(quotient, result[0]);
        remainder = result[1];
        return new Polynomial[]{quotient, remainder};
    }

    /**
     * @param polynomial
     * @return derived from the polynomial received as a parameter
     */
    public Polynomial derivesPolynomial(Polynomial polynomial) {
        List<Monomial> resultPolynomialTerms = new ArrayList<>();
        for (Monomial term : polynomial.getTerms()) {
            resultPolynomialTerms.add(derivesMonomial(term));
        }
        Polynomial result= groupMonomialsWithSamePower(resultPolynomialTerms);
        return new Polynomial(convertToListOfIntegerMonomial(result.getTerms()));
    }

    /**
     * @param polynomial
     * @return the integral of the polynomial received as a parameter
     */
    public Polynomial integratesPolynomial(Polynomial polynomial) {
        List<Monomial> resultPolynomialTerms = new ArrayList<>();
        for (Monomial term : polynomial.getTerms()) {
            resultPolynomialTerms.add(integratesMonomial(term));
        }
        return groupMonomialsWithSamePower(resultPolynomialTerms);
    }


    //monomial operations

    /**
     * @param monomial1
     * @param monomial2
     * @return the sum of the two polymonomials received as a parameter
     */
    private DoubleMonomial addsMonomials(Monomial monomial1, Monomial monomial2) {
        if (monomial1.getPower() == monomial2.getPower())
            return new DoubleMonomial(monomial1.getCoefficient() + monomial2.getCoefficient(), monomial1.getPower());
        return null;
    }

    /**
     * @param monomial1
     * @param monomial2
     * @return the product of the two monomials received as a parameter
     */
    private DoubleMonomial multipliesMonomials(Monomial monomial1, Monomial monomial2) {
        if (monomial1.getCoefficient() == 0 || monomial2.getCoefficient() == 0)
            return new DoubleMonomial(0, 0);
        return new DoubleMonomial(monomial1.getCoefficient() * monomial2.getCoefficient(), monomial1.getPower() + monomial2.getPower());
    }

    /**
     * @param monomial1
     * @param monomial2
     * @return the division of the two monomials received as a parameter
     */
    private DoubleMonomial dividesMonomial(Monomial monomial1, Monomial monomial2) {
        return new DoubleMonomial(monomial1.getCoefficient() / monomial2.getCoefficient(), monomial1.getPower() - monomial2.getPower());
    }

    /**
     * @param monomial
     * @return derived from the monomial received as a parameter
     */
    private IntegerMonomial derivesMonomial(Monomial monomial) {
        if (monomial.getPower() == 0)
            return new IntegerMonomial(0, 0);
        return new IntegerMonomial(monomial.getCoefficient() * monomial.getPower(), monomial.getPower() - 1);
    }

    /**
     * @param monomial
     * @return the integral of the monomial received as a parameter
     */
    private DoubleMonomial integratesMonomial(Monomial monomial) {
        if (monomial.getPower() == 0)
            if (monomial.getCoefficient() == 0)
                return new DoubleMonomial(0.0, 0);
        return new DoubleMonomial(monomial.getCoefficient() / (monomial.getPower() + 1), monomial.getPower() + 1);
    }


    //group monomials with same power

    /**
     * @param terms
     * @return one polinom care care are ca termeni termenii (din lista de monoame primita ca parametri) grupati in functie de putere; exceptie termenii rendundanti care sunt eliminati
     */
    private Polynomial groupMonomialsWithSamePower(List<Monomial> terms) {
        for (int i = 0; i < terms.size() - 1; i++)
            for (int j = i + 1; j < terms.size(); j++) {
                Monomial m1 = terms.get(i), m2 = terms.get(j);
                Monomial sum = addsMonomials(m1, m2);
                if (sum != null) {
                    terms.add(sum);
                    terms.remove(j);
                    terms.remove(i);
                    i = 0;
                    j = 0;
                }
            }
        return new Polynomial(eliminatesRedundantTerms(terms));
    }


    //eliminates redundant terms

    /**
     * @param terms
     * @return a list of monomials that has as terms the terms (from the list of monomials received as parameters), except for the redundant terms that are removed
     */
    private List<Monomial> eliminatesRedundantTerms(List<Monomial> terms) {
        terms.removeIf(term -> term.getCoefficient() == 0 && term.getPower() != 0);
        if(terms.isEmpty())
            terms.add(new DoubleMonomial(0, 0));
        return terms;
    }


    //conversions terms of polynomials

    /**
     * @param monomialList
     * @return a list of monomials that contains only monomials with real coefficients
     */
    private List<Monomial> convertToListOfDoubleMonomial(List<Monomial> monomialList) {
        List<Monomial> doubleMonomialList = new ArrayList<>();
        for (Monomial m : monomialList)
            doubleMonomialList.add(new DoubleMonomial(m.getCoefficient(), m.getPower()));
        return doubleMonomialList;
    }

    /**
     * @param monomialList
     * @return a list of monomials that contains only monomials with integer coefficients
     */
    public List<Monomial> convertToListOfIntegerMonomial(List<Monomial> monomialList) {
        List<Monomial> integerMonomialList = new ArrayList<>();
        for (Monomial m : monomialList)
            integerMonomialList.add(new IntegerMonomial(m.getCoefficient(), m.getPower()));
        return integerMonomialList;
    }
}