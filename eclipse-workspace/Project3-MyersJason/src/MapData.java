import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * @author Jason Myers
 * @version 10/3/2018
 * @author jason.myers-1@ou.edu
 *
 */
public class MapData
{
    private ArrayList<Observation> sradData = new ArrayList<>();
    private ArrayList<Observation> tairData = new ArrayList<>();
    private ArrayList<Observation> ta9mData = new ArrayList<>();
    private final int NUMBER_OF_MISSING_OBSERVATIONS = 10;
    private Integer numberOfStations = 0;
    private final String TA9M = "TA9M";
    private final String TAIR = "TAIR";
    private final String SRAD = "SRAD";
    private final String STID = "STID";
    private int stidPosition = 0;
    private int tairPosition = 4;
    private int sradPosition = 13;
    private int ta9mPosition = 14;
    private final String MESONET = "Mesonet";
    private String directory;
    private Statistics tairMin;
    private Statistics tairMax;
    private Statistics tairAverage;
    private Statistics ta9mMin;
    private Statistics ta9mMax;
    private Statistics ta9mAverage;
    private Statistics sradMin;
    private Statistics sradMax;
    private Statistics sradAverage;
    private Statistics sradTotal;
    private String fileName;
    private GregorianCalendar utcDateTime;
    private String paramId;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    /**
     * MapData constructor bring in the date to set gregronian calendar
     * 
     * @param int
     *            int int int int string
     */
    public MapData(int year, int month, int day, int hour, int minute, String directory)
    {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.directory = directory;
        createFileName(year, month, day, hour, minute, directory);
        // set GregorianCalendar to pass over to Statistics
        utcDateTime = new GregorianCalendar();
        utcDateTime.setLenient(false);
        utcDateTime.set(GregorianCalendar.YEAR, year);
        utcDateTime.set(GregorianCalendar.MONTH, month - 1);
        utcDateTime.set(GregorianCalendar.DAY_OF_MONTH, day);
        utcDateTime.set(GregorianCalendar.HOUR_OF_DAY, hour);
        utcDateTime.set(GregorianCalendar.MINUTE, minute);
        utcDateTime.set(GregorianCalendar.SECOND, 0);

    }

    /**
     * build file name to pull information
     * 
     * @return string with file build stringyearmonthdayhourminute
     */
    public String createFileName(int year, int month, int day, int hour, int minute, String directory)
    {
        return fileName = String.format("%s/%04d%02d%02d%02d%02d.mdf", directory, year, month, day, hour, minute);
    }

    private void parseParamHeader(String inParamStr)
    {
        String[] temp = inParamStr.split("\\s+");
        paramId = temp[stidPosition] + temp[tairPosition] + temp[ta9mPosition] + temp[ta9mPosition];

    }

    /**
     * open file with fileReader from built fileName from createFileName
     * 
     * @throws IOException
     *             set observation for these ArrayList sradData tairData ta9mData
     *             keep count of number of stations brought in
     * 
     */
    public void parseFile() throws IOException
    {

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String tempString = "";
        boolean isNull = false;
        int lineCount = 0;
        while (!isNull)
        {
            tempString = br.readLine();
            if (tempString != null)
            {
                if (lineCount == 2)
                {
                    parseParamHeader(tempString);
                }
                if (lineCount >= 3)
                {
                    tempString = tempString.trim();
                    String[] temp = tempString.split("\\s+");
                    sradData.add(new Observation(Double.parseDouble(temp[sradPosition]), temp[stidPosition]));
                    tairData.add(new Observation(Double.parseDouble(temp[tairPosition]), temp[stidPosition]));
                    ta9mData.add(new Observation(Double.parseDouble(temp[ta9mPosition]), temp[stidPosition]));
                    // test printlns to make sure data is being inputed properly
                    /*
                     * System.out.println("numberOfStations: " + numberOfStations +
                     * sradData.get(numberOfStations).toString());
                     * System.out.println("numberOfStations: " + numberOfStations +
                     * tairData.get(numberOfStations).toString());
                     * System.out.println("numberOfStations: " + numberOfStations +
                     * ta9mData.get(numberOfStations).toString());
                     */
                    numberOfStations++;
                }
                lineCount++;
            }
            else
            {
                isNull = true;
            }
        }
        /**
         * calculates all of the return data for sradAverage sradMax sradMin tairData
         * for each variable
         * 
         * @param ArrayList
         *            and paramId ta9mData for each variable
         */

        calculateStatistics(sradData, SRAD);
        calculateStatistics(ta9mData, TA9M);
        calculateStatistics(tairData, TAIR);

        br.close();
    }

