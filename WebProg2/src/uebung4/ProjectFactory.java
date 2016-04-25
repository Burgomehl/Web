package uebung4;

public class ProjectFactory {
	public static Person getPerson(){
		return new Person();
	}
	
	public static XmlReaderBean getReader(){
		return new XmlReaderBean();
	}
}
