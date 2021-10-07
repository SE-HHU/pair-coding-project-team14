package Test;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void infixToSuffix() {
        String x;
        x = Calculator.infixToSuffix("1+1");
        Assert.assertEquals("1 1 +",x);
        String y = Calculator.infixToSuffix("(1+1)*2");
        Assert.assertEquals("1 1 + 2 *",y);
        String z = Calculator.infixToSuffix("1*1");
        Assert.assertEquals("1 1 *",z);
    }

    @Test
    public void suffixToArithmetic() {
     String x = Calculator.suffixToArithmetic("1 1 +",10);
     Assert.assertEquals("2",x);
     String y = Calculator.suffixToArithmetic("1'2/4 1 +",10);
     Assert.assertEquals("无解",y);
     String c = Calculator.suffixToArithmetic("1'2/4 1 -",10);
     Assert.assertEquals("1/2",c);
     String z = Calculator.suffixToArithmetic("1 4 ÷",10);
     Assert.assertEquals("1/4",z);
     String a = Calculator.suffixToArithmetic("1 2 -",10);
     Assert.assertEquals("无解",a);
    }

    @Test
    public void calculate() {
        String x = Calculator.calculate("1","1","+");
        Assert.assertEquals("2",x);
        String y = Calculator.calculate("1","1/2","-");
        Assert.assertEquals("1/2",y);
        String z = Calculator.calculate("1/2","1","-");
        Assert.assertEquals("-1/2",z);
        String n = Calculator.calculate("1/2","1/2","+");
        Assert.assertEquals("1",n);
        String p = Calculator.calculate("1'1/2","1'1/3","-");
        Assert.assertEquals("1/6",p);
        String k = Calculator.calculate("0","0","÷");
        Assert.assertEquals("无解",k);
    }
}