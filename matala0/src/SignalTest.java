import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.Before;

import org.junit.jupiter.api.Test;



class SignalTest 
{

	Signal wifi;

	@Before

	public void init() 
	{

		wifi= new Signal("[HOT123","c0:ac:54:f6:c5:4b",1,-87);

	}



	@Test

	void testtoString() {

		init();

		String e= "" + "[HOT123" + "," + "c0:ac:54:f6:c5:4b" + "," + "1" + "," + "-87" + "";

		assertThat(e, is(wifi.toString()));

	}
}




 