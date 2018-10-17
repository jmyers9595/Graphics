

/**
 * 
 * @author Jason Myers
 * @version 10/3/2018
 */
public class Observation extends AbstractObservation
{
    double value;
    String stid;

    /**
     * brings in value in double and station id
     * 
     * @param double
     *            string
     * 
     */
    public Observation(double value, String stid)
    {
        this.value = value;
        this.stid = stid;
    }

    /**
     * returns value as a double
     * 
     * @return double
     */
    public double getValue()
    {
        return this.value;
    }

    /**
     * returns station id as string
     * 
     * @return String
     */
    public String getStid()
    {
        return this.stid;
    }

    /**
     * method is contained inside of AbstractObservation must use super to call
     * right method
     */
    @Override
    public boolean isValid()
    {
        super.Observation(new Observation(value, stid));
        return super.isValid();
    }

    /** tostring is override since it is controlled inside of MapData class */
    @Override
    public String toString()
    {
        return String.format("%.1f at %s", value, stid);
    }

}
