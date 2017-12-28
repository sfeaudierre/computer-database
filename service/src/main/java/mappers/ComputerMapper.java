package mappers;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import dto.ComputerDTO;
import model.Company;
import model.Computer;
import org.springframework.stereotype.Component;

@Component
public class ComputerMapper {

	public ArrayList<ComputerDTO> mappToListComputerDTO(ResultSet rs) {
		ArrayList<ComputerDTO> listComputers = new ArrayList<>();

		try {
			while (rs.next()) {
				listComputers.add(new ComputerDTO(rs.getString(1), rs.getString(2), convertDateToString(rs.getDate(3)),
						convertDateToString(rs.getDate(4)), rs.getString(5), rs.getString(7)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listComputers;
	}

	public ComputerDTO mappToComputerDTO(ResultSet rs) {
		ComputerDTO cpDTO = null;
		try {
			cpDTO = new ComputerDTO(rs.getString(1), rs.getString(2), convertDateToString(rs.getDate(3)),
					convertDateToString(rs.getDate(4)), rs.getString(5), rs.getString(7));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cpDTO;
	}

	public ArrayList<ComputerDTO> ComputersToComputersDTO(ArrayList<Computer> list) {
		ArrayList<ComputerDTO> listComputers = new ArrayList<>();

		for (Computer computer : list) {
			listComputers.add(new ComputerDTO(String.valueOf(computer.getId()), computer.getNom(),
					convertLocalDateToString(computer.getIntroduced()), convertLocalDateToString(computer.getDiscontinued()),
					String.valueOf(getCompanyId(computer.getCompany())), getCompanyName(computer.getCompany())));
		}
		return listComputers;

	}

	public Computer dtoToComputer (ComputerDTO computerDto) {

		Computer pc = new Computer();
		String sid = computerDto.getId();
		int id = 0;
		if(sid != null) {
			id = Integer.valueOf(computerDto.getId());
		}
		String name = computerDto.getName();
		LocalDate sqldintro;
		if(computerDto.getDateIntroduced().equals(null) || computerDto.getDateIntroduced() == "") {
			sqldintro = null;
		}
		else {
			sqldintro =  LocalDate.parse(computerDto.getDateIntroduced());
		}

		LocalDate sqldsorti;
		if(computerDto.getDateDiscontinued().equals(null) || computerDto.getDateDiscontinued() == "") {
			sqldsorti = null;
		}
		else {
			sqldsorti =  LocalDate.parse(computerDto.getDateDiscontinued());
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
	
	public ComputerDTO ComputerToComputerDTO(Computer computer) {
		return new ComputerDTO(String.valueOf(computer.getId()), computer.getNom(),
				convertLocalDateToString(computer.getIntroduced()), convertLocalDateToString(computer.getDiscontinued()),
				String.valueOf(getCompanyId(computer.getCompany())), getCompanyName(computer.getCompany()));
	}

	private long getCompanyId(Company company) {
		if(company == null) {
			return 0L;
		}else {
			return company.getId();
		}
	}

	private String getCompanyName(Company company) {
		if(company == null) {
			return "";
		}else {
			return company.getNom();
		}
	}

	private String convertLocalDateToString(LocalDate date) {
		if (date == null) {
			return "";
		} else {
			return date.toString();
		}
	}

	private String convertDateToString(Date date) {
		if (date == null) {
			return "";
		} else {
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			return formatter.format(date);
		}
	}
}