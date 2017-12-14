package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CompanyMapper implements RowMapper<Company> {

	public Company mapRow(ResultSet resultSet, int i) throws SQLException {
		
		Company company = new Company();
		company.setId(resultSet.getInt("id"));
		company.setNom(resultSet.getString("name"));
		
		return company;
	}
}
