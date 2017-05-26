package com.itheima.mapper;

import java.util.List;

import com.itheima.bean.Customer;
import com.itheima.bean.QueryVo;
import com.itheima.utils.Page;

public interface CustomerDao {

	List<Customer> getCustomerList(QueryVo queryVo);
	int getCustomerListCount(QueryVo queryVo);
	Customer getCustomerById(String id);
	void updateCustomer(Customer customer);
}