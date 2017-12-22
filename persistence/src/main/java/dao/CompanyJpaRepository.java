package dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import model.Company;

public interface CompanyJpaRepository extends PagingAndSortingRepository<Company, Integer> {

}
