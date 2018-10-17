import java.util.GregorianCalendar;

/**
 * 
 * @author Jason Myers
 *@version 10/3/2018
 */
/**
 * impalmented the three methods inside of statistics class
 * 
 */
public interface DateTimeComparable
{
    boolean newerThan(GregorianCalendar inDateTimeUTC);

    boolean olderThan(GregorianCalendar inDateTimeUTC);

    boolean sameAs(GregorianCalendar inDateTimeUTC);
}
