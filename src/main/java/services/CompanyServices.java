package services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.CompanyJpaRepository;
import model.Company;

@Component
public class CompanyServices {
	
	@Autowired
	CompanyJpaRepository companyJpaRepository;

	public Iterable<Company> listCompany() {
		return companyJpaRepository.findAll();
	}

	public Optional<Company> listOneCompany(int id) {
		return companyJpaRepository.findById(id);
	}

	public void deleteCompany(Company company) {
		companyJpaRepository.delete(company);
	}
}
