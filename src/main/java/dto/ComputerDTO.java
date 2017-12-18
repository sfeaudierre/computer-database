package dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ComputerDTO {
	
	private String id;
	
	@Size(min=2, max=30)
	@NotNull
	@Pattern(regexp = "{A-Za-z0-9}*$")
	private String name;
	
	private String introduced;
	
	private String discontinued;
	
	private String companyId;
	
	public ComputerDTO() {}
	
	public ComputerDTO(String id, String name, String introduced, String discontinued, String companyId) {
		
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}
	
	public String getId() {
		return id;
	}
	public String getNom() {
		return name;
	}
	public String getIntroduced() {
		return introduced;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public String getCompanyId() {
		return companyId;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setNom(String name) {
		this.name = name;
	}
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
