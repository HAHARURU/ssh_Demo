package cn.muke.ssh.service;

import java.util.List;

import cn.muke.ssh.domain.Department;
import cn.muke.ssh.domain.PageBean;

public interface DepartmentService {
	
	PageBean<Department> findByPage(Integer currPage);
	void save(Department department);
	Department findById(Integer did);
	void update(Department department);
	void delete(Department department);
	List<Department> findAll(); 

}
