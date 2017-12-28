package model;

import java.time.LocalDate;

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
	private LocalDate introduced;
	private LocalDate discontinued;
	@ManyToOne
	private Company company;
	
		public Computer() {}
	
		public Computer(int id, String name, LocalDate introduced, LocalDate discontinued, Company company) {
			
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
		public LocalDate getIntroduced() {
			return introduced;
		}
		public LocalDate getDiscontinued() {
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
		public void setIntroduced(LocalDate introduced) {
			this.introduced = introduced;
		}
		public void setDiscontinued(LocalDate discontinued) {
			this.discontinued = discontinued;
		}
		public void setCompany(Company company) {
			this.company = company;
		}
}
