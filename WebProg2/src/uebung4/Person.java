package uebung4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person {
	private String surename;
	private String lastname;
	private String childrenCount;
	private String residence;
	
	public Map<String, String> getPersonmap(){
		Map<String, String> map = new HashMap<>();
		map.put("Surename", surename);
		map.put("lastname", lastname);
		map.put("Residence", residence);
		map.put("Amount of Children", childrenCount);
		return map;
	}
	
	public static Person createInstance(){
		Person p = new Person();
		p.setChildrenCount("10");
		p.setLastname("Byl");
		p.setSurename("Benjamin");
		p.setResidence("An der Mühle");
		return p;
	}
	
	public String getSurename() {
		return surename;
	}
	public void setSurename(String surename) {
		this.surename = surename;
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

	public String getChildrenCount() {
		return childrenCount;
	}

	public void setChildrenCount(String childrenCount) {
		this.childrenCount = childrenCount;
	}
}
