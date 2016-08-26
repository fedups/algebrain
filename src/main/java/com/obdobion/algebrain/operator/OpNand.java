package com.obdobion.algebrain.operator;

import java.text.ParseException;

import com.obdobion.algebrain.EquPart;
import com.obdobion.algebrain.Operator;
import com.obdobion.algebrain.ValueStack;

/**
 * <p>
 * OpNand class.
 * </p>
 *
 * @author Chris DeGreef fedupforone@gmail.com
 * @since 1.3.9
 */
public class OpNand extends Operator
{
    /**
     * <p>
     * Constructor for OpNand.
     * </p>
     */
    public OpNand()
    {
    }

    /**
     * <p>
     * Constructor for OpNand.
     * </p>
     *
     * @param opTok
     *            a {@link com.obdobion.algebrain.EquPart} object.
     */
    public OpNand(final EquPart opTok)
    {
        super(opTok);
    }

    /** {@inheritDoc} */
    @Override
    protected int precedence()
    {
        return 9;
    }

    /** {@inheritDoc} */
    @Override
    public void resolve(final ValueStack values) throws Exception
    {
        if (values.size() < 2)
            throw new Exception("missing operands for " + toString());
        try
        {
            final boolean b0 = values.popBoolean();
            final boolean b1 = values.popBoolean();
            values.push(new Boolean(!(b1 && b0)));
        } catch (final ParseException e)
        {
            e.fillInStackTrace();
            throw new Exception(toString() + "; " + e.getMessage(), e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "op(nand)";
    }
}
