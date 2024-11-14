package nepjr.nf.api.capability;

public interface ISolarPanel 
{
	/**
    *
    * @return whether this solar panel can see the sun or not
    */
	boolean canSeeSun();
	int tier();
}
