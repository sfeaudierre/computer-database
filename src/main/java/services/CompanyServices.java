package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.CompanyDAO;
import model.Company;

@Component
public class CompanyServices {

	@Autowired
	CompanyDAO cydao;

	public List<Company> listCompany() {
		return cydao.findAll();
	}

	public Company listOneCompany(int id) {
		return cydao.find(id);
	}

	public void deleteCompany(Company company) {
		cydao.delete(company);
	}
}
