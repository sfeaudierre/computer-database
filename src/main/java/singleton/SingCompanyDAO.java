package singleton;

import dao.CompanyDAO;

public class SingCompanyDAO {
	
	private static CompanyDAO companydao;
	
	public static synchronized CompanyDAO getCompanyDAO(){
		if(companydao == null) {
				companydao = new CompanyDAO();
		}
		return companydao;
	}
	
}

