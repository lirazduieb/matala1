import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class csvreader
{
	public static arrOfarrOf10Signals readCSV (String filesOfCsv) 
	throws IOException, NumberFormatException, ParseException 
	{// gets a file and reads it

		File file = new File(filesOfCsv);
		FilenameFilter filt = new FilenameFilter() 
		{
			public boolean accept(File d, String name) //gets a file and checks if csv or not.
			{
				return name.endsWith(".csv");
			}
		};
		arrOfarrOf10Signals arr=new arrOfarrOf10Signals();
		try {
			
			for (File file1 : file.listFiles(filt)) {
				check(file1,arr);
			}
		}
		finally {
			System.out.println("Read complete");
		}
		return arr;
	}
	
	private static Signal add(String []p) //adds a row to the table
	throws NumberFormatException, ParseException
	{
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		Signal temp= new Signal(p[0],p[1],p[2],format.parse(p[3]),Integer.parseInt(p[4]),
				Integer.parseInt(p[5]),Double.parseDouble(p[6]),
				Double.parseDouble(p[7]),Double.parseDouble(p[8]),
				Double.parseDouble(p[9]),p[10]);
		return temp;
	}

private static void check(File file,arrOfarrOf10Signals arr) // sorts the table by content(to an orginized table)
		throws NumberFormatException, ParseException, IOException
		{
			BufferedReader br = null;
			String csvSplit = ",";
			String line = "";
			String csvFile = file.toString();
			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();
			try
			{
				if(line!=null) 
				{
					String[] pow=line.split(csvSplit);
					String id="";
					if(pow.length==8)
					{
						id=pow[2];
						line = br.readLine();
						line = br.readLine();
						pow=line.split(csvSplit);
						Signal temp=add(pow);
						temp.setId(id);
						arr.add(temp);

					}
					if(pow.length>10)
					{	
						while ((line = br.readLine()) != null) {
							pow = line.split(csvSplit);
							Signal temp=add(pow);
							temp.setId(id);
							arr.add(temp);
						}
					}
				}
			}
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
			catch (Exception e) 
			{
		//		System.out.println(e);
				e.printStackTrace();
			}
			finally 
			{

				if (br != null) 
				{
					try 
					{
						br.close();
					} catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
			}		
		}
}