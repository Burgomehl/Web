package uebung4;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class XmlReaderBean {
	private Map<String, String> persons;
	private String searchedPerson;

	private XmlReaderBean() {
		persons = new HashMap<>();
		File f = new File("X:/WebProg2Ordner/WEB-INF/classes/Uebung3/persons.xml");
		Document doc = null;
		try {
			SAXBuilder builder = new SAXBuilder();
			doc = builder.build(f);
			Element root = doc.getRootElement();
			searchPersons(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static XmlReaderBean createInstance(){
		return new XmlReaderBean();
	}

	private void searchPersons(Element ele) {
		System.out.println(ele.getName());
		if (ele.getName().equals("person")) {
			List<Element> children = ele.getChildren();
			String vorname = "";
			String nachname = "";
			for (Element element : children) {
				if (element.getName().equals("firstname") || element.getName().equals("lastname")) {
					if (element.getName().equals("firstname")) {
						vorname = element.getValue();
					} else if (element.getName().equals("lastname")) {
						nachname = element.getValue();
					}
				} else if (element.getName().equals("children")) {
					searchPersons(element);
				}
			}
			if(nachname.isEmpty()){
				nachname = "*";
			}
			persons.put(vorname, nachname);
		} else {
			List<Element> child = ele.getChildren();
			for (Element element : child) {
				searchPersons(element);
			}
		}
	}

	public Map<String, String> getPersons() {
		return persons;
	}

	public String getSearchedPerson() {
		return searchedPerson;
	}

	public void setSearchedPerson(String searchedPerson) {
		this.searchedPerson = searchedPerson;
	}
}