    /**
     * private method built to run through each arraylist set all of the different
     * variable attach to each type of arrayList
     * 
     * @param arraylist
     *            string pass in arraylist sort return
     * 
     */
    private void calculateStatistics(ArrayList<Observation> inData, String paramld5)
    {
        double total = 0;
        Observation max = null;
        Observation min = null;
        int numberOfInvalid = 0;
        int i = 0;

        for (i = 0; i < inData.size(); i++)
        {
            if (inData.get(i).isValid())
            {
                max = inData.get(i);
                min = inData.get(i);
                total += inData.get(i).getValue();
                break;
            }
            else
            {
                numberOfInvalid--;
            }
        }
        for (int j = i + 1; j < inData.size(); j++)
        {
            if (inData.get(j).isValid())
            {
                total += inData.get(j).getValue();
                if (inData.get(j).getValue() > max.getValue())
                {
                    max = inData.get(j);
                }
                if (inData.get(j).getValue() < min.getValue())
                {
                    min = inData.get(j);
                }
            }
            else
            {
                numberOfInvalid--;
            }
        }
        /** find number of Valid stations */
        if ((numberOfInvalid * -1) >= NUMBER_OF_MISSING_OBSERVATIONS)
        {
            max = new Observation(-999, "invalid");
            min = new Observation(-999, "invalid");
            total = -999;
        }
        else
        {
            numberOfStations = numberOfStations + numberOfInvalid;
            findAndSetStatistics(total, max, min, numberOfStations + 1, paramld5);
        }

    }

    /**
     * Set in calculate stats
     * 
     * @return sradAverage
     */
    public Statistics getSradAverage()
    {
        return sradAverage;
    }

    /**
     * Set in calculate stats
     * 
     * @return sradMax
     */
    public Statistics getSradMax()
    {
        return sradMax;
    }

    /**
     * Set in calculate stats
     * 
     * @return sradMin
     */
    public Statistics getSradMin()
    {
        return sradMin;
    }

    /**
     * Set in calculate stats
     * 
     * @return sradTotal
     */
    public Statistics getSradTotal()
    {
        return sradTotal;
    }

    /**
     * Set in calculate stats
     * 
     * @return Ta9mAverage
     */
    public Statistics getTa9mAverage()
    {
        return ta9mAverage;
    }

    /**
     * Set in calculate stats
     * 
     * @return Ta9mMax
     */
    public Statistics getTa9mMax()
    {
        return ta9mMax;
    }

    /**
     * Set in calculate stats
     * 
     * @return Ta9mMin
     */
    public Statistics getTa9mMin()
    {
        return ta9mMin;
    }

    /**
     * Set in calculate stats
     * 
     * @return tairAverage
     */
    public Statistics getTairAverage()
    {
        return tairAverage;
    }

    /**
     * Set in calculate stats
     * 
     * @return tairMax
     */
    public Statistics getTairMax()
    {
        return tairMax;
    }

    /**
     * Set in calculate stats
     * 
     * @return tairMin
     */
    public Statistics getTairMin()
    {
        return tairMin;
    }

    /**
     * uses to different methods to setup final string equals relate to 57 equal
     * signs going across the screen equals(how many lines you want)
     * 
     * @return string print out to screen all overrides end here
     */
    public String toString()
    {

        String finish = "";
        finish += equals(1);
        finish += String.format("=== %4d-%02d-%02d %02d:%02d ===\n", year, month, day, hour, minute);
        finish += equals(2);
        finish += writeAirTemperture();
        finish += equals(2);
        finish += writeAir9Temperture();
        finish += equals(2);
        finish += writeSradData();
        finish += equals(1);

        return finish;
    }

