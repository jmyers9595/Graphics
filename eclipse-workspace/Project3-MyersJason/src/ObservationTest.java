

import org.junit.Test;
import org.junit.Assert;

public class ObservationTest
{
    /**
     * Test that the observation class is being set correctly
     * 
     * 
     * @param double
     *            string
     * 
     */
    @Test
    public void testObservation()
    {
        Observation test = new Observation(29.0, "test");
        double expected = 29.0;
        double actual = test.getValue();
        String expectedString = "test";
        String actualString = test.getStid();
        Assert.assertEquals(expected, actual, .1);
        Assert.assertEquals(expectedString, actualString);
    }

    /**
     * test that the correct value is being returned
     * 
     * @return double return the value of object
     */
    @Test
    public void testGetValue()
    {
        Observation test = new Observation(29.0, "test");
        double expected = 29.0;
        double actual = test.getValue();
        Assert.assertEquals(expected, actual, .1);
    }

    /**
     * test that the correct stid is being returned
     * 
     * @return string returns the station idea
     */
    @Test
    public void testGetStid()
    {
        Observation test = new Observation(29.0, "test");
        String expected = "test";
        String actual = test.getStid();
        Assert.assertEquals(expected, actual);
    }

    /**
     * test that the correct string is being return method is override to MapData
     * 
     * @return string overrided to mapData
     */
    @Test
    public void testToString()
    {
        Observation test = new Observation(29.0, "test");
        String expected = "29.0 at test";
        String actual = test.toString();
        Assert.assertEquals(expected, actual);
    }

    /**
     * test that the correct true value is returned when value isValid
     * 
     * @return boolean true if object passes
     */
    @Test
    public void testisValidTrue()
    {
        Observation trueTest = new Observation(1.9, "test");
        boolean expectedtrueTest = true;
        boolean actualtrueTest = trueTest.isValid();
        Assert.assertEquals(expectedtrueTest, actualtrueTest);
    }

    /**
     * test that when a invalid data is enter returns false
     * 
     * @return boolean if value is valid or not
     */
    @Test
    public void testisValidFalse()
    {
        Observation falseTest = new Observation(-999, "test");
        boolean expected = false;
        boolean actual = falseTest.isValid();
        Assert.assertEquals(expected, actual);
    }
}
