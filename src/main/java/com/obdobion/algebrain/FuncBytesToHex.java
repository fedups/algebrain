package com.obdobion.algebrain;

import java.text.ParseException;

import org.apache.commons.codec.binary.Hex;

/**
 * @author Chris DeGreef
 *
 */
public class FuncBytesToHex extends Function
{
    public FuncBytesToHex()
    {
        super();
    }

    public FuncBytesToHex(final TokVariable var)
    {
        super(var);
    }

    @Override
    public void resolve (final ValueStack values) throws Exception
    {
        if (values.size() < getParameterCount())
            throw new Exception("missing operands for " + toString());
        try
        {
            values.push(Hex.encodeHexString(values.popByteArray()));

        } catch (final ParseException e)
        {
            e.fillInStackTrace();
            throw new Exception(toString() + "; " + e.getMessage(), e);
        }
    }

    @Override
    public String toString ()
    {
        return "function(toHex";
    }
}
