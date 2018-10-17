import src.Observation;

/**
 * 
 * @author jason myers
 * @version 10/3/2018
 *
 */
public abstract class AbstractObservation
{
    /**
     * abstract class variables
     */
    private Observation inData;
    private boolean isValid;

    /**
     * sets the observation indata
     * 
     * @param inData
     */
    public void Observation(Observation inData)
    {
        this.inData = inData;
    }

    /**
     * return observation
     * 
     * @return inData
     */
    public Observation getInData()
    {
        return this.inData;
    }

    /**
     * private isValid forces the method to reside inside of class check value to
     * see if valid or not
     * 
     * @return isValid
     */
    public boolean isValid()
    {

        if (inData.getValue() < -900.0)
        {
            isValid = false;
        }
        else
        {
            isValid = true;
        }
        return isValid;
    }

}
