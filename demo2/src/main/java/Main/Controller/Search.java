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

import Main.Entity.Employee;
import Main.Form.LoginForm;
import Main.Repository.EmployeeRepository;

@Controller
public class Search {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@RequestMapping(value = "/search")
	public String search(Model model , HttpServletRequest request, HttpServletResponse response,LoginForm LoginForm) {
		model.addAttribute("LoginForm",new LoginForm());
		System.out.println(" date time " +LoginForm.getDatetime());
		if (LoginForm.getDatetime() == "") {
			System.out.println("truy ván 1 ");
		}else {
			System.out.println(" truy vấn 2");
		}
	
		return "ListUser";
	
}}

