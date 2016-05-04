package com.obdobion.algebrain;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chris DeGreef
 * 
 */
public class TestFactorial
{
    @Test
    public void fac1 () throws Exception
    {
        final Double result = (Double) Equ.getInstance().evaluate("3!");
        Assert.assertEquals("factorial result ", 6, result.doubleValue(), 0D);
    }

    @Test
    public void precedence () throws Exception
    {
        final Double result = (Double) Equ.getInstance().evaluate("3*2!");
        Assert.assertEquals("factorial result ", 6, result.doubleValue(), 0D);
    }

    @Test
    public void preliminaryResult () throws Exception
    {
        final Double result = (Double) Equ.getInstance().evaluate("(3*2)!");
        Assert.assertEquals("factorial result ", 720, result.doubleValue(), 0D);
    }

    @Test
    public void zero () throws Exception
    {
        final Double result = (Double) Equ.getInstance().evaluate("0!");
        Assert.assertEquals("factorial result ", 1, result.doubleValue(), 0D);
    }

    @Test
    public void fraction () throws Exception
    {
        final Double result = (Double) Equ.getInstance().evaluate("5.4!");
        Assert.assertEquals("factorial result ", 120, result.doubleValue(), 0D);
    }

    @Test
    public void veryLarge () throws Exception
    {
        final Double result = (Double) Equ.getInstance().evaluate("20!");
        Assert.assertEquals("factorial result ", 2.43290200817664e18, result.doubleValue(), 0D);
    }

    @Test
    public void binomialCoefficient () throws Exception
    {
        Equ.getInstance().getSupport().assignVariable("n", new Double(5));
        Equ.getInstance().getSupport().assignVariable("k", new Double(6));

        final Double result = (Double) Equ.getInstance().evaluate("(n^k)/(k!)");

        Assert.assertEquals("binomialCoefficient result ", 21.7, result.doubleValue(), 1D);
    }

    @Test
    public void tooLarge () throws Exception
    {
        try
        {
            Equ.getInstance().evaluate("21!");
            Assert.fail("exception expected but not thrown");
        } catch (Exception e)
        {
            Assert.assertEquals("tooLarge ", "numeric overflow for op(factorial)", e.getMessage());
        }
    }

    @Test
    public void negative () throws Exception
    {
        try
        {
            Equ.getInstance().evaluate("-5!");
            Assert.fail("exception expected but not thrown");
        } catch (Exception e)
        {
            Assert.assertEquals("negative ", "negative numbers not allowed for op(factorial)", e.getMessage());
        }
    }
}