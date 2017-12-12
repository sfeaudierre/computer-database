package model;

import java.sql.Date;

public class Computer {
	
	private int id;
	private String name;
	private java.sql.Date introduced;
	private java.sql.Date discontinued;
	private int companyId;
	
		public Computer() {}
	
		public Computer(int id, String name, Date introduced, Date discontinued, int companyId) {
			
			this.id = id;
			this.name = name;
			this.introduced = introduced;
			this.discontinued = discontinued;
			this.companyId = companyId;
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
		public int getCompanyId() {
			return companyId;
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
		public void setCompanyId(int companyId) {
			this.companyId = companyId;
		}
}
