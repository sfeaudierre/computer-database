package singleton;

import dao.ComputerDAO;

public class SingComputerDAO {
	
	private static ComputerDAO computerDao;
	
	public static synchronized ComputerDAO getComputerDAO(){
		if(computerDao == null) {
				computerDao = new ComputerDAO();
		}
		return computerDao;
	}
	
}
