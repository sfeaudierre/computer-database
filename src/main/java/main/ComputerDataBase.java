package main;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Company;
import services.CompanyServices;
import singleton.SingCompanyServices;

public class ComputerDataBase {

    private static Logger logger = LoggerFactory.getLogger(ComputerDataBase.class);
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		
		try {
			System.out.println("Entrez le nom de l'entreprise à supprimer");
			String companyName = scan.nextLine();
			
			Company company = new Company();
			company.setNom(companyName);
			CompanyServices companyServices = SingCompanyServices.getCompanyServices();
			companyServices.deleteCompany(company);
			System.out.println("Les ordinateurs de l'entreprise "+company.getNom()+" ont été supprimé");
			System.out.println("L'entreprise "+company.getNom()+" a été supprimé");

		}
		catch (Exception e){
			logger.error("Failed closing %s", e);
		}
		finally {
			try { 
		    	if (scan != null) 
		    		scan.close(); 
		    	} 
			catch (Exception e) {
				logger.error("Failed closing %s", e);
			}
		}
		
		
	}
}