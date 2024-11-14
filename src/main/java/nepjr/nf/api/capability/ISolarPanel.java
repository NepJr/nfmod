package nepjr.nf.api.capability;

public interface ISolarPanel 
{
	/**
    *
    * @return whether this solar panel can see the sun or not
    */
	boolean canSeeSun();
	
	/**
    *
    * @return the tier of the solar panel as an integer (to use with GTValues.V[] or something)
    */
	int tier();
}
