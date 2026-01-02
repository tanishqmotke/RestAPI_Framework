package resources;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;

public class TestDataBuild {

	public AddPlace addPlacePayLoad() {
		
		AddPlace p = new AddPlace();
		p.setAccuracy(40);
		p.setAddress("29, Default address");
		p.setLanguage("English");
		p.setPhone_number("34234234234");
		p.setWebsite("www.google.com");
		p.setName("FrontLine");
		List<String> MyList = new ArrayList<String>();
		MyList.add("shoe park");
		MyList.add("park");
		p.setTypes(MyList);
		Location l = new Location();
		l.setLat(-342434234);
		l.setLng(341312);
		p.setLocation(l);
		return p;
	}
}
