package com.obdobion.algebrain;

import java.text.ParseException;

/**
 * @author Chris DeGreef
 *
 */
public class FuncRound extends Function
{

    public FuncRound()
    {
        super();
    }

    public FuncRound(final TokVariable var)
    {
        super(var);
    }

    /**
     * The round function uses two operands. The second one is the number of
     * decimal places in which to round to.
     *
     * @see com.obdobion.algebrain.EquPart#resolve(java.util.Stack)
     */
    @Override
    public void resolve (final ValueStack values) throws Exception
    {
        if (values.size() < 2)
            throw new Exception("missing operands for " + toString());
        try
        {
            final double multiplier = Math.pow(10, values.popDouble());
            values.push(new Double(Math.round(values.popDouble() * multiplier) / multiplier));
        } catch (final ParseException e)
        {
            e.fillInStackTrace();
            throw new Exception(toString() + "; " + e.getMessage(), e);
        }
    }

    @Override
    public String toString ()
    {
        return "function(round)";
    }
}
