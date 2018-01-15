import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.KmlFactory;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

public class Algorithems {

	public List<ArrayList<String>> Data;

	public Algorithems()
	{
		WriteToKML test=new WriteToKML();
		this.Data=test.inputheCSVfile();
	}
//calculates the signal point location with the mac address

	public PointWith3D getpoints(String MAC)//first algoritem
	{
		List<ArrayList<String>> ListOfMacs=new ArrayList<ArrayList<String>>();
		List<SignalWithIndex> ListOfSignals=new ArrayList<SignalWithIndex>();
		for(int i=0; i<Data.size(); i++)
		{
			for(int k=6; k<Data.get(i).size(); k+=4)
			{
				if(MAC.equals(Data.get(i).get(k)))
				{
					if(!ListOfMacs.contains(Data.get(i)))
					{
						ListOfMacs.add(Data.get(i));
						int signal;
						if(Data.get(i).get(k+2).length()==4)
						{
							String temp=Data.get(i).get(k+2);
							String end=temp.substring(0,3);
							signal=Integer.parseInt(end);
						}
						else
						{
							signal=Integer.parseInt(Data.get(i).get(k+2));
						}
						SignalWithIndex temp=new SignalWithIndex(signal,ListOfMacs.size()-1);
						ListOfSignals.add(temp);
					}
				}
			}
		}
		if(ListOfMacs.isEmpty())
		{
			PointWith3D ans=new PointWith3D();
			return ans;
		}
		SignalbubbleSort(ListOfSignals);
		double[] arr=new double[ListOfSignals.size()];
		PointWith3D[] Points=new PointWith3D[ListOfMacs.size()];
		for(int i=0; i<ListOfSignals.size(); i++)
		{
			arr[i]=1.0/(Math.pow(ListOfSignals.get(i).getSignal(), 2));
			double Lat=Double.parseDouble(ListOfMacs.get(ListOfSignals.get(i).getIndex()).get(0));
			double Lon=Double.parseDouble(ListOfMacs.get(ListOfSignals.get(i).getIndex()).get(1));
			double Alt=Double.parseDouble(ListOfMacs.get(ListOfSignals.get(i).getIndex()).get(2));
			PointWith3D point=new PointWith3D();
			Points[i]=point;
		}
		PointWith3D answer=theCenter(Points,arr);
		return answer;
	}
//the center of all signals in array
	
	public PointWith3D theCenter(PointWith3D[] points, double[] arr)
	{
		PointWith3D theCenter=new PointWith3D();
		double Lat=0;
		double Latetude=0;
		for(int i=0; i<arr.length; i++)
		{
			Lat+=(points[i].getLat())*(arr[i]);
			Latetude+=arr[i];
		}
		theCenter.Lat=Lat/Latetude;
		double Lon=0;
		double Lontetude=0;
		for(int i=0; i<arr.length; i++)
		{
			Lon+=(points[i].getLon())*(arr[i]);
			Lontetude+=arr[i];
		}
		theCenter.Lon=Lon/Lontetude;
		double Alt=0;
		double Altetude=0;
		for(int i=0; i<arr.length; i++)
		{
			Alt+=(points[i].getAlt())*(arr[i]);
			Altetude+=arr[i];
		}
		theCenter.Alt=Alt/Altetude;
		return theCenter;
	}

	//calculate the point with the second algoritem.

	public PointWith3D GetWlocation(ListOfMacSignals[] arr) // second algoritem
	{
		List<ArrayList<ListOfMacSignals>> listMacs=new ArrayList<ArrayList<ListOfMacSignals>>();
		for(int k=0; k<arr.length; k++)
		{
			ArrayList<ListOfMacSignals> inner=new ArrayList<ListOfMacSignals>();
			for(int i=0; i<this.Data.size(); i++)
			{
				for(int j=6; j<this.Data.get(i).size(); j+=4)
				{
					if(Data.get(i).get(j).equals(arr[k].getMac()))
					{
						String mac=Data.get(i).get(j);
						int signal;
						if(Data.get(i).get(j+2).length()==4)
						{
							String temp=Data.get(i).get(j+2);
							String finish=temp.substring(0,3);
							signal=Integer.parseInt(finish);
						}
						else
						{
							signal=Integer.parseInt(Data.get(i).get(j+2));
						}
						ListOfMacSignals temp=new ListOfMacSignals(mac,signal);
						inner.add(temp);
					}

				}
			}
			if(inner.isEmpty())
			{
				ListOfMacSignals temp0=new ListOfMacSignals(arr[k].getMac());
				inner.add(temp0);
			}
			listMacs.add(inner);
		}
		List<Integer> indexes=new ArrayList<Integer>();
		for(int i=0; i<listMacs.size(); i++)
		{
		    int index=indexoftheclosemacsignal(arr[i].getSignal(),listMacs.get(i));
		    indexes.add(index);
		}
		String[] Macs=new String[arr.length];
		double[] WSignals=new double[arr.length];
		for(int i=0; i<listMacs.size(); i++)
		{
			Macs[i]=listMacs.get(i).get(indexes.get(i)).getMac();
			WSignals[i]=1.0/(Math.pow(listMacs.get(i).get(indexes.get(i)).getSignal(), 2));
		}
		PointWith3D[] points=new PointWith3D[arr.length];
		for(int i=0; i<points.length; i++)
		{
			points[i]=getpoints(Macs[i]);
		}
		PointWith3D answer=theCenter(points,WSignals);
		return answer;
	}
	
