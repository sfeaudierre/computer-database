package dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class ComputerDTO {

	private String id;
	private String name;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String dateIntroduced;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String dateDiscontinued;
	private String companyId;
	private String companyName;
	
	public ComputerDTO(){}
	
	public ComputerDTO(String id, String name, String dateIntroduced, String dateDiscontinued, String companyId,
			String companyName) {
		super();
		this.id = id;
		this.name = name;
		this.dateIntroduced = dateIntroduced;
		this.dateDiscontinued = dateDiscontinued;
		this.companyId = companyId;
		this.companyName = companyName;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String string) {
		this.id = string;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDateIntroduced() {
		return dateIntroduced;
	}
	public void setDateIntroduced(String dateIntroduced) {
		this.dateIntroduced = dateIntroduced;
	}
	public String getDateDiscontinued() {
		return dateDiscontinued;
	}
	public void setDateDiscontinued(String dateDiscontinued) {
		this.dateDiscontinued = dateDiscontinued;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String string) {
		this.companyId = string;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}