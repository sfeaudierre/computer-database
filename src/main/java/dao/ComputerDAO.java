package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
import model.Computer;

@Repository
public class ComputerDAO {
		
	String SELECTCOMPUTER = "select id, name, introduced, discontinued, company_id from computer ";
	String INSERTCOMPUTER = "insert into computer(name, introduced, discontinued, company_id) values ";
	String UPDATECOMPUTER = "update computer set ";
	String DELETECOMPUTER = "delete from computer ";
	
		Connection connect = null;
	
	    private Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	    
		public Computer create(Computer computer) {
			
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				DataSource ds = HikariCPConnect.getConnection();
				connect = ds.getConnection();				
				stmt = this.connect.prepareStatement(INSERTCOMPUTER+"(?, ?, ?, ?)");
                stmt.setString(1, computer.getNom());
                stmt.setDate(2, computer.getIntroduced());
                stmt.setDate(3, computer.getDiscontinued());
                stmt.setInt(4, computer.getCompanyId());
                stmt.executeUpdate();
                rs = stmt.executeQuery(SELECTCOMPUTER+" where id = (select Max(id) from computer)");
                if(rs.first()) {
                	computer = new Computer(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5));
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
			return computer;
		}
		
		public List<Computer> findAll() {
			
			List<Computer> listpc = new ArrayList<Computer>();
			Statement stmt = null;
			ResultSet rs = null;
			
			try {
				DataSource ds = HikariCPConnect.getConnection();
				connect = ds.getConnection();
				stmt = this.connect.createStatement();
				rs = stmt.executeQuery(SELECTCOMPUTER);
				while(rs.next()) {
					listpc.add(new Computer(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5)));
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
			return listpc;
		}
		
		public List<Computer> findAll(int start, int total) {
			
			List<Computer> listpc = new ArrayList<Computer>();
			Statement stmt = null;
			ResultSet rs = null;
			
			try {
				DataSource ds = HikariCPConnect.getConnection();
				connect = ds.getConnection();
				stmt = this.connect.createStatement();
				rs = stmt.executeQuery(SELECTCOMPUTER+"limit "+(start-1)+","+total);
				while(rs.next()) {
					
					listpc.add(new Computer(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5)));
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
			return listpc;
		}
		
		public List<Computer> findSearch(String search) {
			
			Statement stmt = null;
			ResultSet rs = null;
			List<Computer> listpc = new ArrayList<Computer>();

			try {
				DataSource ds = HikariCPConnect.getConnection();
				connect = ds.getConnection();
				stmt = this.connect.createStatement();
				rs = stmt.executeQuery(SELECTCOMPUTER+"where name = '"+search+"' or company_id = (select id from company where name = '"+search+"')");
			
				while(rs.next()) {
					
					listpc.add(new Computer(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5)));
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
			
			return listpc;
		}

		public Computer find(int id) {
			
			Computer computer = new Computer();
			Statement stmt = null;
			ResultSet rs = null;
			
			try {
				DataSource ds = HikariCPConnect.getConnection();
				connect = ds.getConnection();
				stmt = this.connect.createStatement();
				rs = stmt.executeQuery(SELECTCOMPUTER+"where id = "+id);
				if(rs.first()) {
					computer = new Computer(id, rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5));
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
			return computer;
		}

		public Computer update(Computer computer) {
			
			PreparedStatement stmt = null;
			
			try {
				DataSource ds = HikariCPConnect.getConnection();
				connect = ds.getConnection();
				stmt = this.connect.prepareStatement(UPDATECOMPUTER+" name = ?, introduced = ?, discontinued = ?, company_id = ? where id = "+computer.getId());
				stmt.setString(1, computer.getNom());
                stmt.setDate(2, computer.getIntroduced());
                stmt.setDate(3, computer.getDiscontinued());
                stmt.setInt(4, computer.getCompanyId());
                stmt.executeUpdate();
			} 
			catch (SQLException e) {
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
			    	if (connect != null) 
			    		connect.close(); 
			    	} 
				catch (Exception e) {
					logger.error("Failed closing %s", e);
				}
			}
			return computer;
		}

		public void delete(Computer computer) {
			
			Statement stmt = null;
			
			try {
				DataSource ds = HikariCPConnect.getConnection();
				connect = ds.getConnection();
				stmt = this.connect.createStatement();
				stmt.executeUpdate(DELETECOMPUTER+" where id = "+computer.getId());
			}
			catch (SQLException e) {
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
			    	if (connect != null) 
			    		connect.close(); 
			    	} 
				catch (Exception e) {
					logger.error("Failed closing %s", e);
				}
			}
		}
		
		public int count() {
			
			int count = 0;
			Statement stmt = null;

			try {
				DataSource ds = HikariCPConnect.getConnection();
				connect = ds.getConnection();
				stmt = this.connect.createStatement();
				ResultSet rs = stmt.executeQuery("Select count(*) as cpt from computer");
				while(rs.next()) {
					count = rs.getInt("cpt");
				}
			}
			catch (SQLException e) {
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
			    	if (connect != null) 
			    		connect.close(); 
			    	} 
				catch (Exception e) {
					logger.error("Failed closing %s", e);
				}
			}
			
			return count;
		}

}