	public void MakeaKmlFile(PointWith3D[] arr) throws FileNotFoundException
	{
		final Kml kml=new Kml();
		Document doc=kml.createAndSetDocument().withName("points");
		for(int i=0; i<arr.length; i++)
		{
			String Location=arr[i].getLon()+","+arr[i].getLat();
			Placemark p=KmlFactory.createPlacemark();
			doc.createAndAddPlacemark().withName("point"+i).withOpen(Boolean.TRUE).withTimePrimitive(p.getTimePrimitive())
			.createAndSetPoint().addToCoordinates(Location);
		}
		kml.marshal(new File(""));
	}




	public static void SignalbubbleSort(List<SignalWithIndex> arr)
	{  
		int a = arr.size(); 
		SignalWithIndex temp;
		for(int i=0; i < a; i++)
		{  
			for(int j=1; j < (a-i); j++)
			{ 
				int a1=(arr.get(j-1).Signal);
				int a2=(arr.get(j).Signal);
				if(a1 < a2){  
					//swap  
					temp = arr.get(j-1); 
					arr.set(j-1, arr.get(j));  
					arr.set(j, temp);
				}  

			}  
		}  
	}


	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
	//	Algorithems A1=new Algorithems();
		//Point3D MacLocation=A1.getWpoint("0a:8d:cb:6e:71:6d",3);
		//System.out.println(MacLocation);	
	//	ListOfMacSignals[] arr=new ListOfMacSignals[3];
	//	arr[0]=new ListOfMacSignals("4c:60:de:d1:da:80",-70);
	//	arr[1]=new ListOfMacSignals("6c:b0:ce:e8:2a:b0",-75);
	//	arr[2]=new ListOfMacSignals("c4:12:f5:83:aa:0c",-80);
	//	Point3D test=A1.GetWlocation(arr);
	//	System.out.println(test);
	//	Point3D[] arr0= {test};
	//	A1.MakeKml(arr0);  
		
		/**
		 * boaz test for algo1
		 */
		Algorithems B1=new Algorithems();
		Point3D line1=B1.getWpoint("b2:6c:ac:9f:f1:c5");
		//System.out.println(line1);
		ListOfMacSignals[] arr1=new ListOfMacSignals[3];
		arr1[0]=new ListOfMacSignals("3c:52:82:ef:a4:8b",-60);
		arr1[1]=new ListOfMacSignals("24:79:2a:2b:07:bc",-63);
		arr1[2]=new ListOfMacSignals("24:79:2a:ab:07:b7",-67);
		Point3D ans=B1.GetWlocation(arr1);
		System.out.println(ans);
		
		// localtime and localdate very important!
	}

	

	/**
	 * this function gets an ArrayList with all of the ListOfMacSignals and returns the closest ListOfMacSignals object according to the signal 
	 * @param signal the signal we want
	 * @param list the list with all of the ListOfMacSignals
	 * @return the closets ListOfMacSignals object
	 */
	public static int indexoftheclosemacsignal(int signal,ArrayList<ListOfMacSignals> list)
	{
		int index=-1;
		int min=Integer.MAX_VALUE;
		int maxmin=Integer.MAX_VALUE;
		for(int i=0; i<list.size(); i++)
		{
			min=Math.abs(list.get(i).getSignal()-signal);
			if(min<maxmin)
			{
				maxmin=min;
				index=i;
			}
		}
		return index;
	}

	



}

	
	
	
	
	