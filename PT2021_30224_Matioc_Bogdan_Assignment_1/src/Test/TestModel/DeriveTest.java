package TestModel;

import Exceptions.InvalidDataInputException;
import Model.OperationModel;
import Model.ParsingDataModel;
import Model.Polynomial.Polynomial;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DeriveTest {
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
    public void derivesPolynomialTest() {
        Assert.assertEquals( "0", operationModel.derivesPolynomial(p1).toString());
        Assert.assertEquals( "2", operationModel.derivesPolynomial(p2).toString());
        Assert.assertEquals( "3x^2", operationModel.derivesPolynomial(p3).toString());
        Assert.assertEquals( "12x^3", operationModel.derivesPolynomial(p4).toString());
        Assert.assertEquals( "1", operationModel.derivesPolynomial(p5).toString());
        Assert.assertEquals( "1", operationModel.derivesPolynomial(p6).toString());
        Assert.assertEquals( "3x^2-5", operationModel.derivesPolynomial(p7).toString());
        Assert.assertEquals( "-1", operationModel.derivesPolynomial(p8).toString());
        Assert.assertEquals( "0", operationModel.derivesPolynomial(p9).toString());
    }

}
