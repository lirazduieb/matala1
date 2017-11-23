import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class filter 
{
	
	
	public static arrOfarrOf10Signals FilterId(String name,arrOfarrOf10Signals s)
	{
		Signal[] temp=new Signal[10];
		int count=0;
		for(int i=0;i<s.getReal_size();i++)
		{
			count=0;
			temp=s.getarr()[i].getarr();
			for(int j=0;j<10;j++)
				if(temp[j]!=null)
				{
					if(temp[j].getid().equals(name))
					{
						temp[j]=null;
					}
				}
			for(int q=0;q<10;q++)
			{
				if(temp[q]==null) count++;
			}
			if(count==10) 
			{
				s.getarr()[i]=null;
			}
		}
		return nt;
	}
	/*
	 * Filter by date to date with erasing if true
	 * 
	 */
	public static arrOfarrOf10Signals FilterByDate(Date x,Date y,arrOfarrOf10Signals nt)
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		WIFI[] temp=new WIFI[10];
		int count=0;
		for(int i=0;i<nt.getReal_size();i++)
		{
			count=0;
			temp=nt.getLine()[i].getLine();
			for(int j=0;j<10;j++)
				if(temp[j]!=null)
				{
					if(temp[j].getFirtseen().after(y)||temp[j].getFirtseen().before(x))
					{
						temp[j]=null;
					}
				}
			for(int q=0;q<10;q++)
			{
				if(temp[q]==null) count++;
			}
			if(count==10) 
			{
				nt.getLine()[i]=null;
			}
		}
		System.out.println();

		return nt;
	}
	
	public static arrOfarrOf10Signals FilterByRadius(double radius,double lat,double lot,arrOfarrOf10Signals n)
	{
		Signal[] temp=new Signal[10];
		int count=0;
		for(int i=0;i<n.getReal_size();i++)
		{
			count=0;
			temp=n.getarr()[i].getarr();
			for(int j=0;j<10;j++)
				if(temp[j]!=null)
				{
					if((temp[j].getLot()>lot+radius||temp[j].getLot()<lot-radius)||
					   (temp[j].getLat()>lat+radius||temp[j].getLat()<lat-radius))
					{
						temp[j]=null;
					}
				}
			for(int q=0;q<10;q++)
			{
				if(temp[q]==null) count++;
			}
			if(count==10) 
			{
				nt.getLine()[i]=null;
			}
		}
		System.out.println();

		return nt;
	}

}

