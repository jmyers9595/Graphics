
import java.io.IOException;

import org.junit.Test;
import org.junit.Assert;

public class MapDataTest
{
    /**
     * checks that the MapData constructor is set correctly constructor passes file
     * name through sierries of int which are brought together threw
     * createFileName()
     * 
     * @param int
     *            int int int int string
     */
    @Test
    public void testMapData()
    {
        MapData test = new MapData(2018, 8, 1, 7, 0, "data");
        String actual = test.createFileName(2018, 8, 1, 7, 0, "data");
        String expected = "data/201808010700.mdf";
        Assert.assertEquals(expected, actual);

    }

    /**
     * takes in the int pass threw the constructor to build the name
     * 
     * @return ArrayList full of data in right catagory
     */

    @Test
    public void testCreateFileName()
    {
        MapData test = new MapData(2018, 1, 1, 7, 0, "data");

        String expected = "data/201801010700.mdf";
        String actual = test.createFileName(2018, 1, 1, 7, 0, "data");
        Assert.assertEquals(expected, actual);
    }

    /**
     * takes in the file name from createfilename sets it inside fileReader and
     * parse the information and sets ArrayList for each data point
     * 
     * @throws IOException
     *             calls calculate statics to set the different value looking for
     */

    @Test
    public void testParseFile() throws IOException
    {
        try
        {
            MapData test = new MapData(2018, 8, 1, 7, 0, "data");
            test.parseFile();
            Assert.assertTrue("File was read", true);
        }
        catch (IOException e)
        {
            Assert.fail("File not found");
        }
    }

    /**
     * @return sradAverageGetValue
     * @throws IOException
     *             test that sradAverage return correct value
     */
    @Test
    public void testGetSradAverageGetValue() throws IOException
    {
        MapData test = new MapData(2018, 8, 01, 07, 00, "data");
        test.parseFile();
        double actualDouble = test.getSradAverage().getValue();
        double expectedDouble = 0.0;

        Assert.assertEquals(expectedDouble, actualDouble, .1);
    }

    /**
     * test that correct stid return
     * 
     * @return sradAverageGetStid
     * @throws IOException
     */
    @Test
    public void testGetSradAverageGetStid() throws IOException
    {
        MapData test = new MapData(2018, 8, 1, 7, 0, "data");
        test.parseFile();
        String actual = test.getSradAverage().getStid();
        String expected = "Mesonet";
        Assert.assertEquals(expected, actual);
    }

    /**
     * @return sradMaxGetValue
     * @throws IOException
     *             test to check that max value is found
     */
    @Test
    public void testGetSradMaxGetValue() throws IOException
    {
        MapData test = new MapData(2018, 8, 01, 07, 00, "data");
        test.parseFile();
        double actual = test.getSradMax().getValue();
        double expected = 0.0;
        Assert.assertEquals(expected, actual, .1);
    }

    /**
     * Test to check that max value station id
     * 
     * @return statistic with date value and id
     */
    @Test
    public void testGetSradMaxGetStid() throws IOException
    {
        MapData test = new MapData(2018, 8, 01, 07, 00, "data");
        test.parseFile();
        String actual = test.getSradMax().getStid();
        String expected = "ACME";
        Assert.assertEquals(expected, actual);
    }

    /**
     * Test to check that min value
     */
    @Test
    public void testGetSradMinGetValue() throws IOException
    {
        MapData test = new MapData(2018, 8, 01, 07, 00, "data");
        test.parseFile();
        double expected = 0.0;
        double actual = test.getSradMin().getValue();
        Assert.assertEquals(expected, actual, .1);
    }

    /**
     * test to check that min station id is return correctly
     * 
     * @return statistic with date value and id
     */
    @Test
    public void testGetSradMinGetStid() throws IOException
    {
        MapData test = new MapData(2018, 8, 01, 07, 00, "data");
        test.parseFile();
        String expected = "ACME";
        String actual = test.getSradMin().getStid();
        Assert.assertEquals(expected, actual);
    }

