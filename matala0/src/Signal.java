//ONE WIFI SIGNAL

public class Signal 
{

	

	public String SSID;

	public String MAC;

	public int Frequency;

	public int Signal;

	

	public Signal (String SSID, String MAC, int Freq, int Sig)

	{

		this.SSID=SSID;

		this.MAC=MAC;

		this.Frequency=Freq;

		this.Signal=Sig;

	}

	



	

	public String toString() 
	{

		return "" + SSID + "," + MAC + "," + Frequency + "," + Signal + "";

	}





}