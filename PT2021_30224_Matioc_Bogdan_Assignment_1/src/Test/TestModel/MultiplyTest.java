package TestModel;

import Exceptions.InvalidDataInputException;
import Model.Monomial.Monomial;
import Model.OperationModel;
import Model.ParsingDataModel;
import Model.Polynomial.Polynomial;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class MultiplyTest {
    OperationModel operationModel = new OperationModel();
    static ParsingDataModel pm = new ParsingDataModel();
    static Polynomial p1;
    static Polynomial p2;
    static Polynomial p3;
    static Polynomial p4;
    static Polynomial p5;
    static Polynomial p6;
    static Polynomial p7;
    static Polynomial p8;
    static Polynomial p9;

    @BeforeClass
    public static void setUp(){
        try {
            p1 = pm.parseStingToPolynomial("1");
            p2 = pm.parseStingToPolynomial("2x");
            p3 = pm.parseStingToPolynomial("x^3");
            p4 = pm.parseStingToPolynomial("3x^4");
            p5 = pm.parseStingToPolynomial("1+x");
            p6 = pm.parseStingToPolynomial("x^1");
            p7 = pm.parseStingToPolynomial("-5x+x^3");
            p8 = pm.parseStingToPolynomial("-x");
            p9 = pm.parseStingToPolynomial("0");
        } catch (InvalidDataInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void multipliesPolynomialsTest() {
        Assert.assertEquals( "1", multiply(p1, p1));
        Assert.assertEquals( "4x^2", multiply(p2, p2));
        Assert.assertEquals( "2x^4", multiply(p2, p3));
        Assert.assertEquals( "6x^5", multiply(p2, p4));
        Assert.assertEquals( "2x^2+2x", multiply(p2, p5));
        Assert.assertEquals( "2x^2", multiply(p2, p6));
        Assert.assertEquals( "2x^4-10x^2", multiply(p2, p7));
        Assert.assertEquals( "-2x^2", multiply(p2, p8));
        Assert.assertEquals( "x^6", multiply(p3, p3));
        Assert.assertEquals( "3x^7", multiply(p3, p4));
        Assert.assertEquals( "x^4+x^3", multiply(p3, p5));
        Assert.assertEquals( "x^4", multiply(p3, p6));
        Assert.assertEquals( "x^6-5x^4", multiply(p3, p7));
        Assert.assertEquals( "-x^4", multiply(p3, p8));
        Assert.assertEquals( "x^2+2x+1", multiply(p5, p5));
        Assert.assertEquals( "x^2+x", multiply(p5, p6));
        Assert.assertEquals( "x^4+x^3-5x^2-5x", multiply(p5, p7));
        Assert.assertEquals( "-x^2-x", multiply(p5, p8));
        Assert.assertEquals( "x^2", multiply(p6, p6));
        Assert.assertEquals( "x^4-5x^2", multiply(p6, p7));
        Assert.assertEquals( "-x^2", multiply(p6, p8));
        Assert.assertEquals( "x^6-10x^4+25x^2", multiply(p7, p7));
        Assert.assertEquals( "-x^4+5x^2", multiply(p7, p8));
        Assert.assertEquals( "x^2", multiply(p8, p8));
        Assert.assertEquals( "0", multiply(p9, p9));
    }

    //auxiliary test method
    private String multiply(Polynomial p1, Polynomial p2){
        List<Monomial> retTerms = operationModel.convertToListOfIntegerMonomial(operationModel.multipliesPolynomials(p1, p2).getTerms());
        return (new Polynomial(retTerms).toString()); }
}
