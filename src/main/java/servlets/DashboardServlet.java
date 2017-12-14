package servlets;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
import mapper.ComputerDtoMapper;
import model.Company;
import model.Computer;
import services.CompanyServices;
import services.ComputerServices; 

@Component
public class DashboardServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total = 10;
	private Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

	@Autowired
	private ComputerServices computerServices;
	@Autowired
	private CompanyServices companyServices;
	@Autowired
	private ComputerDtoMapper mapper;
	
	@SuppressWarnings("resource")
	public void init() {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.getAutowireCapableBeanFactory().autowireBean(this);
	}

	public void doGet( HttpServletRequest request, HttpServletResponse response )
			throws ServletException, IOException {
		logger.info("test");

		List<Company> listCompany = companyServices.listCompany();
		request.setAttribute("listcp", listCompany);

		listPaginator (request, response);

	}

	public void listPaginator (HttpServletRequest request, HttpServletResponse response) {

		String nombre = request.getParameter("nombre");
		String spageid = request.getParameter("page"); 

		int pageid;
		if(spageid == null || spageid.isEmpty()) {
			pageid = 1;
			request.setAttribute("pageid", pageid);
		}
		else {
			pageid=Integer.parseInt(spageid);
			request.setAttribute("pageid", pageid);
		}

		if(nombre !=  null && !nombre.isEmpty()) {
			if(Integer.valueOf(nombre) ==  10) {
				total = 10;
			}
			else if(Integer.valueOf(nombre) ==  50) {
				total = 50;
			}
			else if(Integer.valueOf(nombre) ==  100) {
				total = 100;
			}
		}

		if(pageid != 1){
			pageid=pageid-1;  
			pageid=pageid*total+1;  
		}  

		List<Computer> listComputer = computerServices.listComputer(pageid, total);
		List<ComputerDTO> listPcDto =  listComputer.stream()
				.map(computer -> mapper.computerToDto(computer))
				.collect(Collectors.toList());

		int count = computerServices.counting();
		request.setAttribute("count", count);
		request.setAttribute("searchPc", listPcDto.stream().collect(Collectors.toList()));
		try {
			request.getRequestDispatcher( "views/dashboard.jsp" ).forward( request, response );
		} 
		catch (ServletException e) {
			logger.error("Exception : %s", e);
		} 
		catch (IOException e) {
			logger.error("Exception : %s", e);
		}	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if(action.equals("delete")) {
			deleteComputer(request, response);
		}
		else if(action.equals("search")) {
			searchComputer(request, response);
		} 
	}

	private void searchComputer(HttpServletRequest request, HttpServletResponse response) {

		String search = request.getParameter("search");
		List<Company> listCompany = companyServices.listCompany();
		request.setAttribute("listcp", listCompany);

		List<Computer> searchPc = computerServices.searchComputer(search);
		List<ComputerDTO> listPcDto =  searchPc.stream()
				.map(computer -> mapper.computerToDto(computer))
				.collect(Collectors.toList());

		long count = searchPc.stream().collect(Collectors.counting());
		request.setAttribute("count", count);

		request.setAttribute("searchPc", listPcDto.stream().collect(Collectors.toList()));
		try {
			request.getRequestDispatcher("views/dashboard.jsp").forward( request, response );
		} 
		catch (ServletException e) {
			logger.error("Exception : %s", e);
		} 
		catch (IOException e) {
			logger.error("Exception : %s", e);
		}
	}

	public void deleteComputer (HttpServletRequest request, HttpServletResponse response) {

		String idList = request.getParameter("selection"); 
		for(String id : idList.split(",")) {
			Computer pc = new Computer();
			pc.setId(Integer.valueOf(id));
			computerServices.deleteComputer(pc);
		}
		try {
			response.sendRedirect("dashboard");
		} 
		catch (IOException e) {
			logger.error("Exception : %s", e);
		}
	}
}
