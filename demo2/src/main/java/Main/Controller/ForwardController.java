package Main.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Main.Entity.Employee;
import Main.Repository.EmployeeRepository;


@Controller
public class ForwardController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@RequestMapping(value = "/next",method = RequestMethod.GET)
	public String Next(Model model , HttpServletRequest request, HttpServletResponse response ) {
		HttpSession session = request.getSession();
		
//		int page = (Integer) null;
//		if(session.getAttribute("CurrentPage") != null ) {
				
		
		int page = (int) session.getAttribute("CurrentPage") + 1;
		
		Pageable Pageable = PageRequest.of(page, (int) session.getAttribute("Pagesize"));
		Page<Employee> ListUser =  employeeRepository.GetAll(Pageable);	

		session.setAttribute("CurrentPage", page);
				
		System.out.println("NextlistUser  : " + ListUser);
		session.setAttribute("listUser", ListUser);
		
		if(ListUser.hasNext() ) {
			return "ListUser";
		}else {
			model.addAttribute("NextDisable", "True");
		}		 
		return "ListUser";

	}
	
	@RequestMapping(value = "/pre",method = RequestMethod.GET)
	public String Previous(Model model , HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int page = (int) session.getAttribute("CurrentPage") - 1;
		
		Pageable Pageable = PageRequest.of(page, (int) session.getAttribute("Pagesize"));
		Page<Employee> ListUser =  employeeRepository.GetAll(Pageable);	

		session.setAttribute("CurrentPage", page);
				
		System.out.println("NextlistUser  : " + ListUser);
		session.setAttribute("listUser", ListUser);
		
		if(ListUser.hasPrevious() ) {
			return "ListUser";
		}else {
			model.addAttribute("PreDisable", "True");
		}		 
		return "ListUser";

	}
	@RequestMapping(value = "/first",method = RequestMethod.GET)
	public String first(Model model , HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int page = 0;
		session.setAttribute("CurrentPage", page);
		Pageable Pageable = PageRequest.of(page, (int) session.getAttribute("Pagesize"));
		Page<Employee> ListUser =  employeeRepository.GetAll(Pageable);	

		session.setAttribute("listUser", ListUser);
		
		if(ListUser.hasPrevious() ) {
			return "ListUser";
		}else {
			model.addAttribute("PreDisable", "True");
		}		 
		return "ListUser";

	}
	@RequestMapping(value = "/last" ,method = RequestMethod.GET)
	public String last(Model model , HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int page = (int) session.getAttribute("TotalPage");
		session.setAttribute("CurrentPage", page );
		System.out.println("current page  " +session.getAttribute("CurrentPage"));
		Pageable Pageable = PageRequest.of(page, (int) session.getAttribute("Pagesize"));
		Page<Employee> ListUser =  employeeRepository.GetAll(Pageable);	
		session.setAttribute("listUser", ListUser);	
		if(ListUser.hasNext() ) {
			return "ListUser";
		}else {
			model.addAttribute("NextDisable", "True");
		}		 
		return "ListUser";

	}
}
