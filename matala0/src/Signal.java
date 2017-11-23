import java.util.Date;

public class Signal 
{
	
	
	java.util.Date getDate() 
	{
		return null;
	}
	
	private String mac,ssid,authmode,type,id;
	private int channel,rssi;
	private double lat,lot,alt,accm;
	private Date Date;

	public Signal() 
	{
		this.mac=null;
		this.ssid=null;
		this.authmode=null;
		this.type=null;
		this.id=null;
		this.channel=0;
		this.rssi=0;
		this.lat=0;
		this.lot=0;
		this.alt=0;
		this.accm=0;
	}
	
	public  Signal(String mac,String ssid,String authmode,
					Date Date,int channel,int rssi,double lat,
					double lot,double alt,double accm,String type) 
	{
		this.Date=Date;
		this.mac=mac;
		this.ssid=ssid;
		this.authmode=authmode;
		this.type=type;
		this.channel=channel;
		this.rssi=rssi;
		this.lat=lat;
		this.lot=lot;
		this.alt=alt;
		this.accm=accm;
	}
	
	public Signal(Signal s) //gets one wifi point and properties
	{
		this.Date=s.getDate();
		this.mac=s.getMac();
		this.ssid=s.getSsid();
		this.authmode=s.getAuthmode();
		this.type=s.getType();
		this.channel=s.getChannel();
		this.rssi=s.getRssi();
		this.lat=s.getLat();
		this.lot=s.getLot();
		this.alt=s.getAlt();
		this.accm=s.getAccm();
		this.id=s.getid();
	}
	

	public String getid() 
	{
		return id;
	}
	public void setId(String id) 
	{
		this.id = id;
	}
	public String getMac() 
	{
		return mac;
	}
	public void setMac(String mac) 
	{
		this.mac = mac;
	}
	public String getSsid() 
	{
		return ssid;
	}
	public void setSsid(String ssid)
	{
		this.ssid = ssid;
	}
	public String getAuthmode() 
	{
		return authmode;
	}
	public void setAuthmode(String authmode) 
	{
		this.authmode = authmode;
	}
	public String getType() 
	{
		return type;
	}
	public void setType(String type) 
	{
		this.type = type;
	}
	public Date getDate1() 
	{
		return Date;
		
	}
	public void setDate(Date Date) 
	{
		this.Date = Date;
	}
	public int getChannel() 
	{
		return channel;
	}
	public void setChannel(int channel) 
	{
		this.channel = channel;
	}
	public int getRssi() 
	{
		return rssi;
	}
	public void setRssi(int rssi) 
	{
		this.rssi = rssi;
	}
	public double getLat() 
	{
		return lat;
	}
	public void setLat(double lat)
	{
		this.lat = lat;
	}
	public double getLot()
	{
		return lot;
	}
	public void setLot(double lot) 
	{
		this.lot = lot;
	}
	public double getAlt()
	{
		return alt;
	}
	public void setAlt(double alt)
	{
		this.alt = alt;
	}
	public double getAccm() 
	{
		return accm;
	}
	public void setAccm(double accm) 
	{
		this.accm = accm;
	}
	public String toString()
	{
		return   "mac:"+mac +"\nssid:"+ ssid +"\nProtection:"+ authmode + "\nDate:"+Date + "\nFrequency:"+
				channel + "\nPower:"+ rssi +"\nType:"+type+"\nID:"+id ;
	}
}
