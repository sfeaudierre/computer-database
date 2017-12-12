package singleton;

import services.ComputerServices;

public class SingComputerServices{
	
	private static ComputerServices computerServices;
	
	public static synchronized ComputerServices getComputerServices(){
		if(computerServices == null) {
				computerServices = new ComputerServices();
		}
		return computerServices;
	}
	
}