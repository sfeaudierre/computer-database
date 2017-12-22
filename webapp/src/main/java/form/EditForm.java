package form;

public class EditForm {

	private String id;
	private String computerName;
	private String introduced;
	private String discontinued;
	private String companyId;
	
	public String getId() {
		return id;
	}
	public String getComputerName() {
		return computerName;
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
	public void setComputerName(String computerName) {
		this.computerName = computerName;
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
