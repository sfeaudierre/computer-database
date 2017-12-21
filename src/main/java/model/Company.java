package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name="company")
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
