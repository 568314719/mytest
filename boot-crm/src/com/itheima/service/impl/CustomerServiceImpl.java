package com.itheima.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.itheima.bean.Customer;
import com.itheima.bean.QueryVo;
import com.itheima.mapper.BaseDictDao;
import com.itheima.mapper.CustomerDao;
import com.itheima.service.CustomerService;
import com.itheima.utils.Page;
/**
 * 客户管理
 * @author lx
 *
 */

@Service
public class CustomerServiceImpl implements CustomerService {

	// 定义dao属性
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private BaseDictDao baseDictDao;
	
	@Override
	public Page<Customer> getCustomerList(QueryVo queryVo, int page) {
		// 1、根据页码计算start
		int start = (page - 1) * queryVo.getRows();
		queryVo.setStart(start);
		// 2、调用Dao查询客户列表。
		List<Customer> customerList = customerDao.getCustomerList(queryVo);
		// 3、调用dao查询客户的总记录数。
		int count = customerDao.getCustomerListCount(queryVo);
		// 4、向Page对象中设置分页信息及客户列表。
		Page<Customer> result = new Page<>();
		// 5、返回Page对象
		result.setPage(page);
		result.setRows(customerList);
		result.setSize(queryVo.getRows());
		result.setTotal(count);
		return result;
	}

	@Override
	public Customer getCustomerById(String id) {
		Customer customer = customerDao.getCustomerById(id);
		return customer;
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerDao.updateCustomer(customer);
	}

	


}