    /**
     * Test to check that srad tallies correctly
     * 
     * @return statistic with date value and id
     */
    @Test
    public void testGetSradTotalGetValue() throws IOException
    {
        MapData test = new MapData(2018, 8, 01, 07, 00, "data");
        test.parseFile();
        double expected = 0.0;
        double actual = test.getSradTotal().getValue();
        Assert.assertEquals(expected, actual, .1);
    }

    @Test
    /**
     * test that stid return Mesonet
     */
    public void testGetSradTotalGetStid() throws IOException
    {
        MapData test = new MapData(2018, 8, 01, 07, 00, "data");
        test.parseFile();
        String expected = "Mesonet";
        String actual = test.getSradTotal().getStid();
        Assert.assertEquals(expected, actual);
    }

    /**
     * @throws IOException
     *             check that ta9mAverage return correct value
     * @return statistic with date value and id
     */
    @Test
    public void testGetTa9mAverageGetValue() throws IOException
    {
        MapData test = new MapData(2018, 8, 01, 07, 00, "data");
        test.parseFile();
        double expected = 19.68;
        double actual = test.getTa9mAverage().getValue();
        Assert.assertEquals(expected, actual, .01);
    }

    /**
     * @throws IOException
     *             return mesonet for Stid
     */
    @Test
    public void testGetTa9mAverageGetStid() throws IOException
    {
        MapData test = new MapData(2018, 8, 01, 07, 00, "data");
        test.parseFile();
        String expected = "Mesonet";
        String actual = test.getTa9mAverage().getStid();
        Assert.assertEquals(expected, actual);
    }

    /**
     * @throws IOException
     *             return the max value
     */
    @Test
    public void testGetTa9mMaxGetValue() throws IOException
    {
        MapData test = new MapData(2018, 8, 01, 07, 00, "data");
        test.parseFile();
        double expected = 23.3;
        double actual = test.getTa9mMax().getValue();
        Assert.assertEquals(expected, actual, .1);
    }

    /**
     * @throws IOException
     *             return the correct stid for max value
     */

    @Test
    public void testGetTa9mMaxGetStid() throws IOException
    {
        MapData test = new MapData(2018, 8, 01, 07, 00, "data");
        test.parseFile();
        String expected = "MARE";
        String actual = test.getTa9mMax().getStid();
        Assert.assertEquals(expected, actual);
    }

    /**
     * @throws IOException
     *             check that the min value is return correctly
     * @return statistic with date value and id
     */
    @Test
    public void testGetTa9mMinGetValue() throws IOException
    {
        MapData test = new MapData(2018, 8, 1, 7, 0, "data");
        test.parseFile();
        double expected = 15.8;
        double actual = test.getTa9mMin().getValue();
        Assert.assertEquals(expected, actual, .1);
    }

    /**
     * @throws IOException
     *             check that the correct stations is being returned
     * @return statistic with date value and id
     */
    @Test
    public void testGetTa9mMinGetStid() throws IOException
    {
        MapData test = new MapData(2018, 8, 1, 7, 0, "data");
        test.parseFile();
        String expected = "COOK";
        String actual = test.getTa9mMin().getStid();
        Assert.assertEquals(expected, actual);
    }

    /**
     * 
     * @throws IOException
     *             check that the correct max value is being returned
     * @return statistic with date value and id
     */
    @Test
    public void testGetTairAverageGetValue() throws IOException
    {
        MapData test = new MapData(2018, 8, 1, 7, 0, "data");
        test.parseFile();
        double expected = 17.98;
        double actual = test.getTairAverage().getValue();
        Assert.assertEquals(expected, actual, .01);
    }

    /**
     * 
     * @throws IOException
     *             check that the correct stations is being returned Mesonet since
     *             average is all
     * @return statistic with date value and id
     */
    @Test
    public void testGetTairAverageGetStid() throws IOException
    {
        MapData test = new MapData(2018, 8, 1, 7, 0, "data");
        test.parseFile();
        String expected = "Mesonet";
        String actual = test.getTairAverage().getStid();
        Assert.assertEquals(expected, actual);
    }

    /**
     * 
     * @throws IOException
     *             check that the correct tairMax value is being returned
     * @return statistic with date value and id
     */
    @Test
    public void testGetTairMaxGetValue() throws IOException
    {
        MapData test = new MapData(2018, 8, 1, 7, 0, "data");
        test.parseFile();
        double expected = 21.7;
        double actual = test.getTairMax().getValue();
        Assert.assertEquals(expected, actual, .1);
    }

