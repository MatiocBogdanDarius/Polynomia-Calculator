package Model.Polynomial;

import Model.Monomial.Monomial;

import java.util.Collections;
import java.util.List;

/**
 * This class represents the structure of a mathematical polynomial
 */
public class Polynomial {
    private List<Monomial> terms;
    private int degree;

    public Polynomial(List<Monomial> terms) {
        this.terms = terms;
        setDegree();
    }

    public List<Monomial> getTerms() {
        return terms;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree() {
        if(terms.isEmpty())
            degree = 0;
        else {
            Collections.sort(terms);
            degree = terms.get(0).getPower();
        }
        //dupa sortare termenul cu cea mai mare putere se va afla pe pozitia 0

    }

    @Override
    public String toString() {
        StringBuilder rez = new StringBuilder();
        if(degree == 0 && terms.size() > 0)
            return terms.get(0) + "";
        Collections.sort(terms);
        for (Monomial term : terms) {
            if (term.getCoefficient() > 0)
                    if (term != terms.get(0))
                        //daca nu este primul termen si coeficientul este pozitiv inainte de a pune termanul in stringul rez voi pune  '+'
                        rez.append('+');
            if (term.getCoefficient() != 0)
                rez.append(term.toString());
        }

        return rez.toString();
    }
}
