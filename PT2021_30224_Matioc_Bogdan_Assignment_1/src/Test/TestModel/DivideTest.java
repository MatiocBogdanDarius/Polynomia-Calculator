package TestModel;

import Exceptions.DivideByZeroPolynomialException;
import Exceptions.InvalidDataInputException;
import Model.OperationModel;
import Model.ParsingDataModel;
import Model.Polynomial.Polynomial;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DivideTest {
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

    @Test(expected = DivideByZeroPolynomialException.class)
    public void dividesPolynomialsTest() {
        Assert.assertEquals( "1.0", operationModel.dividesPolynomials(p1, p1)[0].toString());
        Assert.assertEquals( "0.0", operationModel.dividesPolynomials(p1, p1)[1].toString());
        Assert.assertEquals( "2.00x", operationModel.dividesPolynomials(p2, p1)[0].toString());
        Assert.assertEquals( "0.0", operationModel.dividesPolynomials(p2, p1)[1].toString());
        Assert.assertEquals( "1.0", operationModel.dividesPolynomials(p2, p2)[0].toString());
        Assert.assertEquals( "0.0", operationModel.dividesPolynomials(p2, p2)[1].toString());
        Assert.assertEquals( "2x", operationModel.dividesPolynomials(p2, p4)[1].toString());
        Assert.assertEquals( "2.0", operationModel.dividesPolynomials(p2, p5)[0].toString());
        Assert.assertEquals( "-2.0", operationModel.dividesPolynomials(p2, p5)[1].toString());
        Assert.assertEquals( "2.0", operationModel.dividesPolynomials(p2, p6)[0].toString());
        Assert.assertEquals( "0.0", operationModel.dividesPolynomials(p2, p6)[1].toString());
        Assert.assertEquals( "0.50x^2", operationModel.dividesPolynomials(p3, p2)[0].toString());
        Assert.assertEquals( "0.0", operationModel.dividesPolynomials(p3, p2)[1].toString());
        Assert.assertEquals( "0.0", operationModel.dividesPolynomials(p3, p4)[0].toString());
        Assert.assertEquals( "x^3", operationModel.dividesPolynomials(p3, p4)[1].toString());
        Assert.assertEquals( "x^2-x+1.0", operationModel.dividesPolynomials(p3, p5)[0].toString());
        Assert.assertEquals( "-1.0", operationModel.dividesPolynomials(p3, p5)[1].toString());
        Assert.assertEquals( "1.0", operationModel.dividesPolynomials(p3, p7)[0].toString());
        Assert.assertEquals( "5.00x", operationModel.dividesPolynomials(p3, p7)[1].toString());
        Assert.assertEquals( "1.50x^3", operationModel.dividesPolynomials(p4, p2)[0].toString());
        Assert.assertEquals( "0.0", operationModel.dividesPolynomials(p4, p2)[1].toString());
        Assert.assertEquals( "3.00x^3-3.00x^2+3.00x-3.0", operationModel.dividesPolynomials(p4, p5)[0].toString());
        Assert.assertEquals( "3.0", operationModel.dividesPolynomials(p4, p5)[1].toString());
        Assert.assertEquals( "3.00x^3", operationModel.dividesPolynomials(p4, p6)[0].toString());
        Assert.assertEquals( "0.0", operationModel.dividesPolynomials(p4, p6)[1].toString());
        Assert.assertEquals( "3.00x", operationModel.dividesPolynomials(p4, p7)[0].toString());
        Assert.assertEquals( "15.00x^2", operationModel.dividesPolynomials(p4, p7)[1].toString());
        Assert.assertEquals( "0.5", operationModel.dividesPolynomials(p5, p2)[0].toString());
        Assert.assertEquals( "1.0", operationModel.dividesPolynomials(p5, p2)[1].toString());
        Assert.assertEquals( "0.0", operationModel.dividesPolynomials(p5, p3)[0].toString());
        Assert.assertEquals( "x+1", operationModel.dividesPolynomials(p5, p3)[1].toString());
        Assert.assertEquals( "1.0", operationModel.dividesPolynomials(p5, p6)[0].toString());
        Assert.assertEquals( "1.0", operationModel.dividesPolynomials(p5, p6)[1].toString());
        Assert.assertEquals( "0.0", operationModel.dividesPolynomials(p5, p7)[0].toString());
        Assert.assertEquals( "x+1", operationModel.dividesPolynomials(p5, p7)[1].toString());
        Assert.assertEquals( "0.5", operationModel.dividesPolynomials(p6, p2)[0].toString());
        Assert.assertEquals( "0.0", operationModel.dividesPolynomials(p6, p2)[1].toString());
        Assert.assertEquals( "1.0", operationModel.dividesPolynomials(p6, p5)[0].toString());
        Assert.assertEquals( "-1.0", operationModel.dividesPolynomials(p6, p5)[1].toString());
        Assert.assertEquals( "0.0", operationModel.dividesPolynomials(p7, p4)[0].toString());
        Assert.assertEquals( "x^3-5x", operationModel.dividesPolynomials(p7, p4)[1].toString());
        Assert.assertEquals( "x^2-x-4.0", operationModel.dividesPolynomials(p7, p5)[0].toString());
        Assert.assertEquals( "4.0", operationModel.dividesPolynomials(p7, p5)[1].toString());
        Assert.assertEquals( "x^2-5.0", operationModel.dividesPolynomials(p7, p6)[0].toString());
        Assert.assertEquals( "0.0", operationModel.dividesPolynomials(p7, p6)[1].toString());
        Assert.assertEquals( "-x^2+5.0", operationModel.dividesPolynomials(p7, p8)[0].toString());
        Assert.assertEquals( "0.0", operationModel.dividesPolynomials(p7, p8)[1].toString());
        Assert.assertEquals( "0.0", operationModel.dividesPolynomials(p9, p1)[0].toString());
        Assert.assertEquals( "0", operationModel.dividesPolynomials(p9, p1)[1].toString());
        Assert.assertEquals( "Aceasta verificare nu ar trebui sa se faca", operationModel.dividesPolynomials(p1, p9)[0].toString());
        Assert.assertEquals( "Aceasta verificare nu ar trebui sa se faca", operationModel.dividesPolynomials(p1, p9)[1].toString());
    }
}
