package cn.muke.ssh.dao;

import java.util.List;

import cn.muke.ssh.domain.Department;

public interface DepartmentDao {
	int findCount();
	List<Department> findByPage(int begin,int pageSize);
	void save(Department department);
	Department findById(Integer did);
	void update(Department department);
	void delete(Department department);
	List<Department> findAll();
}
