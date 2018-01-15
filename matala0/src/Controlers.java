import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Controlers
{

	public List<ArrayList<String>> listData;

	public Controlers(List<ArrayList<String>> data)
	{
		this.listData=data;
	}
	public Controlers()
	{
		this.listData=new ArrayList<ArrayList<String>>();
	}

	public void FilterByMinMaxLoc(double minLat,double maxLat,double minLon ,double maxLon,double minAlt,double maxAlt,boolean no)
	{
		List<ArrayList<String>> ans=new ArrayList<ArrayList<String>>();
		if(!no) // if no button is not clicked in gui
		{
			for(int i=0; i<this.listData.size(); i++)
			{
				double lat=Double.parseDouble(this.listData.get(i).get(0));
				double lon=Double.parseDouble(this.listData.get(i).get(1));
				double alt=Double.parseDouble(this.listData.get(i).get(2));
				if((lat>=minLat && lat<=maxLat) && 
						(lon>=minLon && lon<=maxLon) &&
						(alt>=minAlt && alt<=maxAlt))
				{
					ans.add(this.listData.get(i));
				}
			}
			this.listData=ans;
		}
		else // if no button is clicked in gui
		{
			for(int i=0; i<this.listData.size(); i++)
			{
				double lat=Double.parseDouble(this.listData.get(i).get(0));
				double lon=Double.parseDouble(this.listData.get(i).get(1));
				double alt=Double.parseDouble(this.listData.get(i).get(2));
				if((lat<minLat || lat>maxLat) || 
						(lon<=minLon || lon>=maxLon) ||
						(alt<=minAlt || alt>=maxAlt))
				{
					ans.add(this.listData.get(i));
				}
			}
			this.listData=ans;
		}
	}

	//filter the data by id

	public void FilterByID(String ID,boolean no)
	{
		List<ArrayList<String>> ans=new ArrayList<ArrayList<String>>();
		if(!no) // if no button is not clicked in gui
		{
			for(int i=0; i<this.listData.size(); i++)
			{
				if(this.listData.get(i).get(3).contains(ID))
				{
					ans.add(this.listData.get(i));
				}
			}
			this.listData=ans;
		}
		else // if no button is clicked in gui
		{
			for(int i=0; i<listData.size(); i++)
			{
				if(!(listData.get(i).get(3).contains(ID)))
				{
					ans.add(this.listData.get(i));
				}
			}
			this.listData=ans;
		}
	}

	/**
	 * This function filters by time
	*/
	public void FilterByMinMaxTime(String MinTime,String MaxTime,boolean not)
	{
		List<ArrayList<String>> ans=new ArrayList<ArrayList<String>>();
		LocalTime MinTimeR=LocalTime.of(Integer.parseInt(MinTime.substring(0,2)),Integer.parseInt(MinTime.substring(3,5)),Integer.parseInt(MinTime.substring(6)));
		LocalTime MaxTimeR=LocalTime.of(Integer.parseInt(MaxTime.substring(0,2)),Integer.parseInt(MaxTime.substring(3,5)),Integer.parseInt(MaxTime.substring(6)));
		if(!not) //if not button is not clicked in gui
		{
			for(int i=0; i<this.listData.size(); i++)
			{
				LocalTime datatime=LocalTime.of(Integer.parseInt(this.listData.get(i).get(4).substring(11,13)),Integer.parseInt(Data0.get(i).get(4).substring(14,16)),Integer.parseInt(Data0.get(i).get(4).substring(17,19)));
				if(datatime.isAfter(MinTimeR) && datatime.isBefore(MaxTimeR))
				{
					ans.add(this.listData.get(i));
				}
			}
			this.listData=ans;
		}
		else // if not button is clicked in gui
		{
			for(int i=0; i<this.listData.size(); i++)
			{
				LocalTime datatime=LocalTime.of(Integer.parseInt(this.listData.get(i).get(4).substring(11,13)),Integer.parseInt(listData.get(i).get(4).substring(14,16)),Integer.parseInt(listData.get(i).get(4).substring(17,19)));
				if(datatime.isAfter(MaxTimeR) || datatime.isBefore(MinTimeR))
				{
					ans.add(this.listData.get(i));
				}
			}
			this.listData=ans;
		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WriteToKML A1=new WriteToKML();
		List<ArrayList<String>> test=A1.inputheCSVfile();
		GuiFilters B1=new GuiFilters(test);
		//String mintime="16:20:00";
		//String maxtime="16:30:00";
		//B1.FilterByMinMaxTime(mintime, maxtime, false);
		String id="SHIELD";
		B1.FilterByID(id, false);
		//System.out.println(B1.Data0);
		System.out.println(test);
	}
	
	/**
	 * takes 2 lists and merges the data
	 *  Data1
	 *  Data2
	 *  merged List
	 */
	public static List<ArrayList<String>> MergeData(List<ArrayList<String>> listOfData1, List<ArrayList<String>> listOfData2)
	{
		List<ArrayList<String>> MergeList=new ArrayList<ArrayList<String>>();
		for(int i=0; i<listOfData1.size(); i++)
		{
			MergeList.add(listOfData1.get(i));
		}
		for(int j=0; j<listOfData2.size(); j++)
		{
			if(!(MergeList.contains(listOfData2.get(j))))
			{
				MergeList.add(listOfData2.get(j));
			}
		}
		return MergeList;
	}

}
	
	
	
	
	