package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ComputerDAO;
import model.Computer;

@Service
public class ComputerServices {
	
	@Autowired
	ComputerDAO cdao;

	public List<Computer> listAllComputer() {
		return cdao.findAll();
	}
	
	public List<Computer> listComputer(int start, int total) {
		return cdao.findAll(start, total);
	}
	
	public Computer listOneComputer(int id) {
		return cdao.find(id);
	}
	
	public Computer createComputer(Computer computer) {
		return cdao.create(computer);
	}
	
	public Computer updateComputer(Computer computer) {
		return cdao.update(computer);
	}
	
	public void deleteComputer(Computer computer) {
		cdao.delete(computer);
	}

	public List<Computer> searchComputer(String search) {
		return cdao.findSearch(search);
	}
	
	public int counting() {
		return cdao.count();
	}
}
