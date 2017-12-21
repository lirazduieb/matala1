import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.KmlFactory;
import de.micromata.opengis.kml.v_2_2_0.Placemark;




public class WriteToKML 
{
	public String csv46columsfile;
	public WriteToKML()
	{
		this.csv46columsfile="";
	}
	
	public static List<ArrayList<String>> inputheCSVfile(WriteToKML writeKml)
	{
		List<ArrayList<String>> csv=new ArrayList<ArrayList<String>>();

		String csvFile =writeKml.csv46columsfile;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try 
		{

			br = new BufferedReader(new FileReader(csvFile));

			while ((line = br.readLine()) != null) 
			{

				String[] input = line.split(cvsSplitBy);

				ArrayList<String> inList=new ArrayList<String>();

				for(int i=0; i<input.length; i++)
				{
					inList.add(input[i]);
				}
				csv.add(inList);
			}	
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		finally
		{
			if (br != null)
			{
				try 
				{
					br.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return csv;
	}

	

	/**
	 filter:
		 * id=0

		 * time=1

		 * Location filter enter string- "Lat,Lon" and a number(not 1 or 0)

		 * 

		 */

	

	public static List<ArrayList<String>> filterthelist(List<ArrayList<String>> list, String filterby, int num )

	{
		Predicate<ArrayList<String>> thefilter;
		if(num==0)//  ID
		{
			thefilter=s -> s.get(3).contains(filterby);
		}
		else 
			if(num==1)//Time
		{
			thefilter=s -> s.get(4).contains(filterby);
		}
		else //  Location
		{
			thefilter=s -> s.get(0).contains(filterby) & s.get(1).contains(filterby);
		}
		List<ArrayList<String>> filteredStrings=filterwithinterface(list ,thefilter);
		return filteredStrings;

	}

	


	public static void KmlWriting(List<ArrayList<String>> listOfFilter) throws FileNotFoundException

	{

		final Kml kmlfile=new Kml();
		Document doc=kmlfile.createAndSetDocument().withName("points");
		for(int i=0; i<listOfFilter.size(); i++)
		{
			String Location=listOfFilter.get(i).get(1)+","+listOfFilter.get(i).get(0);
			String time=listOfFilter.get(i).get(4);
			Timestamp ts=Timestamp.valueOf(time);
			Placemark t=KmlFactory.createPlacemark();
			t.createAndSetTimeStamp().addToTimeStampSimpleExtension(ts);
			doc.createAndAddPlacemark().withName("point"+i).withOpen(Boolean.TRUE).withTimePrimitive(t.getTimePrimitive())
			.createAndSetPoint().addToCoordinates(Location);

		}

		kmlfile.marshal(new File("C:\\Users\\emotz\\Desktop\\OutPut\\KMLoutputID.kml"));

	}



//the code above has taken from: labs.micromata.de/projects/jak/quickstart.html and stackoverflow.com/questions/12701364/how-to-mark-multiple-coordinates-in-kml-using-java	



	public static void main(String[] args) throws FileNotFoundException
	{
		WriteToKML testTheKml=new WriteToKML();

		List<ArrayList<String>> arryList=new ArrayList<ArrayList<String>>();

		arryList=inputheCSVfile(testTheKml);

		List<ArrayList<String>> afterfilter=new ArrayList<ArrayList<String>>();

		

		afterfilter=filterthelist(arryList,"toshiba",3);

		KmlWriting(afterfilter);
	}

	//filter with the interface

	public static List<ArrayList<String>> filterwithinterface(List<ArrayList<String>> strings, Predicate<ArrayList<String>> condition)
	{

		List<ArrayList<String>> op=new ArrayList<ArrayList<String>>();
		for(int i=0; i<strings.size(); i++)
		{
			if(condition.test(strings.get(i)))
			{
				op.add(strings.get(i));
			}
		}
		return op;
	}

	

}