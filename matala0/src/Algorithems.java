
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

	public PointWith3D getWpoint(String MAC)//first algoritem
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
		double[] Warr=new double[ListOfSignals.size()];
		PointWith3D[] Points=new PointWith3D[ListOfMacs.size()];
		for(int i=0; i<ListOfSignals.size(); i++)
		{
			Warr[i]=1.0/(Math.pow(ListOfSignals.get(i).getSignal(), 2));
			double Lat=Double.parseDouble(ListOfMacs.get(ListOfSignals.get(i).getIndex()).get(0));
			double Lon=Double.parseDouble(ListOfMacs.get(ListOfSignals.get(i).getIndex()).get(1));
			double Alt=Double.parseDouble(ListOfMacs.get(ListOfSignals.get(i).getIndex()).get(2));
			PointWith3D point=new PointWith3D(Lat,Lon,Alt);
			Points[i]=point;
		}
		PointWith3D answer=theCenter(Points,Warr);
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

	