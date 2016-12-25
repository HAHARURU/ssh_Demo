package cn.muke.ssh.service;

import cn.muke.ssh.domain.Employee;
import cn.muke.ssh.domain.PageBean;

public interface EmployeeService {
	Employee login(Employee employee);

	PageBean<Employee> findByPage(Integer currPage);

	void save(Employee employee);

	Employee findById(Integer eid);

	void update(Employee employee);

	void delete(Employee employee);

}
