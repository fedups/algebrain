package com.obdobion.algebrain;

/**
 * @author Chris DeGreef
 *
 */
public abstract class Operation extends EquPart
{
    public Operation()
    {
        super();
    }

    @Deprecated
    protected boolean[] convertToBoolean (final Object... fromStack) throws Exception
    {
        final boolean[] converted = new boolean[fromStack.length];
        for (int c = 0; c < fromStack.length; c++)
        {
            final Object data = fromStack[c];

            if (data instanceof Number)
            {
                /*
                 * 0 is the only number that is false, all others are true.
                 */
                converted[c] = ((Number) data).intValue() != 0;
                continue;
            }
            if (data instanceof String)
            {
                converted[c] = Boolean.parseBoolean((String) data);
                continue;
            }
            if (data instanceof Boolean)
            {
                converted[c] = (Boolean) data;
                continue;
            }

            final StringBuilder errMsg = new StringBuilder();
            errMsg.append("invalid type for ");
            errMsg.append(toString());
            errMsg.append("; ");
            for (int e = 0; e < fromStack.length; e++)
            {
                errMsg.append(fromStack[e].getClass().getSimpleName());
                errMsg.append(" ");
            }
            throw new Exception(errMsg.toString());
        }
        return converted;
    }

    @Deprecated
    protected double[] convertToDouble (final Object... fromStack) throws Exception
    {
        final double[] converted = new double[fromStack.length];
        for (int c = 0; c < fromStack.length; c++)
        {
            final Object data = fromStack[c];

            if (data instanceof Number)
            {
                converted[c] = ((Number) data).doubleValue();
                continue;
            }
            if (data instanceof String)
            {
                converted[c] = Double.parseDouble((String) data);
                continue;
            }

            final StringBuilder errMsg = new StringBuilder();
            errMsg.append("invalid type for ");
            errMsg.append(toString());
            errMsg.append("; ");
            for (int e = 0; e < fromStack.length; e++)
            {
                errMsg.append(fromStack[e].getClass().getSimpleName());
                errMsg.append(" ");
            }
            throw new Exception(errMsg.toString());
        }
        return converted;
    }

    protected boolean includeInRpn ()
    {
        return true;
    }

    protected abstract int precedence ();

    public boolean preceeds (final Operation rightOp)
    {
        if (this.getLevel() > rightOp.getLevel())
            return true;

        if (this.getLevel() < rightOp.getLevel())
            return false;

        if (this.precedence() <= rightOp.precedence())
            return true;

        return false;
    }
}
