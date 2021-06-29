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

public class DecreaseTest {
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
    public void decreasesPolynomialsTest() {
        Assert.assertEquals( "0", dif(p1, p1));
        Assert.assertEquals( "-2x+1", dif(p1, p2));
        Assert.assertEquals( "-x^3+1", dif(p1, p3));
        Assert.assertEquals( "-3x^4+1", dif(p1, p4));
        Assert.assertEquals( "-x", dif(p1, p5));
        Assert.assertEquals( "-x+1", dif(p1, p6));
        Assert.assertEquals( "-x^3+5x+1", dif(p1, p7));
        Assert.assertEquals( "x+1", dif(p1, p8));
        Assert.assertEquals( "-3x^4+x^3", dif(p3, p4));
        Assert.assertEquals( "x^3-x-1", dif(p3, p5));
        Assert.assertEquals( "x^3-x", dif(p3, p6));
        Assert.assertEquals( "5x", dif(p3, p7));
        Assert.assertEquals( "x^3+x", dif(p3, p8));
        Assert.assertEquals( "3x^4-x-1", dif(p4, p5));
        Assert.assertEquals( "3x^4-x", dif(p4, p6));
        Assert.assertEquals( "3x^4-x^3+5x", dif(p4, p7));
        Assert.assertEquals( "3x^4+x", dif(p4, p8));
        Assert.assertEquals( "1", dif(p5, p6));
        Assert.assertEquals( "-x^3+6x+1", dif(p5, p7));
        Assert.assertEquals( "2x+1", dif(p5, p8));
        Assert.assertEquals( "-x^3+6x", dif(p6, p7));
        Assert.assertEquals( "2x", dif(p6, p8));
        Assert.assertEquals( "x", dif(p6, p9));
        Assert.assertEquals( "x^3-4x", dif(p7, p8));
        Assert.assertEquals( "-x", dif(p8, p9));
    }

    //auxiliary test method
    private String dif(Polynomial p1, Polynomial p2){
        List<Monomial> retTerms = operationModel.convertToListOfIntegerMonomial(operationModel.decreasesPolynomials(p1, p2).getTerms());
        return (new Polynomial(retTerms).toString()); }


}
