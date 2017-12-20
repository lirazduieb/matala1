

/**

 * get location Lat,Lon,Alt,ID and time

 *

 *

 */

public class Location
{

	

	public double Lat;
	public double Lon;
	public double Alt;
	public String ID; 
	public String Time;

	public Location(double Lat, double Lon, double Alt,String ID, String time)
	{
		this.Lat=Lat;
		this.Lon=Lon;
		this.Alt=Alt;
		this.ID=ID;
		this.Time=time;
	}

	public boolean isequalocation(Location Loc)
	{
		if(this.Lat==Loc.Lat & this.Lon==Loc.Lon & this.Alt==Loc.Alt)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public double getLat() 
	{
		return Lat;
	}
	public void setLat(double lat) 
	{
		Lat = lat;
	}

	public double getLon()
	{
		return Lon;
	}
	public void setLon(double lon) 
	{
		this.Lon = lon;
	}
	public double getAlt() 
	{
		return Alt;
	}

	public void setAlt(double alt) 
	{
		this.Alt = alt;
	}
	public String getID() 
	{
		return ID;
	}
	public void setID(String iD) 
	{
		this.ID = iD;
	}

	public String getTime()
	{
		return Time;
	}

	public void setTime(String time) 
	{
		this.Time = time;
	}

	public String toString() 
	{
		return "" + Lat + "," + Lon + "," + Alt + "," + ID + "," + Time + "";

	}
}




