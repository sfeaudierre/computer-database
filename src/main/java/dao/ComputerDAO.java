package dao;

import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Computer;
import model.ComputerMapper;

@Repository
public class ComputerDAO {

	String SELECTCOMPUTER = "select id, name, introduced, discontinued, company_id from computer ";
	String INSERTCOMPUTER = "insert into computer(name, introduced, discontinued, company_id) values ";
	String UPDATECOMPUTER = "update computer set ";
	String DELETECOMPUTER = "delete from computer ";

	Connection connect = null;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Computer create(Computer computer) {

		jdbcTemplate.update(INSERTCOMPUTER+"(?, ?, ?, ?)", computer.getNom(), computer.getIntroduced(), computer.getDiscontinued(), computer.getCompanyId());

		return computer;
	}

	public List<Computer> findAll() {

		List<Computer> listpc = jdbcTemplate.query(SELECTCOMPUTER, new ComputerMapper());

		return listpc;
	}

	public List<Computer> findAll(int start, int total) {

		List<Computer> listpc = (List<Computer>) jdbcTemplate.query(SELECTCOMPUTER+"limit "+(start-1)+", "+total, new ComputerMapper());

		return listpc;
	}

	public List<Computer> findSearch(String search) {

		List<Computer> listpc = (List<Computer>) jdbcTemplate.query
				(SELECTCOMPUTER+"where name like '%"+search+"%' or company_id = (select id from company where name like '%"+search+"%')"
						, new ComputerMapper());

		return listpc;
	}

	public Computer find(int id) {

		Computer computer = jdbcTemplate.queryForObject(SELECTCOMPUTER+"where id = ?", new Object[] {id}, new ComputerMapper());

		return computer;
	}

	public Computer update(Computer computer) {

		jdbcTemplate.update(UPDATECOMPUTER+" name = ?, introduced = ?, discontinued = ?, company_id = ? where id = ?",
				computer.getNom(), computer.getIntroduced(), computer.getDiscontinued(), computer.getCompanyId(), computer.getId());

		return computer;
	}

	public void delete(Computer computer) {

		jdbcTemplate.update(DELETECOMPUTER+" where id = ?", computer.getId());

	}

	public int count() {

		int count = jdbcTemplate.queryForObject("select count(*) from computer", Integer.class);
			
		return count;
	}

}
