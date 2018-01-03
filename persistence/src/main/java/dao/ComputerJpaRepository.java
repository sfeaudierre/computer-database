package dao;

	
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import model.Computer;

public interface ComputerJpaRepository extends PagingAndSortingRepository<Computer, Integer> {

	String SEARCHCOMPUTER = "from Computer c where c.name like %?1% or c.company.id = (select id from Company where name like %?1%)";
	String SEARCHCOMPUTERBYDATE ="from Computer c where c.introduced like ?1 or c.discontinued like ?1";
	
	@Query(SEARCHCOMPUTER)
	List<Computer> findByName(String search);

	@Query(SEARCHCOMPUTERBYDATE)
	List<Computer> findByDate(LocalDate date);

}
