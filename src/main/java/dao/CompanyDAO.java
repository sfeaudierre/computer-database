package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import connection.HikariCPConnect;
import model.Company;

@Repository
public class CompanyDAO {

	String SELECTCOMPANY = "select id, name from company ";
	String DELETECOMPUTER = "delete from computer ";
	String DELETECOMPANY = "delete from company ";

	Connection connect = null;
    private Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

	public Company find(int id) {
		
		Company company = new Company();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			DataSource ds = HikariCPConnect.getConnection();
			connect = ds.getConnection();	
			stmt = this.connect.createStatement();
			rs = stmt.executeQuery(SELECTCOMPANY+"where id = "+id);
			if(rs.first()) {
				company = new Company(id, rs.getString(2));
			}
			
		} 
		catch (SQLException e) {
			logger.error("SQL Exception : %s", e);
		}
		finally {
			try { 
				if (rs != null) 
					rs.close(); 
				} 
			catch (Exception e) {
				logger.error("Failed closing %s", rs);
			}
			try { 
		    	if (stmt != null) 
		    		stmt.close(); 
		    	} 
			catch (Exception e) {
				logger.error("Failed closing %s", stmt);
			}
			try { 
		    	if (connect != null) 
		    		connect.close(); 
		    	} 
			catch (Exception e) {
				logger.error("Failed closing %s", e);
			}
		}
		return company;
	}
	
	public List<Company> findAll() {
		
		List<Company> company = new ArrayList<Company>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			DataSource ds = HikariCPConnect.getConnection();
			connect = ds.getConnection();
			stmt = this.connect.createStatement();
			rs = stmt.executeQuery(SELECTCOMPANY);
			while(rs.next()) {
				company.add(new Company(rs.getInt(1), rs.getString(2)));
			}
			
		} 
		catch (SQLException e) {
			logger.error("SQL Exception : %s", e);
		}
		finally {
			try { 
				if (rs != null) 
					rs.close(); 
				} 
			catch (Exception e) {
				logger.error("Failed closing %s", rs);
			}
			try { 
		    	if (stmt != null) 
		    		stmt.close(); 
		    	} 
			catch (Exception e) {
				logger.error("Failed closing %s", stmt);
			}
			try { 
		    	if (connect != null) 
		    		connect.close(); 
		    	} 
			catch (Exception e) {
				logger.error("Failed closing %s", e);
			}
		}
		return company;
	}

	public void delete(Company company) {
		
		Statement stmt = null;
		ResultSet rs = null;

		try {
			DataSource ds = HikariCPConnect.getConnection();
			connect = ds.getConnection();
			connect.setAutoCommit(false);
			stmt = this.connect.createStatement();
			
			rs = stmt.executeQuery(SELECTCOMPANY+"where name = '"+company.getNom()+"'");
			connect.commit();
			if(rs.first()) {
				company = new Company(rs.getInt(1), rs.getString(2));
			}
			
			stmt.executeUpdate(DELETECOMPUTER+"where company_id = "+company.getId());
			connect.commit();
			
			stmt.executeUpdate(DELETECOMPANY+"where name = '"+company.getNom()+"'");
			connect.commit();
			connect.setAutoCommit(true); 
		}
		catch (SQLException e) {
			try {
				connect.rollback();
			}
			catch(Exception ex) {
				logger.error("Exception : %s", ex);

			}
			logger.error("SQL Exception : %s", e);
		}
		finally {
			try { 
				if (stmt != null) 
					stmt.close(); 
			} 
			catch (Exception e) {
				logger.error("Failed closing %s", stmt);
			}
			try { 
				if (rs != null) 
					rs.close(); 
			} 
			catch (Exception e) {
				logger.error("Failed closing %s", rs);
			}
			try { 
		    	if (connect != null) 
		    		connect.close(); 
		    	} 
			catch (Exception e) {
				logger.error("Failed closing %s", e);
			}
		}
	}
}
