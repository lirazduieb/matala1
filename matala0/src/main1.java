import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;

public class main1 
{
	public static void main(String[] args) 
			throws NumberFormatException, IOException, ParseException, ParserConfigurationException 
	{
		arrOfarrOf10Signals network=csvreader.readCSV("C:\\Users\\liraz\\Desktop");
		arrOfarrOf10Signals networks = null;
		network.writetoCSV("C:\\Users\\liraz\\Desktop\\");
		
		
		
		
		//network.writeKML("C:\\\\Users\\\\liraz\\\\Desktop\\\\c.kml");
		//System.out.println();
	}
}
