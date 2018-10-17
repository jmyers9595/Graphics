
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.Assert;

/**
 * @author Jason Myers
 * @version 10/3/2018
 *
 */
public class StatisticsTest
{
    /**
     * test that Statistic constructor converts the date to correct string
     * 
     * @param double
     *            string string int statsType
     * 
     */
    @Test
    public void testStatisticsDoubleStringStringIntStatsType()
    {
        Statistics test = new Statistics(29.0, "test", "201808010700", 119, StatsType.AVERAGE);
        double expectedDoubleValue = 29.0;
        double actualDoubleValue = test.getValue();
        Assert.assertEquals(expectedDoubleValue, actualDoubleValue, .1);
        String expectedString = "test";
        String actualString = test.getStid();
        Assert.assertEquals(expectedString, actualString);
        GregorianCalendar expectedDatecalendar = new GregorianCalendar(2018, 7, 1, 7, 0);
        String actualCalendar = test.StringFromDate(expectedDatecalendar);
        String expectedDate = "2018-08-01T07:00:00 CDT";

        Assert.assertEquals(actualCalendar, expectedDate);
        int expectedIntValue = 119;
        int actualIntValue = test.getNumberOfReportingStations();
        Assert.assertEquals(expectedIntValue, actualIntValue);
    }

    /**
     * test that the constructor takes in all of the corect data and set them to the
     * variables
     * 
     * @param double
     *            string GregorianCalendar Int StatsType
     */
    @Test
    public void testStatisticsDoubleStringGregorianCalendarIntStatsType()
    {
        GregorianCalendar expectedDatecalendar = new GregorianCalendar(2018, 7, 1, 7, 0);
        Statistics test = new Statistics(29.0, "test", expectedDatecalendar, 119, StatsType.AVERAGE);
        double expectedDoubleValue = 29.0;
        double actualDoubleValue = test.getValue();
        Assert.assertEquals(expectedDoubleValue, actualDoubleValue, .1);
        String expectedString = "test";
        String actualString = test.getStid();
        Assert.assertEquals(expectedString, actualString);
        String actualCalendar = test.StringFromDate(expectedDatecalendar);
        String expectedDate = "2018-08-01T07:00:00 CDT";
        Assert.assertEquals(actualCalendar, expectedDate);
        int expectedIntValue = 119;
        int actualIntValue = test.getNumberOfReportingStations();
        Assert.assertEquals(expectedIntValue, actualIntValue);
    }

    /**
     * test that string passed into the constructor passes to this method and sets
     * UTCDateTime
     * 
     * @return GregorianCalendar from string passed threw the constructor
     */
    @Test
    public void testCreateDateFromString()
    {
        Statistics test = new Statistics(29.0, "test", "201808010700", 119, StatsType.AVERAGE);
        GregorianCalendar expectedDatecalendar = new GregorianCalendar(2018, 7, 1, 7, 0);
        GregorianCalendar actual = test.createDateFromString("201808010700");
        Assert.assertEquals(expectedDatecalendar, actual);
    }

    /**
     * test that when calendar is pass threw constructor or utcDateTime is set
     * 
     * @returns calendar in "yyyy-mm-ddThh:mm:ss z
     */
    @Test
    public void testStringFromDate()
    {
        Statistics test = new Statistics(29.0, "test", "201808010700", 119, StatsType.AVERAGE);
        GregorianCalendar expectedDatecalendar = new GregorianCalendar(2018, 7, 1, 7, 0);
        String actual = test.StringFromDate(expectedDatecalendar);
        System.out.println(actual);
        String expected = "2018-08-01T07:00:00 CDT";
        Assert.assertEquals(expected, actual);

    }

    /**
     * test that the correct amount of stations is passed threw constructor
     * 
     * @return returns the correct valid amount of stations reported
     */
    @Test
    public void testGetNumberOfReportingStations()
    {
        Statistics test = new Statistics(29.0, "test", "201808010700", 119, StatsType.AVERAGE);
        int expected = 119;
        int actual = test.getNumberOfReportingStations();
        Assert.assertEquals(expected, actual);
    }

    /**
     * thest that correct string is returned from gregorianCalendar
     * 
     * @retun return sting in the format of yyyy-mm-ddThh:mm:ss z
     */
    @Test
    public void testGetUTCDateTimeString()
    {
        Statistics test = new Statistics(29.0, "test", "201808010700", 119, StatsType.AVERAGE);
        String expected = "2018-08-01T07:00:00 CDT";
        String actual = test.getUTCDateTimeString();
        Assert.assertEquals(expected, actual);
    }

    /**
     * Implamented from dateTimeComparable checks that the right return is set from
     * this method If date set is newer than one passed return true
     * 
     * @return boolean true if the calendar passed in is newer than the one on file
     * @param GregorianCalendar
     */
    @Test
    public void testNewerThan()
    {
        Statistics test = new Statistics(29.0, "test", "201808010700", 119, StatsType.AVERAGE);
        GregorianCalendar testDate = new GregorianCalendar(2019, 1, 1, 7, 0);
        boolean expectTrue = true;
        boolean actualTest = test.newerThan(testDate);
        Assert.assertEquals(expectTrue, actualTest);
        testDate = new GregorianCalendar(2016, 7, 1, 7, 0);
        boolean expectedfalse = false;
        boolean actualfalse = test.sameAs(testDate);
        Assert.assertEquals(expectedfalse, actualfalse);
    }

    /**
     * Implamented from dateTimeComparable check that if a data If date set inside
     * constructor is older than date passed in return true
     * 
     * @return boolean true if the date is older than the one you passed in
     * @param GregorianCalendar
     */
    @Test
    public void testOlderThan()
    {
        Statistics test = new Statistics(29.0, "test", "201808010700", 119, StatsType.AVERAGE);
        GregorianCalendar testDate = new GregorianCalendar(2011, 1, 1, 7, 0);
        boolean expected = true;
        boolean actual = test.olderThan(testDate);
        Assert.assertEquals(expected, actual);
        testDate = new GregorianCalendar(2019, 7, 1, 7, 0);
        boolean expectedfalse = false;
        boolean actualfalse = test.sameAs(testDate);
        Assert.assertEquals(expectedfalse, actualfalse);
    }

    /**
     * check that when date passed threw the constructor and set in variable return
     * true if the date are the same
     * 
     * @return boolean true if dates are the same
     * @param GregorianCalendar
     */
    @Test
    public void testSameAs()
    {
        Statistics test = new Statistics(29.0, "test", "201808010700", 119, StatsType.AVERAGE);
        GregorianCalendar testDate = new GregorianCalendar(2018, 7, 1, 7, 0);
        boolean expected = true;
        boolean actual = test.sameAs(testDate);
        Assert.assertEquals(expected, actual);
        testDate = new GregorianCalendar(2019, 7, 1, 7, 0);
        boolean expectedfalse = false;
        boolean actualfalse = test.sameAs(testDate);
        Assert.assertEquals(expectedfalse, actualfalse);

    }

    /**
     * Tostring is overrided to the MapData class
     * 
     * @return string that is overrided in the Mapdata class
     * @param double
     *            string string int StatsType
     */
    @Test
    public void testToString()
    {
        Statistics test = new Statistics(29.0, "test", "201808010700", 119, StatsType.AVERAGE);
        String expected = "29.0 at test time: 2018-08-01T07:00:00 CDT";
        String actual = test.toString();
        Assert.assertEquals(expected, actual);
    }

}