    /**
     * loads each average max min and srad total inside of MapData class
     * 
     * @param total
     * @param biggest
     * @param smallest
     * @param numberOfValidStations
     * @param paramId2
     */
    private void findAndSetStatistics(double total, Observation biggest, Observation smallest,
            int numberOfValidStations, String paramId2)
    {

        if (paramId2.equalsIgnoreCase(SRAD))
        {
            sradMax = new Statistics(biggest.getValue(), biggest.getStid(), utcDateTime, numberOfValidStations,
                    StatsType.MAXIMUM);
            sradMin = new Statistics(smallest.getValue(), biggest.getStid(), utcDateTime, numberOfValidStations,
                    StatsType.MINIMUM);
            sradAverage = new Statistics((total / numberOfValidStations), MESONET, utcDateTime, numberOfValidStations,
                    StatsType.AVERAGE);
            sradTotal = new Statistics(total, MESONET, utcDateTime, numberOfValidStations, StatsType.TOTAL);
        }
        if (paramId2.equalsIgnoreCase(TAIR))
        {
            tairMax = new Statistics(biggest.getValue(), biggest.getStid(), utcDateTime, numberOfValidStations,
                    StatsType.MAXIMUM);
            tairMin = new Statistics(smallest.getValue(), smallest.getStid(), utcDateTime, numberOfValidStations,
                    StatsType.MINIMUM);
            tairAverage = new Statistics((total / numberOfValidStations), MESONET, utcDateTime, numberOfValidStations,
                    StatsType.AVERAGE);
        }
        if (paramId2.equalsIgnoreCase(TA9M))
        {
            ta9mMax = new Statistics(biggest.getValue(), biggest.getStid(), utcDateTime, numberOfValidStations,
                    StatsType.MAXIMUM);
            ta9mMin = new Statistics(smallest.getValue(), smallest.getStid(), utcDateTime, numberOfValidStations,
                    StatsType.MINIMUM);
            ta9mAverage = new Statistics((total / numberOfValidStations), MESONET, utcDateTime, numberOfValidStations,
                    StatsType.AVERAGE);
        }

    }

    /**
     * 
     * @param i
     *            how many line of 57 equals you want
     * @return string containing the lines with a \n at the end line
     */
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

    /**
     * build AirTemperture out going string using String.format
     * 
     * @return
     */
    private String writeAirTemperture()
    {
        String stringAirTemp = "";
        stringAirTemp += String.format("Maximum Air Temperture[1.5m] = %.1f C at %s\n", tairMax.getValue(),
                tairMax.getStid());
        stringAirTemp += String.format("Minimun Air Temperture[1.5m] = %.1f C at %s\n", tairMin.getValue(),
                tairMin.getStid());
        stringAirTemp += String.format("Average Air Temperture[1.5m] = %.1f c at %s\n", tairAverage.getValue(),
                tairAverage.getStid());
        return stringAirTemp;
    }

    /**
     * build Air9temp toString using String.format
     * 
     * @return
     */
    private String writeAir9Temperture()
    {
        String stringAir9Temp = "";
        stringAir9Temp += String.format("Maximun Air Temperture[9.0m] = %.1f C at %s\n", ta9mMax.getValue(),
                ta9mMax.getStid());
        stringAir9Temp += String.format("Minimun Air Temperture[9.0m] = %.1f C at %s\n", ta9mMin.getValue(),
                ta9mMin.getStid());
        stringAir9Temp += String.format("Average Air Temperture[9.0m] = %.1f C at %s\n", ta9mAverage.getValue(),
                ta9mAverage.getStid());
        return stringAir9Temp;
    }

    /**
     * sradData toString using String.format
     * 
     * @return
     */
    private String writeSradData()
    {
        String dataOfSrad = "";
        dataOfSrad += String.format("Maximun Solar Radiation[1.5m] = %.1f W/m^2 at %s\n", sradMax.getValue(),
                sradMax.getStid());
        dataOfSrad += String.format("Minimun Solar Radiation[1.5m] = %.1f W/m^2 at %s\n", sradMin.getValue(),
                sradMin.getStid());
        dataOfSrad += String.format("Average Solar Radiation[1.5m] = %.1f W/m^2 at %s\n", sradAverage.getValue(),
                sradAverage.getStid());
        return dataOfSrad;
    }
}
