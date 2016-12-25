package cn.muke.ssh.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.muke.ssh.dao.DepartmentDao;
import cn.muke.ssh.domain.Department;
import cn.muke.ssh.domain.PageBean;
import cn.muke.ssh.service.DepartmentService;

@Transactional
public class DepartmentServiceImpl implements DepartmentService{
	private DepartmentDao departmentDao;

	
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}


	@Override
	public PageBean<Department> findByPage(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<Department> departmentPageBean= new 	PageBean<Department>();
		departmentPageBean.setCurrPage(currPage);
		int pageSize=3;
		departmentPageBean.setPageSize(pageSize);
		int totalCount =departmentDao.findCount();
		departmentPageBean.setTotalCount(totalCount);
		double tc=totalCount;
		Double numDouble=Math.ceil(tc/pageSize);
		departmentPageBean.setTotalPage(numDouble.intValue());
		int begin=(currPage-1)*pageSize;
		List<Department> list=departmentDao.findByPage(begin,pageSize);
		departmentPageBean.setList(list); 
		return departmentPageBean;
	}


	@Override
	public void save(Department department) {
		// TODO Auto-generated method stub
		departmentDao.save(department);
	}


	@Override
	public Department findById(Integer did) {
		// TODO Auto-generated method stub
		return departmentDao.findById(did);
	}


	@Override
	public void update(Department department) {
		// TODO Auto-generated method stub
		departmentDao.update(department);
	}


	@Override
	public void delete(Department department) {
		// TODO Auto-generated method stub
		departmentDao.delete(department);
	}


	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return departmentDao.findAll();
	}

}
