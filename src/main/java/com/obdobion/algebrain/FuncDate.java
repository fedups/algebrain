package com.obdobion.algebrain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.obdobion.calendar.CalendarFactory;

/**
 * @author Chris DeGreef
 *
 */
public class FuncDate extends Function
{

    public FuncDate()
    {
        super();
    }

    public FuncDate(final TokVariable var)
    {
        super(var);
    }

    @Override
    public void resolve (final ValueStack values) throws Exception
    {
        if (values.size() < 1)
            throw new Exception("missing operands for " + toString());

        try
        {
            String adjustments = "";
            String dateFormat = "";

            if (getParameterCount() == 3)
                adjustments = values.popString();

            if (getParameterCount() > 1)
                dateFormat = values.popString();

            /*
             * format contains the adjustments for everything except String
             * input
             */

            final Object dateInput = values.popWhatever();

            Date convertedInputDate = null;
            if (dateInput instanceof String)
            {
                if (((String) dateInput).equalsIgnoreCase("today"))
                {
                    adjustments = dateFormat;
                    values.push(CalendarFactory.today(adjustments));

                } else if (((String) dateInput).equalsIgnoreCase("now"))
                {
                    adjustments = dateFormat;
                    values.push(CalendarFactory.now(adjustments));
                } else
                {
                    final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                    convertedInputDate = sdf.parse((String) dateInput);
                    values.push(CalendarFactory.modify(convertedInputDate, adjustments));
                }
                return;
            }

            adjustments = dateFormat;
            if (dateInput instanceof Long)
            {
                convertedInputDate = new Date((Long) dateInput);
            } else if (dateInput instanceof Double)
            {
                convertedInputDate = new Date(((Double) dateInput).longValue());
            } else
                convertedInputDate = ((Calendar) dateInput).getTime();

            values.push(CalendarFactory.modify(convertedInputDate, adjustments));

        } catch (final ParseException e)
        {
            e.fillInStackTrace();
            throw new Exception(toString() + "; " + e.getMessage(), e);
        }
    }

    @Override
    public String toString ()
    {
        return "function(date)";
    }
}
