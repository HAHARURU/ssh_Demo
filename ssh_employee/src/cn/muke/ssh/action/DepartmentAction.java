package cn.muke.ssh.action;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.muke.ssh.domain.Department;
import cn.muke.ssh.domain.PageBean;
import cn.muke.ssh.service.DepartmentService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DepartmentAction extends ActionSupport implements ModelDriven<Department>{

	Department department = new Department();
	private Integer currPage=1;
	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	@Override
	public Department getModel() {
		// TODO Auto-generated method stub
		return department;
	}
	
	//提供查询方法
	public String findAll() {
		PageBean<Department> departmentPageBean = departmentService.findByPage(currPage);
		ActionContext.getContext().getValueStack().push(departmentPageBean);
		return "findAll";
	}
	
	//调整到添加
	public String saveUI(){
		return "saveUI";
	}
	
	//添加部门
	public String saveInfo(){
		departmentService.save(department);
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++) {
			final int index = i;
			fixedThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println("@@@@@@"+index);
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		return "addSuccess";
	}
	
	//编辑部门
	public String editById(){
		department=departmentService.findById(department.getDid());
		return "editSuccess";
	}
	
	public String update() {
		departmentService.update(department);
		return "updateSuccess";
	}
	
	public String delete() {
		department=departmentService.findById(department.getDid());
		departmentService.delete(department);
		return "deleteSuccess";
	}

}
