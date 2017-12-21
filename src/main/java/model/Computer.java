package model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="computer")
public class Computer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private java.sql.Date introduced;
	private java.sql.Date discontinued;
	@ManyToOne
	private Company company;
	
		public Computer() {}
	
		public Computer(int id, String name, Date introduced, Date discontinued, Company company) {
			
			this.id = id;
			this.name = name;
			this.introduced = introduced;
			this.discontinued = discontinued;
			this.company = company;
		}

		
		public int getId() {
			return id;
		}
		public String getNom() {
			return name;
		}
		public Date getIntroduced() {
			return introduced;
		}
		public Date getDiscontinued() {
			return discontinued;
		}
		public Company getCompany() {
			return company;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		public void setNom(String name) {
			this.name = name;
		}
		public void setIntroduced(Date introduced) {
			this.introduced = introduced;
		}
		public void setDiscontinued(Date discontinued) {
			this.discontinued = discontinued;
		}
		public void setCompany(Company company) {
			this.company = company;
		}
}
