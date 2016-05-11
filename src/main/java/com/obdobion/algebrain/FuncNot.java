package com.obdobion.algebrain;

import java.text.ParseException;

/**
 * @author Chris DeGreef
 *
 */
public class FuncNot extends Function
{
    public FuncNot()
    {
        super();
    }

    public FuncNot(final TokVariable var)
    {
        super(var);
    }

    @Override
    public void resolve (final ValueStack values) throws Exception
    {
        if (values.size() < 1)
            throw new Exception("missing operand for " + toString());
        try
        {
            values.push(new Boolean(!values.popBoolean()));
        } catch (final ParseException e)
        {
            e.fillInStackTrace();
            throw new Exception(toString() + "; " + e.getMessage(), e);
        }
    }

    @Override
    public String toString ()
    {
        return "function(not)";
    }
}
