import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;



public class WriteToCsv // * this code has taken from https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/

{

	public File f;
	public WriteToCsv()
	{

		this.f=new File("C:\\Users\\liraz\\eclipse-workspace\\oop\\src");
	}
	    // create a list of signals from one file

	static ArrayList<String> IDlist=new ArrayList<String>();

	static ArrayList<Integer> IDsplit=new ArrayList<Integer>();

	public static List<ArrayList<String>> createlistofdata(WriteToCsv fcsv) throws IOException
	{
		List<ArrayList<String>> inputCSV=new ArrayList<ArrayList<String>>();
		File f = fcsv.f;



		FilenameFilter textFilter = new FilenameFilter() 
		{

			public boolean accept(File dir, String name) 
			{

				String lowercaseName = name.toLowerCase();

				if (lowercaseName.endsWith(".csv")) 
				{ // check if csv 

					return true;                      

				}
				else 
				{

					return false;

				}

			}

		};



		File[] files = f.listFiles(textFilter);

		for (File file : files) 
		{



			if (file.isDirectory()) 
			{

				//System.out.print("directory:");

			} else
			{

				//System.out.print("file:");

			}

			//System.out.println(file.getCanonicalPath());


			String csvfile=file.getCanonicalPath();

			BufferedReader br = null;

			String line = "";

			String cvsSplitBy = ",";



			try {



				int count=0;

				br = new BufferedReader(new FileReader(csvfile));

				while ((line = br.readLine()) != null)

				{

					// use comma as separator

					String[] Getline = line.split(cvsSplitBy);

					if(count==0)

					{

						

						String ID=Getline[2];

						IDlist.add(ID);

						IDsplit.add(inputCSV.size());

						count++;

					}

					else if(count==1)

					{

						//skip titles line

						count++;

					}

					else

					{
						ArrayList<String> inTheList=new ArrayList<String>();
						for(int i=0; i<=10; i++)
						{
							inTheList.add(Getline[i]);
						}
						inputCSV.add(inTheList);
					}
				}

			}
			catch (FileNotFoundException e) 
			{

				e.printStackTrace();

			} 
			catch (IOException e)
			{

				e.printStackTrace();

			} finally {

				if (br != null) {

					try {

						br.close();

					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
			}
		}
		bubbleSort(inputCSV);//sort list signals
		return inputCSV;
	}
	static public HashMap<Location,ArrayList<Signal>> createMap(List<ArrayList<String>> inputCSV)// this code has taken from https://www.mkyong.com/java/how-to-export-data-to-csv-file-java/

	{

		HashMap<Location,ArrayList<Signal>> map=new HashMap<Location, ArrayList<Signal>>();

		int counttheid=0;

		int maxsize=IDsplit.size();

		for(int i=0; i<inputCSV.size(); i++)

		{

			double Lat=Double.parseDouble(inputCSV.get(i).get(6));

			double Lon=Double.parseDouble(inputCSV.get(i).get(7));

			double Alt=Double.parseDouble(inputCSV.get(i).get(8));

			String ID=IDlist.get(counttheid);

			if(counttheid<maxsize-1)

			{

				if(i==IDsplit.get(counttheid+1) & i!=0)
				{
					
					counttheid++;

					ID=IDlist.get(counttheid);

				}

			}

			String Time=inputCSV.get(i).get(3);

			Location Loc=new Location(Lat,Lon,Alt,ID,Time);

			ArrayList<Signal> signalData=new ArrayList<Signal>();

			int countsignals=1;

			for(int k=0; k<inputCSV.size() & countsignals<=10; k++)//take the top 10 

			{

				double Lat0=Double.parseDouble(inputCSV.get(k).get(6));

				double Lon0=Double.parseDouble(inputCSV.get(k).get(7));

				double Alt0=Double.parseDouble(inputCSV.get(k).get(8));

				String Time0=inputCSV.get(k).get(3);

				Location temp=new Location(Lat0,Lon0,Alt0,ID,Time0);

				if(Loc.isequalocation(temp)==true & countsignals!=11 )

				{

					String SSID=inputCSV.get(k).get(1);

					String MAC=inputCSV.get(k).get(0);

					int Frequency=Integer.parseInt(inputCSV.get(k).get(4));

					int Signal=Integer.parseInt(inputCSV.get(k).get(5));
					Signal W=new Signal(SSID,MAC,Frequency,Signal);
					signalData.add(W);
					countsignals++;
				}
			}
			map.put(Loc, signalData);

		}

		return map;
	}
	
	//write to 46 columes
	static public void writethecsvtable(HashMap<Location,ArrayList<Signal>> s) throws IOException//this code has taken from  http://www.avajava.com/tutorials/lessons/how-do-i-use-a-filenamefilter-to-display-a-subset-of-files-in-a-directory.html;jsessionid=C8740187DF79764CE4DAD895FD5078F0


	{

		FileWriter writethecsv = new FileWriter("C:\\Users\\liraz\\Desktop\\csvfiles");

		List<String> titlesofthefile= new ArrayList<>();
		
		titlesofthefile.add("Lat");
		titlesofthefile.add("Long");
		titlesofthefile.add("Alt");
		titlesofthefile.add("ID");
		titlesofthefile.add("Time");

		for(int i=1; i<=10; i++)
		{
			titlesofthefile.add("SSID"+i);
			titlesofthefile.add("MAC"+i);
			titlesofthefile.add("Frequency"+i);
			titlesofthefile.add("Signal"+i);
		}

		String collectitles= titlesofthefile.stream().collect(Collectors.joining(","));
		writethecsv.write(collectitles);
		writethecsv.write("\n");
		for(Entry<Location, ArrayList<Signal>> enter : s.entrySet())
		{
			String collectalldata=enter.getKey()+","+enter.getValue();
			writethecsv.write(collectalldata);
			writethecsv.write("\n");
		}

		writethecsv.close();
	}
	public static void main(String[] args) throws IOException 
	{
		WriteToCsv write=new WriteToCsv();
		List<ArrayList<String>> writeCsv=new ArrayList<ArrayList<String>>();
		writeCsv=createlistofdata(write);
		HashMap<Location,ArrayList<Signal>> newmap=new HashMap<Location, ArrayList<Signal>>();
		newmap=createMap(writeCsv);
		writethecsvtable(newmap);
	}
	//sort list 
	static void bubbleSort(List<ArrayList<String>> arr)
	{  
		int arrsize = arr.size();  
		ArrayList<String> temp=new ArrayList<String>();  
		for(int i=0; i < arrsize; i++)
		{  
			for(int j=1; j < (arrsize-i); j++)
			{ 
				int num1=Integer.parseInt(arr.get(j-1).get(5));
				int num2=Integer.parseInt(arr.get(j).get(5));
				if(num1 < num2)
				{   
					temp = arr.get(j-1); 
					arr.set(j-1, arr.get(j));  
					arr.set(j, temp);
				}  
			}  
		}  
	}
}
