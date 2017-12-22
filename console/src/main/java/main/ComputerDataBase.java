package main;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import model.Company;
import services.CompanyServices;

public class ComputerDataBase {

	private static Logger logger = LoggerFactory.getLogger(ComputerDataBase.class);
	static Scanner scan = new Scanner(System.in);

	@Autowired
	static CompanyServices companyServices;

	public static void main (String[] args) {

		try {
			System.out.println("Entrez le nom de l'entreprise à supprimer");
			String companyName = scan.nextLine();

			Company company = new Company();
			company.setNom(companyName);
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