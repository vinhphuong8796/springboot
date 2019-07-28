package Main.Controller;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import Main.Entity.Employee;
import Main.Form.LoginForm;
import Main.Repository.EmployeeRepository;

@Controller
public class ControllerApp {
	@Autowired
	private EmployeeRepository employeeRepository;

	@RequestMapping(value = "/")
	public String home() {

		return "login";

	}

	@ResponseBody
	@RequestMapping(value = "/insert")
	public String insert() {
		Employee employee = new Employee();
		Date date = new Date();
		employee.setId(1L);
		employee.setEmpNo("1");
		employee.setFullName("vpp");
		employee.setHireDate(date);
		this.employeeRepository.save(employee);

		return "Insert : " + employee;

	}

	@ResponseBody
	@RequestMapping(value = "/show")
	public String show() {
		Employee employee = new Employee();

		return "Insert : " + employee;
	}

	@ResponseBody
	@RequestMapping(value = "/getshow")
	public String getshow() {
		Employee employee = new Employee();
		employee.getFullName();
//		employee.getId();

		return "getshow: " + employee;
	}

	@RequestMapping(value = "/login", params = "submit", method = RequestMethod.POST)
	public String login(Model model, @RequestParam("id") String id, @RequestParam("password") String password) {
		List<Employee> Username = employeeRepository.findByFullName(id);
		List<Employee> Pass = employeeRepository.findByEmpNo(password);

		if (Username.size() > 0 && Pass.size() > 0) {
			model.addAttribute("message", "thành công");
		} else {
			model.addAttribute("message", "Thất bại");
		}
		// return "Thong bao: " + Username.size() + "Pass :" + Pass.size();
		return "login";
	}

	@RequestMapping(value = "/login", params = "clear", method = RequestMethod.POST)
	public String clear(Model model) {
		model.addAttribute("message", "");
		return "login";

	}

	@RequestMapping(value = "/delete")
	public String delete(Model model) {
		return "delete";

	}

	@RequestMapping(value = "/listuser")
	public String listuser(Model model , HttpServletRequest request, HttpServletResponse response,LoginForm LoginForm) {
		int page = 0;
		int size = 2;
		HttpSession session = request.getSession();
		session.setAttribute("CurrentPage", page);
		session.setAttribute("Pagesize", size);
		model.addAttribute("PreDisable", "True");
		Pageable Pageable = PageRequest.of(page, size);
		Page<Employee> ListUser =  employeeRepository.GetAll(Pageable);
		session.setAttribute("TotalPage", ListUser.getTotalPages() - 1);
		session.setAttribute("listUser", ListUser);
		if (ListUser.getTotalElements() < 5) {
			model.addAttribute("PreDisable", "True");
		}
		return "ListUser";

	}


	
	

}
