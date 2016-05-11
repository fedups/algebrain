package com.obdobion.algebrain;


/**
 * @author Chris DeGreef
 *
 */
public class OpEquals extends Operator
{
    public OpEquals()
    {
        super();
    }

    public OpEquals(final EquPart opTok)
    {
        super(opTok);
    }

    @Override
    protected int precedence ()
    {
        return 999;
    }

    @Override
    public void resolve (final ValueStack values) throws Exception
    {
        if (values.size() != 1)
            throw new Exception("Wrong number of operands " + toString());
    }

    @Override
    public String toString ()
    {
        return "op(assignment)";
    }
}
