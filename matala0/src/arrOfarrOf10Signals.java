import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class arrOfarrOf10Signals
{
	private arryOfSignal[] arr;
	private int real_size;
	private final int INIT_SIZE=10, RESIZE=10;

	public arrOfarrOf10Signals()
	{
		arr=new arryOfSignal[INIT_SIZE];
		real_size=0;
	}

	private boolean checDate(Signal s) {//checks the date
		if(arr[0]==null)
		{
			return false;
		}

		else
			if(arr[real_size-1].getarr()[0].getDate1().equals(s.getDate())) 
			{
				return true;
			}
		return false;
	}

	
	public void add(Signal s)//adds a point to an array of arrays.
	{
		if(real_size==arr.length)
		{
			resize();
		}
		if(checDate(s))//if the dates are even we put them in the same array
		{
			arr[real_size-1].add(s);
		}
		else
			arr[real_size++]=new arryOfSignal(s);
	}

	
	private void resize()
	{//changes the size of the array
		arryOfSignal temp[]=new arryOfSignal[arr.length+RESIZE];
		for(int i=0;i<arr.length;i++)
		{
			temp[i]=arr[i];
		}
		arr=temp;

	}

	private boolean check(Signal s)// if the line is empty then we delete it
	{
		if(arr[0]==null) 
		{
			return false;
		}
		else if(arr[real_size-1].getarr()[0].getDate().equals(s.getDate()))
		{
			return true;
		}
		return false;
	}

	
	
	public void writetoCSV (String folder) //adds up the array to one csv file
			throws FileNotFoundException, IOException, ParserConfigurationException//מדפיס

	{
		if(real_size!=0) 
		{
			PrintWriter p = new PrintWriter(new File(folder));
			StringBuilder s = new StringBuilder();
			s.append("Time,ID,LAT,LON,ALT,#WiFi networks,");
			try 
			{
				for(int i=1;i<=10;i++)
				{
					s.append("SSID"+ i +",Mac"+ i +",Frequncy"+ i +",Signal"+ i +"," );
				}
				s.append("\n");
				int max=real_size;
				Signal[] temp=new Signal[10];
				int k=0;
				for(int i=0;i<max;i++)
				{
					k=0;
					if(arr[i]!=null)
					{
						temp=arr[i].getarr();
						if(temp[k]!=null) {
							s.append(temp[k].getDate()+","+temp[k].getid()+","+temp[k].getLat()+","+
									temp[k].getLot()+","+temp[k].getAlt()+","+temp.length+",");
							for(;k<temp.length&&temp[k]!=null;k++)
							{
								if(temp[k]!=null) 
								{
									String freq;
									if(temp[k].getChannel()==0)
									{
										freq="gsm";
									}
									else if(temp[k].getChannel()>35)
										freq= "5 GHZ";
									else freq= "2.4 GHZ";
									s.append(temp[k].getSsid()+","+temp[k].getMac()+","+freq+","+temp[k].getRssi()+",");
								}
							}
						}
						s.append("\n");
					}
				}
			}
			catch (Exception e) 
			{
				System.out.println(e);
			}
			finally 
			{
				System.out.println("Write file complete");
			}
			p.append(s.toString());
			p.close();
		}
		else System.out.println("errorrrr!");
	}
}

	






//*********************************************************************************************
	
	//kml:
	
	/*public boolean writeKML(String output) throws IOException 
	{
		
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document document = docBuilder.newDocument();
			Element root = document.createElement("kml");
			Attr attr = document.createAttribute("xmlns");
			attr.setValue("http://www.opengis.net/kml/2.2");
			document.appendChild(root);
			root.setAttributeNode(attr);
			Element Document = document.createElement("Document");
			root.appendChild(Document);
			Element name = document.createElement("name");
			name.appendChild(document.createTextNode("my"));
			Document.appendChild(name);
			Element Folder = document.createElement("Folder");
			Document.appendChild(Folder);
			Element fname = document.createElement("name");
			fname.appendChild(document.createTextNode("WiFi Networks"));
			Folder.appendChild(fname);
			int count = 0;
			signal[] temp=new signal[10];
			for (int i = 0 ; i < real_size; i++) 
			{
				if(arr[i]!=null) {
					temp=arr[i].getarr();
					for(int j=0;j<temp.length;j++)
					{
						if(temp[j]!=null) {
							Element placemark = document.createElement("Placemark");
							Attr at = document.createAttribute("id");
							at.setValue(count++ + "A");
							placemark.setAttributeNode(at);
							Folder.appendChild(placemark);
							Element tname = document.createElement("name");
							tname.appendChild(document.createTextNode(temp[j].getSsid()));
							placemark.appendChild(tname);
							Element description = document.createElement("description");
							String desc = temp[j].toString();
							description.appendChild(document.createTextNode(desc)); 
							placemark.appendChild(description);
							Element point = document.createElement("Point");
							Element coordinates = document.createElement("coordinates");
							coordinates.appendChild(document.createTextNode(Double.toString(temp[j].getLot())+","
									+Double.toString(temp[j].getLat())));
							point.appendChild(coordinates);
							placemark.appendChild(point);
						}
					}
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource domSource = new DOMSource(document);
					StreamResult streamResult = new StreamResult(new File(output));
					transformer.transform(domSource, streamResult);
				} 
			}
		}
		catch (ParserConfigurationException pce) 
		{
			pce.printStackTrace();
			return false;
		} catch (TransformerException tfe) 
		{
			tfe.printStackTrace();
			return false;
		} catch (Exception e)
		{
			System.out.println(e);
			return false;

		}
		finally
		{
			System.out.println("kml file was created");
		}
		return false;
	}

	public void setReal_size(int real_size)
	{
		this.real_size = real_size;
	}

	public arryOfSignal[] getarr() 
	{
		return arr;
	}
	public void setLine(arryOfSignal[] line) 
	{
		this.arr = arr;
	}

	public int getReal_size() 
	{
		return real_size;
	}
*/
//************************************************************************************************
	
	