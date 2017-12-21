package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ComputerMapper implements RowMapper<Computer> {

public Computer mapRow(ResultSet resultSet, int i) throws SQLException {
		
		Computer computer = new Computer();
//		computer.setId(resultSet.getInt("id"));
//		computer.setNom(resultSet.getString("name"));
//		computer.setIntroduced(resultSet.getDate("introduced"));
//		computer.setDiscontinued(resultSet.getDate("discontinued"));
//		computer.setCompany(resultSet.getCompany("company"));

		return computer;
	}
}
