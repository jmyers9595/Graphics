import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * extend the object observation
 * 
 * @author Jason Myers
 * @version 10/3/18
 */

public class Statistics extends Observation implements DateTimeComparable
{
    protected String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss z";
    private GregorianCalendar utcDateTime;
    private int numberOFReportingStations;

    /**
     * set observation class with super constructor set utcDateTime by passing
     * string to a constructor to set calendar set numberOfReportingStations set
     * StatsType
     * 
     * @param double
     *            string string int StatsType
     */
    public Statistics(double value, String stid, String dateTimeStr, int numberOfValidStations, StatsType inStatType)
    {
        super(value, stid);
        numberOFReportingStations = numberOfValidStations;
        utcDateTime = createDateFromString(dateTimeStr);
    }

    /**
     * set observation class with super constructor set utcDateTime using the
     * constructor set numberOfReportingStations set StatsType
     * 
     * @param double
     *            string, GregronianCalendar,int,statsType
     */
    public Statistics(double value, String stid, GregorianCalendar dateTime, int numberOfValidStations,
            StatsType inStatType)
    {
        super(value, stid);
        utcDateTime = dateTime;
        numberOFReportingStations = numberOfValidStations;

    }

    /**
     * builds a string date and sets the greorian calendar and set the utcDateTime
     * 
     * @return gregorianCalendar
     */
    public GregorianCalendar createDateFromString(String dateTimeStr)
    {
        int year = Integer.parseInt(dateTimeStr.substring(0, 4));
        int month = Integer.parseInt(dateTimeStr.substring(4, 6));
        int day = Integer.parseInt(dateTimeStr.substring(6, 8));
        int hour = Integer.parseInt(dateTimeStr.substring(8, 10));
        int minute = Integer.parseInt(dateTimeStr.substring(10, 12));

        return utcDateTime = new GregorianCalendar(year, (month - 1), day, hour, minute);

    }

    /**
     * bring in calendar and converts to formatted date using simpleDateFormat
     * 
     * @return String
     */
    public String StringFromDate(GregorianCalendar calendar)
    {
        SimpleDateFormat date = new SimpleDateFormat(DATE_TIME_FORMAT);
        date.setCalendar(calendar);
        return date.format(calendar.getTime());
    }

    /**
     * number of valid stations
     * 
     * @return int
     */
    public int getNumberOfReportingStations()
    {
        return numberOFReportingStations;
    }

    /**
     * formats utcDateTime to a string
     * 
     * @return String format yyyy-mm-ddThh:mm:ss z
     */
    public String getUTCDateTimeString()
    {
        return StringFromDate(utcDateTime);
    }

    /**
     * check if incoming calendar is newerThan calendar set in class
     * 
     * @return boolean
     * @param GregorianCalendar
     */
    public boolean newerThan(GregorianCalendar inDateTime)
    {
        int number = inDateTime.compareTo(utcDateTime);
        if (number > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * check if incoming calendar is olderThan calendar set in class
     * 
     * @param GregorianCalendar
     * @return boolean
     */
    public boolean olderThan(GregorianCalendar inDateTime)
    {
        int number = inDateTime.compareTo(utcDateTime);
        if (number < 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * check if incoming calendar is sameas calendar set in class
     * 
     * @param GregorianCalendar
     * @return boolean
     */
    public boolean sameAs(GregorianCalendar inDateTime)
    {
        int number = inDateTime.compareTo(utcDateTime);
        if (number == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * override since mapdata control tostring
     * 
     * @return String
     */
    @Override
    public String toString()
    {
        return String.format("%.1f at %s time: %s", super.getValue(), super.getStid(), getUTCDateTimeString());
    }

}
