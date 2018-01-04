







public class ListOfMacSignals
{
		public String Mac;
		public int Signal;
		
		public ListOfMacSignals(String Mac,int Signal)
		{
			this.Mac=Mac;
			this.Signal=Signal;
		}

		//if algorithem 1 is not working
		
		public ListOfMacSignals(String Mac)

		{
			this.Mac=Mac;
			this.Signal=-360000;
		}
		public String getMac() 
		{
			return Mac;
		}
		public void setMac(String mac) 
		{
			Mac = mac;
		}
		public int getSignal() 
		{
			return Signal;
		}
		public void setSignal(int signal)
		{
			Signal = signal;
		}
		public String toString() 
		{
			return "ListOfMacSignals [Mac=" + Mac + ", Signal=" + Signal + "]";
		}

	}


