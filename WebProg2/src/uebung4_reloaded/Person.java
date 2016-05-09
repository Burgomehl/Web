package uebung4_reloaded;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@XmlAttribute
	private String id;
	
	private String firstname;
	private String lastname;
	private String residence;
	
	private List<Person> children;
	
	public Person(){
		
	}

	public Person(String id, String firstname, String lastname, String residence, List<Person> children) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.residence = residence;
		this.children = children;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public List<Person> getChildren() {
		return children;
	}

	public void setChildren(List<Person> children) {
		this.children = children;
	}
}
