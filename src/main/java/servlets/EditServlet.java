package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import configuration.AppConfig;
import dto.ComputerDTO;
import mapper.ComputerMapper;
import model.Company;
import model.Computer;
import services.CompanyServices;
import services.ComputerServices;

@Component
public class EditServlet extends HttpServlet {
	
    private Logger logger = LoggerFactory.getLogger(DashboardServlet.class);
    
    @Autowired
    private ComputerServices computerServices;
    @Autowired
    private CompanyServices companyServices;
	@Autowired
	private ComputerMapper mapper;

	public void init() {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.getAutowireCapableBeanFactory().autowireBean(this);
	}
	
	public void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
		
	    List<Company> listCompany = companyServices.listCompany();
		request.setAttribute("listcp", listCompany);

		int id = Integer.valueOf(request.getParameter("id"));
		Computer computer = computerServices.listOneComputer(id);
		ComputerDTO pcDto = mapper.computerToDto(computer);
		request.setAttribute("pcDto", pcDto);
		request.getRequestDispatcher( "views/editComputer.jsp" ).forward( request, response );
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String nom = request.getParameter("computerName");
	    String introduced = request.getParameter("introduced");
	    String discontinued = request.getParameter("discontinued");
	    String companyId = request.getParameter("companyId");
	    		
	    ComputerDTO cdto = new ComputerDTO();
	    
		cdto.setId(id);
	    cdto.setNom(nom);
	    cdto.setIntroduced(introduced);
	    cdto.setDiscontinued(discontinued);
	    cdto.setCompanyId(companyId);
	    
	    ComputerMapper mapper = new ComputerMapper();
	    Computer pc = mapper.dtoToComputer(cdto);
	    
	    pc = computerServices.updateComputer(pc);
	    try {
			response.sendRedirect("dashboard");
		} 
	    catch (IOException e) {
			logger.error("SQL Exception : %s", e);		
		}
	}
}
