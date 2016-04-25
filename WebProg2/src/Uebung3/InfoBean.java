package Uebung3;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class InfoBean {
	private Map<String, String> propertiMap;
	public InfoBean() {
		propertiMap = new HashMap<>();
		Properties properties = System.getProperties();
		Enumeration<?> propertyNames = properties.propertyNames();
		while(propertyNames.hasMoreElements()){
			String nextElement = (String) propertyNames.nextElement();
			String property = properties.getProperty(nextElement);
			propertiMap.put(nextElement, property);
			System.out.println(nextElement+" "+property);
		}
	}
	public Map<String, String> getPropertimap() {
		return propertiMap;
	}
}
