import java.io.IOException;
/**@author Jason Myers
 * @version 9/18/2018
 * <Driver Class runs the program by passing in the date and times to out on screen>
 */
import java.util.ArrayList;

public class Driver
{

    public static void main(String[] args) throws IOException
    {
        /**
         * enter the year month day hour minute string to find min max average of srad
         * airt air9t
         */
        final int YEAR = 2018;
        final int MONTH = 8;
        final int DAY = 01;
        final int HOUR = 07;
        final int MINUTE = 00;
        String fileName = "data";

        /** Construtor to build MapData */
        MapData mapData = new MapData(YEAR, MONTH, DAY, HOUR, MINUTE, fileName);
        /** run mapData class */
        mapData.parseFile();
        /** print out all information found in file */
        System.out.println(mapData);
    }
}