package cn.muke.ssh.action;

import java.util.List;

import cn.muke.ssh.domain.Department;
import cn.muke.ssh.domain.Employee;
import cn.muke.ssh.domain.PageBean;
import cn.muke.ssh.service.DepartmentService;
import cn.muke.ssh.service.EmployeeService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>{
	
	private Employee employee = new Employee();
	
	private EmployeeService employeeService;
	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	private Integer currPage=1;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	
	@Override
	public Employee getModel() {
		// TODO Auto-generated method stub
		return employee;
	}
	
	public String login(){
		System.out.print("执行");
		Employee exiteEmployee= employeeService.login(employee);
		if (exiteEmployee==null) {
			this.addActionError("错误");
			return INPUT;
		}else {
			ActionContext.getContext().getSession().put("exiteEmployee", exiteEmployee);
			return SUCCESS;
		}
	}
	
	
	
	//查询员工分页
	public String findAll() {
		PageBean<Employee> employeeBean=employeeService.findByPage(currPage);
		ActionContext.getContext().getValueStack().push(employeeBean);
		return "findAll";
	}
	
	//调整到添加员工
	public String  saveUI() {
		List<Department> list=departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		 return "saveUI";
	}

	public String save() {
		employeeService.save(employee);
		return "addSuccess";
	}
	
	public String edit() {
		//查询员工
		employee=employeeService.findById(employee.getEid());
		//查询部门
		List<Department> list=departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "editSuccess";
		
	}
	
	public String update() {
		employeeService.update(employee);
		return "updateSuccess";
	}
	
	public String delete() {
		employee=employeeService.findById(employee.getEid());
		employeeService.delete(employee);
		return "deleteSuccess";
	}
}
