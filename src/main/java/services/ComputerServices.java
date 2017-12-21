package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dao.ComputerJpaRepository;
//import dao.ComputerJpaRepository;
import model.Computer;

@Service
public class ComputerServices {

	@Autowired
	ComputerJpaRepository computerJpaRepository;
	
	public Page<Computer> listAllComputer(Pageable pageable) {
		return computerJpaRepository.findAll(pageable);
	}
	
	public Computer listOneComputer(int id) {
		return computerJpaRepository.findById(id).get();
	}
	
	public Computer createComputer(Computer computer) {
		return computerJpaRepository.save(computer);
	}
	
	public Computer updateComputer(Computer computer) {
		return computerJpaRepository.save(computer);
	}
	
	public void deleteComputer(Computer computer) {
		computerJpaRepository.delete(computer);
	}

	public List<Computer> searchComputer(String search) {
		return null;//return computerJpaRepository.findSearch(search);
	}
	
	public long counting() {
		return computerJpaRepository.count();
	}
}
