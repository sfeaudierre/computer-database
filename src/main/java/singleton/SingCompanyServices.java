package singleton;

import services.CompanyServices;

public class SingCompanyServices {

		
		private static CompanyServices companyServices;
		
		public static synchronized CompanyServices getCompanyServices(){
			if(companyServices == null) {
					companyServices = new CompanyServices();
			}
			return companyServices;
		}
		
}

