import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

class signalTest {

	@Test
	void test() {
		/*
		 * public  signal(String mac,String ssid,String authmode,
					Date Date,int channel,int rssi,double lat,
					double lot,double alt,double accm,String type) 
	{
		 * 
		 * 
		 */
		String mac = "10:20:30:40:50:60";
		String ssid = "my_router";
		String auto = "do_delete_this_field";
		Date now = new Date();
		int ch = 13;
		int rssi = -45;
		double lat = 32.103, lon = 35.208, alt = 682;
		double acc = 123;
		String type = "aa";
		Signal s = new Signal (mac,ssid, auto, now, ch, rssi, lat,lon, alt, acc, type);
		Date d1 = s.getDate();
		if(!d1.equals(now)) {
			fail("Date is not working well!!");
		}
	}

}
