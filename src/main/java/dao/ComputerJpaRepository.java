package dao;

	
import org.springframework.data.repository.PagingAndSortingRepository;

import model.Computer;

public interface ComputerJpaRepository extends PagingAndSortingRepository<Computer, Integer> {

}
