package model;

public class Company {
	
	private int id;
	private String name;
	
		public Company() {}
	
		public Company(int id, String name) {
			this.id = id;
			this.name = name;
		}
		
		
		public int getId() {
			return id;
		}
		public String getNom() {
			return name;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		public void setNom(String name) {
			this.name = name;
		}
}
