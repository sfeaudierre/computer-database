package controller;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import mapper.ComputerDtoMapper;
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
	private ComputerDtoMapper mapper;

	@Autowired
    private MessageSource messageSource;

	
	/*
	 * 	Dashboard
	 */

	@RequestMapping(path = "/dashboard", method=RequestMethod.GET)
	public String dashboardGet(@RequestParam(value = "nombre", required = false) String nombre, @RequestParam(value = "page", required = false) String page, ModelMap model, Locale locale) {

		String computerFound = messageSource.getMessage("label.computersFound", null, locale);
        model.addAttribute("computersFound", computerFound);
        String name = messageSource.getMessage("label.name", null, locale);
        model.addAttribute("name", name);
        String introduced = messageSource.getMessage("label.introduced", null, locale);
        model.addAttribute("introduced", introduced);
        String discontinued = messageSource.getMessage("label.discontinued", null, locale);
        model.addAttribute("discontinued", discontinued);
        String company = messageSource.getMessage("label.company", null, locale);
        model.addAttribute("company", company);
        String filter = messageSource.getMessage("label.filter", null, locale);
        model.addAttribute("filter", filter);
        String search = messageSource.getMessage("label.search", null, locale);
        model.addAttribute("search", search);
        String addComputer = messageSource.getMessage("label.addComputer", null, locale);
        model.addAttribute("addComputer", addComputer);
        String edit = messageSource.getMessage("label.edit", null, locale);
        model.addAttribute("edit", edit);
        String view = messageSource.getMessage("label.view", null, locale);
        model.addAttribute("view", view);
        
		Iterable<Company> listCompany = companyServices.listCompany();
		model.addAttribute("listcp", listCompany);

		listPaginator (nombre, page, model);

		return "dashboard";
	}

	public void listPaginator (String nombre, String page, ModelMap model) {


		int pageid;
		model.addAttribute("nombre", total);

		if(page == null || page.isEmpty()) {
			pageid = 1;
			model.addAttribute("pageid", pageid);
		}
		else {
			pageid=Integer.parseInt(page);
			model.addAttribute("pageid", pageid);
		}

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

		Pageable pageable = PageRequest.of(pageid-1, total); 
		Page<Computer> listComputer = computerServices.listAllComputer(pageable);
		List<ComputerDTO> listPcDto =  listComputer.stream()
				.map(computer -> mapper.computerToDto(computer))
				.collect(Collectors.toList());

		long count = computerServices.counting();
		model.addAttribute("count", count);
		model.addAttribute("searchPc", listPcDto.stream().collect(Collectors.toList()));
	}

	@RequestMapping(path = "/dashboard", method=RequestMethod.POST)
	public String dashboardPost(@RequestParam(value = "search", required = false) String search, @RequestParam(value = "action", required = false) String action, @RequestParam(value = "selection", required = false) String selection, ModelMap model, Locale locale) {

		String computerFound = messageSource.getMessage("label.computersFound", null, locale);
        model.addAttribute("computersFound", computerFound);
        String name = messageSource.getMessage("label.name", null, locale);
        model.addAttribute("name", name);
        String introduced = messageSource.getMessage("label.introduced", null, locale);
        model.addAttribute("introduced", introduced);
        String discontinued = messageSource.getMessage("label.discontinued", null, locale);
        model.addAttribute("discontinued", discontinued);
        String company = messageSource.getMessage("label.company", null, locale);
        model.addAttribute("company", company);
        String filter = messageSource.getMessage("label.filter", null, locale);
        model.addAttribute("filter", filter);
        String search1 = messageSource.getMessage("label.search", null, locale);
        model.addAttribute("search", search1);
        String addComputer = messageSource.getMessage("label.addComputer", null, locale);
        model.addAttribute("addComputer", addComputer);
        String edit = messageSource.getMessage("label.edit", null, locale);
        model.addAttribute("edit", edit);
        String view = messageSource.getMessage("label.view", null, locale);
        model.addAttribute("view", view);

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
				.map(computer -> mapper.computerToDto(computer))
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
		
		String editComputerTitle = messageSource.getMessage("label.editComputerTitle", null, locale);
        model.addAttribute("editComputerTitle", editComputerTitle);
        String editComputerName = messageSource.getMessage("label.editComputerName", null, locale);
        model.addAttribute("editComputerName", editComputerName);
        String editComputerIntroduced = messageSource.getMessage("label.editComputerIntroduced", null, locale);
        model.addAttribute("editComputerIntroduced", editComputerIntroduced);
        String editComputerDiscontinued = messageSource.getMessage("label.editComputerDiscontinued", null, locale);
        model.addAttribute("editComputerDiscontinued", editComputerDiscontinued);
        String editComputerCompany = messageSource.getMessage("label.editComputerCompany", null, locale);
        model.addAttribute("editComputerCompany", editComputerCompany);
        String edit = messageSource.getMessage("label.edit", null, locale);
        model.addAttribute("edit", edit);
        String ou = messageSource.getMessage("label.ou", null, locale);
        model.addAttribute("ou", ou);
        String cancel = messageSource.getMessage("label.cancel", null, locale);
        model.addAttribute("cancel", cancel);

		Iterable<Company> listCompany = companyServices.listCompany();
		model.addAttribute("listcp", listCompany);
		
		final EditForm form = new EditForm();
		model.addAttribute("editForm", form);

		int id = Integer.valueOf(sid);
		Computer computer = computerServices.listOneComputer(id);
		ComputerDTO pcDto = mapper.computerToDto(computer);
		model.addAttribute("pcDto", pcDto);

		return "editComputer";
	}

	@RequestMapping(path = "/editComputer", method=RequestMethod.POST)
	public String editPost(@ModelAttribute("editForm") final EditForm editForm, final Model model, Locale locale) {
		
		ComputerDTO cdto = new ComputerDTO();

		cdto.setId(editForm.getId());
		cdto.setNom(editForm.getComputerName());
		cdto.setIntroduced(editForm.getIntroduced());
		cdto.setDiscontinued(editForm.getDiscontinued());
		cdto.setCompanyId(editForm.getCompanyId());

		ComputerDtoMapper mapper = new ComputerDtoMapper();
		Computer pc = mapper.dtoToComputer(cdto);

		pc = computerServices.updateComputer(pc);

		return "redirect:dashboard";
	}

	/*
	 * AddComputer
	 */

	@RequestMapping(path = "/addComputer", method=RequestMethod.GET)
	public String addGet(ModelMap model, Locale locale) {
		
		String addComputerTitle = messageSource.getMessage("label.addComputerTitle", null, locale);
        model.addAttribute("addComputerTitle", addComputerTitle);
        String addComputerName = messageSource.getMessage("label.addComputerName", null, locale);
        model.addAttribute("addComputerName", addComputerName);
        String addComputerIntroduced = messageSource.getMessage("label.addComputerIntroduced", null, locale);
        model.addAttribute("addComputerIntroduced", addComputerIntroduced);
        String addComputerDiscontinued = messageSource.getMessage("label.addComputerDiscontinued", null, locale);
        model.addAttribute("addComputerDiscontinued", addComputerDiscontinued);
        String addComputerCompany = messageSource.getMessage("label.addComputerCompany", null, locale);
        model.addAttribute("addComputerCompany", addComputerCompany);
        String add = messageSource.getMessage("label.add", null, locale);
        model.addAttribute("add", add);
        String ou = messageSource.getMessage("label.ou", null, locale);
        model.addAttribute("ou", ou);
        String cancel = messageSource.getMessage("label.cancel", null, locale);
        model.addAttribute("cancel", cancel);

		Iterable<Company> listCompany = companyServices.listCompany();
		model.addAttribute("listcp", listCompany);

		final AddForm form = new AddForm();
		model.addAttribute("addForm", form);
		
		return "addComputer";
	}

	@RequestMapping(path = "/addComputer", method=RequestMethod.POST)
	public String addPost(@ModelAttribute("addForm") final AddForm addForm, final Model model, Locale locale) {
		
		ComputerDTO cdto = new ComputerDTO();

		cdto.setNom(addForm.getComputerName());
		cdto.setIntroduced(addForm.getIntroduced());
		cdto.setDiscontinued(addForm.getDiscontinued());
		cdto.setCompanyId(addForm.getCompanyId());

		Computer pc = mapper.dtoToComputer(cdto);

		pc = computerServices.createComputer(pc);

		return "redirect:dashboard";
	}
}