    /**
     * 
     * @throws IOException
     *             check that the correct Max stations is being returned
     * @return statistic with date value and id
     */
    @Test
    public void testGetTairMaxGetStid() throws IOException
    {
        MapData test = new MapData(2018, 8, 1, 7, 0, "data");
        test.parseFile();
        String expected = "MEDI";
        String actual = test.getTairMax().getStid();
        Assert.assertEquals(expected, actual);
    }

    /**
     * 
     * @throws IOException
     *             check that the correct stations value is being returned
     * @return statistic with date value and id
     */
    @Test
    public void testGetTairMinGetValue() throws IOException
    {
        MapData test = new MapData(2018, 8, 1, 7, 0, "data");
        test.parseFile();
        double expected = 13.8;
        double actual = test.getTairMin().getValue();
        Assert.assertEquals(expected, actual, .1);
    }

    /**
     * 
     * @throws IOException
     *             check that the correct stations is being returned
     */
    @Test
    public void testGetTairMinGetStid() throws IOException
    {
        MapData test = new MapData(2018, 8, 1, 7, 0, "data");
        test.parseFile();
        String expected = "EVAX";
        String actual = test.getTairMin().getStid();
        Assert.assertEquals(expected, actual);
    }

    /**
     * check that the output is correct match the output of the teacher file
     * 
     * @throws IOException
     * @return string print out the full information page
     */
    @Test
    public void testToString() throws IOException
    {
        MapData test = new MapData(2018, 8, 1, 7, 0, "data");
        test.parseFile();
        String expected1 = "";
        expected1 += equals(1);
        expected1 += String.format("=== %4d-%02d-%02d %02d:%02d ===\n", 2018, 8, 1, 7, 0);
        expected1 += equals(2);
        expected1 += writeAirTemperture();
        expected1 += equals(2);
        expected1 += writeAir9Temperture();
        expected1 += equals(2);
        expected1 += writeSradData();
        expected1 += equals(1);
        ;
        String actual1 = test.toString();
        Assert.assertEquals(expected1, actual1);
    }

    private String equals(int i)
    {
        String equals = "";
        for (int j = 0; j < i; j++)
        {
            for (int d = 0; d < 57; d++)
            {
                equals += "=";
            }
            equals += "\n";

        }
        return equals;

    }

    /** build AirTemperture out going string using String.format */
    private String writeAirTemperture()
    {
        String stringAirTemp = "";
        stringAirTemp += String.format("Maximum Air Temperture[1.5m] = %.1f C at %s\n", 21.7, "MEDI");
        stringAirTemp += String.format("Minimun Air Temperture[1.5m] = %.1f C at %s\n", 13.8, "EVAX");
        stringAirTemp += String.format("Average Air Temperture[1.5m] = %.1f c at %s\n", 18.0, "Mesonet");
        return stringAirTemp;
    }

    /** build Air9temp toString using String.format */
    private String writeAir9Temperture()
    {
        String stringAir9Temp = "";
        stringAir9Temp += String.format("Maximun Air Temperture[9.0m] = %.1f C at %s\n", 23.3, "MARE");
        stringAir9Temp += String.format("Minimun Air Temperture[9.0m] = %.1f C at %s\n", 15.8, "COOK");
        stringAir9Temp += String.format("Average Air Temperture[9.0m] = %.1f C at %s\n", 19.7, "Mesonet");
        return stringAir9Temp;
    }

    /** sradData toString using String.format */
    private String writeSradData()
    {
        String dataOfSrad = "";
        dataOfSrad += String.format("Maximun Solar Radiation[1.5m] = %.1f W/m^2 at %s\n", 0.0, "ACME");
        dataOfSrad += String.format("Minimun Solar Radiation[1.5m] = %.1f W/m^2 at %s\n", 0.0, "ACME");
        dataOfSrad += String.format("Average Solar Radiation[1.5m] = %.1f W/m^2 at %s\n", 0.0, "Mesonet");
        return dataOfSrad;
    }

}
