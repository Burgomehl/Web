package uebung4_reloaded;

public class PersonsToWork {
	private Persons data;
	
	public PersonsToWork() {
		data = new Persons();
	}
	
	public Person getPersonByName(String firstname){
		return data.getPerson().stream().filter(a -> firstname.equals(a.getFirstname())).findFirst().get();
	}
	
	public Persons getPersons(){
		return data;
	}
}
