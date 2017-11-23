

public class arryOfSignal 
{

	public void setarr(Signal[] arr) 
	{
		this.arr=arr;
	}
	
	private Signal [] arr;
	private final int INIT_SIZE=10;
	private int real_size;
	

	public arryOfSignal()
	{
		real_size=0;
		arr=new Signal[INIT_SIZE];
		
	}
	
	public arryOfSignal (Signal s)
	{
		if(arr==null)
		{
			arr=new Signal[INIT_SIZE];
		}
		
		
		if(real_size==arr.length)
		{
			int i=checkSignal(s);
			if(i!=-1)
			{
				arr[i]=new Signal(s);	
			}
		}
		arr[real_size++]=new Signal(s);
	}
	
	
	
//adds to the array only by top 10 signals.
	public void add(Signal s)
	{
		if(arr==null) arr=new Signal [INIT_SIZE];
		if(real_size==arr.length)
		{
			int i=checkSignal(s);
			if(i!=-1)
			{
				arr[i]=new Signal(s);	
			}
		}
		else arr[real_size++]=new Signal(s);
	}
	
	public int getReal_size() 
	{
		return real_size;
	}
	
	public Signal[] getarr() 
	{
		return arr;
	}
	
	
	//checks if the signal is stronger than the previous one.
	private int checkSignal(Signal s)
	{
		int max=arr[0].getRssi();
		int k=0;
		
		for(int i=1;i<real_size;i++)
		{
			if(arr[i].getRssi()>max)
			{
				max=arr[i].getRssi();
				k=i;
			}
		}
		if(max>s.getRssi()) return k;
		else return -1;
	}

}
