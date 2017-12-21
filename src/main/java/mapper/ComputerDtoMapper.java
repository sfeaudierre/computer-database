package mapper;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import dto.ComputerDTO;
import model.Company;
import model.Computer;

@Component
public class ComputerDtoMapper{
	
	public ComputerDTO computerToDto (Computer computer) {
		
		ComputerDTO cdto = new ComputerDTO();
		
		String id = String.valueOf(computer.getId());
		String name = computer.getNom();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String intro;
		if(computer.getIntroduced() == null) {
			intro = "";
		}
		else
		intro = df.format(computer.getIntroduced());
		
		String sorti;
		if(computer.getDiscontinued() == null) {
			sorti = "";
		}
		else
		sorti = df.format(computer.getDiscontinued());
		
		Company company = computer.getCompany();
			 
		cdto.setId(id);
		cdto.setNom(name);
		cdto.setIntroduced(intro);			
		cdto.setDiscontinued(sorti);
		if(company == null) {
			cdto.setCompanyId("");
		}
		else {
			cdto.setCompanyId(String.valueOf(company.getId()));
		}
		
		return cdto;
	}
	
	public Computer dtoToComputer (ComputerDTO computerDto) {
		
		
		Computer pc = new Computer();
		String sid = computerDto.getId();
		int id = 0;
		if(sid != null) {
			id = Integer.valueOf(computerDto.getId());
		}
		String name = computerDto.getNom();
		Date sqldintro;
		if(computerDto.getIntroduced().equals(null) || computerDto.getIntroduced() == "") {
			sqldintro = null;
		}
		else {
			LocalDate dintro =  LocalDate.parse(computerDto.getIntroduced());
			sqldintro = Date.valueOf(dintro);
		}
		
		Date sqldsorti;
		if(computerDto.getDiscontinued().equals(null) || computerDto.getDiscontinued() == "") {
			sqldsorti = null;
		}
		else {
			LocalDate dsorti =  LocalDate.parse(computerDto.getDiscontinued());
			sqldsorti = Date.valueOf(dsorti);
		}
		
		int companyId;
		if(computerDto.getCompanyId().equals("") || computerDto.getCompanyId() == null) {
			companyId = 0;
		}
		else {
			companyId = Integer.valueOf(computerDto.getCompanyId());
		}
		Company company = new Company();
		company.setId(companyId);
		
		pc.setId(id);
		pc.setNom(name);
		pc.setIntroduced(sqldintro);			
		pc.setDiscontinued(sqldsorti);
		pc.setCompany(company);
		
		return pc;
	}
}

