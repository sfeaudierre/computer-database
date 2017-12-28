package controller;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dto.ComputerDTO;
import form.AddForm;
import form.EditForm;
import mappers.ComputerMapper;
import model.Company;
import model.Computer;
import services.CompanyServices;
import services.ComputerServices;

@Controller
public class IndexController {

	private int total = 10;

	@Autowired
	private ComputerServices computerServices;
	@Autowired
	private CompanyServices companyServices;
	@Autowired
	private ComputerMapper mapper;

	/*
	 * 	Login
	 */

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(@RequestParam(value = "error", required = false) String error, Model model) {

		if (error != null) {
			model.addAttribute("error", "Incorrect username or password !");
		}
		return "login";
	}

	/*
	 * 	Dashboard
	 */

	@RequestMapping(path = "/dashboard", method=RequestMethod.GET)
	public String dashboardGet(@RequestParam(value = "nombre", required = false) String nombre, @RequestParam(value = "page", required = false) String page, ModelMap model, Locale locale) {


		Iterable<Company> listCompany = companyServices.listCompany();
		model.addAttribute("listcp", listCompany);

		listPaginator (nombre, page, model);

		return "dashboard";
	}

	public void listPaginator (String nombre, String page, ModelMap model) {

		long count = computerServices.counting();

		int pageid;
		model.addAttribute("nombre", total);

		if(nombre !=  null && !nombre.isEmpty()) {
			if(Integer.valueOf(nombre) ==  10) {
				total = 10;
				model.addAttribute("nombre", total);
			}
			else if(Integer.valueOf(nombre) ==  50) {
				total = 50;
				model.addAttribute("nombre", total);
			}
			else if(Integer.valueOf(nombre) ==  100) {
				total = 100;
				model.addAttribute("nombre", total);
			}
		}
		
		int lastpage = (int) ((count+total)/total);
		model.addAttribute("lastpage", lastpage);
		
		if(page == null || page.isEmpty()) {
			pageid = 1;
			model.addAttribute("pageid", pageid);
		}
		else {
			pageid=Integer.parseInt(page);
			if(pageid == 0) {
				pageid = 1;
			}
			else if (pageid > lastpage) {
				pageid = lastpage;
			}
			model.addAttribute("pageid", pageid);
		}

		Pageable pageable = PageRequest.of(pageid-1, total); 
		Page<Computer> listComputer = computerServices.listAllComputer(pageable);
		List<ComputerDTO> listPcDto =  listComputer.stream()
				.map(computer -> mapper.ComputerToComputerDTO(computer))
				.collect(Collectors.toList());

		model.addAttribute("count", count);
		model.addAttribute("searchPc", listPcDto.stream().collect(Collectors.toList()));
	}

	@RequestMapping(path = "/dashboard", method=RequestMethod.POST)
	public String dashboardPost(@RequestParam(value = "search", required = false) String search, @RequestParam(value = "action", required = false) String action, @RequestParam(value = "selection", required = false) String selection, ModelMap model, Locale locale) {

		if(action.equals("delete")) {
			deleteComputer(selection);
		}
		else if(action.equals("search")) {
			searchComputer(search, model);
		} 

		return "dashboard";
	}
	private void searchComputer(String search, ModelMap model) {

		Iterable<Company> listCompany = companyServices.listCompany();
		model.addAttribute("listcp", listCompany);

		List<Computer> searchPc = computerServices.searchComputer(search);
		List<ComputerDTO> listPcDto =  searchPc.stream()
				.map(computer -> mapper.ComputerToComputerDTO(computer))
				.collect(Collectors.toList());

		long count = searchPc.stream().collect(Collectors.counting());
		model.addAttribute("count", count);

		model.addAttribute("searchPc", listPcDto.stream().collect(Collectors.toList()));
	}

	public void deleteComputer (String selection) {

		for(String id : selection.split(",")) {
			Computer pc = new Computer();
			pc.setId(Integer.valueOf(id));
			computerServices.deleteComputer(pc);
		}
	}

	/*
	 * EditComputer
	 */

	@RequestMapping(path = "/editComputer", method=RequestMethod.GET)
	public String editGet(@RequestParam(value = "id", required = false) String sid, ModelMap model, Locale locale) {

		Iterable<Company> listCompany = companyServices.listCompany();
		model.addAttribute("listcp", listCompany);

		final EditForm form = new EditForm();
		model.addAttribute("editForm", form);

		int id = Integer.valueOf(sid);
		Computer computer = computerServices.listOneComputer(id);
		ComputerDTO pcDto = mapper.ComputerToComputerDTO(computer);
		model.addAttribute("pcDto", pcDto);

		return "editComputer";
	}

	@RequestMapping(path = "/editComputer", method=RequestMethod.POST)
	public String editPost(@ModelAttribute("editForm") final EditForm editForm, final Model model, Locale locale) {

		ComputerDTO cdto = new ComputerDTO();

		cdto.setId(editForm.getId());
		cdto.setName(editForm.getComputerName());
		cdto.setDateIntroduced(editForm.getIntroduced());
		cdto.setDateDiscontinued(editForm.getDiscontinued());
		cdto.setCompanyId(editForm.getCompanyId());

		Computer pc = mapper.dtoToComputer(cdto);

		pc = computerServices.updateComputer(pc);

		return "redirect:dashboard";
	}

	/*
	 * AddComputer
	 */

	@RequestMapping(path = "/addComputer", method=RequestMethod.GET)
	public String addGet(ModelMap model, Locale locale) {

		Iterable<Company> listCompany = companyServices.listCompany();
		model.addAttribute("listcp", listCompany);

		final AddForm form = new AddForm();
		model.addAttribute("addForm", form);

		return "addComputer";
	}

	@RequestMapping(path = "/addComputer", method=RequestMethod.POST)
	public String addPost(@ModelAttribute("addForm") final AddForm addForm, final Model model, Locale locale) {

		ComputerDTO cdto = new ComputerDTO();

		cdto.setName(addForm.getComputerName());
		cdto.setDateIntroduced(addForm.getIntroduced());
		cdto.setDateDiscontinued(addForm.getDiscontinued());
		cdto.setCompanyId(addForm.getCompanyId());

		Computer pc = mapper.dtoToComputer(cdto);

		pc = computerServices.createComputer(pc);

		return "redirect:dashboard";
	}
}
