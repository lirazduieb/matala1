package matala2;


import java.util.List;

//create a class with signal and index of a point

public class SignalWithIndex
{

		public int Signal;
		public int Index;

		public SignalWithIndex (int signal, int index)
		{
			this.Signal=signal;
			this.Index=index;
		}
		public String toString() 
		{
			return "SignalWithIndex [Signal=" + Signal + ", Index=" + Index + "]";
		}

		public int getSignal() 
		{
			return Signal;
		}
		public void setSignal(int signal) 
		{
			Signal = signal;
		}
		public int getIndex() 
		{
			return Index;
		}

		public void setIndex(int index) 
		{
			Index = index;
		}
		
        //sort the signals in arr of SignalWithIndex objects
		
		public void S_IbubbleSort(List<SignalWithIndex> arr)
		{  
			int arrsize= arr.size(); 
			SignalWithIndex temp;
			for(int i=0; i <arrsize; i++)
			{  
				for(int j=1; j < (arrsize-i); j++)
				{ 
					int num1=(arr.get(j-1).Signal);
					int num2=(arr.get(j).Signal);
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

	
	
	

