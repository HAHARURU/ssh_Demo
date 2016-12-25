package cn.muke.ssh.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.muke.ssh.dao.EmployeeDao;
import cn.muke.ssh.domain.Employee;
import cn.muke.ssh.domain.PageBean;
import cn.muke.ssh.service.EmployeeService;

@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	public Employee login(Employee employee) {
		Employee exiteEmployee=employeeDao.findByUsernameAndPassword(employee);
		return exiteEmployee;
	}

	@Override
	public PageBean<Employee> findByPage(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<Employee> pageBean=new PageBean<Employee>();
		pageBean.setCurrPage(currPage);
		int pageSize=3;
		pageBean.setPageSize(pageSize);
		int totalCount=employeeDao.findCount();
		pageBean.setTotalCount(totalCount);
		double tc=totalCount;
		Double numDouble=Math.ceil(tc/pageSize);
		pageBean.setTotalPage(numDouble.intValue());
		int begin=(currPage-1)*pageSize;
		List<Employee> list=employeeDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.save(employee);
	}

	@Override
	public Employee findById(Integer eid) {
		// TODO Auto-generated method stub
		return employeeDao.findById(eid);
	}

	@Override
	public void update(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.update(employee);
	}

	@Override
	public void delete(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.delete(employee);
	}

}
